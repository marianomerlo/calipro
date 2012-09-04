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
import ar.edu.utn.frba.proyecto.domain.Profile;
import ar.edu.utn.frba.proyecto.domain.Usuario;

public class ProfileDao extends BaseDao<Profile> implements Dao<Profile> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2358184555406079360L;

	@Override
	protected Profile getFromResult(ResultSet result) {
		try {
			Profile profile = new Profile(result.getInt(ConstantsDatatable.PERFIL_ID), 
											 result.getString(ConstantsDatatable.GENERAL_NOMBRE));
			return profile;
			
		} catch (SQLException e) { e.printStackTrace();	}
		
		return null;
	}
	
	public void addProfilesToUser(Usuario user, Profile[] profiles){
		Connection conn = getConnection();
		ResultSet result = null;
		String query = "INSERT INTO Perfil_por_Usuario (idPerfil,idUsuario) VALUES (?,?)";
		try {
			for (Profile profile : profiles) {

				PreparedStatement prepStatement = conn.prepareStatement(query);
				prepStatement.setInt(1, profile.getId());
				prepStatement.setInt(2, user.getId());

				prepStatement.executeUpdate();
			}
			user.setPerfiles(Arrays.asList(profiles));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
	        if (result != null) try { result.close(); } catch (SQLException logOrIgnore) {}
	        releaseConnection(conn);
		}
	}

	public List<Profile> getProfilesByUser(Usuario user) {
		Connection conn = getConnection();
		ResultSet result = null;
		List<Profile> resultList = new ArrayList<Profile>();
		String query = "SELECT * FROM PERFIL WHERE idPerfil in (SELECT idPerfil FROM Perfil_por_Usuario WHERE idUsuario = ?)";
		try {
				PreparedStatement prepStatement = conn.prepareStatement(query);
				prepStatement.setInt(1, user.getId());

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

	public void removeProfilesFromUser(Usuario user){
		Connection conn = getConnection();
		String query = "DELETE FROM Perfil_por_Usuario WHERE idUsuario = ?";
		try {
				PreparedStatement prepStatement = conn.prepareStatement(query);
				prepStatement.setInt(1, user.getId());

				prepStatement.execute();
				user.setPerfiles(new ArrayList<Profile>());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
	        releaseConnection(conn);
		}
	}

}
