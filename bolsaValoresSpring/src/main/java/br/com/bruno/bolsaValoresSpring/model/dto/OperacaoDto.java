package br.com.bruno.bolsaValoresSpring.model.dto;

import br.com.bruno.bolsaValoresSpring.model.Carteiras;

public class OperacaoDto extends Carteiras{

	private String nomeAtivo;
//	private String codigoAtivo;
//	private String qtd;
//	private String precoMedio;
	private String valorAtualAtivo;
	private String porcentagem;
	private String lucroPrejuizo;
	private String urlAtivo;
	
	public OperacaoDto() {
	}


	
	public String getValorAtualAtivo() {
		return valorAtualAtivo;
	}

	public void setValorAtualAtivo(String valorAtualAtivo) {
		this.valorAtualAtivo = valorAtualAtivo;
	}



	public String getUrlAtivo() {
		return urlAtivo;
	}

	public void setUrlAtivo(String urlAtivo) {
		this.urlAtivo = urlAtivo;
	}

	public String getLucroPrejuizo() {
		return lucroPrejuizo;
	}

	public void setLucroPrejuizo(String lucroPrejuizo) {
		this.lucroPrejuizo = lucroPrejuizo;
	}

	public String getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(String porcentagem) {
		this.porcentagem = porcentagem;
	}

	public void setNomeAtivo(String nomeAtivo) {
		this.nomeAtivo = nomeAtivo;
	}

//	public void setQtd(String qtd) {
//		this.qtd = qtd;
//	}
//	
//	public String getNomeAtivo() {
//		return nomeAtivo;
//	}
//
//	public String getQtd() {
//		return qtd;
//	}
//	public String getPrecoMedio() {
//	return precoMedio;
//	}
//
//	public void setPrecoMedio(String precoMedio) {
//		this.precoMedio = precoMedio;
//	}
//	public String getCodigoAtivo() {
//	return codigoAtivo;
//	}
//	public void setCodigoAtivo(String codigoAtivo) {
//	this.codigoAtivo = codigoAtivo;
//	}

}
