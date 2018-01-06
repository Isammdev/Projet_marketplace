package com.marketplace.resource;

 
import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.domain.Article;
import com.marketplace.domain.Commentaire_Article;
import com.marketplace.domain.Favori;
import com.marketplace.domain.Panier;
import com.marketplace.domain.Personne;
import com.marketplace.service.ArticleService;
import com.marketplace.service.CommentaireService;
import com.marketplace.service.FavoriService;
import com.marketplace.service.PanierService;
import com.marketplace.service.PersonneService;



@RestController
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	private PersonneService userService;	
	
@Autowired
private ArticleService articleService;

@Autowired
private FavoriService favoriService;


@Autowired
private CommentaireService commentaireService;

@Autowired
private PanierService panierService;

@RequestMapping(value = "/allArticle", method = RequestMethod.GET)
public List<Article> getAllArticle() {
return articleService.getArticle(); 
	
}

@RequestMapping(value = "/getArticleID/{idArticle}", method = RequestMethod.GET)
public Article getAllArticle(@PathVariable("idArticle") int idArticle) {
return articleService.getArticleByID(idArticle); 
	
}

@RequestMapping(value = "/favoriArticle/{idArticle}", method = RequestMethod.GET)
public void AjoutFavori(@PathVariable("idArticle") int idArticle,Principal principal) {

	if (null != principal) {
		
		Personne p = userService.findByPersonneMail(principal.getName());
		
		Article a=articleService.getArticleByID(idArticle);
		 
		Favori f=new Favori(p, a);
		
		favoriService.SaveFavori(f);
		
	}
	
	
}

@RequestMapping(value = "/getFavori", method = RequestMethod.GET)
public List<Favori> getFavoriByID(Principal principal) {
	Personne p=new Personne();
	int id=0;
if (null != principal) {
		
		 p = userService.findByPersonneMail(principal.getName());
		 id=p.getPersonneID();

}
return favoriService.ListeFavori(id); 
}


@RequestMapping(value = "/SupprimerFavori/{id}", method = RequestMethod.GET)
public void supprimeFavori(@PathVariable("id") int id) {
favoriService.supprime(id);
	
}

@RequestMapping(value = "/getArticleFiltre/{categorie}", method = RequestMethod.GET)
public List<Article> getAllArticleID(@PathVariable("categorie") String categorie) {
return articleService.getArticleByCategorie(categorie); 
	
}

@RequestMapping(value = "/panierArticle/{idArticle}/{qte}", method = RequestMethod.GET)
public void AjoutPanier(@PathVariable("idArticle") int idArticle,@PathVariable("qte") int qte,Principal principal) {

	if (null != principal) {
		
		Personne p = userService.findByPersonneMail(principal.getName());
		
		Article a=articleService.getArticleByID(idArticle);
		 a.setArticleNumberDispo(a.getArticleNumberDispo()-qte);
		 articleService.save(a);
		 Date d=new Date();
		Panier panier=new Panier(p,a,d,qte);
		
		panierService.savePanier(panier);
		
	}
	
	
}
@RequestMapping(value = "/getPanier", method = RequestMethod.GET)
public List<Panier> getPanierByID(Principal principal) {
	Personne p=new Personne();
	int id=0;
if (null != principal) {
		
		 p = userService.findByPersonneMail(principal.getName());
		 id=p.getPersonneID();

}
return panierService.getPanierByIDPersonne(id);
}


@RequestMapping(value = "/SupprimerPanier/{id}", method = RequestMethod.GET)
public void supprimePanier(@PathVariable("id") int id) {
panierService.supprimerPanierByID(id);
	
}

@RequestMapping(value = "/ajoutCommentaire/{idArticle}/{commentaire}", method = RequestMethod.GET)
public void AjoutCommentaire(@PathVariable("idArticle") int idArticle,@PathVariable("commentaire") String commentaire,Principal principal) {

	if (null != principal) {
		
		Personne p = userService.findByPersonneMail(principal.getName());
		
		Article a=articleService.getArticleByID(idArticle);
		
		Commentaire_Article com=new Commentaire_Article(p, a, commentaire);
		 
		commentaireService.saveCommentaire(com);
		
	}
		
	}


}
