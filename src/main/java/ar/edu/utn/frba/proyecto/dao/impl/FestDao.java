package ar.edu.utn.frba.proyecto.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ar.edu.utn.frba.proyecto.constants.ConstantsDatatable;
import ar.edu.utn.frba.proyecto.domain.Festival;

import com.mysql.jdbc.Statement;

public class FestDao extends BaseAbmDao<Festival> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2630787135372347058L;

	@Override
	protected PreparedStatement prepareAddStatement(Festival element) {
		String query = "INSERT INTO " + DATATABLE_NAME + 
				" (nombre,fecha_inicio,cantidad_dias,estado) VALUES (?,?,?,?)";
		PreparedStatement prepStatement = null;
		try {
			prepStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			prepStatement.setString(1, element.getNombre());
			prepStatement.setString(2, element.getFechaInicio());
			prepStatement.setInt(3, element.getCantidadDias());
			prepStatement.setInt(4, ConstantsDatatable.ESTADO_FESTIVAL_EN_PROGRAMCION);
		} catch (SQLException e) {e.printStackTrace();}
		
		return prepStatement;
	}

	@Override
	protected PreparedStatement prepareUpdateStatement(Festival element) {
		String query = "UPDATE " + DATATABLE_NAME + 
		" SET nombre = ?, fecha_inicio = ?,  cantidad_dias = ?, estado = ? WHERE " + DATATABLE_ID + " = ? ";
		PreparedStatement prepStatement = null;
		try {
			prepStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			prepStatement.setString(1, element.getNombre());
			prepStatement.setString(2, element.getFechaInicio());
			prepStatement.setInt(3, element.getCantidadDias());
			prepStatement.setInt(4, element.getEstado().getId());
			prepStatement.setInt(5, element.getId());
		} catch (SQLException e) {e.printStackTrace();}
		
		return prepStatement;
	}

	@Override
	public Festival getFromResult(ResultSet result) {
		try {
			return new Festival(result.getInt(ConstantsDatatable.FESTIVAL_ID),
							   result.getString(ConstantsDatatable.GENERAL_NOMBRE), 
							   result.getString(ConstantsDatatable.FESTIVAL_FECHA_INICIO), 
							   result.getInt(ConstantsDatatable.FESTIVAL_CANTIDAD_DIAS), 
							   result.getInt(ConstantsDatatable.GENERAL_ESTADO));
		} catch (SQLException e) { e.printStackTrace();	}
		
		return null;
		
	}

	@Override
	protected PreparedStatement prepareUniqueStatement(Festival element) {
		String query = "SELECT * FROM " + DATATABLE_NAME + " WHERE alias = ?";
		PreparedStatement prepStatement = null;
		try {
			prepStatement = conn.prepareStatement(query);
			prepStatement.setString(1, element.getNombre());
			
		} catch (SQLException e) {e.printStackTrace(); }
		
		return prepStatement;
	}
}
