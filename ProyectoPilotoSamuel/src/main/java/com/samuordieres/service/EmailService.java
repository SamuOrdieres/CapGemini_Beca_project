package com.samuordieres.service;

import java.util.List;

import com.samuordieres.model.Cliente;
import com.samuordieres.model.Email;

public interface EmailService {

	Email findById(int id);
    
    void saveEmail(Email email);
     
    void updateEmail(Cliente cliente);
    
//    void updateEmailByCliente(Cliente cliente);
    
    List<Email> findAllEmails(); 
     
    Email findEmailByClienteId(int clienteId);
    
    Email findEmailByEmail(String emailStr);
 
    boolean isEmailUnique(Integer id, String emailStr);

}
