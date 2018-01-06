package com.marketplace.resource;

 
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.domain.Article;
import com.marketplace.domain.Personne;
import com.marketplace.repository.ArticleRepository;
import com.marketplace.service.ArticleService;
import com.marketplace.service.PersonneService;
 


@RestController
@RequestMapping("/vendeur")
public class VendeurController {


	@Autowired
	private PersonneService userService;

 

	@Autowired
	private  ArticleRepository articleService;
	
	
	@RequestMapping (value="/addArticle", method=RequestMethod.POST)
	public Article addArticle(@RequestBody Article article ,Principal principal) {
		Personne user = new Personne();
		if (null != principal) {
			user = userService.findByPersonneMail(principal.getName());
			article.setVendeur(user);
			user.getMesArticles().add(article);
			userService.save(user);
			System.out.println("iiiiiiiiiiiiii"+article.getArticleID());
			return articleService.findLast() ;

		}
		return null ;
	 
	}
	@RequestMapping("/getCurrentUser")
	public Personne getCurrentUser(Principal principal) {
		Personne user = new Personne();
		if (null != principal) {
			user = userService.findByPersonneMail(principal.getName());
		}

		return user;
	}
	
	
	@RequestMapping (value="/getMyArticles")
	public List<Article> GetArticle( Principal principal) {
		Personne user = new Personne();
		if (null != principal) {
			user = userService.findByPersonneMail(principal.getName());	
			return user.getMesArticles();

		}
		return null ;
	 
	}
	
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public ResponseEntity remove(
			@RequestBody int id ,Principal principal
			) throws IOException {
		Personne user = new Personne();
		if (null != principal) {
 			System.out.println("iiiiiiiiiiiiii"+articleService.findOne(id).getArticleNom());

 			articleService.delete(articleService.findOne(id));
 			
		 

			 
  		return new ResponseEntity("Remove Success!", HttpStatus.OK);
	}
	
  		return new ResponseEntity("Remove failed!", HttpStatus.BAD_REQUEST);

	}
	
	
	
	 
	
	
	@RequestMapping(value = "/updateArticle/{articleID}/{articleNom}/{articleCategorie}/{articleDescription}/{articlePrix}/{ArticleNumberDispo}", method = RequestMethod.GET, produces={"application/json"})
	public void updateOffre(@PathVariable("articleID") int articleID , @PathVariable("articleNom") String articleNom, @PathVariable("articleCategorie") String articleCategorie,@PathVariable("articleDescription") String articleDescription,@PathVariable("articlePrix") double articlePrix,@PathVariable("ArticleNumberDispo") int ArticleNumberDispo) {
 		articleService.UpdateArticle(articleID, articleNom, articleCategorie, articleDescription, articlePrix, ArticleNumberDispo);
	}


 	 
	 
	
	
}
