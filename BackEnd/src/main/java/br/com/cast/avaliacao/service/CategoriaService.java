package br.com.cast.avaliacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.avaliacao.model.Categoria;
import br.com.cast.avaliacao.model.QCategoria;
import br.com.cast.avaliacao.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	/**
	 * Retorna a categoria de acordo com o ID
	 * @param id
	 * @return
	 */
	public Categoria findById(Integer id) {
		return categoriaRepository.getOne(id);
	}
	/**
	 * Retorna todas as categorias cadastradas
	 * @return
	 */
	public List<Categoria> findAll() {

		return categoriaRepository.findAll();
	}
	/**
	 * Salva registro categoria no banco de dados
	 * @param categoria
	 * @return
	 */
	public Object save(Categoria categoria) {

		return categoriaRepository.save(categoria);
	}
	/**
	 * Retorna a categoria com o código informado no parametro
	 * @param categoria
	 * @return
	 */
	public Categoria findByCodigo(Integer codigo) {
		
		return categoriaRepository.findOne(QCategoria.categoria.codigo.eq(codigo)).get();
	}

}