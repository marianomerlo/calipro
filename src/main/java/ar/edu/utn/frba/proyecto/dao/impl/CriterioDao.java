package ar.edu.utn.frba.proyecto.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.utn.frba.proyecto.constants.ConstantsDatatable;
import ar.edu.utn.frba.proyecto.dao.Dao;
import ar.edu.utn.frba.proyecto.domain.Analisis;
import ar.edu.utn.frba.proyecto.domain.Criterio;

public class CriterioDao extends BaseAbmDao<Criterio> implements Dao<Criterio> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7398381670676694567L;

	@Override
	protected Criterio getFromResult(ResultSet result) {
		try {
			Criterio criterio = new Criterio(result.getInt(ConstantsDatatable.CRITERIO_ID), 
											 result.getString(ConstantsDatatable.GENERAL_NOMBRE));
			return criterio;
			
		} catch (SQLException e) { e.printStackTrace();	}
		
		return null;
	}

	public void addCriteriosToAnalisis(Analisis analisis, Criterio[] selectedCriterios) {
		Connection conn = getConnection();
		ResultSet result = null;
		String query = "INSERT INTO Criterio_por_Analisis (idCriterio,idAnalisis) VALUES (?,?)";
		try {
			for (Criterio criterio : selectedCriterios) {

				PreparedStatement prepStatement = conn.prepareStatement(query);
				prepStatement.setInt(1, criterio.getId());
				prepStatement.setInt(2, analisis.getId());

				prepStatement.executeUpdate();
			}
			analisis.setCriterios(Arrays.asList(selectedCriterios));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
	        if (result != null) try { result.close(); } catch (SQLException logOrIgnore) {}
	        releaseConnection(conn);
		}
		
	}

	public void removeCriteriosFromAnalisis(Analisis analisis) {
		Connection conn = getConnection();
		String query = "DELETE FROM Criterio_por_Analisis WHERE idAnalisis = ?";
		try {
				PreparedStatement prepStatement = conn.prepareStatement(query);
				prepStatement.setInt(1, analisis.getId());

				prepStatement.execute();
				analisis.setCriterios(new ArrayList<Criterio>());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
	        releaseConnection(conn);
		}
	}

	public List<Criterio> getCriteriosByAnalisis(Analisis analisis) {
		Connection conn = getConnection();
		ResultSet result = null;
		List<Criterio> resultList = new ArrayList<Criterio>();
		String query = "SELECT * FROM CRITERIO WHERE idCriterio in (SELECT idCriterio FROM Criterio_por_Analisis WHERE idAnalisis = ?)";
		try {
				PreparedStatement prepStatement = conn.prepareStatement(query);
				prepStatement.setInt(1, analisis.getId());

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

	@Override
	protected PreparedStatement prepareAddStatement(Criterio element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected PreparedStatement prepareUpdateStatement(Criterio element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected PreparedStatement prepareUniqueStatement(Criterio element) {
		// TODO Auto-generated method stub
		return null;
	}

}
