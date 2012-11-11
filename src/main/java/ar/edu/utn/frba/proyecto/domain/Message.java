package ar.edu.utn.frba.proyecto.domain;

import ar.edu.utn.frba.proyecto.domain.enumType.StatusType;

public class Message {

	private String message;
	
	private StatusType status;
	
	public Message(){
		super();
	}
	
	public Message(String message, StatusType status){
		super();
		this.message = message;
		this.status = status;
	}

	/**
	 * @return the status
	 */
	public StatusType getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(StatusType status) {
		this.status = status;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
