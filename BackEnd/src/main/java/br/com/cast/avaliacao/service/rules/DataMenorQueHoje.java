package br.com.cast.avaliacao.service.rules;

import java.util.Date;

import br.com.cast.avaliacao.model.Curso;

/**
 * Classe verifica se a data informada esta no passado
 * @author jmveloso
 *
 */
public class DataMenorQueHoje implements Regras {

	/*Armazena proima regra*/
	private Regras proximaRegra;
	
	private Date sourceData;
	
	public DataMenorQueHoje(Curso curso) {
		
		this(null, curso);
	}

	public DataMenorQueHoje(Regras regras, Curso curso) {
		
		this.proximaRegra = regras;
		this.sourceData = Datas.removeTempo(curso.getInicio());
	}
	
	@Override
	public void passa() throws Exception {

		if (proximaRegra != null)
			proximaRegra.passa();
		
		Date hoje = Datas.removeTempo(new Date());
		
		if (sourceData.before(hoje)) {
			 throw new Exception("A data informada esta no passado");
		}
	}
}
