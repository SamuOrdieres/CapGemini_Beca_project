package com.samuordieres.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="clientes")
public class Cliente {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
 
    @Size(min=3, max=50)
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Size(min=3, max=50)
    @Column(name = "primer_apellido", nullable = false)
    private String primerApellido;
    
    @Size(min=3, max=50)
    @Column(name = "segundo_apellido", nullable = false)
    private String segundoApellido;
     
    @NotEmpty
    @Column(name = "dni", unique=true, nullable = false)
    private String dni;

    @OneToOne(fetch = FetchType.EAGER, mappedBy="cliente", cascade = CascadeType.ALL)
    private Email email;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente", cascade = CascadeType.ALL)
//    @JoinTable(name="reservas", joinColumns=@JoinColumn(name="clientes_id"), inverseJoinColumns=@JoinColumn(name="centros_turisticos_id"))
    private List<Reserva> reservas;
 
    public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

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
 
   
    public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	
	public String getDni() {
        return dni;
    }
 
    public void setDni(String dni) {
        this.dni = dni;
    }

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	
	public String getFullName() {
		String fullName = nombre + " " + primerApellido + " " + segundoApellido;
		
		return fullName;
	}
	
	public String getFullNameDni() {
		String fullNameDni = nombre + " " + primerApellido + " " + segundoApellido + " - " + dni;
		
		return fullNameDni;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + id;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((primerApellido == null) ? 0 : primerApellido.hashCode());
		result = prime * result + ((reservas == null) ? 0 : reservas.hashCode());
		result = prime * result + ((segundoApellido == null) ? 0 : segundoApellido.hashCode());
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
		Cliente other = (Cliente) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (primerApellido == null) {
			if (other.primerApellido != null)
				return false;
		} else if (!primerApellido.equals(other.primerApellido))
			return false;
		if (reservas == null) {
			if (other.reservas != null)
				return false;
		} else if (!reservas.equals(other.reservas))
			return false;
		if (segundoApellido == null) {
			if (other.segundoApellido != null)
				return false;
		} else if (!segundoApellido.equals(other.segundoApellido))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", primerApellido=" + primerApellido + ", segundoApellido="
				+ segundoApellido + ", dni=" + dni + ", email=" + email + ", reservas=" + reservas + "]";
	}

}

