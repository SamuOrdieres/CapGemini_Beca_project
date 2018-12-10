package com.samuordieres.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ReservasId implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "centros_turisticos_id")
	private Long centroTuristicoId;
	
	@Column(name = "clientes_id")
	private Long clienteId;

	public Long getCentrosTuristicosId() {
		return centroTuristicoId;
	}

	public void setCentrosTuristicosId(Long centrosTuristicosId) {
		this.centroTuristicoId = centrosTuristicosId;
	}

	public Long getClientes_id() {
		return clienteId;
	}

	public void setClientes_id(Long clientes_id) {
		this.clienteId = clientes_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((centroTuristicoId == null) ? 0 : centroTuristicoId.hashCode());
		result = prime * result + ((clienteId == null) ? 0 : clienteId.hashCode());
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
		ReservasId other = (ReservasId) obj;
		if (centroTuristicoId == null) {
			if (other.centroTuristicoId != null)
				return false;
		} else if (!centroTuristicoId.equals(other.centroTuristicoId))
			return false;
		if (clienteId == null) {
			if (other.clienteId != null)
				return false;
		} else if (!clienteId.equals(other.clienteId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReservasId [centrosTuristicosId=" + centroTuristicoId + ", clientes_id=" + clienteId + "]";
	}
	
	
	
}
