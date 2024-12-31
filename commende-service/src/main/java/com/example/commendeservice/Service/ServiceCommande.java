package com.example.commendeservice.Service;

import com.example.commendeservice.Client.ClientRestClient;
import com.example.commendeservice.Client.ProduitRestClient;
import com.example.commendeservice.Entity.Commande;
import com.example.commendeservice.Entity.StatusType;
import com.example.commendeservice.Modele.Client;
import com.example.commendeservice.Modele.Produit;
import com.example.commendeservice.Repository.CommandeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceCommande implements IServiceCommande {
    private final CommandeRepository commandeRepository;
    private final ClientRestClient clientRestClient;
    private final ProduitRestClient produitRestClient;

    @Override
    public List<Commande> getAllCommandes() {
        List<Commande> commandes = commandeRepository.findAll();
        commandes.forEach(commande -> {
            commande.setClient(clientRestClient.getClientById(commande.getClientId()));
            commande.setProduits(commande.getProduitsIds().stream()
                    .map(produitRestClient::getProduitById)
                    .collect(Collectors.toList()));
        });
        return commandes;
    }

    @Override
    public Commande getCommandeById(Long id) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
        commande.setClient(clientRestClient.getClientById(commande.getClientId()));
        commande.setProduits(commande.getProduitsIds().stream()
                .map(produitRestClient::getProduitById)
                .collect(Collectors.toList()));
        return commande;
    }

    @Override
    public Commande saveCommande(Commande commande) {
        // Vérification du client
        Client client = clientRestClient.getClientById(commande.getClientId());
        if (client == null) {
            throw new RuntimeException("Client non trouvé");
        }
        commande.setClient(client);

        // Vérification et récupération des produits
        double total = 0;
        List<Long> produitIds = commande.getProduitsIds();
        if (produitIds == null || produitIds.isEmpty()) {
            throw new RuntimeException("Aucun produit sélectionné");
        }
        List<Produit> produits = new ArrayList<>();
        for (Long produitId : produitIds) {
            Produit produit = produitRestClient.getProduitById(produitId);
            if (produit == null) {
                throw new RuntimeException("Produit avec ID " + produitId + " non trouvé");
            }
            produits.add(produit);
            total += produit.getPrix();
        }
        commande.setProduits(produits);
        commande.setTotal(total);

        // Statut par défaut
        if (commande.getStatut() == null) {
            commande.setStatut(StatusType.REFUSED);
        }

        return commandeRepository.save(commande);
    }

    @Override
    public void deleteCommande(Long id) {
        commandeRepository.deleteById(id);
    }

    @Override
    public List<Commande> getCommandesByStatut(StatusType statut) {
        List<Commande> commandes = commandeRepository.findAll().stream()
                .filter(commande -> commande.getStatut().equals(statut))
                .collect(Collectors.toList());
        commandes.forEach(commande -> {
            commande.setClient(clientRestClient.getClientById(commande.getClientId()));
            commande.setProduits(commande.getProduitsIds().stream()
                    .map(produitRestClient::getProduitById)
                    .collect(Collectors.toList()));
        });
        return commandes;
    }

    @Override
    public List<Commande> getByStatut(String statut) {
        List<Commande> commandes = commandeRepository.findAll().stream()
                .filter(commande -> commande.getStatut().name().equalsIgnoreCase(statut))
                .collect(Collectors.toList());
        commandes.forEach(commande -> {
            commande.setClient(clientRestClient.getClientById(commande.getClientId()));
            commande.setProduits(commande.getProduitsIds().stream()
                    .map(produitRestClient::getProduitById)
                    .collect(Collectors.toList()));
        });
        return commandes;
    }
}
