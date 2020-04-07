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

		if (proximaRegra != null)
			proximaRegra.passa();

		String mensagem = "Curso já cadastrado no periodo";
		
		if (dataCurso.getInicio().before(dataCursoNovo.getInicio())
				&& dataCurso.getFim().after(dataCursoNovo.getFim()))
			throw new Exception(mensagem);
		
		if (dataCurso.getInicio().before(dataCursoNovo.getInicio()) 
				&& dataCurso.getFim().after(dataCursoNovo.getInicio())) 
			throw new Exception(mensagem);
		
		if (dataCurso.getInicio().before(dataCursoNovo.getFim()) 
				&& dataCurso.getFim().after(dataCursoNovo.getFim())) 
			throw new Exception(mensagem);
		
		if (dataCurso.getInicio().after(dataCursoNovo.getInicio())
				&& dataCurso.getFim().before(dataCursoNovo.getFim()))
			throw new Exception(mensagem);
	}
}