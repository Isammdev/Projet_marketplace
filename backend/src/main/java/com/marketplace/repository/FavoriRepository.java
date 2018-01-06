package com.marketplace.repository;

 
import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.marketplace.domain.Favori;

public interface FavoriRepository  extends CrudRepository<Favori, Serializable> 
{
	@Query("SELECT f FROM Favori f  where f.acheteur.personneID=?")
	List<Favori> selectFavoriByID(int idPer); 
}