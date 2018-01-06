package com.marketplace.repository;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.marketplace.domain.*;;

 

public interface PersonneRepository extends CrudRepository<Personne, Serializable> {

	Personne findByPersonneMail(String email);
	@Modifying(clearAutomatically = true)
	@Query("update Personne personne set personne.personneNom =:personneNom , personne.personnePrenom =:personnePrenom ,personne.personneAdresse =:personneAdresse, personne.personneMobile=:personneMobile where personne.personneID=:idpersonne")
	@Transactional
	void Updatepersonne(@Param("idpersonne") int idpersonne, @Param("personneNom") String personneNom ,@Param("personnePrenom") String personnePrenom,@Param("personneAdresse") String personneAdresse,@Param("personneMobile") String personneMobile);
	
	
	
}
