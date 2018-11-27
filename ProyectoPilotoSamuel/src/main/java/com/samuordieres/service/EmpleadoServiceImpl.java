package com.samuordieres.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.samuordieres.dao.EmpleadoDAO;
import com.samuordieres.model.Empleado;

@Service("empleadoService")
@Transactional
public class EmpleadoServiceImpl implements EmpleadoService {
	
	@Autowired
    private EmpleadoDAO dao;

	@Override
	public void deleteEmpleadoByDni(String dni) {
		// TODO Auto-generated method stub
		dao.deleteEmpleadoByDni(dni);
	}

	@Override
	public Empleado findById(int id) {		
		return dao.findById(id);
	}

	@Override
	public void saveEmpleado(Empleado empleado) {
		// TODO Auto-generated method stub
		 dao.saveEmpleado(empleado);
	}

	@Override
	public void updateEmpleado(Empleado empleado) {
		// TODO Auto-generated method stub
		Empleado entity = dao.findById(empleado.getId());
        if(entity!=null){
            entity.setNombre(empleado.getNombre());
            entity.setFechaAlta(empleado.getFechaAlta());
            entity.setSalario(empleado.getSalario());
            entity.setDni(empleado.getDni());
        }
		
	}

	@Override
	public List<Empleado> findAllEmpleados() {
		return dao.findAllEmpleados();
	}

	@Override
	public Empleado findEmpleadoByDni(String dni) {
		return dao.findEmpleadoByDni(dni);
	}
	
	public boolean isEmpleadoDniUnique(Integer id, String dni) {
        Empleado empleado = findEmpleadoByDni(dni);
        return ( empleado == null || ((id != null) && (empleado.getId() == id)));
    }

}
