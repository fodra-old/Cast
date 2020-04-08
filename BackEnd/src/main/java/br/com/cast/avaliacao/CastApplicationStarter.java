package br.com.cast.avaliacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.cast.avaliacao.model.Categoria;
import br.com.cast.avaliacao.service.CategoriaService;

/**
 * Classe inicia a aplicação Spring.
 * @author jmveloso
 */
@SpringBootApplication
public class CastApplicationStarter extends SpringBootServletInitializer implements WebMvcConfigurer {

	public static void main(String[] args) {
		
		CategoriaService categoriaService = SpringApplication.run(CastApplicationStarter.class).getBean(CategoriaService.class);
		categoriaService.save(new Categoria(1, "Comportamental"));
		categoriaService.save(new Categoria(2, "Programação"));
		categoriaService.save(new Categoria(3, "Qualidade"));
		categoriaService.save(new Categoria(4, "Processos"));
	}
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CastApplicationStarter.class);
	}
	
	/*
	 * Permite o acesso a todos os endpoints da aplicação
	 */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}
