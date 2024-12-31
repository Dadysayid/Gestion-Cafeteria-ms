package com.example.commendeservice.Entity;

import com.example.commendeservice.Modele.Client;
import com.example.commendeservice.Modele.Produit;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dateCommande;

    private StatusType statut;
    private Double total;
    @Transient
    private Client client;
    private Long clientId;

    @Transient
    private List<Produit> produits; // Liste de produits
    @ElementCollection
    private List<Long> produitsIds;


}