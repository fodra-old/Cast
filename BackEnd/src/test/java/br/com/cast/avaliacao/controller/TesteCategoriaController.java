package br.com.cast.avaliacao.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
*/

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cast.avaliacao.model.Categoria;
import br.com.cast.avaliacao.service.CategoriaService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@Import(HttpEncodingAutoConfiguration.class)
public class TesteCategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	private MockMvc mockMvc;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private final String COMPORTAMENTAL = "Comportamental";
	private final String PROGRAMACAO = "Programação";
	private final String QUALIDADE = "Qualidade";
	private final String PROCESSOS = "Processos";
	
	@BeforeAll
	public void setup(@Autowired CategoriaController controller) {
	
		mockMvc = MockMvcBuilders
				.standaloneSetup(controller)
				.addFilter((request, response, chain) ->  {
			          response.setCharacterEncoding("UTF-8");
			          chain.doFilter(request, response);
			        }, "/*")
				.build();
		
		categoriaService.save(new Categoria(1, COMPORTAMENTAL));
		categoriaService.save(new Categoria(2, PROGRAMACAO));
		categoriaService.save(new Categoria(3, QUALIDADE));
		categoriaService.save(new Categoria(4, PROCESSOS));
	}
	
	@Test
	public void testaRegistrosPeloEndPoint() throws Exception {
		
		MvcResult result = mockMvc.perform(get("/categoria/all"))
			.andExpect(status().isOk())
			.andReturn();
		
		List<Categoria> categorias = mapper.readValue(
						result.getResponse().getContentAsString(), new TypeReference<List<Categoria>>() {});
		
		assertEquals(4, categorias.size());
		
		assertTrue(categorias.stream().anyMatch(e -> e.getDescricao().equals(COMPORTAMENTAL)));
		assertTrue(categorias.stream().anyMatch(e -> e.getDescricao().equals(PROGRAMACAO)));
		assertTrue(categorias.stream().anyMatch(e -> e.getDescricao().equals(QUALIDADE)));
		assertTrue(categorias.stream().anyMatch(e -> e.getDescricao().equals(PROCESSOS)));
	}
}
