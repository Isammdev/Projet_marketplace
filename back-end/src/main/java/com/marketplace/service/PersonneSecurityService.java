package com.marketplace.service;

 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.marketplace.domain.Personne;
import com.marketplace.repository.PersonneRepository;


@Service
public class PersonneSecurityService implements UserDetailsService{
	
	private static final Logger LOG = LoggerFactory.getLogger(PersonneSecurityService.class);
	
	@Autowired 
	private PersonneRepository personneRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Personne personne = personneRepository.findByPersonneMail(email);
		if(null == personne) {
			LOG.warn("Username {} not found", email);
			throw new UsernameNotFoundException("Username "+email+" not found");
		}
		return personne;
	}
}
