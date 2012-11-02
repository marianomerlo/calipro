package ar.edu.utn.frba.proyecto.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ar.edu.utn.frba.proyecto.dao.Dao;
import ar.edu.utn.frba.proyecto.domain.Producto;
import ar.edu.utn.frba.proyecto.domain.Receta;

public class RecetaDao extends BaseDao<Receta> implements Dao<Receta> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5571436515005837290L;

	@Override
	protected Receta getFromResult(ResultSet result) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Integer getProductVersion(Producto producto){
		Connection conn = getConnection();
		ResultSet result = null;
		String query = "SELECT idversion FROM " + DATATABLE_NAME + " WHERE idProducto = ? ORDER BY idversion DESC LIMIT 1";
		try {
				PreparedStatement prepStatement = conn.prepareStatement(query);
				prepStatement.setInt(1, producto.getId());

				result = prepStatement.executeQuery();
				if (result.next()){
					return result.getInt(1);
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
