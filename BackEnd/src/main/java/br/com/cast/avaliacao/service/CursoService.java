package br.com.cast.avaliacao.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.google.common.collect.Lists;

import br.com.cast.avaliacao.model.Categoria;
import br.com.cast.avaliacao.model.Curso;
import br.com.cast.avaliacao.model.QCurso;
import br.com.cast.avaliacao.repository.CursoRepository;
import br.com.cast.avaliacao.service.rules.DataEntreInicioFim;
import br.com.cast.avaliacao.service.rules.DataFimAntesInicio;
import br.com.cast.avaliacao.service.rules.DataMenorQueHoje;
import br.com.cast.avaliacao.service.rules.Regras;

@Service
public class CursoService {

	@Autowired
	private CursoRepository cursoRepository;
	
	/**
	 * Retorna lista de cursos da categoria informada no parametro
	 * @param categoria_id
	 * @return
	 */
	public List<Curso> findByCategoria(Integer categoria_id) {
	
		return Lists.newArrayList(cursoRepository.findAll(QCurso.curso.categoria.id.eq(categoria_id)));
	}
	
	/**
	 * Salva registro de curso informado no parametro
	 * @param curso
	 * @return
	 * @throws Exception 
	 */
	public Curso salvar(@ModelAttribute("curso") Curso curso) throws Exception {
		
		List<Curso> cursosCadastrados = cursoRepository.findAll();
		Regras regras = null;
		
		for (Curso c : cursosCadastrados) {
			
			Regras newRegra = new DataEntreInicioFim(regras, c, curso);
			regras = newRegra;
		}
		regras = new DataMenorQueHoje(regras, curso);
		regras = new DataFimAntesInicio(regras, curso);
		
		regras.passa();
		
		cursoRepository.save(curso);
		return curso;
	}
}