package br.com.bruno.bolsaValoresSpring.form;

import br.com.bruno.bolsaValoresSpring.model.Usuario;

public class OperacaoForm {
	
	private Double valorCompraVenda;
	private String tipo;
	private Integer quantidadeAcoes;
	private String codigoAtivo;
	private Usuario usuario;
	
	public OperacaoForm() {
	}

	public Double getValorCompraVenda() {
		return valorCompraVenda;
	}

	public void setValorCompraVenda(Double valorCompraVenda) {
		this.valorCompraVenda = valorCompraVenda;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getQuantidadeAcoes() {
		return quantidadeAcoes;
	}

	public void setQuantidadeAcoes(Integer quantidadeAcoes) {
		this.quantidadeAcoes = quantidadeAcoes;
	}

	public String getCodigoAtivo() {
		return codigoAtivo;
	}

	public void setCodigoAtivo(String codigoAtivo) {
		this.codigoAtivo = codigoAtivo;
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	
}
