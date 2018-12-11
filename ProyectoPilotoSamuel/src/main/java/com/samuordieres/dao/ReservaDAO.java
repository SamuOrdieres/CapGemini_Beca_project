package com.samuordieres.dao;

import java.util.List;

import com.samuordieres.model.Reserva;

public interface ReservaDAO {

	Reserva findById(int id);

	void saveReserva(Reserva reserva);

	void deleteReserva(int id);

	List<Reserva> findAllReservas();

}
