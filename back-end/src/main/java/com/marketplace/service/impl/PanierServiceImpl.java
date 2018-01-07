package com.marketplace.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketplace.domain.Panier;
import com.marketplace.repository.PanierRepository;
import com.marketplace.service.PanierService;
@Service
public class PanierServiceImpl implements PanierService {
	@Autowired
	private PanierRepository panierRepository;
	
	@Override
	public void savePanier(Panier panier) {
		panierRepository.save(panier);
		
	}

	@Override
	public List<Panier> getPanierByIDPersonne(int idPer) {
		
		return panierRepository.selectPanierByID(idPer);
	}

	@Override
	public void supprimerPanierByID(int idPannier) {
		panierRepository.delete(idPannier);
		
	}

	@Override
	public void deleteAll(int idPer) {
		panierRepository.deletPanierByIDPersonne(idPer);
		
		
	}

}
