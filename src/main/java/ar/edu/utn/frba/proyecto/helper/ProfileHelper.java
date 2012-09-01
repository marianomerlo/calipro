package ar.edu.utn.frba.proyecto.helper;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.proyecto.constants.ConstantsDatatable;
import ar.edu.utn.frba.proyecto.domain.Profile;
import ar.edu.utn.frba.proyecto.domain.Vista;

public class ProfileHelper {

	// TODO: find some way to replace this
	public static List<Vista> getViews(Profile profile) {
		List<Vista> views = new ArrayList<Vista>();

		if (ConstantsDatatable.PERFIL_DESARROLLO.equals(profile.getName())) {
			views.add(new Vista("Analisis", "analisisView.xhtml"));
			views.add(new Vista("Productos", "productosView.xhtml"));

		} else if (ConstantsDatatable.PERFIL_PRODUCCION.equals(profile
				.getName())) {

		} else if (ConstantsDatatable.PERFIL_SUPERVISION.equals(profile
				.getName())) {
			views.add(new Vista("Usuarios", "usuariosView.xhtml"));
		} else if (ConstantsDatatable.PERFIL_CALIDAD.equals(profile.getName())) {

		}

		return views;
	}
}
