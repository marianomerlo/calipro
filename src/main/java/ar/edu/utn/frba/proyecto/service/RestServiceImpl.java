package ar.edu.utn.frba.proyecto.service;

import org.apache.commons.httpclient.HttpMethod;

/**
 * 
 * @author mariano.gonzalez@mulesoft.com
 *
 */
public class RestServiceImpl extends BaseRestServiceImpl implements RestService {

	protected HttpMethod getHttpMethod() {
		this.method = super.getHttpMethod();
		this.method.addRequestHeader("accept", "application/json");
		
		return this.method;
	}

	
	

}
