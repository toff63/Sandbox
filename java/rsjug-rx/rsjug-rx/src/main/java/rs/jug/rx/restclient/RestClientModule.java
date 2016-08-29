package rs.jug.rx.restclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestClientModule {

	@Bean
	public GturnquistQuotersGateway restGateway(){
		return new GturnquistQuotersGateway();
	}
	
	@Bean
	public JsonReader jsonReader(){
		return new JsonReader();
	}
}
