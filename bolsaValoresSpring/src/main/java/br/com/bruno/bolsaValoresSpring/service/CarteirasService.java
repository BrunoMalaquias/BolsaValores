package br.com.bruno.bolsaValoresSpring.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.bruno.bolsaValoresSpring.enums.StatusPosicao;
import br.com.bruno.bolsaValoresSpring.form.OperacaoForm;
import br.com.bruno.bolsaValoresSpring.interfaces.CarteiraDtoInteface;
import br.com.bruno.bolsaValoresSpring.manager.ManagerAtivo;
import br.com.bruno.bolsaValoresSpring.model.Carteiras;
import br.com.bruno.bolsaValoresSpring.model.dto.CarteiraDto;
import br.com.bruno.bolsaValoresSpring.repository.CarteirasRepository;
import br.com.bruno.bolsaValoresSpring.repository.UsuarioRepository;

@Service
public class CarteirasService {
	
	@Autowired
	CarteirasRepository carteirasRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;

	public ResponseEntity<List<CarteiraDto>> listarCarteira() {
	
		List<CarteiraDtoInteface> findByPorCodigo = carteirasRepository.findByPorCodigo();
		
		List<CarteiraDto> lista = new ArrayList<CarteiraDto>();
		
		ManagerAtivo ma = new ManagerAtivo();
		
		if(!findByPorCodigo.isEmpty()) {
			
			findByPorCodigo.forEach( ativo -> {
				
				CarteiraDto carteiraDto= new CarteiraDto();
				
				BigDecimal diferencaValorCompraEValorAtual = new BigDecimal(ma.retornaValorAtivo(ativo.getCodigo_ativo()).replace(",", ".")).setScale(2, RoundingMode.UP).subtract(new BigDecimal(ativo.getPreco_medio().toString().replace(",", ".")).setScale(2, RoundingMode.UP));
				
				Double lucroPrejuizo = Double.parseDouble(diferencaValorCompraEValorAtual.setScale(2, RoundingMode.UP).toString().replace(",", ".")) * ativo.getQtd();
				
				BigDecimal porcentagem = new BigDecimal(lucroPrejuizo.toString()).setScale(2, RoundingMode.UP).multiply(new BigDecimal("100")).divide(new BigDecimal(ativo.getValor_investido().toString()),2,RoundingMode.UP);
			
				carteiraDto.setUrlAtivo(ma.retornaurlAtivo(ativo.getCodigo_ativo()).get(1));
				carteiraDto.setCodigoAtivo(ativo.getCodigo_ativo());
				carteiraDto.setQtd(ativo.getQtd());
				carteiraDto.setPrecoMedio(ativo.getPreco_medio());
				carteiraDto.setValorAtualAtivo(ma.retornaValorAtivo(ativo.getCodigo_ativo().replace(",", ".")));
				carteiraDto.setValorInvestido(ativo.getValor_investido());
				carteiraDto.setPorcentagem(porcentagem.toString());
				carteiraDto.setLucroPrejuizo(new BigDecimal(lucroPrejuizo.toString().replace(",", ".")).setScale(2, RoundingMode.UP).toString().replace(",", "."));
				
				lista.add(carteiraDto);
				
			});
		}
		return ResponseEntity.ok(lista);
	}

	public void registrarOperacaoNaCarteira(OperacaoForm form, String operacaoCV) {
		
		Carteiras carteira= new Carteiras();
		
		Carteiras ativoNaCarteiras = null;
		
		carteira.setCodigoAtivo(form.getCodigoAtivo());
		carteira.setUsuario(usuarioRepository.findById(form.getUsuario().getId()).get());
		
		if(operacaoCV.equalsIgnoreCase("C")) {
			
			ativoNaCarteiras = carteirasRepository.findByCodigoAtivoAndUsuarioIdAndStatusPosicao(form.getCodigoAtivo(), form.getUsuario().getId(), StatusPosicao.ABERTA.toString());
			
			if(ativoNaCarteiras != null){
				carteira.setId(ativoNaCarteiras.getId());
				carteira.setQtd(Integer.sum(ativoNaCarteiras.getQtd(), form.getQuantidadeAcoes())); 
				BigDecimal valorInvestidoCompra = new BigDecimal(String.valueOf(form.getQuantidadeAcoes() * form.getValorCompraVenda()));
				BigDecimal valor_investidoCarteira = new BigDecimal(ativoNaCarteiras.getValorInvestido());
				BigDecimal valorTotal = valorInvestidoCompra.add(valor_investidoCarteira).setScale(2, RoundingMode.UP);
				carteira.setValorInvestido(valorTotal.toString());
				carteira.setPrecoMedio(valorTotal.divide(new BigDecimal(carteira.getQtd().toString()),2,RoundingMode.UP).toString());
				carteira.setStatusPosicao(StatusPosicao.ABERTA.toString());
				carteirasRepository.save(carteira);
			}else {
				registrarPrimeiraCompraOuUltimaVendaAtivoNaCarteira(form, carteira, "PrimeiraCompra");
			}
					
		}else {
			
			ativoNaCarteiras = carteirasRepository.findByCodigoAtivoAndUsuarioIdAndStatusPosicao(form.getCodigoAtivo(), form.getUsuario().getId(), StatusPosicao.ABERTA.toString());
			
			carteira.setId(ativoNaCarteiras.getId());
			carteira.setQtd(ativoNaCarteiras.getQtd() - form.getQuantidadeAcoes()); 
			carteira.setPrecoMedio(ativoNaCarteiras.getPrecoMedio());
			BigDecimal valorInvestidoCompra = new BigDecimal(Double.parseDouble(ativoNaCarteiras.getPrecoMedio())).multiply(new BigDecimal(carteira.getQtd()));
			carteira.setValorInvestido(valorInvestidoCompra.setScale(2, RoundingMode.UP).toString()); 
			carteira.setStatusPosicao(StatusPosicao.ABERTA.toString());
			carteirasRepository.save(carteira);
			
			registrarPrimeiraCompraOuUltimaVendaAtivoNaCarteira(form, carteira, "UltimaVenda");
		}
				
	}

	private void registrarPrimeiraCompraOuUltimaVendaAtivoNaCarteira(OperacaoForm form, Carteiras carteira, String primeiraOuUltimaCompra) {
		Carteiras novaCarteira = new Carteiras();
		novaCarteira = carteira;
		
		novaCarteira.setQtd(form.getQuantidadeAcoes());
		novaCarteira.setValorInvestido(new BigDecimal(String.valueOf(form.getQuantidadeAcoes() * form.getValorCompraVenda()).toString()).setScale(2, RoundingMode.UP).toString());
		novaCarteira.setPrecoMedio(new BigDecimal(String.valueOf(form.getValorCompraVenda()).toString()).setScale(2, RoundingMode.UP).toString());
		
		if(primeiraOuUltimaCompra.equalsIgnoreCase("UltimaVenda")) {
			novaCarteira.setStatusPosicao(StatusPosicao.CONCLUIDA.toString());
		}else {
			novaCarteira.setStatusPosicao(StatusPosicao.ABERTA.toString());
		}
		
		carteirasRepository.save(novaCarteira);
	}
}
