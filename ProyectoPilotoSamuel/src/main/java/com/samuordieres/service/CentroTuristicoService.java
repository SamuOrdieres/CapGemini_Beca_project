package com.samuordieres.service;

import java.util.List;

import com.samuordieres.model.CentroTuristico;

public interface CentroTuristicoService {

	CentroTuristico findById(int id);
    
    void saveCentroTuristico(CentroTuristico centroTuristico);
     
    void updateCentroTuristico(CentroTuristico centroTuristico);
     
    void deleteCentroTuristicoById(int id);
 
    List<CentroTuristico> findAllCentrosTuristicos(); 
     
    CentroTuristico findCentroTuristicoByNombre(String nombre);
 
    boolean isCentroTuristicoNombreUnique(Integer id, String nombre);
}
