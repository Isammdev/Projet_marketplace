package com.marketplace.service.impl;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketplace.domain.Commentaire_Article;
import com.marketplace.repository.CommentaireRepository;
import com.marketplace.service.CommentaireService;

@Service
public class CommentaireServiceImpl implements CommentaireService {
	@Autowired
	private CommentaireRepository commentaireRepository;

	@Override
	public void saveCommentaire(Commentaire_Article com) {
		commentaireRepository.save(com);
		
	}

}
