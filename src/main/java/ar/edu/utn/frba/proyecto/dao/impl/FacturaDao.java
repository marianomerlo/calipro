package ar.edu.utn.frba.proyecto.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ar.edu.utn.frba.proyecto.domain.Factura;

import com.mysql.jdbc.Statement;

public class FacturaDao extends BaseAbmDao<Factura> {

	@Override
	protected PreparedStatement prepareAddStatement(Factura element) {
		String query = "INSERT INTO " + DATATABLE_NAME + 
				" (costoTotal,fecha) VALUES (?,?)";
		PreparedStatement prepStatement = null;
		try {
			prepStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			prepStatement.setDouble(1, element.getCostoTotal());
			prepStatement.setString(2, element.getFecha());
		} catch (SQLException e) {e.printStackTrace();}
		
		return prepStatement;
	}

	@Override
	protected PreparedStatement prepareUpdateStatement(Factura element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected PreparedStatement prepareUniqueStatement(Factura element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Factura getFromResult(ResultSet result) {
		try {
			return new Factura(result.getInt("idFactura"),
								result.getString("fecha"), 
								result.getDouble("costoTotal"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Integer getLastFactura() {
		Connection conn = getConnection();
		ResultSet result = null;
		String query = "SELECT idFactura FROM tpanual.factura ORDER BY idFactura DESC LIMIT 1";
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			
			result = prepStatement.executeQuery();
			if (result.next()){
				return result.getInt("idFactura");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if (result != null) try { result.close(); } catch (SQLException logOrIgnore) {}
			releaseConnection(conn);
		}
		return 0;
	}

}
