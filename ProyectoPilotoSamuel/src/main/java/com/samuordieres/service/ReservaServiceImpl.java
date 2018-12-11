package com.samuordieres.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.samuordieres.dao.ReservaDAO;
import com.samuordieres.model.Reserva;

@Service("reservaService")
@Transactional
public class ReservaServiceImpl implements ReservaService {

	private ReservaDAO dao;

	@Override
	public Reserva findById(int id) {
		return dao.findById(id);
	}

	@Override
	public void saveReserva(Reserva reserva) {
		dao.saveReserva(reserva);
	}

	@Override
	public void updateReserva(Reserva reserva) {
		Reserva entity = dao.findById(reserva.getId());
		if (entity != null) {
			entity.setCliente(reserva.getCliente());
			entity.setCentroTuristico(reserva.getCentroTuristico());
			entity.setFechaEntrada(reserva.getFechaEntrada());
			entity.setFechaSalida(reserva.getFechaSalida());
		}

	}

	@Override
	public void deleteReserva(int id) {
		dao.deleteReserva(id);
	}

	@Override
	public List<Reserva> findAllReservas() {
		return dao.findAllReservas();
	}

}
