package ar.edu.utn.frba.proyecto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.proyecto.domain.Analisis;

import com.mysql.jdbc.Statement;

public class AnalisisDao extends GenericDao implements Dao<Analisis> {

	@Override
	public Analisis get(Analisis element) {
		String query = "SELECT * FROM ANALISIS WHERE idAnalisis = ? ";
		Connection conn = getConnection();
		ResultSet result = null;
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setInt(1, element.getAnalisisId());
			result = prepStatement.executeQuery();
			
			if ( result.first()){
				return getAnalisis(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	        releaseConnection(conn);
		}
		return null;
	}


	@Override
	public List<Analisis> getAll() {
		List<Analisis> resultList = new ArrayList<Analisis>();
		
		String query = "SELECT * FROM ANALISIS";
		Connection conn = getConnection();
		ResultSet result = null;
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			result = prepStatement.executeQuery();
			
			while (result.next()){
				resultList.add(getAnalisis(result));
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
	public void add(Analisis element) {
		String query = "INSERT INTO ANALISIS (nombre) VALUES (?)";
		Connection conn = getConnection();
		ResultSet generatedKeys = null;
		
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			prepStatement.setString(1, element.getNombre());
			
			prepStatement.execute();
			generatedKeys = prepStatement.getGeneratedKeys();
			
			if ( generatedKeys.next())
				element.setAnalisisId(generatedKeys.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
	        if (generatedKeys != null) try { generatedKeys.close(); } catch (SQLException logOrIgnore) {}
	        releaseConnection(conn);
		}
		
	}

	@Override
	public void update(Analisis element) {
		String query = "UPDATE ANALISIS SET nombre = ? WHERE idAnalisis = ? ";
		Connection conn = getConnection();
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setString(1, element.getNombre());
			prepStatement.setInt(2, element.getAnalisisId());
			prepStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	        releaseConnection(conn);
		}
		
	}

	@Override
	public void delete(List<Analisis> elements) {
		String query = "DELETE FROM ANALISIS WHERE idAnalisis = ?";
		Connection conn = getConnection();
		try {
			for (Analisis analisis : elements) {
				PreparedStatement prepStatement = conn.prepareStatement(query);
				prepStatement.setInt(1,analisis.getAnalisisId() );
				prepStatement.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseConnection(conn);
		}
		
	}

	@Override
	public void deleteAll() {
		String query = "SELECT * FROM ANALISIS";
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

	private Analisis getAnalisis(ResultSet result) {
		try {
			return new Analisis(result.getInt(1), result.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
