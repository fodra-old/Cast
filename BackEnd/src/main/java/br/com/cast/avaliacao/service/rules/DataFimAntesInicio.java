package br.com.cast.avaliacao.service.rules;

import br.com.cast.avaliacao.model.Curso;

public class DataFimAntesInicio implements Regras {

	/*Armazena proima regra*/
	private Regras proximaRegra;
	
	private Curso novoCurso;
	
	public DataFimAntesInicio(Curso curso) {
		
		this(null, curso);
	}

	public DataFimAntesInicio(Regras regras, Curso curso) {
		
		this.proximaRegra = regras;
		this.novoCurso = curso;
	}
	
	@Override
	public void passa() throws Exception {

		if (proximaRegra != null)
			proximaRegra.passa();
		
		if (novoCurso.getFim().compareTo(novoCurso.getInicio()) <= 0)
			throw new Exception("A data fim do curso é menor que a data fim");
	}

}
