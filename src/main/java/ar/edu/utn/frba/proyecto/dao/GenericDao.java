package ar.edu.utn.frba.proyecto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class GenericDao {

	private final String dbURL = "jdbc:mysql://localhost:3306/calipro";
	private final String sqlDriver = "com.mysql.jdbc.Driver";
	private final String username = "mariano";
	private final String password = "mariano";

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

}
