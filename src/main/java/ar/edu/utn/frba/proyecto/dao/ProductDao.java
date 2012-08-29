package ar.edu.utn.frba.proyecto.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ar.edu.utn.frba.proyecto.domain.Producto;

import com.mysql.jdbc.Statement;

public class ProductDao extends GenericDao<Producto>  {

	@Override
	public Producto getFromResult(ResultSet result) {
		try {
			return new Producto(result.getInt(1), result.getString(2), result.getString(3));
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

