package com.example.employeservice.Modele;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Commande {
    private Long id;
    private StatusType statut;
    private Double total;
}