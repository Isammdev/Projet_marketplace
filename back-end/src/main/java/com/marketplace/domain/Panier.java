package com.marketplace.domain;

 
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Panier implements Serializable {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)	
int id;


@ManyToOne
@JoinColumn(name ="personneID")
private Personne acheteur;

@ManyToOne
@JoinColumn(name = "article")
private Article article; 


private Date dateAchat;
private int nombreArticle;

public Panier(Personne acheteur, Article article, Date dateAchat) {
	super();
	this.acheteur = acheteur;
	this.article = article;
	this.dateAchat = dateAchat;
}


public Panier(Personne acheteur, Article article, Date dateAchat, int nombreArticle) {
	super();
	this.acheteur = acheteur;
	this.article = article;
	this.dateAchat = dateAchat;
	this.nombreArticle = nombreArticle;
}




public int getNombreArticle() {
	return nombreArticle;
}


public void setNombreArticle(int nombreArticle) {
	this.nombreArticle = nombreArticle;
}


public Panier() {
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


public Date getDateAchat() {
	return dateAchat;
}


public void setDateAchat(Date dateAchat) {
	this.dateAchat = dateAchat;
}





}
