package ar.edu.utn.frba.proyecto.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.utn.frba.proyecto.constants.ConstantsDatatable;
import ar.edu.utn.frba.proyecto.domain.Analisis;
import ar.edu.utn.frba.proyecto.domain.Criterio;
import ar.edu.utn.frba.proyecto.domain.Paso;
import ar.edu.utn.frba.proyecto.domain.Producto;

import com.mysql.jdbc.Statement;

public class PasoDao extends BaseAbmDao<Paso> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6159568350551661051L;

	@Override
	protected PreparedStatement prepareAddStatement(Paso element) {
		String query = "CALL " + "sp_paso_insert " + "(?,?,?,?)";
		PreparedStatement prepStatement = null;
		try {
			prepStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			prepStatement.setInt(1, element.getProductoId());
			prepStatement.setInt(2, element.getVersion());
			prepStatement.setInt(3, element.getUsuarioCreacion().getId());
			prepStatement.setString(4, element.getDescripcion());
		} catch (SQLException e) {e.printStackTrace();}
		
		return prepStatement;
	}

	@Override
	protected PreparedStatement prepareDeleteStatement(Paso element) {
		String query = "CALL " + "sp_paso_delete " + "(?,?,?,?)";
		PreparedStatement prepStatement = null;
		try {
			prepStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			prepStatement.setInt(1, element.getId());
			prepStatement.setInt(2, element.getProductoId());
			prepStatement.setInt(3, element.getVersion());
			prepStatement.setInt(4, element.getUsuarioCreacion().getId());
		} catch (SQLException e) {e.printStackTrace();}
		
		return prepStatement;
	}

	@Override
	protected PreparedStatement prepareUpdateStatement(Paso element) {
		String query = "UPDATE " + DATATABLE_NAME + 
		" SET descripcion = ?, idUsuarioUltimaMod = ? WHERE " + DATATABLE_ID + " = ? and idProducto = ? and idVersion = ?";
		PreparedStatement prepStatement = null;
		try {
			prepStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			prepStatement.setString(1, element.getDescripcion());
			prepStatement.setInt(2, element.getUsuarioUltimaModificacion().getId());
			prepStatement.setInt(3, element.getId());
			prepStatement.setInt(4, element.getProductoId());
			prepStatement.setInt(5, element.getVersion());
		} catch (SQLException e) {e.printStackTrace();}
		
		return prepStatement;
	}

	@Override
	protected PreparedStatement prepareUniqueStatement(Paso element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Paso getFromResult(ResultSet result) {
		try {
			return new Paso(result.getInt(ConstantsDatatable.PASO_ID), 
							   result.getInt(ConstantsDatatable.PRODUCTO_ID), 
							   result.getInt(ConstantsDatatable.VERSION_ID), 
							   result.getString(ConstantsDatatable.GENERAL_DESCRIPCION));
		} catch (SQLException e) { e.printStackTrace();	}
		
		return null;
	}
	
	public List<Paso> getPasosByProduct(Producto producto){
		Connection conn = getConnection();
		ResultSet result = null;
		List<Paso> resultList = new ArrayList<Paso>();
		String query = "SELECT p.* FROM Paso p WHERE p.idProducto = ? and p.idversion = (select max(r.idversion) from receta r where r.idproducto=p.idproducto)";
		try {
				PreparedStatement prepStatement = conn.prepareStatement(query);
				prepStatement.setInt(1, producto.getId());

				result = prepStatement.executeQuery();
				while (result.next()){
					resultList.add(getFromResult(result));
				}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
	        if (result != null) try { result.close(); } catch (SQLException logOrIgnore) {}
	        releaseConnection(conn);
		}
		return resultList;
		
	}
	
	public void addAnalisisToPaso(Paso paso, Analisis analisis){
		Connection conn = getConnection();
		ResultSet result = null;
		try {
			for (Criterio criterio : analisis.getCriterios()) {
				if (!"".equals(criterio.getValorEsperado().trim())) {
					String query = "CALL " + "sp_analisis_por_paso_insert"
							+ " (?,?,?,?,?,?,?)";
					PreparedStatement prepStatement = conn
							.prepareStatement(query);
					prepStatement.setInt(1, paso.getProductoId());
					prepStatement.setInt(2, paso.getVersion());
					prepStatement.setInt(3, paso.getId());
					prepStatement.setInt(4, analisis.getId());
					prepStatement.setInt(5, criterio.getId());
					prepStatement.setString(6, criterio.getValorEsperado());
					prepStatement.setInt(7, paso.getUsuarioCreacion().getId());
					prepStatement.executeUpdate();
				}
			}
			paso.setAnalisis(Arrays.asList(analisis));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if (result != null) try { result.close(); } catch (SQLException logOrIgnore) {}
			releaseConnection(conn);
		}
	}

	public void updateAnalisisToPaso(Paso paso, Analisis analisis){
		Connection conn = getConnection();
		ResultSet result = null;
		List<Criterio> toRemove = new ArrayList<Criterio>();
		try {
			for (Criterio criterio : analisis.getCriterios()) {
				if (!"".equals(criterio.getValorEsperado().trim())) {
					String query = "CALL " + "sp_analisis_por_paso_update"
							+ " (?,?,?,?,?,?,?)";
					PreparedStatement prepStatement = conn
							.prepareStatement(query);
					prepStatement.setInt(1, paso.getProductoId());
					prepStatement.setInt(2, paso.getVersion());
					prepStatement.setInt(3, paso.getId());
					prepStatement.setInt(4, analisis.getId());
					prepStatement.setInt(5, criterio.getId());
					prepStatement.setString(6, criterio.getValorEsperado());
					prepStatement.setInt(7, paso.getUsuarioUltimaModificacion().getId());
					prepStatement.executeUpdate();
				} else {
					toRemove.add(criterio);
				}
			}
			analisis.setCriterios(toRemove);
			deleteAnalisisToPaso(paso, analisis);
			
//			paso.setAnalisis(Arrays.asList(analisis));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if (result != null) try { result.close(); } catch (SQLException logOrIgnore) {}
			releaseConnection(conn);
		}
	}

	public void deleteAnalisisToPaso(Paso paso, Analisis analisis){
		Connection conn = getConnection();
		ResultSet result = null;
		try {
			for (Criterio criterio : analisis.getCriterios()) {
					String query = "CALL " + "sp_analisis_por_paso_delete"
							+ " (?,?,?,?,?,?)";
					PreparedStatement prepStatement = conn
							.prepareStatement(query);
					prepStatement.setInt(1, paso.getProductoId());
					prepStatement.setInt(2, paso.getVersion());
					prepStatement.setInt(3, paso.getId());
					prepStatement.setInt(4, analisis.getId());
					prepStatement.setInt(5, criterio.getId());
					prepStatement.setInt(6, paso.getUsuarioUltimaModificacion().getId());
					prepStatement.executeUpdate();
			}
			paso.setAnalisis(Arrays.asList(analisis));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if (result != null) try { result.close(); } catch (SQLException logOrIgnore) {}
			releaseConnection(conn);
		}
	}

}
