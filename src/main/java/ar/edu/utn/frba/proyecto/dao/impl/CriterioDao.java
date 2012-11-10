package ar.edu.utn.frba.proyecto.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import ar.edu.utn.frba.proyecto.constants.ConstantsDatatable;
import ar.edu.utn.frba.proyecto.dao.Dao;
import ar.edu.utn.frba.proyecto.domain.Analisis;
import ar.edu.utn.frba.proyecto.domain.Criterio;

import com.mysql.jdbc.Statement;

public class CriterioDao extends BaseAbmDao<Criterio> implements Dao<Criterio> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7398381670676694567L;

	@Override
	protected Criterio getFromResult(ResultSet result) {
		try {
			Criterio criterio = new Criterio(
					result.getInt(ConstantsDatatable.CRITERIO_ID),
					result.getString(ConstantsDatatable.GENERAL_NOMBRE));
			return criterio;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void addCriteriosToAnalisis(Analisis analisis,
			List<Criterio> selectedCriterios) {
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
			analisis.setCriterios(selectedCriterios);
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

	}

	public void removeCriteriosFromAnalisis(Analisis analisis,
			List<Criterio> criterios) {
		Connection conn = getConnection();

		try {
			for (Criterio criterio : criterios) {

				String query = "DELETE FROM Criterio_por_Analisis WHERE idAnalisis = ? and idCriterio = ?";
				PreparedStatement prepStatement = conn.prepareStatement(query);
				prepStatement.setInt(1, analisis.getId());
				prepStatement.setInt(2, criterio.getId());

				prepStatement.execute();
			}
		} catch (SQLException e) {
			String message = e.getLocalizedMessage();
			FacesContext.getCurrentInstance()
					.addMessage(
							"updateAnalisisGrowlMessageKeys",
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									message, null));
		} finally {
			releaseConnection(conn);
		}
		analisis.setCriterios(new ArrayList<Criterio>());
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
			while (result.next()) {
				resultList.add(getFromResult(result));
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

	@Override
	public void add(Criterio element) {
		Connection conn = getConnection();
		ResultSet result = null;

		try {
			String query = "INSERT INTO CRITERIO (nombre) VALUES (?)";
			PreparedStatement prepStatement = conn.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			prepStatement.setString(1, element.getNombre());

			prepStatement.executeUpdate();
			result = prepStatement.getGeneratedKeys();

			if (result.next())
				element.setId(result.getInt(1));

			for (String opcion : element.getOpciones()) {
				if (!"".equals(opcion.trim())) {
					String query2 = "INSERT INTO VALOR (idCriterio,nombre) VALUES (?,?)";
					PreparedStatement prepStatement2 = conn
							.prepareStatement(query2);
					prepStatement2.setInt(1, element.getId());
					prepStatement2.setString(2, opcion);
					prepStatement2.executeUpdate();
				}
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
	}

	public List<String> getValuesFromCriterio(Criterio criterio) {
		Connection conn = getConnection();
		ResultSet result = null;
		List<String> resultList = new ArrayList<String>();
		try {
			String query = "SELECT * FROM VALOR WHERE idCriterio = ?";
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setInt(1, criterio.getId());

			result = prepStatement.executeQuery();

			while (result.next()) {
				resultList.add(result
						.getString(ConstantsDatatable.GENERAL_NOMBRE));
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
