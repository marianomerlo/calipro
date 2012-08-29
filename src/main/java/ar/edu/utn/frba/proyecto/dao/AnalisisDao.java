package ar.edu.utn.frba.proyecto.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ar.edu.utn.frba.proyecto.domain.Analisis;

import com.mysql.jdbc.Statement;

public class AnalisisDao extends GenericDao<Analisis> {

	@Override
	public Analisis getFromResult(ResultSet result) {
		try {
			return new Analisis(result.getInt(1), result.getString(2));
		} catch (SQLException e) { e.printStackTrace(); }

		return null;
	}

	@Override
	protected PreparedStatement prepareAddStatement(Analisis element) {
		String query = "INSERT INTO ANALISIS (nombre) VALUES (?)";
		PreparedStatement prepStatement = null;
		try {
			prepStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			prepStatement.setString(1, element.getNombre());
		} catch (SQLException e) {e.printStackTrace();}
		
		return prepStatement;
	}

	@Override
	protected PreparedStatement prepareUpdateStatement(Analisis element) {
		String query = "UPDATE ANALISIS SET nombre = ? WHERE idAnalisis = ? ";
		PreparedStatement prepStatement = null;
		try {
			prepStatement = conn.prepareStatement(query);
			prepStatement.setString(1, element.getNombre());
			prepStatement.setInt(2, element.getId());
		} catch (SQLException e) {e.printStackTrace();}
		
		return prepStatement;
	}

	@Override
	protected PreparedStatement prepareUniqueStatement(Analisis element) {
		// TODO Auto-generated method stub
		return null;
	}
}
