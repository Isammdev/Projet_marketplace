package com.marketplace.domain;

 
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Message implements Serializable{
	@Id
	@ManyToOne
	@JoinColumn(name ="acheteurID")
	private Personne acheteur;

	@Id
	@ManyToOne
	@JoinColumn(name = "vendeurID")
	private Personne vendeur; 
	
	
	private String message;

}
