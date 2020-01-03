package com.technopreneur.moneytransfer.model;

public class Response {
	private Status status;
    private String message;
    private Object data;
     
   
	public Response(Status status) {
		super();
		this.status = status;
	}

	public Response(Status status, String message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public Response(Status status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public Response(Object data) {
		this.data = data;
	}

	public Response(Status status, Object data) {
		super();
		this.status = status;
		this.data = data;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
     

}
