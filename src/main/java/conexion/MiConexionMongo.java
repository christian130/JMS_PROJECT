package conexion;

import java.net.UnknownHostException;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;

@Configuration
@EnableMongoRepositories("conexion")
public class MiConexionMongo extends AbstractMongoConfiguration {
	
	private static Mongo mongo=null;

	@SuppressWarnings("deprecation")
	@Scope("singleton")
	public @Bean Mongo mongo() throws UnknownHostException {
		if( mongo == null ){
			mongo=new Mongo("localhost");
			
			
			mongo.getMongoOptions().setThreadsAllowedToBlockForConnectionMultiplier(10);
			mongo.getMongoOptions().setConnectTimeout(2000);
			mongo.getMongoOptions().setMaxWaitTime(2000);
			mongo.getMongoOptions().setAutoConnectRetry(true);
			mongo.getMongoOptions().setSocketKeepAlive(true);
			mongo.getMongoOptions().setSocketTimeout(2000);
			//System.out.println( "status mongo "+ mongo.getReplicaSetStatus().getName() );
		}
		return mongo;
	}
	
	@Bean
	public  MongoTemplate mongoTemplate() throws UnknownHostException {
		MongoTemplate mongoTemplate = new MongoTemplate(mongo(), "fieldvision_etl");  
		Converter[] ca = new Converter[] {};
		CustomConversions cc = new CustomConversions(Arrays.asList(ca));
		MappingMongoConverter mmc = (MappingMongoConverter) mongoTemplate.getConverter();
		mmc.setCustomConversions(cc);
		mmc.afterPropertiesSet();
		return mongoTemplate;
	}

	@Override
	 protected String getMappingBasePackage() {
	  return "conexion";
	 }

	@Override
	protected String getDatabaseName() {
		//return "tracksporter";
		return "fieldvision_etl";
	}

	




}
