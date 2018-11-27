package com.samuordieres.dao;

import java.util.List;

import com.samuordieres.model.Empleado;

public interface EmpleadoDAO {

	Empleado findById(int id);
	 
    void saveEmpleado(Empleado empleado);
     
    void deleteEmpleadoByDni(String dni);
     
    List<Empleado> findAllEmpleados();
 
    Empleado findEmpleadoByDni(String dni);
}

