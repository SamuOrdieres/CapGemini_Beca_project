package com.samuordieres.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.samuordieres.model.Cliente;

@Repository("clienteDAO")
public class ClienteDAOImpl extends AbstractDAO<Integer, Cliente> implements ClienteDAO {

	@Override
	public void deleteClienteByDni(String dni) {
		Query query = getSession().createSQLQuery("delete from clientes where dni = :dni");
        query.setString("dni", dni);
        query.executeUpdate();
	}

	@Override
	public Cliente findById(int id) {
		return getByKey(id);
	}

	@Override
	public void saveCliente(Cliente cliente) {
		
		persist(cliente);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> findAllClientes() {
		Criteria criteria = createEntityCriteria();
        return (List<Cliente>) criteria.list();
	}

	@Override
	public Cliente findClienteByDni(String dni) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("dni", dni));
        return (Cliente) criteria.uniqueResult();
	}
	
	@Override
	public void mergeCliente(Cliente cliente) {
		getSession().merge(cliente);
	}
	

}
