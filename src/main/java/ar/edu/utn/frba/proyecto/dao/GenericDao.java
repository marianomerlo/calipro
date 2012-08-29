package ar.edu.utn.frba.proyecto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.proyecto.domain.AuditObject;

public abstract class GenericDao<T extends AuditObject> implements Dao<T>{

	/* Spring Properties */
	
	protected String dbURL;
	protected String sqlDriver;
	protected String username;
	protected String password;
	
	protected String DATATABLE_NAME;
	protected String DATATABLE_ID;

	protected Connection conn;
	
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

	@Override
	public void add(T element){
		conn = getConnection();
		ResultSet generatedKeys = null;
		
		try {
			PreparedStatement prepStatement = prepareAddStatement(element);
			
			prepStatement.execute();
			generatedKeys = prepStatement.getGeneratedKeys();
			
			if ( generatedKeys.next())
				element.setId(generatedKeys.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
	        if (generatedKeys != null) try { generatedKeys.close(); } catch (SQLException logOrIgnore) {}
	        releaseConnection(conn);
		}
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

	public abstract T getFromResult(ResultSet result);

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
