package com.technopreneur.moneytransfer.model;

import com.google.gson.JsonElement;

public class Response {
	private Status status;
    private String message;
    private JsonElement data;
     
    public Response(Status status) {
    }
    public Response(Status status, String message) {
    }
    public Response(Status status, JsonElement data) {
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
	public JsonElement getData() {
		return data;
	}
	public void setData(JsonElement data) {
		this.data = data;
	}
     

}
