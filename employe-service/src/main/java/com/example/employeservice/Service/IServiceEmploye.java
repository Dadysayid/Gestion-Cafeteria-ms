package com.example.employeservice.Service;

import com.example.employeservice.Entity.Employe;
import com.example.employeservice.Modele.Commande;
import com.example.employeservice.Modele.StatusType;

import java.util.List;

public interface IServiceEmploye {
    List<Employe> getAllEmployes();
    Employe getEmployeById(Long id);
    List<Commande> getAllComandesStatus(StatusType status);
    List<Commande> getAllCommandes();
    Commande updateCommandeStatus(Long id, StatusType status);
    Employe saveEmploye(Employe employe);
    void deleteEmploye(Long id);
}