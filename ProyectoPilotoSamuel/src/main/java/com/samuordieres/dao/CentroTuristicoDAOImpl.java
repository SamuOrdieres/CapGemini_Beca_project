package com.samuordieres.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.samuordieres.model.CentroTuristico;

@Repository("centroTuristicoDao")
public class CentroTuristicoDAOImpl extends AbstractDAO<Integer, CentroTuristico> implements CentroTuristicoDAO{

	@Override
	public CentroTuristico findById(int id) {
		return getByKey(id);
	}

	@Override
	public void saveCentroTuristico(CentroTuristico centroTuristico) {
		persist(centroTuristico);
		
	}

	@Override
	public void deleteCentroTuristicoById(int id) {
		Query query = getSession().createSQLQuery("delete from centros_turisticos where id = :id");
        query.setInteger("id", id);
        query.executeUpdate();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CentroTuristico> findAllCentrosTuristicos() {
		Criteria criteria = createEntityCriteria();
        return (List<CentroTuristico>) criteria.list();
	}

	@Override
	public CentroTuristico findCentroTuristicoByNombre(String nombre) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("nombre", nombre));
        return (CentroTuristico) criteria.uniqueResult();
        
        
	} 
	
	

}
