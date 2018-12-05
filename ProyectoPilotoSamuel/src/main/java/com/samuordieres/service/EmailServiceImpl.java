package com.samuordieres.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.samuordieres.dao.EmailDAO;
import com.samuordieres.model.Email;

@Service("emailService")
@Transactional
public class EmailServiceImpl implements EmailService {

	@Autowired
	private EmailDAO dao;

	@Override
	public Email findById(int id) {
		return dao.findById(id);
	}

	@Override
	public void saveEmail(Email email) {
		dao.saveEmail(email);
	}

	@Override
	public void updateEmail(Email email) {
		Email entity = dao.findById(email.getId());
		if (entity != null) {
			entity.setEmail(email.getEmail());
		}
	}

//	@Override
//	public void deleteCentroTuristicoByNombre(String nombre) {
//		dao.deleteCentroTuristicoByNombre(nombre);
//	}

	@Override
	public List<Email> findAllEmails() {
		return dao.findAllEmails();
	}

	@Override
	public Email findEmailByClienteId(int clienteId) {
		return dao.findEmailByClienteId(clienteId);
	}

//	@Override
//	public boolean isEmailUnique(Integer id, String emailStr) {
//		Email email = findEmailByEmail(emailStr);
//        return ( email == null || ((id != null) && (email.getId() == id)));
//        
//	}

}
