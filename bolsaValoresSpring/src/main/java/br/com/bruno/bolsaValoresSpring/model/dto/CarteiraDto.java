package br.com.bruno.bolsaValoresSpring.model.dto;

import br.com.bruno.bolsaValoresSpring.model.Carteiras;

public class CarteiraDto{

	private String valorAtualAtivo;
	private String porcentagem;
	private String lucroPrejuizo;
	private String urlAtivo;
	private String codigoAtivo;
	private Integer qtd;
	private String precoMedio;
	private String valorInvestido;
	
	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}

	public String getPrecoMedio() {
		return precoMedio;
	}

	public void setPrecoMedio(String precoMedio) {
		this.precoMedio = precoMedio;
	}

	public String getValorInvestido() {
		return valorInvestido;
	}

	public void setValorInvestido(String valorInvestido) {
		this.valorInvestido = valorInvestido;
	}

	public CarteiraDto() {
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

	public String getCodigoAtivo() {
		return codigoAtivo;
	}

	public void setCodigoAtivo(String codigoAtivo) {
		this.codigoAtivo = codigoAtivo;
	}
	
	

}
