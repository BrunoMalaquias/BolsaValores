package br.com.bruno.bolsaValoresSpring.enums;

public enum  StatusPosicao {
	ABERTA , CONCLUIDA;
	
	
//	ABERTA(1, "Aberta"),
//	CONCLUÍDA(2, "Concluída");
//	
//	private int cod;
//	private String descricao;
//	
//	private StatusPosicao(int cod, String descricao) {
//		this.cod = cod;
//		this.descricao = descricao;
//	}
//
//	public int getCod() {
//		return cod;
//	}
//
//	public String getDescricao() {
//		return descricao;
//	}
//	
//	public static StatusPosicao toEnum(Integer cod) {
//		
//		if(cod == null) {
//			return null;
//		}
//		
//		for(StatusPosicao x : StatusPosicao.values()) {
//			if(cod.equals(x.getCod())) {
//				return x;
//			}
//		}
//		throw new IllegalArgumentException("Id inválido: " + cod);
//	}
}
