package com.example.demo.Service;

import com.example.demo.Entity.Produit;

import java.util.List;

public interface IServiceProduit {
    List<Produit> getAllProduits();
    Produit getProduitById(Long id);
    Produit saveProduit(Produit produit);
    void deleteProduit(Long id);
}