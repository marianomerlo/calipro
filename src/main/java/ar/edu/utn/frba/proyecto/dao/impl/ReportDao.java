package ar.edu.utn.frba.proyecto.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ar.edu.utn.frba.proyecto.constants.ConstantsDatatable;
import ar.edu.utn.frba.proyecto.domain.Reporte;

public class ReportDao extends BaseDao<Reporte> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3818698204355565623L;

	@Override
	protected Reporte getFromResult(ResultSet result) {
		try {
			return new Reporte(result.getInt(ConstantsDatatable.REPORTE_ID),
					result.getString(ConstantsDatatable.GENERAL_NOMBRE));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ResultSet getReporteCantidadAnalisisUsuario() {
		String query = "";
		return getReporte(query);

	}

	public ResultSet getReporteEstadosProductosProcesadios() {
		String query = "";
		return getReporte(query);

	}

	public ResultSet getReporteLotesProducto() {
		String query = "";
		return getReporte(query);

	}

	public ResultSet getReporteTiempoPromedioProducto() {
		String query = "";
		return getReporte(query);

	}

	private ResultSet getReporte(String query) {
		Connection conn = getConnection();
		ResultSet result = null;
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			result = prepStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseConnection(conn);
		}
		return result;
	}
	
	private String getQuery(String spName, Object...objects ){
		StringBuilder query = new StringBuilder();
		query.append("CALL ");
		query.append(spName);
		query.append("(");
		
		
		return query.toString();
	}

}
