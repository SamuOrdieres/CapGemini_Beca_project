package com.samuordieres.service;

import java.util.List;

import com.samuordieres.model.Reserva;

public interface ReservaService {
	
	Reserva findById (int id);
	
	void saveReserva (Reserva reserva);
	
	void updateReserva(Reserva reserva);
	
	void deleteReserva (int id);
	
	List <Reserva> findAllReservas();
	
}
