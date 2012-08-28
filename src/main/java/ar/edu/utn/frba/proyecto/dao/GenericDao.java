package ar.edu.utn.frba.proyecto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericDao {

	/* Spring Properties */
	protected String dbURL;
	protected String sqlDriver;
	protected String username;
	protected String password;

	protected Connection getConnection() {
		try {
			Class.forName(sqlDriver).newInstance();
			// Get a connection
			Connection connection = DriverManager.getConnection(dbURL,
					username, password);
			return connection;
		} catch (Exception except) {
			except.printStackTrace();
		}
		return null;
	}

	protected void releaseConnection(Connection connection) {
		if (connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
