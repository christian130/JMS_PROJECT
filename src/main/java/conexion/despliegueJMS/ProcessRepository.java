package conexion.despliegueJMS;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProcessRepository extends MongoRepository<Process, String> {
	public Process findById(String id);
}
