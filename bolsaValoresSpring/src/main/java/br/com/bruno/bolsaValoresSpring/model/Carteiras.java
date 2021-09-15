package br.com.bruno.bolsaValoresSpring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Carteiras {
	
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne//Para o "listarPorCodigo", tem que comentar essa linha
	private Usuario usuario;
	private String codigoAtivo;
	private Integer qtd;
	private String precoMedio;
	private String valorInvestido;
	private String statusPosicao;
	
	public Carteiras() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public String getNome() {
		return this.getUsuario().getNome();
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getCodigoAtivo() {
		return codigoAtivo;
	}
	public void setCodigoAtivo(String codigoAtivo) {
		this.codigoAtivo = codigoAtivo;
	}
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

	public String getStatusPosicao() {
		return statusPosicao;
	}

	public void setStatusPosicao(String statusPosicao) {
		this.statusPosicao = statusPosicao;
	}
}
