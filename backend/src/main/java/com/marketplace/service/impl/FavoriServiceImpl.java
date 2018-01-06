package com.marketplace.service.impl;

 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketplace.domain.Favori;
import com.marketplace.repository.FavoriRepository;
import com.marketplace.service.FavoriService;

@Service
public class FavoriServiceImpl implements FavoriService {
	
	@Autowired
	private FavoriRepository favoriRepository;
	
	@Override
	public void SaveFavori(Favori favori) {
		
		favoriRepository.save(favori);
	}
	
 
	 

	@Override
	public List<Favori> ListeFavori(int idPers) {
		return favoriRepository.selectFavoriByID(idPers);
	}

	@Override
	public void supprime(int id) {
		favoriRepository.delete(id);
		
	}
	
}
