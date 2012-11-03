package ar.edu.utn.frba.proyecto.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ar.edu.utn.frba.proyecto.dao.AbmDao;
import ar.edu.utn.frba.proyecto.domain.Solicitud;

public class SolicitudDao extends BaseAbmDao<Solicitud> implements AbmDao<Solicitud> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5734181093478278571L;

	@Override
	protected PreparedStatement prepareAddStatement(Solicitud element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected PreparedStatement prepareUpdateStatement(Solicitud element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected PreparedStatement prepareUniqueStatement(Solicitud element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Solicitud getFromResult(ResultSet result) {
		// TODO Auto-generated method stub
		return null;
	}

}
