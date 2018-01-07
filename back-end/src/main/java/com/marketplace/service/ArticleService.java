package com.marketplace.service;

 
import java.util.List;

import com.marketplace.domain.Article;

public interface ArticleService {
 List<Article> getArticle();
 Article save(Article article);
Article findById(int id);
Article getArticleByID(int idArticle);
List<Article> getArticleByCategorie(String categorie);
}
