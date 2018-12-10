package com.samuordieres.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="reservas")
public class Reservas {
	
	@EmbeddedId
	private ReservasId id;
	
	@ManyToOne
	@MapsId("clienteId")
	private Cliente cliente;
	
	@ManyToOne
	@MapsId("centroTuristicoId")
	private CentroTuristico centroTuristico;
	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "fecha_entrada", nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate fechaEntrada;

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "fecha_salida", nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate fechaSalida;

	public ReservasId getId() {
		return id;
	}

	public void setId(ReservasId id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public CentroTuristico getCentroTuristico() {
		return centroTuristico;
	}

	public void setCentroTuristico(CentroTuristico centroTuristico) {
		this.centroTuristico = centroTuristico;
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((centroTuristico == null) ? 0 : centroTuristico.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((fechaEntrada == null) ? 0 : fechaEntrada.hashCode());
		result = prime * result + ((fechaSalida == null) ? 0 : fechaSalida.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Reservas other = (Reservas) obj;
		if (centroTuristico == null) {
			if (other.centroTuristico != null)
				return false;
		} else if (!centroTuristico.equals(other.centroTuristico))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (fechaEntrada == null) {
			if (other.fechaEntrada != null)
				return false;
		} else if (!fechaEntrada.equals(other.fechaEntrada))
			return false;
		if (fechaSalida == null) {
			if (other.fechaSalida != null)
				return false;
		} else if (!fechaSalida.equals(other.fechaSalida))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reservas [id=" + id + ", cliente=" + cliente + ", centroTuristico=" + centroTuristico
				+ ", fechaEntrada=" + fechaEntrada + ", fechaSalida=" + fechaSalida + "]";
	}
	
	
}
