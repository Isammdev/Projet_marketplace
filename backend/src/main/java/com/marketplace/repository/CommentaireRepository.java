package com.marketplace.repository;

 
import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.marketplace.domain.Commentaire_Article;

public interface CommentaireRepository extends CrudRepository<Commentaire_Article, Serializable> {

}
