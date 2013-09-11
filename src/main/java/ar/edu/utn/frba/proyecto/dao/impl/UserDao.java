package ar.edu.utn.frba.proyecto.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ar.edu.utn.frba.proyecto.constants.ConstantsDatatable;
import ar.edu.utn.frba.proyecto.domain.Usuario;

import com.mysql.jdbc.Statement;

public class UserDao extends BaseAbmDao<Usuario> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2630787135372347058L;

	@Override
	protected PreparedStatement prepareAddStatement(Usuario element) {
		String query = "INSERT INTO " + DATATABLE_NAME + 
				" (alias,nombre,apellido,legajo,estado,contrasenia) VALUES (?,?,?,?,?,?)";
		PreparedStatement prepStatement = null;
		try {
			prepStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			prepStatement.setString(1, element.getAlias());
			prepStatement.setString(2, element.getNombre());
			prepStatement.setString(3, element.getApellido());
			prepStatement.setString(4, element.getLegajo());
			prepStatement.setInt(5, ConstantsDatatable.ESTADO_USUARIO_HABILITADO);
			prepStatement.setString(6, element.getContraseña());
		} catch (SQLException e) {e.printStackTrace();}
		
		return prepStatement;
	}

	@Override
	protected PreparedStatement prepareUpdateStatement(Usuario element) {
		String query = "UPDATE " + DATATABLE_NAME + 
		" SET alias = ?, nombre = ?, apellido = ?,  legajo = ?, estado = ?, contrasenia = ? WHERE " + DATATABLE_ID + " = ? ";
		PreparedStatement prepStatement = null;
		try {
			prepStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			prepStatement.setString(1, element.getAlias());
			prepStatement.setString(2, element.getNombre());
			prepStatement.setString(3, element.getApellido());
			prepStatement.setString(4, element.getLegajo());
			prepStatement.setInt(5, element.getEstado().getId());
			prepStatement.setString(6, element.getContraseña());
			prepStatement.setInt(7, element.getId());
		} catch (SQLException e) {e.printStackTrace();}
		
		return prepStatement;
	}

	@Override
	public Usuario getFromResult(ResultSet result) {
		try {
			return new Usuario(result.getInt(ConstantsDatatable.USUARIO_ID), 
							   result.getString(ConstantsDatatable.USUARIO_ALIAS), 
							   result.getString(ConstantsDatatable.GENERAL_NOMBRE), 
							   result.getString(ConstantsDatatable.GENERAL_APELLIDO), 
							   result.getString(ConstantsDatatable.USUARIO_LEGAJO), 
							   result.getInt(ConstantsDatatable.GENERAL_ESTADO),
							   result.getString(ConstantsDatatable.USUARIO_CONTRASEÑA));
		} catch (SQLException e) { e.printStackTrace();	}
		
		return null;
		
	}

	@Override
	protected PreparedStatement prepareUniqueStatement(Usuario element) {
		String query = "SELECT * FROM " + DATATABLE_NAME + " WHERE alias = ?";
		PreparedStatement prepStatement = null;
		try {
			prepStatement = conn.prepareStatement(query);
			prepStatement.setString(1, element.getAlias());
			
		} catch (SQLException e) {e.printStackTrace(); }
		
		return prepStatement;
	}
}
