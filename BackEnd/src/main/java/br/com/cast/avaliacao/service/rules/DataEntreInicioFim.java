package br.com.cast.avaliacao.service.rules;

import br.com.cast.avaliacao.model.ICursoDatas;

public class DataEntreInicioFim implements Regras {

	private Regras proximaRegra;
	
	private ICursoDatas dataCurso;
	private ICursoDatas dataCursoNovo;
	
	public DataEntreInicioFim(ICursoDatas datasCurso, ICursoDatas datasCursoNovo) {
		
		this(null, datasCurso, datasCursoNovo);
	}
	
	public DataEntreInicioFim(Regras regras, ICursoDatas datasCurso, ICursoDatas dataCursoNovo) {
		
		this.proximaRegra = regras;
		this.dataCurso = datasCurso;
		this.dataCursoNovo = dataCursoNovo;
	}

	@Override
	public void passa() throws Exception {

		if(dataCurso.getId().equals(dataCursoNovo.getId()))
			return;
		
		if (proximaRegra != null)
			proximaRegra.passa();

		String mensagem = "Curso já cadastrado no periodo";
		
		if (dataCurso.getInicio().compareTo(dataCursoNovo.getInicio()) <= 0
				&& dataCurso.getFim().compareTo(dataCursoNovo.getFim()) >= 0)
			throw new Exception(mensagem);
		
		if (dataCurso.getInicio().compareTo(dataCursoNovo.getInicio()) >= 0 
				&& dataCurso.getFim().compareTo(dataCursoNovo.getInicio()) <= 0) 
			throw new Exception(mensagem);
		
		if (dataCurso.getInicio().compareTo(dataCursoNovo.getFim()) <= 0 
				&& dataCurso.getFim().compareTo(dataCursoNovo.getFim()) >= 0) 
			throw new Exception(mensagem);
		
		if (dataCurso.getInicio().compareTo(dataCursoNovo.getInicio()) >= 0
				&& dataCurso.getFim().compareTo(dataCursoNovo.getFim()) <= 0)
			throw new Exception(mensagem);
	}
}