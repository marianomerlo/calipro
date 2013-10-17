package ar.edu.utn.frba.proyecto.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.proyecto.constants.ConstantsDatatable;
import ar.edu.utn.frba.proyecto.domain.Dia;
import ar.edu.utn.frba.proyecto.domain.Festival;

import com.mysql.jdbc.Statement;

public class FestDao extends BaseAbmDao<Festival> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2630787135372347058L;

	@Override
	protected PreparedStatement prepareAddStatement(Festival element) {
		String query = "INSERT INTO "
				+ DATATABLE_NAME
				+ " (nombre,fecha_inicio,cantidad_dias,estado) VALUES (?,?,?,?)";
		PreparedStatement prepStatement = null;
		try {
			prepStatement = conn.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			prepStatement.setString(1, element.getNombre());
			prepStatement.setString(2, element.getFechaInicio());
			prepStatement.setInt(3, element.getCantidadDias());
			prepStatement.setInt(4,
					ConstantsDatatable.ESTADO_FESTIVAL_EN_PROGRAMCION);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return prepStatement;
	}

	@Override
	protected PreparedStatement prepareUpdateStatement(Festival element) {
		String query = "UPDATE "
				+ DATATABLE_NAME
				+ " SET nombre = ?, fecha_inicio = ?,  cantidad_dias = ?, estado = ? WHERE "
				+ DATATABLE_ID + " = ? ";
		PreparedStatement prepStatement = null;
		try {
			prepStatement = conn.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			prepStatement.setString(1, element.getNombre());
			prepStatement.setString(2, element.getFechaInicio());
			prepStatement.setInt(3, element.getCantidadDias());
			prepStatement.setInt(4, element.getEstado().getId());
			prepStatement.setInt(5, element.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return prepStatement;
	}

	@Override
	public Festival getFromResult(ResultSet result) {
		try {
			return new Festival(result.getInt(ConstantsDatatable.FESTIVAL_ID),
					result.getString(ConstantsDatatable.GENERAL_NOMBRE),
					result.getString(ConstantsDatatable.FESTIVAL_FECHA_INICIO),
					result.getInt(ConstantsDatatable.FESTIVAL_CANTIDAD_DIAS),
					result.getInt(ConstantsDatatable.GENERAL_ESTADO));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	protected PreparedStatement prepareUniqueStatement(Festival element) {
		String query = "SELECT * FROM " + DATATABLE_NAME + " WHERE alias = ?";
		PreparedStatement prepStatement = null;
		try {
			prepStatement = conn.prepareStatement(query);
			prepStatement.setString(1, element.getNombre());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return prepStatement;
	}

	public PreparedStatement addDiasToFestival(Festival element) {
		Connection conn = getConnection();
		String query = "INSERT INTO dia_festival (idDia,idFestival,hora_inicio) VALUES(?,?,?)";
		PreparedStatement prepStatement = null;
		try {

			for (int i = 1; i <= element.getCantidadDias(); i++) {
				prepStatement = conn.prepareStatement(query);
				prepStatement.setInt(1, i);
				prepStatement.setInt(2, element.getId());
				prepStatement.setString(3, "18:00");

				prepStatement.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return prepStatement;
	}

	public List<Dia> getDiasByFestival(Festival festival) {
		Connection conn = getConnection();
		ResultSet result = null;
		List<Dia> resultList = new ArrayList<Dia>();
		String query = "SELECT * FROM dia_festival WHERE idFestival = ?";
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setInt(1, festival.getId());

			result = prepStatement.executeQuery();
			while (result.next()) {
				resultList.add(getDiaFromResult(result));
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

	private Dia getDiaFromResult(ResultSet result) throws SQLException {

		return new Dia(result.getInt("idDia"), result.getString("hora_inicio"),
				null);
	}

	public void addEstadioToFestival(Festival currentItem) {
		Connection conn = getConnection();

		ResultSet result = null;
		try {
			for (int i = 1; i <= currentItem.getCantidadDias(); i++) {
				String query = "INSERT INTO dia_festival_estadio (idFestival,idDia) VALUES(?,?)";
				PreparedStatement prepStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				prepStatement.setInt(1, currentItem.getId());
				prepStatement.setInt(2, i);
				prepStatement.executeUpdate();

				result = prepStatement.getGeneratedKeys();

				if (result.next()) {
					Integer idEstadio = result.getInt(1);
					String query2 = "CALL llenar_estadio(?)";
					PreparedStatement prepStatement2 = conn.prepareStatement(
							query2, Statement.RETURN_GENERATED_KEYS);
					prepStatement2.setInt(1, idEstadio);
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

}
