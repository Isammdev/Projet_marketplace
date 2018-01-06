package com.marketplace.service;

 
import java.util.List;

import com.marketplace.domain.Favori;

public interface FavoriService {
	
  public void SaveFavori(Favori favori);
public List<Favori>ListeFavori(int idPers);
public void supprime(int id);
	
}
