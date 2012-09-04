package ar.edu.utn.frba.proyecto.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ar.edu.utn.frba.proyecto.constants.ConstantsDatatable;
import ar.edu.utn.frba.proyecto.domain.Analisis;

import com.mysql.jdbc.Statement;

public class AnalisisDao extends BaseAbmDao<Analisis> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5774061313214568651L;

	@Override
	public Analisis getFromResult(ResultSet result) {
		try {
			Analisis analisis = new Analisis(result.getInt(ConstantsDatatable.ANALISIS_ID), 
											 result.getString(ConstantsDatatable.GENERAL_NOMBRE));
			
			analisis.setFechaUltimaModificacion(getFechaUltimaMod(result));
			
			return analisis;
			
		} catch (SQLException e) { e.printStackTrace(); }

		return null;
	}

	@Override
	protected PreparedStatement prepareAddStatement(Analisis element) {
		String query = "INSERT INTO ANALISIS (nombre) VALUES (?)";
		PreparedStatement prepStatement = null;
		try {
			prepStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			prepStatement.setString(1, element.getNombre());
		} catch (SQLException e) {e.printStackTrace();}
		
		return prepStatement;
	}

	@Override
	protected PreparedStatement prepareUpdateStatement(Analisis element) {
		String query = "UPDATE ANALISIS SET nombre = ? WHERE idAnalisis = ? ";
		PreparedStatement prepStatement = null;
		try {
			prepStatement = conn.prepareStatement(query);
			prepStatement.setString(1, element.getNombre());
			prepStatement.setInt(2, element.getId());
		} catch (SQLException e) {e.printStackTrace();}
		
		return prepStatement;
	}

	@Override
	protected PreparedStatement prepareUniqueStatement(Analisis element) {
		// TODO Auto-generated method stub
		return null;
	}
}
