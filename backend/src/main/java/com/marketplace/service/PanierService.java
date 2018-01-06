package com.marketplace.service;

 
import java.util.List;

import com.marketplace.domain.Panier;

public interface PanierService {
public void savePanier(Panier panier);
public List<Panier> getPanierByIDPersonne(int idPer);
public void supprimerPanierByID(int idPannier);
public void deleteAll(int idPer);
}
