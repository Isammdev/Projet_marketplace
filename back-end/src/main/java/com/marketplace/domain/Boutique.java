package com.marketplace.domain;

 
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Boutique  implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public Boutique() {
		// TODO Auto-generated constructor stub
	
	}
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int boutiqueID;
private String boutiqueNom;
private String boutiqueAdresse;



public int getBoutiqueID() {
	return boutiqueID;
}
public void setBoutiqueID(int boutiqueID) {
	this.boutiqueID = boutiqueID;
}
public String getBoutiqueNom() {
	return boutiqueNom;
}
public void setBoutiqueNom(String boutiqueNom) {
	this.boutiqueNom = boutiqueNom;
}
public String getBoutiqueAdresse() {
	return boutiqueAdresse;
}
public void setBoutiqueAdresse(String boutiqueAdresse) {
	this.boutiqueAdresse = boutiqueAdresse;
}




}
