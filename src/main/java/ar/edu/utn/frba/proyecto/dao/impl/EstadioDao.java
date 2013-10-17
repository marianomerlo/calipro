package ar.edu.utn.frba.proyecto.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.proyecto.constants.ConstantsDatatable;
import ar.edu.utn.frba.proyecto.domain.Asiento;
import ar.edu.utn.frba.proyecto.domain.Dia;
import ar.edu.utn.frba.proyecto.domain.Entrada;
import ar.edu.utn.frba.proyecto.domain.Estadio;
import ar.edu.utn.frba.proyecto.domain.Festival;
import ar.edu.utn.frba.proyecto.domain.Fila;
import ar.edu.utn.frba.proyecto.domain.Sector;

public class EstadioDao extends BaseDao<Estadio> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7228527751654408011L;

	@Override
	protected Estadio getFromResult(ResultSet result) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Sector> getSectoresDisponiblesFromFestivalAndDia(Festival festival, Dia dia) {
		Connection conn = getConnection();
		ResultSet result = null;
		List<Sector> resultList = new ArrayList<Sector>();
		String query = "SELECT idEstadio,idSector,nombreSector as nombre FROM estadio e1 where e1.idEstadio = (SELECT idEstadio from dia_festival_estadio where idFestival = ? AND idDia = ?) AND (SELECT count(*) FROM estadio e2 WHERE e1.idSector = e2.idSector and e2.disponible=?) > 0 GROUP BY idSector";
		try {
				PreparedStatement prepStatement = conn.prepareStatement(query);
				prepStatement.setInt(1, festival.getId());
				prepStatement.setInt(2, dia.getId());
				prepStatement.setString(3, "TRUE");

				result = prepStatement.executeQuery();
				while (result.next()){
					resultList.add(new Sector(result.getInt("idSector"), 
											  result.getString(ConstantsDatatable.GENERAL_NOMBRE),
											  result.getInt("idEstadio")
								  ));
				}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
	        if (result != null) try { result.close(); } catch (SQLException logOrIgnore) {}
	        releaseConnection(conn);
		}
		return resultList;
	}

	public List<Fila> getFilasDisponiblesFromSector(Sector sector) {
		Connection conn = getConnection();
		ResultSet result = null;
		List<Fila> resultList = new ArrayList<Fila>();
		String query = "SELECT fila FROM estadio e1 where e1.idSector = ? and e1.idEstadio = ? and (SELECT count(*) from estadio e2 WHERE e1.idSector = e2.idSector and e1.fila = e2.fila and e2.disponible='TRUE') > 0 GROUP BY fila";
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setInt(1, sector.getId());
			prepStatement.setInt(2, sector.getEstadio().getId());
			
			result = prepStatement.executeQuery();
			while (result.next()){
				resultList.add(new Fila(result.getInt("fila"),sector));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if (result != null) try { result.close(); } catch (SQLException logOrIgnore) {}
			releaseConnection(conn);
		}
		return resultList;
	}

	public List<Asiento> getAsientosDisponibleFromFila(Fila fila) {
		Connection conn = getConnection();
		ResultSet result = null;
		List<Asiento> resultList = new ArrayList<Asiento>();
		String query = "SELECT asiento FROM estadio where idEstadio = ? and idSector= ? and fila = ? and disponible = 'TRUE'";
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setInt(1, fila.getSector().getEstadio().getId());
			prepStatement.setInt(2, fila.getSector().getId());
			prepStatement.setInt(3, fila.getId());
			
			result = prepStatement.executeQuery();
			while (result.next()){
				resultList.add(new Asiento(result.getInt("asiento"), fila));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if (result != null) try { result.close(); } catch (SQLException logOrIgnore) {}
			releaseConnection(conn);
		}
		return resultList;
	}

	public Entrada getEntrada(Entrada entrada) {
		Connection conn = getConnection();
		ResultSet result = null;
		String query = "SELECT * FROM estadio where idEstadio = ? and idSector= ? and fila = ? and asiento = ?";
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setInt(1, entrada.getSector().getEstadio().getId());
			prepStatement.setInt(2, entrada.getSector().getId());
			prepStatement.setInt(3, entrada.getFila().getId());
			prepStatement.setInt(4, entrada.getAsiento().getId());
			
			result = prepStatement.executeQuery();
			if (result.next()){
				entrada.setPrecioBase(result.getDouble("precioBase"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if (result != null) try { result.close(); } catch (SQLException logOrIgnore) {}
			releaseConnection(conn);
		}
		return entrada;
	}

	public void venderEntrada(Entrada entrada) {
		Connection conn = getConnection();
		String query = "UPDATE estadio set disponible = ? where idEstadio = ? and idSector= ? and fila = ? and asiento = ?";
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setString(1, "FALSE");
			prepStatement.setInt(2, entrada.getSector().getEstadio().getId());
			prepStatement.setInt(3, entrada.getSector().getId());
			prepStatement.setInt(4, entrada.getFila().getId());
			prepStatement.setInt(5, entrada.getAsiento().getId());
			
			prepStatement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			releaseConnection(conn);
		}
		
	}
}
