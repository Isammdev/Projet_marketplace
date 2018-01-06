package com.marketplace.domain;

 
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Commentaire_Article implements Serializable {
@Id @GeneratedValue(strategy = GenerationType.AUTO)	
int id;

@ManyToOne
@JoinColumn(name ="acheteurID")
private Personne acheteur;


@ManyToOne
@JoinColumn(name = "articleID")
private Article article;

private String commentaire;

public Commentaire_Article(Personne acheteur, Article article, String commentaire) {
	super();
	this.acheteur = acheteur;
	this.article = article;
	this.commentaire = commentaire;
}

public Commentaire_Article() {
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

public String getCommentaire() {
	return commentaire;
}

public void setCommentaire(String commentaire) {
	this.commentaire = commentaire;
}





}
