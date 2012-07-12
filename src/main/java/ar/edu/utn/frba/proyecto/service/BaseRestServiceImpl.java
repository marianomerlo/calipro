package ar.edu.utn.frba.proyecto.service;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;


public class BaseRestServiceImpl implements RestService {

	protected String host;
	protected int port;
	
	protected String REST_PATH;
	
	
	protected HttpClient client;
	protected HttpMethod method;
	
	
	@Override
	public String getData() {
		return this.getBody(this.getHttpMethod());
	}

	@Override
	public String getData(Map<String,String> params) {
		NameValuePair[] nameValuePairs = new NameValuePair[params.size()];
		int index = 0;
		for ( Entry<String, String> param : params.entrySet() ){
			nameValuePairs[index] = new NameValuePair(param.getKey(), param.getValue());
			index++;
		}
		this.getHttpMethod().setQueryString(nameValuePairs);
		
		return this.getData();
	}

	@Override
	public String getMuleURL() {
		return this.port != 0 ? String.format("http://%s:%d", this.host, this.port) : String.format("http://%s", this.host);
	}
	
	protected String getBody(HttpMethod method) {
		int status;
		try {
			status = client.executeMethod(method);
			if (HttpStatus.SC_OK != status) {
				throw new IllegalStateException("Failed : HTTP error code : " + status);
			}
			
			return method.getResponseBodyAsString();
		} catch (Exception e) {
			throw new IllegalStateException("Failed to invoke http client");
		}
	}
	
	protected HttpMethod getHttpMethod() {
		if (method == null) {
			this.client = new HttpClient();
			this.method = new GetMethod(this.getMuleURL() + "/" + REST_PATH);
		}
		
		return this.method;
	}
	

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public void setREST_PATH(String path) {
		this.REST_PATH = path;
	}


}
