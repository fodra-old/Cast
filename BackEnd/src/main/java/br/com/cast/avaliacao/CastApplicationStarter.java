package br.com.cast.avaliacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Classe inicia a aplicação Spring.
 * @author jmveloso
 */
@SpringBootApplication
public class CastApplicationStarter extends SpringBootServletInitializer {

	public static void main(String[] args) {
		
		SpringApplication.run(CastApplicationStarter.class);
	}
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CastApplicationStarter.class);
	}
}
