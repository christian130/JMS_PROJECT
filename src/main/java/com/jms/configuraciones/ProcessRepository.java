package com.jms.configuraciones;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jms.hilos.Process;

public interface ProcessRepository extends MongoRepository<Process, String> {
	public Process findById(String id);
}
