package com.example.commendeservice.Modele;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produit {
    private Long id;
    private String nom;
    private Double prix;
    private String description;
}