package ar.edu.utn.frba.proyecto.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.edu.utn.frba.proyecto.constants.ConstantsDatatable;
import ar.edu.utn.frba.proyecto.dao.AbmDao;
import ar.edu.utn.frba.proyecto.domain.AuditObject;

public abstract class BaseAbmDao<T extends AuditObject> extends BaseDao<T> implements AbmDao<T>{

	@Override
	public T getByUnique(T element){
		
		Connection conn = getConnection();
		ResultSet result = null;
		try {
			PreparedStatement prepStatement = prepareUniqueStatement(element);
			result = prepStatement.executeQuery();
			return result.first() ? getFromResult(result) : null;
		} catch (SQLException e) { 
			e.printStackTrace();
		} finally {
			releaseConnection(conn);
		}
		return null;
	}

	@Override
	public void delete(List<T> elements) {
		String query = "DELETE FROM " + DATATABLE_NAME + " WHERE " +  DATATABLE_ID + " = ?";
		conn = getConnection();
		try {
			for (T element : elements) {
				PreparedStatement prepStatement = conn.prepareStatement(query);
				prepStatement.setInt(1,element.getId() );
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
		String query = "SELECT * FROM " + DATATABLE_NAME;
		conn = getConnection();
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	        releaseConnection(conn);
		}
		
	}

	@Override
	public T add(T element){
		conn = getConnection();
		ResultSet result = null;
		
		try {
			PreparedStatement prepStatement = prepareAddStatement(element);
			
			prepStatement.executeUpdate();
			result = prepStatement.getGeneratedKeys();
			
			if ( result.next())
				element.setId(result.getInt(1));
			
			return element;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
	        if (result != null) try { result.close(); } catch (SQLException logOrIgnore) {}
	        releaseConnection(conn);
		}
		return null;
	}

	protected abstract PreparedStatement prepareAddStatement(T element);

	protected abstract PreparedStatement prepareUpdateStatement(T element);

	protected abstract PreparedStatement prepareUniqueStatement(T element);

	@Override
	public void update(T element){
		conn = getConnection();
		try {
			PreparedStatement prepStatement = prepareUpdateStatement(element);
			prepStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	        releaseConnection(conn);
		}
	}

	
	protected String getFechaUltimaMod(ResultSet result) throws SQLException{
		String fechaUltimaMod = result.getString(ConstantsDatatable.AUDIT_FECHA_ULTIMA_MOD) != null ?
				result.getString(ConstantsDatatable.AUDIT_FECHA_ULTIMA_MOD).split(" ")[0] :
				result.getString(ConstantsDatatable.AUDIT_FECHA_CREACION).split(" ")[0];
				
		return fechaUltimaMod;
	}
}
