package br.com.bruno.bolsaValoresSpring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.bruno.bolsaValoresSpring.interfaces.CarteiraDtoInteface;
import br.com.bruno.bolsaValoresSpring.model.Carteiras;

public interface CarteirasRepository extends JpaRepository<Carteiras, Long>{
	
	@Query(value ="SELECT codigo_ativo as codigo_ativo ,valor_investido, preco_medio , qtd as qtd , usuario_id as usuario FROM Carteiras c where usuario_id = 1 GROUP BY codigo_ativo", nativeQuery = true )
	List<CarteiraDtoInteface> findByPorCodigo();//@Param("id") Integer id
	
	@Query("SELECT c.codigoAtivo FROM Carteiras c where c.codigoAtivo = :codigo and c.id = :id" )
	Optional<String> findByCodigoAtivoss(@Param("codigo") String codigo, @Param("id") Integer id);

	Optional<Carteiras> findByCodigoAtivo(String codigo);
	
	Carteiras findByCodigoAtivoAndUsuarioId(String codigo, Integer usuario_id);
	
	Carteiras findByCodigoAtivoAndUsuarioIdAndStatusPosicao(String codigo, Integer usuario_id, String statusPosicao);
	
}
