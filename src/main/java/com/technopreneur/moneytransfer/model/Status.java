package com.technopreneur.moneytransfer.model;

public enum Status {
	SUCCESS("Success"), ERROR("Error");

	private String status;

	private Status(String status) {
		this.setStatus(status);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
