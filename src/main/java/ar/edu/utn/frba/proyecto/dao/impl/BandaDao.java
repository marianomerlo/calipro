package ar.edu.utn.frba.proyecto.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.proyecto.constants.ConstantsDatatable;
import ar.edu.utn.frba.proyecto.domain.Banda;
import ar.edu.utn.frba.proyecto.domain.Dia;
import ar.edu.utn.frba.proyecto.domain.Festival;

import com.mysql.jdbc.Statement;

public class BandaDao extends BaseAbmDao<Banda> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2630787135372347058L;

	@Override
	protected PreparedStatement prepareAddStatement(Banda element) {
		String query = "INSERT INTO " + DATATABLE_NAME + 
				" (nombre,fecha_inicio,cantidad_dias,estado) VALUES (?,?,?,?)";
		PreparedStatement prepStatement = null;
		try {
			prepStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			prepStatement.setString(1, element.getNombre());
//			prepStatement.setString(2, element.getFechaInicio());
//			prepStatement.setInt(3, element.getCantidadDias());
			prepStatement.setInt(4, ConstantsDatatable.ESTADO_FESTIVAL_EN_PROGRAMCION);
		} catch (SQLException e) {e.printStackTrace();}
		
		return prepStatement;
	}

	@Override
	protected PreparedStatement prepareUpdateStatement(Banda element) {
		String query = "UPDATE " + DATATABLE_NAME + 
		" SET nombre = ?, fecha_inicio = ?,  cantidad_dias = ?, estado = ? WHERE " + DATATABLE_ID + " = ? ";
		PreparedStatement prepStatement = null;
		try {
			prepStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			prepStatement.setString(1, element.getNombre());
//			prepStatement.setString(2, element.getFechaInicio());
//			prepStatement.setInt(3, element.getCantidadDias());
//			prepStatement.setInt(4, element.getEstado().getId());
			prepStatement.setInt(5, element.getId());
		} catch (SQLException e) {e.printStackTrace();}
		
		return prepStatement;
	}

	@Override
	public Banda getFromResult(ResultSet result) {
		try {
			return new Banda(result.getInt(ConstantsDatatable.BANDA_ID),
							   result.getString(ConstantsDatatable.GENERAL_NOMBRE),
							   null,
							   null);
		} catch (SQLException e) { e.printStackTrace();	}
		
		return null;
		
	}

	@Override
	protected PreparedStatement prepareUniqueStatement(Banda element) {
		String query = "SELECT * FROM " + DATATABLE_NAME + " WHERE alias = ?";
		PreparedStatement prepStatement = null;
		try {
			prepStatement = conn.prepareStatement(query);
			prepStatement.setString(1, element.getNombre());
			
		} catch (SQLException e) {e.printStackTrace(); }
		
		return prepStatement;
	}

	public void populateBandasFromFestival(Festival festival) {
		Connection conn = getConnection();
		ResultSet result = null;
		String query = "SELECT * FROM banda_dia_festival where idFestival = ? and idDia = ?";
		try {
				PreparedStatement prepStatement = conn.prepareStatement(query);
				prepStatement.setInt(1, festival.getId());
				
				for (Dia dia : festival.getDias()){
					prepStatement.setInt(2, dia.getId());
					result = prepStatement.executeQuery();
					List<Banda> resultList = new ArrayList<Banda>();
					while (result.next()){
						resultList.add(getBandaFromResult(result));
					}
					
					for (Banda banda : resultList){
						banda.setNombre(this.get(banda).getNombre());
					}
					
					dia.setBandas(resultList);
				}

				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
	        if (result != null) try { result.close(); } catch (SQLException logOrIgnore) {}
	        releaseConnection(conn);
		}
	}

	private Banda getBandaFromResult(ResultSet result) throws SQLException {

		return new Banda(result.getInt("idBanda"), 
						null, 
						result.getString("tiempoAsignado"), 
						result.getString("costoExtra"));
	}

	public void agregarBandaADia(Banda banda, int dia, Festival festival) {
		Connection conn = getConnection();
		ResultSet result = null;
		String query = "INSERT INTO banda_dia_festival (idBanda, idDia, idFestival, tiempoAsignado, costoExtra) VALUES (?,?,?,?,?)";
		try {
				PreparedStatement prepStatement = conn.prepareStatement(query);
				prepStatement.setInt(1, banda.getId());
				prepStatement.setInt(2, dia);
				prepStatement.setInt(3, festival.getId());
				prepStatement.setString(4, banda.getTiempoAsignado());
				prepStatement.setDouble(5, banda.getCostoExtra());
				
				prepStatement.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
	        if (result != null) try { result.close(); } catch (SQLException logOrIgnore) {}
	        releaseConnection(conn);
		}
		
	}
	
}
