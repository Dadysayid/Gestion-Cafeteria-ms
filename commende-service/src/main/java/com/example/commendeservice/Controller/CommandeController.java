package com.example.commendeservice.Controller;

import com.example.commendeservice.Entity.Commande;
import com.example.commendeservice.Entity.StatusType;
import com.example.commendeservice.Service.IServiceCommande;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
@RequiredArgsConstructor
public class CommandeController {

    private final IServiceCommande commandeService;

    @GetMapping
    public ResponseEntity<List<Commande>> getAllCommandes() {
        return ResponseEntity.ok(commandeService.getAllCommandes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable Long id) {
        return ResponseEntity.ok(commandeService.getCommandeById(id));
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<Commande>> getCommandesByStatut(@PathVariable StatusType statut) {
        return ResponseEntity.ok(commandeService.getCommandesByStatut(statut));
    }

    @PostMapping
    public ResponseEntity<Commande> createCommande(@RequestBody Commande commande) {
        return ResponseEntity.ok(commandeService.saveCommande(commande));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
        commandeService.deleteCommande(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Commande> updateCommande(@PathVariable Long id, @RequestBody Commande updatedCommande) {
        Commande commande = commandeService.getCommandeById(id);
        if (commande == null) {
            return ResponseEntity.notFound().build();
        }
        commande.setStatut(updatedCommande.getStatut()); // Mise à jour du statut.
        return ResponseEntity.ok(commandeService.saveCommande(commande));
    }

}
