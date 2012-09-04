package ar.edu.utn.frba.proyecto.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.proyecto.constants.ConstantsDatatable;
import ar.edu.utn.frba.proyecto.dao.Dao;
import ar.edu.utn.frba.proyecto.domain.BaseObject;
import ar.edu.utn.frba.proyecto.domain.Estado;

public class EstadoDao extends BaseDao<Estado> implements Dao<Estado> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4801379593283211053L;

	@Override
	protected Estado getFromResult(ResultSet result) {
		try {
			Estado estado = new Estado(result.getInt(ConstantsDatatable.ESTADO_ID), 
											 result.getString(ConstantsDatatable.GENERAL_NOMBRE));
			return estado;
			
		} catch (SQLException e) { e.printStackTrace();	}
		
		return null;
	}
	
	public List<Estado> getEstadosFromElement(BaseObject element){
		Connection conn = getConnection();
		ResultSet result = null;
		List<Estado> resultList = new ArrayList<Estado>();
		String query = "SELECT * FROM ESTADO WHERE idGrupo = ?";
		try {
				PreparedStatement prepStatement = conn.prepareStatement(query);
				prepStatement.setInt(1, element.getStateGroupId());

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

}
