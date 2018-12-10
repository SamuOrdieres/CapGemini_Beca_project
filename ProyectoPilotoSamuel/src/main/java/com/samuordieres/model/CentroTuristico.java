package com.samuordieres.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="centros_turisticos")
public class CentroTuristico {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
 
    @Size(min=3, max=50)
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @NotNull
    @Column(name = "habitaciones", nullable = false)
    private int habitaciones;
    
    @OneToMany(mappedBy = "centroTuristico", cascade = CascadeType.ALL)
    private List<Reservas> reservas;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(Integer habitaciones) {
		this.habitaciones = habitaciones;
	}

	public List<Reservas> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reservas> reservas) {
		this.reservas = reservas;
	}

	public void setHabitaciones(int habitaciones) {
		this.habitaciones = habitaciones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + habitaciones;
		result = prime * result + id;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((reservas == null) ? 0 : reservas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CentroTuristico other = (CentroTuristico) obj;
		if (habitaciones != other.habitaciones)
			return false;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (reservas == null) {
			if (other.reservas != null)
				return false;
		} else if (!reservas.equals(other.reservas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CentroTuristico [id=" + id + ", nombre=" + nombre + ", habitaciones=" + habitaciones + ", reservas="
				+ reservas + "]";
	}

	

}
