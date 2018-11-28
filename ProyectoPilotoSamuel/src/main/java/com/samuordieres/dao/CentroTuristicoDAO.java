package com.samuordieres.dao;

import java.util.List;

import com.samuordieres.model.CentroTuristico;

public interface CentroTuristicoDAO {
	
	CentroTuristico findById(int id);
	 
    void saveCentroTuristico(CentroTuristico centroTuristico);
     
    void deleteCentroTuristicoByNombre(String nombre);
     
    List<CentroTuristico> findAllCentrosTuristicos();
 
    CentroTuristico findCentroTuristicoByNombre(String nombre);

}
