package ar.edu.utn.frba.proyecto.service;

import java.util.Map;


/**
 * 
 * @author mariano.gonzalez@mulesoft.com
 *
 */
public interface RestService {

	public String getData();
	
	public String getData( Map<String, String> params );
	
	public String getMuleURL();
	
}
