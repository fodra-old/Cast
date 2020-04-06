package br.com.cast.avaliacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cast.avaliacao.model.Categoria;
import br.com.cast.avaliacao.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	/**
	 * @see CategoriaService#findById(Integer)
	 * @param id
	 * @return
	 */
	@GetMapping()
	public Categoria findById(@RequestParam("id") Integer id) {
		
		return categoriaService.findById(id);
	}
	
	/**
	 * @see CategoriaService#findAll()
	 * @return
	 */
	@GetMapping("/all")
	public List<Categoria> findAll() {
		
		return categoriaService.findAll();
	}
}