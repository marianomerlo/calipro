package ar.edu.utn.frba.proyecto.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import ar.edu.utn.frba.proyecto.constants.ConstantsDatatable;
import ar.edu.utn.frba.proyecto.domain.Reporte;
import ar.edu.utn.frba.proyecto.domain.ReporteContainer;

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

	public List<ReporteContainer> getReporteCantidadAnalisisUsuario(Object...objects) {
		String query = getQuery(ConstantsDatatable.reportMap.get(1), objects);
		Connection conn = getConnection();
		ResultSet result = null;
		List<ReporteContainer> resultList = new ArrayList<ReporteContainer>();
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			result = prepStatement.executeQuery();
			
			while (result.next()){
				ReporteContainer container = new ReporteContainer();

				container.setIdUsuario(result.getInt(1));
				container.setNombreUsuario(result.getString(2));
				container.setNombreAnalisis(result.getString(3));
				container.setCantidadAnalisis(result.getInt(4));
				
				resultList.add(container);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseConnection(conn);
		}
		return resultList;

	}

	public List<ReporteContainer> getReporteEstadosProductosProcesados(Object...objects) {
		String query = getQuery(ConstantsDatatable.reportMap.get(2), objects);
		Connection conn = getConnection();
		ResultSet result = null;
		List<ReporteContainer> resultList = new ArrayList<ReporteContainer>();
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			result = prepStatement.executeQuery();
			
			while (result.next()){
				ReporteContainer container = new ReporteContainer();

				container.setIdProducto(result.getInt(1));
				container.setNombreProducto(result.getString(2));
				container.setIdLote(result.getInt(3));
				container.setEstado(result.getString(4));
				container.setFechaUltimaModificacion(result.getString(5));
				
				resultList.add(container);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseConnection(conn);
		}
		return resultList;

	}

	public List<ReporteContainer> getReporteLotesProducto(Object...objects) {
		String query = getQuery(ConstantsDatatable.reportMap.get(3), objects);
		Connection conn = getConnection();
		ResultSet result = null;
		List<ReporteContainer> resultList = new ArrayList<ReporteContainer>();
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			result = prepStatement.executeQuery();
			
			while (result.next()){
				ReporteContainer container = new ReporteContainer();

				container.setIdProducto(result.getInt(1));
				container.setNombreProducto(result.getString(2));
				container.setFechaCreacion(result.getString(3));
				container.setIdLote(result.getInt(4));
				
				resultList.add(container);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseConnection(conn);
		}
		return resultList;

	}

	public List<ReporteContainer> getReporteTiempoPromedioProducto(Object...objects) {
		String query = getQuery(ConstantsDatatable.reportMap.get(4), objects);
		Connection conn = getConnection();
		ResultSet result = null;
		List<ReporteContainer> resultList = new ArrayList<ReporteContainer>();
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			result = prepStatement.executeQuery();
			
			while (result.next()){
				ReporteContainer container = new ReporteContainer();

				container.setIdProducto(result.getInt(1));
				container.setNombreProducto(result.getString(2));
				container.setCantidadDeProcesamientos(result.getInt(3));
				container.setTiempoPromedio(result.getInt(4));
				
				resultList.add(container);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseConnection(conn);
		}
		return resultList;

	}

	private String getQuery(String spName, Object...objects ){
		StringBuilder query = new StringBuilder();
		query.append("CALL ");
		query.append(spName);
		query.append("(");
		query.append(StringUtils.arrayToDelimitedString(objects, ","));
		query.append(")");
		
		
		return query.toString();
	}

}
