package ar.edu.utn.frba.proyecto.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import ar.edu.utn.frba.proyecto.constants.ConstantsDatatable;
import ar.edu.utn.frba.proyecto.dao.Dao;
import ar.edu.utn.frba.proyecto.domain.Profile;

public class ProfileDao extends BaseDao<Profile> implements Dao<Profile> {

	@Override
	protected Profile getFromResult(ResultSet result) {
		try {
			Profile profile = new Profile(result.getInt(ConstantsDatatable.PERFIL_ID), 
											 result.getString(ConstantsDatatable.GENERAL_NOMBRE));
			
			return profile;
			
		} catch (SQLException e) { e.printStackTrace();	}
		
		return null;
	}

}