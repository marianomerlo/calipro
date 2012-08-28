package ar.edu.utn.frba.proyecto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.proyecto.domain.Producto;

import com.mysql.jdbc.Statement;

public class ProductDao extends GenericDao implements Dao<Producto> {
	
	@Override
	public void add(Producto producto){
		
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
	        releaseConnection(conn);
		}
	}
	
	@Override
	public List<Producto> getAll(){
		List<Producto> resultList = new ArrayList<Producto>();
		
		String query = "SELECT * FROM PRODUCTO";
		Connection conn = getConnection();
		ResultSet result = null;
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			result = prepStatement.executeQuery();
			
			while (result.next()){
				resultList.add(getProduct(result));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	        if (result != null) try { result.close(); } catch (SQLException logOrIgnore) {}
	        releaseConnection(conn);
		}
		
		return resultList;
	}
	
	@Override
	public void update(Producto producto){
		
		String query = "UPDATE PRODUCTO SET nombre = ?, descripcion = ? WHERE idProducto = ? ";
		Connection conn = getConnection();
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setString(1, producto.getNombre());
			prepStatement.setString(2, producto.getDescripcion());
			prepStatement.setInt(3, producto.getProdId());
			prepStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	        releaseConnection(conn);
		}
	}
	 
	@Override
	public void delete(List<Producto> productos){
		String query = "DELETE FROM PRODUCTO WHERE idProducto = ?";
		Connection conn = getConnection();
		try {
			for (Producto producto : productos) {
				PreparedStatement prepStatement = conn.prepareStatement(query);
				prepStatement.setInt(1,producto.getProdId() );
				prepStatement.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseConnection(conn);
		}
	}

	@Override
	public Producto get(Producto producto) {
		String query = "SELECT * FROM PRODUCTO WHERE idProducto = ? ";
		Connection conn = getConnection();
		ResultSet result = null;
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setInt(1, producto.getProdId());
			result = prepStatement.executeQuery();
			
			if ( result.first()){
				return getProduct(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	        releaseConnection(conn);
		}
		return null;
		
	}

	@Override
	public void deleteAll() {
		String query = "SELECT * FROM PRODUCTO";
		Connection conn = getConnection();
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	        releaseConnection(conn);
		}
		
	}

	private Producto getProduct(ResultSet result) {
		try {
			return new Producto(result.getInt(1),
					result.getString(2),
					result.getString(3));
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
}
