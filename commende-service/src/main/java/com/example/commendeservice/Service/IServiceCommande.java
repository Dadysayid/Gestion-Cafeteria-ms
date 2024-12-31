package com.example.commendeservice.Service;

import com.example.commendeservice.Entity.Commande;
import com.example.commendeservice.Entity.StatusType;

import java.util.List;

public interface IServiceCommande {
    List<Commande> getAllCommandes();

    Commande getCommandeById(Long id);

    Commande saveCommande(Commande commande);

    void deleteCommande(Long id);

    List<Commande> getCommandesByStatut(StatusType statut);

    List<Commande> getByStatut(String statut);
}