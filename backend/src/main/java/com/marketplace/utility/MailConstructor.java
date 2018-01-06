package com.marketplace.utility;

import java.util.List;
import java.util.Locale;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.marketplace.domain.Commande;
import com.marketplace.domain.Panier;
import com.marketplace.domain.Personne;


@Component
public class MailConstructor {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	public SimpleMailMessage constructNewUserEmail(Personne user, String password) {
		String message="\nPlease use the following credentials to log in and edit your personal information including your own password."
				+ "\nUsername:"+user.getUsername()+"\nPassword:"+password;
		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getPersonneMail());
		email.setSubject("Le's Bookstore - New User");
		email.setText(message);
		email.setFrom(env.getProperty("support.email"));
		return email;
	}
	
	
	public SimpleMailMessage message(String titre, String object ,String email ,String source) {
		String message="\n le  message  provenant  à  partir de utilsateur  de  site Mr/Mrs"+source;
		
		SimpleMailMessage test = new SimpleMailMessage();
		test.setTo(email);
		test.setSubject("Catalog Isamm");
		test.setText(message);
		test.setFrom(env.getProperty("support.email"));
		return test;
	}
	
	 public MimeMessagePreparator constructOrderConfirmationEmail (Personne user, Commande commande,List<Panier> list, int sum, Locale locale) {
		Context context = new Context();
		context.setVariable("commande", commande);
		context.setVariable("user", user);
		context.setVariable("cartItemList", list);
		context.setVariable("sum", sum);

 		String text = templateEngine.process("orderConfirmationEmailTemplate", context);
		
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setTo(user.getPersonneMail());
				email.setSubject("Order Confirmation - " );
				email.setText(text,true);
				email.setFrom(new InternetAddress("mehdisouid1993@gmail.com"));
			}
		};
		
		return messagePreparator;
	 

}}
