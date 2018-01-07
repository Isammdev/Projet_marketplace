package com.marketplace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.marketplace.config.SecurityUtility;
import com.marketplace.domain.Personne;
import com.marketplace.service.PersonneService;


 
@SpringBootApplication
public class MarketplaceApplication   implements CommandLineRunner {
	@Autowired
	private PersonneService userService;
	public static void main(String[] args) {
		SpringApplication.run(MarketplaceApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
	
	}
	
}
