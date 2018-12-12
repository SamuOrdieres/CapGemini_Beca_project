package com.samuordieres.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.samuordieres.model.Reserva;

@Repository("reservaDAO")
public class ReservaDAOImpl extends AbstractDAO<Integer, Reserva> implements ReservaDAO {

	@Override
	public Reserva findById(int id) {
		return getByKey(id);
	}

	@Override
	public void saveReserva(Reserva reserva) {
		persist(reserva);
	}

	@Override
	public void deleteReserva(int id) {
		Query query = getSession().createSQLQuery("delete from reservas where id = :id");
        query.setInteger("id", id);
        query.executeUpdate();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reserva> findAllReservas() {
		Criteria criteria = createEntityCriteria();
        return (List<Reserva>) criteria.list();
	}

}
