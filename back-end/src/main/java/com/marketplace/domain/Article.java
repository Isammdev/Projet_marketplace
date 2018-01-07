package com.marketplace.domain;

 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


@Entity
public class Article implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int articleID;
private String articleNom;
private String articleCategorie;
private String articleDescription;

private double articlePrix;
private   int  ArticleNumberDispo ;

@Lob
@Column(name="photoArticle", length=100000)
private byte[] photoArticle;


@ManyToOne
@JoinColumn(name = "personneID")
 private Personne vendeur;

/**************************CommentaireArticle**********************************/
@OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
@JsonIgnoreProperties(value = "article")
private  List<Commentaire_Article> commentaireArticles = new ArrayList<Commentaire_Article>();


/**************************PanierArticle**********************************/
@OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
@JsonIgnoreProperties(value = "article")
private  List<Panier> panierArticles = new ArrayList<Panier>();



/**************************FavoriArticle**********************************/
@OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
@JsonIgnoreProperties(value = "article")
private  List<Favori> favoriArticles = new ArrayList<Favori>();



public String getArticleNom() {
	return articleNom;
}



public void setArticleNom(String articleNom) {
	this.articleNom = articleNom;
}

public int getArticleNumberDispo() {
	return ArticleNumberDispo;
}



public void setArticleNumberDispo(int articleNumberDispo) {
	ArticleNumberDispo = articleNumberDispo;
}



public int getArticleID() {
	return articleID;
}



public void setArticleID(int articleID) {
	this.articleID = articleID;
}



public void setArticlePrix(double articlePrix) {
	this.articlePrix = articlePrix;
}



public String getArticleCategorie() {
	return articleCategorie;
}



public void setArticleCategorie(String articleCategorie) {
	this.articleCategorie = articleCategorie;
}



public double getArticlePrix() {
	return articlePrix;
}






public String getArticleDescription() {
	return articleDescription;
}



public void setArticleDescription(String articleDescription) {
	this.articleDescription = articleDescription;
}



public byte[] getPhotoArticle() {
	return photoArticle;
}



public void setPhotoArticle(byte[] photoArticle) {
	this.photoArticle = photoArticle;
}



public Personne getVendeur() {
	return vendeur;
}



public void setVendeur(Personne vendeur) {
	this.vendeur = vendeur;
}







}
