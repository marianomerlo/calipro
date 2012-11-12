package ar.edu.utn.frba.proyecto.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.proyecto.dao.AbmDao;
import ar.edu.utn.frba.proyecto.domain.Analisis;
import ar.edu.utn.frba.proyecto.domain.Estado;
import ar.edu.utn.frba.proyecto.domain.Lote;
import ar.edu.utn.frba.proyecto.domain.Producto;
import ar.edu.utn.frba.proyecto.domain.Solicitud;

public class SolicitudDao extends BaseAbmDao<Solicitud> implements AbmDao<Solicitud> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5734181093478278571L;

	@Override
	protected PreparedStatement prepareAddStatement(Solicitud element) {
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
		try {
			Solicitud solicitud = new Solicitud();
			
			solicitud.setId(result.getInt(1));
			solicitud.setLote(new Lote(result.getInt(3), 
										new Producto(null, 
													result.getString(2),
													null), 
										null, 
										7, 
										null));
			solicitud.setFechaCreacion(result.getString(4));
			solicitud.setFechaRecibido(result.getString(5));
			solicitud.setEstado(new Estado(null,result.getString(6)));
			solicitud.setFechaUltimaModificacion(result.getString(7) != null ? result.getString(7) : "");
			solicitud.setAnalisis(new Analisis(result.getInt(8), ""));
			
			return solicitud;
			
		} catch (SQLException e) { e.printStackTrace();	}
		
		return null;
	}

	public List<Solicitud> getSolicitudesInProcess() {
		
		String query = "CALL sp_solicitudes_proceso()";
		return getSolicitudes(query);
	}

	public List<Solicitud> getSolicitudesHist() {
		String query = "CALL sp_solicitudes_historico()";
		return getSolicitudes(query);
	}
	
	public List<Solicitud> getSolicitudes(String query){
		List<Solicitud> resultList = new ArrayList<Solicitud>();
		conn = getConnection();
		ResultSet result = null;
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			result = prepStatement.executeQuery();
			
			while (result.next()){
				resultList.add(getFromResult(result));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	        releaseConnection(conn);
		}
		
		return resultList;
	}

	public void nextState(Solicitud solicitud) {
		String query = "CALL sp_modificar_estado_solicitud (?)";
		conn = getConnection();
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setInt(1, solicitud.getId());
			prepStatement.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	        releaseConnection(conn);
		}
		
	}

}
