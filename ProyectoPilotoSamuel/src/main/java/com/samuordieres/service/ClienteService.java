package com.samuordieres.service;

import java.util.List;

import com.samuordieres.model.Cliente;

public interface ClienteService {
	
	Cliente findById(int id);
    
    void saveCliente(Cliente cliente);
     
    void updateCliente(Cliente cliente);
     
    void deleteClienteByDni(String dni);
 
    List<Cliente> findAllClientes(); 
     
    Cliente findClienteByDni(String dni);
 
    boolean isClienteDniUnique(Integer id, String dni);

}

