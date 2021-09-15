package br.com.bruno.bolsaValoresSpring.model.dto;

public class AtivoListaDto {
	
	private String nome;
	private String codigo;
	private String valor;
	private String qtdAcoes;
	private String precoCompra;
	private String precoAtual;
	private String porcentagem;
	private String lucroPrejuizo;
	
	public AtivoListaDto() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getQtdAcoes() {
		return qtdAcoes;
	}

	public void setQtdAcoes(String qtdAcoes) {
		this.qtdAcoes = qtdAcoes;
	}

	public String getPrecoCompra() {
		return precoCompra;
	}

	public void setPrecoCompra(String precoCompra) {
		this.precoCompra = precoCompra;
	}

	public String getPrecoAtual() {
		return precoAtual;
	}

	public void setPrecoAtual(String precoAtual) {
		this.precoAtual = precoAtual;
	}

	public String getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(String porcentagem) {
		this.porcentagem = porcentagem;
	}

	public String getLucroPrejuizo() {
		return lucroPrejuizo;
	}

	public void setLucroPrejuizo(String lucroPrejuizo) {
		this.lucroPrejuizo = lucroPrejuizo;
	}
	
}
