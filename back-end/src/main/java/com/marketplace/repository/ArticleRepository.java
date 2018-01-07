package com.marketplace.repository;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.marketplace.domain.Article;

public interface ArticleRepository extends CrudRepository<Article, Serializable> {
	@Query("SELECT p FROM Article p")
	List<Article> selectArticle();

	@Query("SELECT p FROM Article p WHERE p.articleID=(SELECT max(articleID) FROM Article)")
	Article findLast();

	

	@Modifying(clearAutomatically = true)
	@Query("update Article article set article.articleNom =:articleNom , article.articleCategorie =:articleCategorie ,article.articleDescription =:articleDescription,article.articlePrix =:articlePrix,article.ArticleNumberDispo=:ArticleNumberDispo where article.articleID=:articleID")
	@Transactional
	void UpdateArticle(@Param("articleID") int articleID, @Param("articleNom") String articleNom,
			@Param("articleCategorie") String articleCategorie, @Param("articleDescription") String articleDescription,
			@Param("articlePrix") double articlePrix, @Param("ArticleNumberDispo") int ArticleNumberDispo);

 
	
	@Query("SELECT p FROM Article p where p.articleID=?")
	Article selectArticleByID(int idArticle); 
	
	@Query("SELECT p FROM Article p where p.articleCategorie=?")
	List<Article> selectArticleByCategorie(String categorie); 
	
	
}
