package com.example.commendeservice.Client;

import com.example.commendeservice.Modele.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CLIENT-SERVICE")
public interface ClientRestClient {
    @GetMapping("/api/clients/{id}")
    Client getClientById(@PathVariable Long id);
}
