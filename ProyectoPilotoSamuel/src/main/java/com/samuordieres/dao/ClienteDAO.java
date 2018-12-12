package com.samuordieres.dao;

import java.util.List;

import com.samuordieres.model.Cliente;

public interface ClienteDAO {

	Cliente findById(int id);
	 
    void saveCliente(Cliente cliente);
     
    void deleteClienteByDni(String dni);
     
    List<Cliente> findAllClientes();
 
    Cliente findClienteByDni(String dni);
    
	void mergeCliente(Cliente cliente);
}

