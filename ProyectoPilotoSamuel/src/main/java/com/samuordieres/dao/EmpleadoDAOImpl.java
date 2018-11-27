package com.samuordieres.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.samuordieres.model.Empleado;

@Repository("empleadoDao")
public class EmpleadoDAOImpl extends AbstractDAO<Integer, Empleado> implements EmpleadoDAO {

	@Override
	public void deleteEmpleadoByDni(String dni) {
		Query query = getSession().createSQLQuery("delete from empleados where dni = :dni");
        query.setString("dni", dni);
        query.executeUpdate();
	}

	@Override
	public Empleado findById(int id) {
		return getByKey(id);
	}

	@Override
	public void saveEmpleado(Empleado empleado) {
		
		persist(empleado);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Empleado> findAllEmpleados() {
		Criteria criteria = createEntityCriteria();
        return (List<Empleado>) criteria.list();
	}

	@Override
	public Empleado findEmpleadoByDni(String dni) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("dni", dni));
        return (Empleado) criteria.uniqueResult();
	}
	
	

}
