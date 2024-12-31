package com.example.commendeservice.Client;

import com.example.commendeservice.Modele.Produit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUIT-SERVICE")
public interface ProduitRestClient {
    @GetMapping("/api/produits/{id}")
    Produit getProduitById(@PathVariable Long id);
}
