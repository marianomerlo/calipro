package ar.edu.utn.frba.proyecto.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.proyecto.constants.ConstantsDatatable;
import ar.edu.utn.frba.proyecto.domain.Analisis;
import ar.edu.utn.frba.proyecto.domain.Criterio;
import ar.edu.utn.frba.proyecto.domain.Estado;
import ar.edu.utn.frba.proyecto.domain.Lote;
import ar.edu.utn.frba.proyecto.domain.Maquinaria;
import ar.edu.utn.frba.proyecto.domain.Message;
import ar.edu.utn.frba.proyecto.domain.Paso;
import ar.edu.utn.frba.proyecto.domain.Producto;
import ar.edu.utn.frba.proyecto.domain.enumType.StatusType;

import com.mysql.jdbc.Statement;

public class ProcessDao extends BaseAbmDao<Lote> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4753420945832799099L;

	@Override
	protected Lote getFromResult(ResultSet result) {
		try {
			Lote lote = new Lote(
					result.getInt(ConstantsDatatable.LOTE_ID),
					new Producto(result.getInt(ConstantsDatatable.PRODUCTO_ID)),
					new Maquinaria(result
							.getInt(ConstantsDatatable.MAQUINARIA_ID)), result
							.getInt(ConstantsDatatable.GENERAL_ESTADO), result
							.getInt(ConstantsDatatable.VERSION_ID));

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
			prepStatement = conn.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			prepStatement.setInt(1, element.getProducto().getId());
			prepStatement.setInt(2, element.getMaquinaria().getId());
			prepStatement.setInt(3, element.getUsuarioCreacion().getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}

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
		String query = "SELECT * FROM " + DATATABLE_NAME + " WHERE "
				+ ConstantsDatatable.GENERAL_ESTADO + " = ?";
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setInt(1, estado);

			result = prepStatement.executeQuery();
			while (result.next()) {
				Lote lote = getFromResult(result);
				lote.setEstado(new Estado(estado, ""));
				resultList.add(lote);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null)
				try {
					result.close();
				} catch (SQLException logOrIgnore) {
				}
			releaseConnection(conn);
		}
		return resultList;
	}

	public List<Paso> getPasosLote(Lote lote) {
		Connection conn = getConnection();
		ResultSet result = null;
		List<Paso> resultList = new ArrayList<Paso>();
		String query = "CALL sp_receta_proceso(?)";
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setInt(1, lote.getId());

			result = prepStatement.executeQuery();
			while (result.next()) {
				resultList.add(new Paso(result
						.getInt(ConstantsDatatable.PASO_ID), result
						.getInt(ConstantsDatatable.PRODUCTO_ID), result
						.getInt(ConstantsDatatable.VERSION_ID), result
						.getString(ConstantsDatatable.GENERAL_DESCRIPCION)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null)
				try {
					result.close();
				} catch (SQLException logOrIgnore) {
				}
			releaseConnection(conn);
		}
		return resultList;
	}

	public Message endProcess(Lote lote) {
		Connection conn = getConnection();
		String query = "CALL sp_finalizar_proceso(?,?,?,?)";
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setInt(1, lote.getProducto().getId());
			prepStatement.setInt(2, lote.getVersion());
			prepStatement.setInt(3, lote.getMaquinaria().getId());
			prepStatement.setInt(4, lote.getUsuarioUltimaModificacion().getId());

			prepStatement.executeQuery();

		} catch (SQLException e) {
			return new Message(e.getLocalizedMessage(),StatusType.ERROR);
		} finally {
			releaseConnection(conn);
		}
		
		return new Message("Proceso de Producción Finalizado Satisfactoriamente",StatusType.SUCCESS);
	}

	public Message cancelProcess(Lote lote) {
		Connection conn = getConnection();
		String query = "CALL sp_cancelar_proceso(?,?,?,?)";
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setInt(1, lote.getProducto().getId());
			prepStatement.setInt(2, lote.getVersion());
			prepStatement.setInt(3, lote.getMaquinaria().getId());
			prepStatement.setInt(4, lote.getUsuarioUltimaModificacion().getId());

			prepStatement.executeQuery();

		} catch (SQLException e) {
			return new Message("No se pudo cancelar el proceso",StatusType.ERROR);
		} finally {
			releaseConnection(conn);
		}
		
		return new Message("Proceso de Producción Cancelado Satisfactoriamente",StatusType.SUCCESS);
	}

	public Message askAnalisis(Lote lote, Paso paso, Analisis analisis) {
		Connection conn = getConnection();
		String query = "CALL sp_solicitar_analisis(?,?,?,?,?,?,?)";
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setInt(1, lote.getProducto().getId());
			prepStatement.setInt(2, lote.getVersion());
			prepStatement.setInt(3, lote.getMaquinaria().getId());
			prepStatement.setInt(4, paso.getId()); //Paso
			prepStatement.setInt(5, analisis.getId()); //Analisis
			
			prepStatement.setInt(7, lote.getUsuarioCreacion().getId());

			for ( Criterio criterio : analisis.getCriterios() ){
				prepStatement.setInt(6, criterio.getId()); //Criterio
				prepStatement.executeQuery();
			}

		} catch (SQLException e) {
			return new Message("No se pudo solicitar el análisis",StatusType.ERROR);
		} finally {
			releaseConnection(conn);
		}
		
		return new Message("Análisis solicitado Satisfactoriamente",StatusType.SUCCESS);
	}

	public void getHistoricInfo(Paso paso,Lote lote) {
//		Connection conn = getConnection();
//		String query = "CALL sp_hint_historico(?,?,?,?,?,?)";
//		try {
//			PreparedStatement prepStatement = conn.prepareStatement(query);
//			prepStatement.setInt(1, paso.getProductoId());
//			prepStatement.setInt(2, paso.getId());
//			prepStatement.setInt(3, paso.getVersion());
//			prepStatement.setInt(4, lote.getId()); //Paso
//			prepStatement.setInt(5, analisis.getId()); //Analisis
//			
//			prepStatement.setInt(7, lote.getUsuarioCreacion().getId());
//
//			for ( Criterio criterio : analisis.getCriterios() ){
//				prepStatement.setInt(6, criterio.getId()); //Criterio
//				prepStatement.executeQuery();
//			}
//
//		} catch (SQLException e) {
//			return new Message("No se pudo solicitar el análisis",StatusType.ERROR);
//		} finally {
//			releaseConnection(conn);
//		}
//		
//		return new Message("Análisis solicitado Satisfactoriamente",StatusType.SUCCESS);
		
	}

}
