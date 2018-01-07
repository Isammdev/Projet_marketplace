package com.marketplace.service.impl;

 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketplace.domain.Article;
import com.marketplace.domain.Personne;
import com.marketplace.repository.ArticleRepository;
import com.marketplace.service.ArticleService;



@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleRepository articleRepository;

	@Override
	public List<Article> getArticle() {
		
		return articleRepository.selectArticle();
 		
	}
	@Override
	public Article findById(int id) {
		return articleRepository.findOne(id);
	}
	
	@Override
	public Article save(Article article) {
		// TODO Auto-generated method stub
		return articleRepository.save(article);
	}
	
	@Override
	public Article getArticleByID(int idArticle) {
		return articleRepository.selectArticleByID(idArticle);
	}

	@Override
	public List<Article> getArticleByCategorie(String categorie) {
		
		return articleRepository.selectArticleByCategorie(categorie);
	}
}
