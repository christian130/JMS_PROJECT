package com.jms.hilos;

import java.io.Serializable;

import org.springframework.data.annotation.Id;


public class Process implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 2353802156327987818L;
@Id
private String id;
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
private String status;
public Process(){}

public Process changeStatus(String _id, String status){
	this.id=_id;
	this.status=status;
	return this;
	
}
}
