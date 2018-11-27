package com.samuordieres.service;

import java.util.List;

import com.samuordieres.model.Empleado;

public interface EmpleadoService {
	
	Empleado findById(int id);
    
    void saveEmpleado(Empleado empleado);
     
    void updateEmpleado(Empleado empleado);
     
    void deleteEmpleadoByDni(String dni);
 
    List<Empleado> findAllEmpleados(); 
     
    Empleado findEmpleadoByDni(String dni);
 
    boolean isEmpleadoDniUnique(Integer id, String dni);

}

