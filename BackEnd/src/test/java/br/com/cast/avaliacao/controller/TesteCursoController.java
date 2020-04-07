package br.com.cast.avaliacao.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cast.avaliacao.model.Categoria;
import br.com.cast.avaliacao.model.Curso;
import br.com.cast.avaliacao.service.CategoriaService;
import br.com.cast.avaliacao.service.rules.Datas;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@Import(HttpEncodingAutoConfiguration.class)
public class TesteCursoController {

	@Autowired
	private CategoriaService categoriaService;

	private ObjectMapper mapper = new ObjectMapper();

	private final String COMPORTAMENTAL = "Comportamental";
	private final String PROGRAMACAO = "Programação";
	private final String QUALIDADE = "Qualidade";
	private final String PROCESSOS = "Processos";

	private final String JAVA = "Java";
	private final String DOTNET = ".NET";
	private final String PYTHON = "Python";

	private MockMvc mockMvc;

	@BeforeAll
	public void setup(@Autowired CursoController controller) {

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
	public void testaCadastrarCursos() throws Exception {
		
		Categoria categoriaProg = categoriaService.findByCodigo(2);
		Date data = Datas.removeTempo(new Date());
		for (String s : Lists.list(JAVA, DOTNET, PYTHON)) {
			
			Curso curso = new Curso();
			curso.setAssunto(s);
			curso.setCategoria(categoriaProg);
			curso.setInicio(data = Datas.adicionaDias(data, 1));
			curso.setFim(data = Datas.adicionaDias(data, 10));
			curso.setQuantidadeAlunos(20);
			
			MvcResult result =
					mockMvc.perform(post("/curso")
							.content(mapper.writeValueAsString(curso))
							.contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andReturn();
			Curso c = mapper.readValue(
					result.getResponse().getContentAsString(), new TypeReference<Curso>() {});
			
			assertNotNull(c.getId());
		}
	}
	
	@Test
	public void testaCursosCadastrados() 
			throws Exception {

		testaCadastrarCursos();
		
		Categoria categoria = categoriaService.findByCodigo(2);

		MvcResult result = mockMvc.perform(get("/curso?categoria=" + categoria.getId()))
				.andExpect(status().isOk())
				.andReturn();

		List<Curso> cursos = mapper.readValue(
				result.getResponse().getContentAsString(), new TypeReference<List<Curso>>() {});

		assertEquals(3, cursos.size());
		for (String s : Lists.list(JAVA, DOTNET, PYTHON))
			assertTrue(cursos.stream().anyMatch(e -> e.getAssunto().equals(s)));
	}

	private void cadastraCursoReferencia() throws Exception {
		
		Categoria categoria = categoriaService.findByCodigo(4);
		
		Date data = Datas.removeTempo(new Date());
		
		Curso curso = new Curso();
		curso.setAssunto("Curso Referencia de Datas");
		curso.setCategoria(categoria);
		curso.setInicio(data = Datas.adicionaDias(data, 1));
		curso.setFim(data = Datas.adicionaDias(data, 10));
		
		MvcResult result =
				mockMvc.perform(post("/curso")
						.content(mapper.writeValueAsString(curso))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
		Curso c = mapper.readValue(
				result.getResponse().getContentAsString(), new TypeReference<Curso>() {});
		
		assertNotNull(c.getId());
	}

	@Test
	public void testaDataInicioEntreCurso() throws Exception {
		
		cadastraCursoReferencia();
		Categoria categoria = categoriaService.findByCodigo(4);
		
		Date data = Datas.removeTempo(new Date());
		
		Curso curso = new Curso();
		curso.setAssunto("Curso com Data inicio entre");
		curso.setCategoria(categoria);
		curso.setInicio(data = Datas.adicionaDias(data, 3));
		curso.setFim(data = Datas.adicionaDias(data, 30));
		
		assertThrows(Exception.class, () -> {
			mockMvc.perform(post("/curso")
					.content(mapper.writeValueAsString(curso))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
		});
	}

	@Test
	public void testaDataFimEntreCurso() throws Exception {
		
		cadastraCursoReferencia();
		Categoria categoria = categoriaService.findByCodigo(4);
		
		Date data = Datas.removeTempo(new Date());
		
		Curso curso = new Curso();
		curso.setAssunto("Curso com Data inicio entre");
		curso.setCategoria(categoria);
		curso.setInicio(data = Datas.adicionaDias(data, -2));
		curso.setFim(data = Datas.adicionaDias(data, 5));
		
		assertThrows(Exception.class, () -> {
			mockMvc.perform(post("/curso")
					.content(mapper.writeValueAsString(curso))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
		});
	}

	@Test
	public void testaDataInicioAntesDataFimDepois() throws Exception {
		
		cadastraCursoReferencia();
		Categoria categoria = categoriaService.findByCodigo(4);
		
		Date data = Datas.removeTempo(new Date());
		
		Curso curso = new Curso();
		curso.setAssunto("Curso com Data inicio entre");
		curso.setCategoria(categoria);
		curso.setInicio(data = Datas.adicionaDias(data, -1));
		curso.setFim(data = Datas.adicionaDias(data, 12));
		
		assertThrows(Exception.class, () -> {
			mockMvc.perform(post("/curso")
					.content(mapper.writeValueAsString(curso))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
		});
	}
	
	@Test
	public void testaDataInicioDepoisDataFimAntes() throws Exception {
		
		cadastraCursoReferencia();
		Categoria categoria = categoriaService.findByCodigo(4);
		
		Date data = Datas.removeTempo(new Date());
		
		Curso curso = new Curso();
		curso.setAssunto("Curso com Data inicio entre");
		curso.setCategoria(categoria);
		curso.setInicio(data = Datas.adicionaDias(data, 2));
		curso.setFim(data = Datas.adicionaDias(data, 7));
		
		assertThrows(Exception.class, () -> {
			mockMvc.perform(post("/curso")
					.content(mapper.writeValueAsString(curso))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
		});
	}
}