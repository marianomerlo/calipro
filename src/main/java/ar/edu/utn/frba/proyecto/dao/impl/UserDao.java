package ar.edu.utn.frba.proyecto.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.id.IdentityGenerator.GetGeneratedKeysDelegate;

import ar.edu.utn.frba.proyecto.constants.ConstantsDatatable;
import ar.edu.utn.frba.proyecto.domain.Usuario;

import com.mysql.jdbc.Statement;

public class UserDao extends BaseAbmDao<Usuario> {

	@Override
	protected PreparedStatement prepareAddStatement(Usuario element) {
		String query = "INSERT INTO USUARIO (alias,nombre,apellido,estado,legajo,contrasenia) VALUES (?,?,?,?,?,?)";
		PreparedStatement prepStatement = null;
		try {
			prepStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			prepStatement.setString(1, element.getAlias());
			prepStatement.setString(2, element.getNombre());
			prepStatement.setString(3, element.getApellido());
			prepStatement.setInt(4, element.getEstado());
			prepStatement.setString(5, element.getLegajo());
			prepStatement.setString(6, element.getContraseña());
		} catch (SQLException e) {e.printStackTrace();}
		
		return prepStatement;
	}

	@Override
	protected PreparedStatement prepareUpdateStatement(Usuario element) {
		String query = "UPDATE PRODUCTO SET alias = ?, nombre = ?, apellido = ?, estado = ?, legajo = ?, contrasenia = ? WHERE idUsuario = ? ";
		PreparedStatement prepStatement = null;
		try {
			prepStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			prepStatement.setString(1, element.getAlias());
			prepStatement.setString(2, element.getNombre());
			prepStatement.setString(3, element.getApellido());
			prepStatement.setInt(4, element.getEstado());
			prepStatement.setString(5, element.getLegajo());
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
							   result.getString(ConstantsDatatable.USUARIO_CONTRASEÑA));
		} catch (SQLException e) { e.printStackTrace();	}
		
		return null;
		
	}

	@Override
	protected PreparedStatement prepareUniqueStatement(Usuario element) {
		String query = "SELECT * FROM USUARIO WHERE legajo = ?";
		PreparedStatement prepStatement = null;
		try {
			prepStatement = conn.prepareStatement(query);
			prepStatement.setString(1, element.getLegajo());
			
		} catch (SQLException e) {e.printStackTrace(); }
		
		return prepStatement;
	}

}
