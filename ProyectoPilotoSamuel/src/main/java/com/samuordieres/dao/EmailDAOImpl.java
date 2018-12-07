package com.samuordieres.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.samuordieres.model.Email;

@Repository("emailDao")
public class EmailDAOImpl extends AbstractDAO<Integer, Email> implements EmailDAO{

	@Override
	public Email findById(int id) {
		return getByKey(id);
	}

	@Override
	public void saveEmail(Email email) {
		persist(email);
		
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Email> findAllEmails() {
		Criteria criteria = createEntityCriteria();
        return (List<Email>) criteria.list();
	}

	@Override
	public Email findEmailByClienteId(int clienteId) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("clientes_id", clienteId));
        return (Email) criteria.uniqueResult();
	} 
	
	@Override
	public Email findEmailByEmail(String emailStr) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("emails", emailStr));
        return (Email) criteria.uniqueResult();
	} 
	
}
