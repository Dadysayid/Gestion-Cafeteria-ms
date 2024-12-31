package com.example.clientservice.Service;

import com.example.clientservice.Entity.Client;

import java.util.List;

public interface IServiceClient {
    List<Client> getAllClients();
    Client getClientById(Long id);
    Client saveClient(Client client);
    void deleteClient(Long id);
}
