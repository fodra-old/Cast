package br.com.cast.avaliacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import br.com.cast.avaliacao.model.Categoria;
import br.com.cast.avaliacao.model.Curso;
import br.com.cast.avaliacao.model.QCurso;
import br.com.cast.avaliacao.repository.CursoRepository;

@Service
public class CursoService {

	@Autowired
	private CursoRepository cursoRepository;
	
	/**
	 * Retorna lista de cursos da categoria informada no parametro
	 * @param categoria
	 * @return
	 */
	public List<Curso> findByCategoria(Categoria categoria) {
	
		return Lists.newArrayList(cursoRepository.findAll(QCurso.curso.categoria.eq(categoria)));
	}
	
	/**
	 * Salva registro de curso informado no parametro
	 * @param curso
	 * @return
	 */
	public Curso salvar(Curso curso) {
		
		cursoRepository.save(curso);
		return curso;
	}
}