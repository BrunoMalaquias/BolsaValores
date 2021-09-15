package br.com.bruno.bolsaValoresSpring.model.dto;

public class OperacaoTeste {

	
	private Integer qtd;
	


	public OperacaoTeste(OperacaoTeste order) {
		this.qtd = order.getQtd();
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}
	
	public Integer getQtd() {
		return qtd;
	}



}
