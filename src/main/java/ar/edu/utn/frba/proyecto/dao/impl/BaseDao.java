package ar.edu.utn.frba.proyecto.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.proyecto.dao.Dao;
import ar.edu.utn.frba.proyecto.domain.BaseObject;

public abstract class BaseDao<T extends BaseObject> implements Dao<T> {

	/* Spring Properties */
	
	protected String dbURL;
	protected String sqlDriver;
	protected String username;
	protected String password;
	
	protected String DATATABLE_NAME;
	protected String DATATABLE_ID;

	protected Connection conn;
	
	protected abstract T getFromResult(ResultSet result);
	
	protected Connection getConnection() {
		try {
			Class.forName(sqlDriver).newInstance();
			// Get a connection
			conn = DriverManager.getConnection(dbURL,
					username, password);
			return conn;
		} catch (Exception except) {
			except.printStackTrace();
		}
		return null;
	}

	protected void releaseConnection(Connection connection) {
		if (connection != null)
			try {
				connection.close();
				this.conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	@Override
	public T get(T element) {
		String query = "SELECT * FROM " + DATATABLE_NAME + " WHERE " + DATATABLE_ID + " = ? ";
		conn = getConnection();
		ResultSet result = null;
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setInt(1, element.getId());
			result = prepStatement.executeQuery();
			
			if ( result.first()){
				return getFromResult(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	        releaseConnection(conn);
		}
		return null;
	}

	@Override
	public List<T> getAll() {
		List<T> resultList = new ArrayList<T>();
		
		String query = "SELECT * FROM " +  DATATABLE_NAME;
		conn = getConnection();
		ResultSet result = null;
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			result = prepStatement.executeQuery();
			
			while (result.next()){
				resultList.add(getFromResult(result));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	        if (result != null) try { result.close(); } catch (SQLException logOrIgnore) {}
	        releaseConnection(conn);
		}
		
		return resultList;
	}
	
	public String getDATATABLE_NAME() {
		return DATATABLE_NAME;
	}

	public void setDATATABLE_NAME(String DATATABLE_NAME) {
		this.DATATABLE_NAME = DATATABLE_NAME;
	}
	
	public String getDATATABLE_ID() {
		return DATATABLE_ID;
	}

	public void setDATATABLE_ID(String dATATABLE_ID) {
		DATATABLE_ID = dATATABLE_ID;
	}
	
	public String getDbURL() {
		return dbURL;
	}
	
	public void setDbURL(String dbURL) {
		this.dbURL = dbURL;
	}
	
	public String getSqlDriver() {
		return sqlDriver;
	}
	
	public void setSqlDriver(String sqlDriver) {
		this.sqlDriver = sqlDriver;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
