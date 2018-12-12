package com.samuordieres.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.samuordieres.dao.ClienteDAO;
import com.samuordieres.model.Cliente;

@Service("clienteService")
@Transactional
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
    private ClienteDAO dao;

	@Override
	public void deleteClienteByDni(String dni) {
		// TODO Auto-generated method stub
		dao.deleteClienteByDni(dni);
	}

	@Override
	public Cliente findById(int id) {		
		return dao.findById(id);
	}

	@Override
	public void saveCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		 dao.saveCliente(cliente);
	}

	@Override
	public void updateCliente(Cliente cliente) {
		Cliente entity = dao.findById(cliente.getId());
        if(entity!=null){
            entity.setNombre(cliente.getNombre());
            entity.setPrimerApellido(cliente.getPrimerApellido());
            entity.setSegundoApellido(cliente.getSegundoApellido());
            entity.setDni(cliente.getDni());
            entity.setEmail(cliente.getEmail());
            entity.setReservas(cliente.getReservas());
    		dao.mergeCliente(cliente);
        }
		
	}

	@Override
	public List<Cliente> findAllClientes() {
		return dao.findAllClientes();
	}

	@Override
	public Cliente findClienteByDni(String dni) {
		return dao.findClienteByDni(dni);
	}
	
	public boolean isClienteDniUnique(Integer id, String dni) {
        Cliente cliente = findClienteByDni(dni);
        return ( cliente == null || ((id != null) && (cliente.getId() == id)));
    }

}
