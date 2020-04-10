package br.com.cast.avaliacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

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
	public List<Curso> findByCategoria(
			@RequestParam(value = "categoria", required = false) Integer categoria_id,
			@RequestParam(value = "id", required = false) Integer id) {
		
		if (categoria_id != null)
			return cursoService.findByCategoria(categoria_id);
		
		else if (id != null)
			return Lists.newArrayList(cursoService.findById(id));
		
		return null;
	}

	
	/**
	 * @see CursoService#salvar(Curso)
	 * @param curso
	 * @return
	 * @throws Exception 
	 */
	@PostMapping
	public Curso salvar(@RequestBody Curso curso) throws Exception {
		
		return cursoService.salvar(curso);
	}
	@PutMapping
	public Curso atualizar(@RequestBody Curso curso) throws Exception {
		
		return cursoService.atualizar(curso);
	}
	
	@DeleteMapping
	public void deletar(@RequestParam("id") Integer id) {
		
		cursoService.deletar(id);
	}
}