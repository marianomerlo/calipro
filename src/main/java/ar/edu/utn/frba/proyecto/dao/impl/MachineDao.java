package ar.edu.utn.frba.proyecto.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import ar.edu.utn.frba.proyecto.constants.ConstantsDatatable;
import ar.edu.utn.frba.proyecto.dao.AbmDao;
import ar.edu.utn.frba.proyecto.domain.Maquinaria;

public class MachineDao extends BaseAbmDao<Maquinaria> implements AbmDao<Maquinaria> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8732372830080908584L;

	@Override
	protected Maquinaria getFromResult(ResultSet result) {
		try {
			Maquinaria maquina = new Maquinaria(result.getInt(ConstantsDatatable.MAQUINARIA_ID), 
											 result.getString(ConstantsDatatable.GENERAL_NOMBRE),
											 result.getInt(ConstantsDatatable.GENERAL_ESTADO));
			
			maquina.setFechaUltimaModificacion(getFechaUltimaMod(result));
			
			return maquina;
			
		} catch (SQLException e) { e.printStackTrace();	}
		
		return null;
	}
	
	public List<Maquinaria> getAvailableMachines() {
		Connection conn = getConnection();
		ResultSet result = null;
		List<Maquinaria> resultList = new ArrayList<Maquinaria>();
		String query = "SELECT * FROM " + DATATABLE_NAME + " WHERE estado = ? ";
		try {
				PreparedStatement prepStatement = conn.prepareStatement(query);
				prepStatement.setInt(1, ConstantsDatatable.ESTADO_MAQUINARIA_DISPONIBLE);

				result = prepStatement.executeQuery();
				while (result.next()){
					resultList.add(getFromResult(result));
				}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
	        if (result != null) try { result.close(); } catch (SQLException logOrIgnore) {}
	        releaseConnection(conn);
		}
		return resultList;
	}

	@Override
	protected PreparedStatement prepareAddStatement(Maquinaria element) {
		String query = "INSERT INTO " + DATATABLE_NAME + " (nombre,estado,idUsuarioCreacion) VALUES (?,?,?)";
		PreparedStatement prepStatement = null;
		try {
			prepStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			prepStatement.setString(1, element.getNombre());
			prepStatement.setInt(2, ConstantsDatatable.ESTADO_MAQUINARIA_DISPONIBLE);
			prepStatement.setInt(3, element.getUsuarioCreacion().getId());
		} catch (SQLException e) {e.printStackTrace();}
		
		return prepStatement;
	}

	@Override
	protected PreparedStatement prepareUpdateStatement(Maquinaria element) {
		String query = "UPDATE " + DATATABLE_NAME + " PRODUCTO SET nombre = ?, estado = ?, idUsuarioUltimaMod = ? WHERE " + DATATABLE_ID + " = ? ";
		PreparedStatement prepStatement = null;
		try {
			prepStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			prepStatement.setString(1, element.getNombre());
			prepStatement.setInt(2, element.getEstado().getId());
			prepStatement.setInt(3, element.getUsuarioUltimaModificacion().getId());
			prepStatement.setInt(4, element.getId());
		} catch (SQLException e) {e.printStackTrace();}
		
		return prepStatement;
	}

	@Override
	protected PreparedStatement prepareUniqueStatement(Maquinaria element) {
		// TODO Auto-generated method stub
		return null;
	}

}
