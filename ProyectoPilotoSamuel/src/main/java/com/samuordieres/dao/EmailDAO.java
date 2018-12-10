package com.samuordieres.dao;

import java.util.List;

import com.samuordieres.model.Email;

public interface EmailDAO {

	Email findById(int id);

	void saveEmail(Email email);

	List<Email> findAllEmails();

	Email findEmailByClienteId(Integer clientes_id);
	
	Email findEmailByEmail(String emailStr);
}
