package com.samuordieres.service;

import java.util.List;

import com.samuordieres.model.Email;

public interface EmailService {

	Email findById(int id);
    
    void saveEmail(Email email);
     
    void updateEmail(Email email);
     
    List<Email> findAllEmails(); 
     
    Email findEmailByClienteId(int clienteId);
 
//    boolean isEmailUnique(Integer id, String emailStr);

}
