package com.marketplace.service.impl;

 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketplace.domain.Personne;
import com.marketplace.repository.PersonneRepository;
import com.marketplace.service.PersonneService;


@Service
public class PersonneServiceImpl implements PersonneService{
	
	private static final Logger LOG = LoggerFactory.getLogger(PersonneService.class);
	
	@Autowired
	private PersonneRepository personneRepository;
	
 	
	@Override
	public void updatePersonne(int idPersonne, String PersonneNom, String PersonnePrenom, 
			String PersonneAdresse, String PersonneMobile) {
		personneRepository.Updatepersonne(idPersonne, PersonneNom, PersonnePrenom, PersonneAdresse, PersonneMobile);
		
	}
	
	
	@Override
	public Personne save(Personne personne)  {
		return personneRepository.save(personne);
	}
	
	@Override
	public Personne findById(int id) {
		return personneRepository.findOne(id);
	}
	
	 
	
	@Override
	public Personne findByPersonneMail(String email) {
		return personneRepository.findByPersonneMail(email);
	}

	
	 
}
