package br.com.cast.avaliacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.google.common.collect.Lists;

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
		
		verificarRegras(curso);
		
		cursoRepository.save(curso);
		return curso;
	}

	/**
	 * 
	 * @param id
	 */
	public void deletar(Integer id) {
		
		cursoRepository.deleteById(id);
	}

	public Curso findById(Integer id) {

		return cursoRepository.findById(id).get();
	}

	public Curso atualizar(Curso curso) throws Exception {

		verificarRegras(curso);
		
		Curso c = cursoRepository.findById(curso.getId()).get();
		c.setAssunto(curso.getAssunto());
		c.setCategoria(curso.getCategoria());
		c.setFim(curso.getFim());
		c.setInicio(curso.getInicio());
		c.setQuantidadeAlunos(curso.getQuantidadeAlunos());
		return cursoRepository.save(c);
	}
	
	private void verificarRegras(Curso curso) throws Exception {
		
		List<Curso> cursosCadastrados = cursoRepository.findAll();
		Regras regras = null;
		
		for (Curso c : cursosCadastrados) {
			
			Regras newRegra = new DataEntreInicioFim(regras, c, curso);
			regras = newRegra;
		}
		regras = new DataMenorQueHoje(regras, curso);
		regras = new DataFimAntesInicio(regras, curso);
		
		regras.passa();
	}
}