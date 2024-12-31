package com.example.employeservice.Service;


import com.example.employeservice.Client.CommandeRestClient;
import com.example.employeservice.Entity.Employe;
import com.example.employeservice.Modele.Commande;
import com.example.employeservice.Modele.StatusType;
import com.example.employeservice.Repository.EmployeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceEmploye implements IServiceEmploye {

    private final EmployeRepository employeRepository;
    private final CommandeRestClient commandeRestClient;


    @Override
    public List<Commande> getAllComandesStatus(StatusType status) {
        try {
            return commandeRestClient.getCommandesByStatut(status.name()); // Utilisation correcte de statut
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des commandes par statut : " + e.getMessage());
        }
    }

    @Override
    public List<Commande> getAllCommandes() {
        try {
            return commandeRestClient.getAllCommandes(); // Appel du Feign Client.
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des commandes : " + e.getMessage());
        }
    }

    @Override
    public Commande updateCommandeStatus(Long id, StatusType status) {
        try {
            Commande commande = commandeRestClient.getCommandeById(id); // Récupérer la commande existante.
            if (commande == null) {
                throw new RuntimeException("Commande non trouvée !");
            }
            commande.setStatut(status); // Mettre à jour le statut.
            return commandeRestClient.updateCommande(id, commande); // Appel pour sauvegarder la modification.
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la mise à jour du statut : " + e.getMessage());
        }
    }



    @Override
    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }

    @Override
    public Employe getEmployeById(Long id) {
        return employeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employé non trouvé"));
    }

    @Override
    public Employe saveEmploye(Employe employe) {
        return employeRepository.save(employe);
    }

    @Override
    public void deleteEmploye(Long id) {
        employeRepository.deleteById(id);
    }
};
