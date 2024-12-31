package com.example.employeservice.Controller;

import com.example.employeservice.Entity.Employe;
import com.example.employeservice.Modele.Commande;
import com.example.employeservice.Modele.StatusType;
import com.example.employeservice.Service.IServiceEmploye;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
@RequiredArgsConstructor
public class EmployeController {

    private final IServiceEmploye employeService;

    @GetMapping
    public ResponseEntity<List<Employe>> getAllEmployes() {
        return ResponseEntity.ok(employeService.getAllEmployes());
    }

    @GetMapping("/commandes")
    public ResponseEntity<List<Commande>> getAllCommandes() {
        try {
            return ResponseEntity.ok(employeService.getAllCommandes());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/commandes/statut/{statusType}")
    public ResponseEntity<List<Commande>> getAllCommandeStatus(@PathVariable String statusType) {
        try {
            StatusType status = StatusType.valueOf(statusType.toUpperCase());
            return ResponseEntity.ok(employeService.getAllComandesStatus(status));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/commandes/{id}/statut/{status}")
    public ResponseEntity<Commande> updateCommandeStatus(@PathVariable Long id, @PathVariable String status) {
        try {
            StatusType statut = StatusType.valueOf(status.toUpperCase());
            return ResponseEntity.ok(employeService.updateCommandeStatus(id, statut));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employe> getEmployeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeService.getEmployeById(id));
    }

    @PostMapping
    public ResponseEntity<Employe> createEmploye(@RequestBody Employe employe) {
        return ResponseEntity.ok(employeService.saveEmploye(employe));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmploye(@PathVariable Long id) {
        employeService.deleteEmploye(id);
        return ResponseEntity.noContent().build();
    }
}

