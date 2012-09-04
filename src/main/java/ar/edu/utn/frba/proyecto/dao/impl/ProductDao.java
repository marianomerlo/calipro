package ar.edu.utn.frba.proyecto.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ar.edu.utn.frba.proyecto.constants.ConstantsDatatable;
import ar.edu.utn.frba.proyecto.domain.Producto;

import com.mysql.jdbc.Statement;

public class ProductDao extends BaseAbmDao<Producto>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3763939206027895436L;

	@Override
	public Producto getFromResult(ResultSet result) {
		try {
			Producto producto = new Producto(result.getInt(ConstantsDatatable.PRODUCTO_ID), 
											 result.getString(ConstantsDatatable.GENERAL_NOMBRE), 
											 result.getString(ConstantsDatatable.GENERAL_DESCRIPCION));
			
			producto.setFechaUltimaModificacion(getFechaUltimaMod(result));

			return producto;
			
		} catch (SQLException e) { e.printStackTrace();	}
		
		return null;
	}

	@Override
	protected PreparedStatement prepareAddStatement(Producto element) {
			String query = "INSERT INTO PRODUCTO (nombre,descripcion) VALUES (?,?)";
			PreparedStatement prepStatement = null;
			try {
				prepStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
				prepStatement.setString(1, element.getNombre());
				prepStatement.setString(2, element.getDescripcion());
			} catch (SQLException e) {e.printStackTrace();}
			
			return prepStatement;
	}
	
	@Override
	protected PreparedStatement prepareUpdateStatement(Producto element) {
		String query = "UPDATE PRODUCTO SET nombre = ?, descripcion = ? WHERE idProducto = ? ";
		PreparedStatement prepStatement = null;
		try {
			prepStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			prepStatement.setString(1, element.getNombre());
			prepStatement.setString(2, element.getDescripcion());
			prepStatement.setInt(3, element.getId());
		} catch (SQLException e) {e.printStackTrace();}
		
		return prepStatement;
	}

	@Override
	protected PreparedStatement prepareUniqueStatement(Producto element) {
		// TODO Auto-generated method stub
		return null;
	}
}

