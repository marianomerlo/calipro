package ar.edu.utn.frba.proyecto.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import ar.edu.utn.frba.proyecto.constants.ConstantsDatatable;
import ar.edu.utn.frba.proyecto.domain.Lote;
import ar.edu.utn.frba.proyecto.domain.Maquinaria;
import ar.edu.utn.frba.proyecto.domain.Producto;

public class ProcessDao extends BaseAbmDao<Lote> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4753420945832799099L;

	@Override
	protected Lote getFromResult(ResultSet result) {
		try {
			Lote lote = new Lote(result.getInt(ConstantsDatatable.LOTE_ID), 
					new Producto(result.getInt(ConstantsDatatable.PRODUCTO_ID)),
					new Maquinaria(result.getInt(ConstantsDatatable.MAQUINARIA_ID)),
					result.getInt(ConstantsDatatable.GENERAL_ESTADO));

			fillAuditInfo(lote, result);

			return lote;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	protected PreparedStatement prepareAddStatement(Lote element) {
		String query = "CALL " + "sp_iniciar_proceso " + "(?,?,?)";
		PreparedStatement prepStatement = null;
		try {
			prepStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			prepStatement.setInt(1, element.getProducto().getId());
			prepStatement.setInt(2, element.getMaquinaria().getId());
			prepStatement.setInt(3, element.getUsuarioCreacion().getId());
		} catch (SQLException e) {e.printStackTrace();}
		
		return prepStatement;
	}

	@Override
	protected PreparedStatement prepareUpdateStatement(Lote element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected PreparedStatement prepareUniqueStatement(Lote element) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Lote> getLotesInStatus(Integer estado) {
		Connection conn = getConnection();
		ResultSet result = null;
		List<Lote> resultList = new ArrayList<Lote>();
		String query = "SELECT * FROM " + DATATABLE_NAME + " WHERE idEstado = ?";
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setInt(1, estado);
			
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
