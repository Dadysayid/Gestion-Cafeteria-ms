package com.example.commendeservice.Modele;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Client {
    private Long id;
    private String nom;
    private String email;
    private String telephone;
}

