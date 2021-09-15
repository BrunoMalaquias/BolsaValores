package br.com.bruno.bolsaValoresSpring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.bruno.bolsaValoresSpring.interfaces.OperacaoDtoInteface;
import br.com.bruno.bolsaValoresSpring.model.Operacao;

public interface OperacaoRepository  extends JpaRepository<Operacao, Integer>{

	
	
	//@Query(value = "SELECT codigo_ativo, sum(valor_compra_venda) AS total FROM OPERACAO GROUP BY codigo_ativo", nativeQuery = true)
	//@Query(value = "SELECT  codigo_ativo, sum(valor_compra_venda) as TotalCompraVenda FROM OPERACAO GROUP BY codigo_ativo", nativeQuery = true)
	//@Query(value ="SELECT  ativo_id, sum(QUANTIDADE_ACOES) as QUANTIDADE_TOTAL_ACOES , sum(valor_investido) as TOTAL_INVESTIDO , STATUS FROM OPERACAO where status = 'EM ANDAMENTO' GROUP BY ativo_id", nativeQuery = true )
	@Query(value ="SELECT ativo_id as ativo_id, sum(quantidade_acoes) as quantidade_acoes, sum(valor_investido) as valor_investido FROM Operacao p GROUP BY ativo_id", nativeQuery = true )
	List<OperacaoDtoInteface> findByPorCodigo();
	
//	@Query("SELECT p.quantidadeAcoes, p.valorCompraVenda from Operacao p")
	//@Query(value ="SELECT  ativo_id, sum(QUANTIDADE_ACOES) as QUANTIDADE_TOTAL_ACOES FROM OPERACAO GROUP BY ativo_id", nativeQuery = true)
//	List<OperacaoDtoInteface> teste();
	
	@Query(value ="SELECT ativo_id as ativo_id, sum(quantidade_acoes) as quantidade_acoes, sum(valor_investido) as valor_investido FROM Operacao p GROUP BY ativo_id", nativeQuery = true )
	List<OperacaoDtoInteface> teste2();
	
	Optional<List<Operacao>> findByAtivoCodigo(String codigoAtivo);
	//SELECT  codigo_ativo, sum(valor_compra_venda) as TotalCompraVenda FROM OPERACAO GROUP BY codigo_ativo;

	Optional<Operacao> findByUsuarioNome(String nome);
}

