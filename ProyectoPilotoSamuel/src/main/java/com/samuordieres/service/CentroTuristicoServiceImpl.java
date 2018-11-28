package com.samuordieres.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.samuordieres.dao.CentroTuristicoDAO;
import com.samuordieres.model.CentroTuristico;

@Service("centroTuristicoService")
@Transactional
public class CentroTuristicoServiceImpl implements CentroTuristicoService {

	private CentroTuristicoDAO dao;

	@Override
	public CentroTuristico findById(int id) {
		return dao.findById(id);
	}

	@Override
	public void saveCentroTuristico(CentroTuristico centroTuristico) {
		dao.saveCentroTuristico(centroTuristico);
	}

	@Override
	public void updateCentroTuristico(CentroTuristico centroTuristico) {
		CentroTuristico entity = dao.findById(centroTuristico.getId());
		if (entity != null) {
			entity.setNombre(centroTuristico.getNombre());
			entity.setHabitaciones(centroTuristico.getHabitaciones());
		}
	}

	@Override
	public void deleteCentroTuristicoByNombre(String nombre) {
		dao.deleteCentroTuristicoByNombre(nombre);
	}

	@Override
	public List<CentroTuristico> findAllCentrosTuristicos() {
		return dao.findAllCentrosTuristicos();
	}

	@Override
	public CentroTuristico findCentroTuristicoByNombre(String nombre) {
		return dao.findCentroTuristicoByNombre(nombre);
	}

	@Override
	public boolean isCentroTuristicoNombreUnique(Integer id, String nombre) {
		CentroTuristico centroTuristico = findCentroTuristicoByNombre(nombre);
        return ( centroTuristico == null || ((id != null) && (centroTuristico.getId() == id)));
        
	}

}
