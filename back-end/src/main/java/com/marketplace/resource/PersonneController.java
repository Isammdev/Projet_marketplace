package com.marketplace.resource;

 
import java.security.Principal;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.marketplace.config.SecurityUtility;
import com.marketplace.domain.Boutique;
import com.marketplace.domain.Commande;
import com.marketplace.domain.Personne;
import com.marketplace.repository.BoutiqueRepository;
import com.marketplace.service.PanierService;
import com.marketplace.service.PersonneService;
import com.marketplace.utility.MailConstructor;



@RestController
@RequestMapping("/personne")
public class PersonneController {


	@Autowired
	private PersonneService userService;

	@Autowired
	private MailConstructor mailConstructor;
	
	@Autowired
	private BoutiqueRepository boutiqueService;

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private PanierService panierService;
	
	
	@RequestMapping(value = "/ajoutPersonne", method = RequestMethod.POST)
	public ResponseEntity newUserPost(HttpServletRequest request, @RequestBody HashMap<String, String> mapper)
			throws Exception {
		String username = mapper.get("username");
		String userEmail = mapper.get("email");
		String role = mapper.get("role");
		String boutiqueAdresse = mapper.get("boutiqueAdresse");
		String boutiqueNom = mapper.get("boutiqueNom");

		 

		if (userService.findByPersonneMail(userEmail) != null) {
			return new ResponseEntity("emailExists", HttpStatus.BAD_REQUEST);
		}

		Personne user = new Personne();
 		user.setPersonneMail(userEmail);
 		user.setPersonneNom(username) ;
 		user.setPersonneRole (role) ;
 		String password = SecurityUtility.randomPassword();

		String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
		user.setPersonnePass(encryptedPassword);
		
 		Boutique b = new Boutique( ) ;
 		b.setBoutiqueAdresse(boutiqueAdresse);
 		b.setBoutiqueNom(boutiqueNom);

  		
  		
  		Boutique boutique=boutiqueService.save(b);		
  		boutiqueService.flush();
  		boutique.getBoutiqueID();
  		user.setBoutique(boutique);

		userService.save(user);

	  
		
		System.out.println(password);
		SimpleMailMessage email = mailConstructor.constructNewUserEmail(user, password);
		mailSender.send(email);

		return new ResponseEntity("User Added Successfully!", HttpStatus.OK);

	}
	
	
	
	
	@RequestMapping(value = "/message", method = RequestMethod.POST)
	public ResponseEntity messsage(HttpServletRequest request, @RequestBody HashMap<String, String> mapper,Principal principal)
			throws Exception {
		String object = mapper.get("object");
		String titre = mapper.get("titre");
		String email = mapper.get("email");
		 

		 

		if (userService.findByPersonneMail(principal.getName()) != null) {
			return new ResponseEntity("pas  conncté", HttpStatus.BAD_REQUEST);
		}

	 
		
 		SimpleMailMessage test = mailConstructor.message( titre, object , email,principal.getName());
		mailSender.send(test);

		return new ResponseEntity("Email envoyé !!", HttpStatus.OK);

	}
	
	
	@RequestMapping(value="/updateUserInfo", method=RequestMethod.POST)
	public ResponseEntity profileInfo(
				@RequestBody HashMap<String, Object> mapper
			) throws Exception{
		
		int id = (Integer) mapper.get("id");
		String email = (String) mapper.get("email");
		String username = (String) mapper.get("username");
		String firstName = (String) mapper.get("firstName");
		String lastName = (String) mapper.get("lastName");
		String newPassword = (String) mapper.get("newPassword");
		String currentPassword = (String) mapper.get("currentPassword");
		
		Personne currentUser = userService.findById (Integer.valueOf(id));
		
		if(currentUser == null) {
			throw new Exception ("User not found");
		}
		
		if(userService.findByPersonneMail (email) != null) {
			if(userService.findByPersonneMail(email).getPersonneID() != currentUser.getPersonneID()) {
				return new ResponseEntity("Email not found!", HttpStatus.BAD_REQUEST);
			}
		}
		
		 
 		
		
			BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
			String dbPassword = currentUser.getPassword();
			
			if(null != currentPassword)
			if(passwordEncoder.matches(currentPassword, dbPassword)) {
				if(newPassword != null && !newPassword.isEmpty() && !newPassword.equals("")) {
					currentUser.setPersonnePass(passwordEncoder.encode(newPassword));
				}
				currentUser.setPersonneMail (email);
			} else {
				return new ResponseEntity("Incorrect current password!", HttpStatus.BAD_REQUEST);
			}
		
		
		currentUser.setPersonnePrenom(firstName);
		currentUser.setPersonneNom (lastName);
 		
		
		userService.save(currentUser);
		
		return new ResponseEntity("Update Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
	public ResponseEntity forgetPasswordPost(HttpServletRequest request, @RequestBody HashMap<String, String> mapper)
			throws Exception {

		Personne user = userService.findByPersonneMail (mapper.get("email"));

		if (user == null) {
			return new ResponseEntity("Email not found", HttpStatus.BAD_REQUEST);
		}
		String password = SecurityUtility.randomPassword();

		String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
		user.setPersonnePass (encryptedPassword);
		userService.save(user);

		SimpleMailMessage newEmail = mailConstructor.constructNewUserEmail(user, password);
		mailSender.send(newEmail);

		return new ResponseEntity("Email sent!", HttpStatus.OK);

	}
	
	@RequestMapping("/getCurrentUser")
	public Personne getCurrentUser(Principal principal) {
		Personne user = new Personne();
		if (null != principal) {
			user = userService.findByPersonneMail(principal.getName());
		}

		return user;
	}
	
	@RequestMapping("/getPersonne")
	public Personne getPersonne(Principal principal) {
		Personne p =new Personne();
		if (null != principal) {
			p = userService.findByPersonneMail(principal.getName());
		}

		return p;
	}
	
	@RequestMapping(value = "/UpdatePersonne/{nom}/{prenom}/{mobile}/{adresse}", method = RequestMethod.GET)
	public void updatePersonne(@PathVariable("nom") String nom, @PathVariable("prenom") String prenom,@PathVariable("mobile") String mobile,@PathVariable("adresse") String adresse,Principal principal) {
	
		if (null != principal) {
		 Personne p = userService.findByPersonneMail(principal.getName());
			
			userService.updatePersonne(p.getPersonneID(), nom, prenom, adresse, mobile);
		}
		
		
	}
	
	
	
	@PostMapping("/UploadPhoto/{id}")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,@PathVariable("id") int id) {
		String message = "";
	
		try {
			
				 Personne p = userService.findById(id);
					
					System.out.println(p.getPersonneID());
					p.setAvatarPersonne(file.getBytes());
				
					userService.save(p);
			
			

			message = "You successfully uploaded " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "FAIL to upload " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}
	
	
	
	@RequestMapping("/updatePassword/{pass}")
	public void updatePassword(@PathVariable("pass") String pass,Principal principal) {
		Personne p =new Personne();
		if (null != principal) {
			p = userService.findByPersonneMail(principal.getName());
			
			String encryptedPassword = SecurityUtility.passwordEncoder().encode(pass);
			p.setPersonnePass(encryptedPassword);
			
			userService.save(p);
		}

		
	}
	
	@RequestMapping("/updateBoutique/{Nom}/{Adresse}")
	public void updatePassword(@PathVariable("Nom") String Nom,@PathVariable("Adresse") String Adresse,Principal principal) {
		Personne p =new Personne();
		if (null != principal) {
			p = userService.findByPersonneMail(principal.getName());
			if (p.getBoutique()!= null) {
		 p.getBoutique().setBoutiqueAdresse(Adresse);
		 p.getBoutique().setBoutiqueNom(Nom);

		}
			
		
		else {
			Boutique b = new Boutique( ) ;
	 		b.setBoutiqueAdresse(Adresse);
	 		b.setBoutiqueNom(Nom);

	  		
	  		
	  		Boutique boutique=boutiqueService.save(b);		
	  		boutiqueService.flush();
	  		boutique.getBoutiqueID();
	  		p.setBoutique(boutique);
			
			
		}
		userService.save(p);


		
	}}
	

	@RequestMapping("/getPersonneByID/{id}")
	public Personne getPersonneID(@PathVariable("id") int id) {
		return userService.findById(id);
		
		
	}
	
	@RequestMapping (value="/payer/{sum}", method=RequestMethod.POST)
	public  void  payer(@PathVariable("sum") int sum ,@RequestBody Commande commande ,Principal principal) {
 	 
		Personne user = new Personne();
 
		int id;
		if (null != principal) {
			user = userService.findByPersonneMail(principal.getName());
 			 id=user.getPersonneID();

	 
	
	 
			mailSender.send(mailConstructor.constructOrderConfirmationEmail(user, commande, panierService.getPanierByIDPersonne(id),sum, Locale.FRANCE));
			
		//	panierService.getPanierByIDPersonne(id).deleteALL
			panierService.deleteAll(user.getPersonneID());
			

		 
 	}  
	 
	 
	
	
	
	
}}
