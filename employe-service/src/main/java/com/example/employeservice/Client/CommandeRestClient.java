package com.example.employeservice.Client;


import com.example.employeservice.Modele.Commande;
import com.example.employeservice.Modele.StatusType;
import jakarta.ws.rs.core.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "COMMENDE-SERVICE")
public interface CommandeRestClient {

    @GetMapping("/api/commandes")
    List<Commande> getAllCommandes();

    @GetMapping("/api/commandes/statut/{statut}")
    List<Commande> getCommandesByStatut(@PathVariable("statut") String statut);

    @GetMapping("/api/commandes/{id}")
    Commande getCommandeById(@PathVariable("id") Long id);
    @PutMapping("/api/commandes/{id}")
    Commande updateCommande(@PathVariable("id") Long id, @RequestBody Commande commande);
}
