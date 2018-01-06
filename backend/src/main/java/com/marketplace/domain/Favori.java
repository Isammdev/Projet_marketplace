package com.marketplace.domain;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Favori implements Serializable {
@Id @GeneratedValue(strategy = GenerationType.AUTO)	
int id;

@ManyToOne
@JoinColumn(name ="personneID")
@JsonIgnoreProperties(value = "vendeur")

private Personne acheteur;

@ManyToOne
@JoinColumn(name = "articleID")
@JsonIgnoreProperties(value = "vendeur")
private Article article;

public Favori(Personne acheteur, Article article) {
	super();
	this.acheteur = acheteur;
	this.article = article;
}

public Favori() {
	super();
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public Personne getAcheteur() {
	return acheteur;
}

public void setAcheteur(Personne acheteur) {
	this.acheteur = acheteur;
}

public Article getArticle() {
	return article;
}

public void setArticle(Article article) {
	this.article = article;
}



}
