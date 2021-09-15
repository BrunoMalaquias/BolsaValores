package br.com.bruno.bolsaValoresSpring.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.bruno.bolsaValoresSpring.interfaces.OperacaoDtoInteface;

@Entity
public class Operacao{

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Double valorCompraVenda;
	private String tipo;
	private Integer quantidadeAcoes;
	private LocalDate data;
	private String status;;
	private String valorInvestido;
	@OneToOne
	private Usuario usuario;
	@OneToOne
	private Ativo ativo;
	
//	@OneToOne
//	private Carteira carteira;
//	
	public Operacao() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Ativo getAtivo() {
		return ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}
	
	public String getValorInvestido() {
		return valorInvestido;
	}

	public void setValorInvestido(String valorInvestido) {
		this.valorInvestido = valorInvestido;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
