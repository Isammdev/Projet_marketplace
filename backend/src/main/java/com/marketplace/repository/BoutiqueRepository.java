package com.marketplace.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.marketplace.domain.Boutique;

public interface BoutiqueRepository extends JpaRepository<Boutique, Serializable> {
	 
	
}
