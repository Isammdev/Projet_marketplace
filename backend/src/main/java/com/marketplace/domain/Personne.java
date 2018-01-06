package com.marketplace.domain;

 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.marketplace.domain.security.Authority;

@Entity
public class Personne implements UserDetails, Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int personneID;
	private String personneNom;
	private String personnePrenom;
	private String personneMail;
	private String personnePass;
	private String personneMobile;
	private float personneSolde;
	private String personneRole;
	private String personneAdresse ;

	public String getPersonneAdresse() {
		return personneAdresse;
	}



	public void setPersonneAdresse(String personneAdresse) {
		this.personneAdresse = personneAdresse;
	}

	@Lob
	@Column(name="AvatarPersonne", length=100000)
	private byte[] AvatarPersonne;
	
	
	
	/**********************************MesArticles******************************************************************/
	@OneToMany(targetEntity=Article.class, mappedBy="vendeur" , fetch = FetchType.LAZY , cascade=CascadeType.ALL)
	@JsonIgnoreProperties(value = "vendeur")
	private  List<Article> MesArticles = new ArrayList<Article>();

	
	 

	@OneToOne
    @JoinColumn(referencedColumnName = "boutiqueID" )
 		private Boutique boutique;
	
	
	/**************************************Message***********************************/
	@OneToMany(mappedBy = "vendeur", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties(value = "vendeur")
	private List<Message>MesMessagesVendeurs=new ArrayList<Message>();
	
	
	
	
	
	
	/**************************MesCommentaires*///////////////////////////////////////
	@OneToMany(mappedBy = "acheteur", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties(value = "acheteur")
	private  List<Commentaire_Article> MesCommentaires= new ArrayList<Commentaire_Article>();
	
	
	
	/****************************************MesAchats*******************************/
	@OneToMany(mappedBy = "acheteur", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties(value = "acheteur")
	private List<Panier>MesPaniers=new ArrayList<Panier>();
	
	
	/****************************************MesFavoris*******************************/
	@OneToMany(mappedBy = "acheteur", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Favori>MesFavoris=new ArrayList<Favori>();
	
	
	
	/**************************************Message***********************************/
	@OneToMany(mappedBy = "acheteur", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties(value = "acheteur")
	private List<Message>MesMessagesAcheteurs=new ArrayList<Message>();



	public int getPersonneID() {
		return personneID;
	}



	public void setPersonneID(int personneID) {
		this.personneID = personneID;
	}



	public String getPersonneNom() {
		return personneNom;
	}



	public void setPersonneNom(String personneNom) {
		this.personneNom = personneNom;
	}



	public String getPersonnePrenom() {
		return personnePrenom;
	}



	public void setPersonnePrenom(String personnePrenom) {
		this.personnePrenom = personnePrenom;
	}



	public String getPersonneMail() {
		return personneMail;
	}



	public void setPersonneMail(String personneMail) {
		this.personneMail = personneMail;
	}



	public String getPersonnePass() {
		return personnePass;
	}



	public void setPersonnePass(String personnePass) {
		this.personnePass = personnePass;
	}



	public String getPersonneMobile() {
		return personneMobile;
	}



	public void setPersonneMobile(String personneMobile) {
		this.personneMobile = personneMobile;
	}



	public float getPersonneSolde() {
		return personneSolde;
	}



	public void setPersonneSolde(float personneSolde) {
		this.personneSolde = personneSolde;
	}



	public String getPersonneRole() {
		return personneRole;
	}



	public void setPersonneRole(String personneRole) {
		this.personneRole = personneRole;
	}



	public byte[] getAvatarPersonne() {
		return AvatarPersonne;
	}



	public void setAvatarPersonne(byte[] avatarPersonne) {
		AvatarPersonne = avatarPersonne;
	}


	@JsonIgnore
	public List<Article> getMesArticles() {
		return MesArticles;
	}



	public void setMesArticles(List<Article> mesArticles) {
		MesArticles = mesArticles;
	}



	public Boutique getBoutique() {
		return boutique;
	}



	public void setBoutique(Boutique boutique) {
		this.boutique = boutique;
	}



	public List<Message> getMesMessagesVendeurs() {
		return MesMessagesVendeurs;
	}



	public void setMesMessagesVendeurs(List<Message> mesMessagesVendeurs) {
		MesMessagesVendeurs = mesMessagesVendeurs;
	}


	@JsonIgnore
	public List<Commentaire_Article> getMesCommentaires() {
		return MesCommentaires;
	}



	public void setMesCommentaires(List<Commentaire_Article> mesCommentaires) {
		MesCommentaires = mesCommentaires;
	}


	@JsonIgnore
	public List<Panier> getMesPaniers() {
		return MesPaniers;
	}



	public void setMesPaniers(List<Panier> mesPaniers) {
		MesPaniers = mesPaniers;
	}


	@JsonIgnore
	public List<Favori> getMesFavoris() {
		return MesFavoris;
	}



	public void setMesFavoris(List<Favori> mesFavoris) {
		MesFavoris = mesFavoris;
	}



	public List<Message> getMesMessagesAcheteurs() {
		return MesMessagesAcheteurs;
	}



	public void setMesMessagesAcheteurs(List<Message> mesMessagesAcheteurs) {
		MesMessagesAcheteurs = mesMessagesAcheteurs;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new Authority(this.getPersonneRole()));
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true ;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return personnePass ;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return personneMail;
	}
	
	
	
	
}
