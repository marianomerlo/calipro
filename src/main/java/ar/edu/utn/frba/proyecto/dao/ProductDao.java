package ar.edu.utn.frba.proyecto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ar.edu.utn.frba.proyecto.domain.Producto;

import com.mysql.jdbc.Statement;

public class ProductDao extends GenericDao {
	
	public Producto addProduct(Producto producto){
		
		String query = "INSERT INTO Producto (nombre,descripcion) VALUES (?,?)";
		Connection conn = getConnection();
		ResultSet generatedKeys = null;
		
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			prepStatement.setString(1, producto.getNombre());
			prepStatement.setString(2, producto.getDescripcion());
			
			prepStatement.execute();
			generatedKeys = prepStatement.getGeneratedKeys();
			
			if ( generatedKeys.next())
				producto.setProdId(generatedKeys.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
	        if (generatedKeys != null) try { generatedKeys.close(); } catch (SQLException logOrIgnore) {}
		}
		
		releaseConnection(conn);
		
		return producto;
	}
}
