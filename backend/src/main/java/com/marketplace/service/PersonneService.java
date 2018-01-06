package com.marketplace.service;

 
import com.marketplace.domain.Personne;

 
public interface PersonneService {
	
 
	Personne findByPersonneMail(String username);
	
 	
	Personne save(Personne personne);
	
	Personne findById(int id);


	void updatePersonne(int idPersonne, String PersonneNom, String PersonnePrenom, String PersonneAdresse,
			String PersonneMobile);


	 
	
	
	
}
