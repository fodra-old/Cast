package br.com.cast.avaliacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cast.avaliacao.model.Categoria;
import br.com.cast.avaliacao.model.Curso;
import br.com.cast.avaliacao.service.CursoService;

@RestController
@RequestMapping("/curso")
public class CursoController {

	@Autowired
	private CursoService cursoService;
	
	/**
	 * @see CursoService#findByCategoria(Categoria)
	 * @param categoria
	 * @return
	 */
	@GetMapping
	public List<Curso> findByCategoria(@ModelAttribute("categoria") Categoria categoria) {
		
		return cursoService.findByCategoria(categoria);
	}
	
	/**
	 * @see CursoService#salvar(Curso)
	 * @param curso
	 * @return
	 */
	@PostMapping
	public Curso salvar(@ModelAttribute("curso") Curso curso) {
		
		return cursoService.salvar(curso);
	}
}