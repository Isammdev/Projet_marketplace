package com.marketplace.repository;

 
import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.marketplace.domain.Panier;

public interface PanierRepository extends CrudRepository<Panier, Serializable>{
	@Query("SELECT p FROM Panier p  where p.acheteur.personneID=?")
	List<Panier> selectPanierByID(int idPer);
	
	@Modifying
	@Transactional
	@Query("delete  FROM Panier p  where p.acheteur.personneID=?")
	void deletPanierByIDPersonne(int idPer);
	
}
