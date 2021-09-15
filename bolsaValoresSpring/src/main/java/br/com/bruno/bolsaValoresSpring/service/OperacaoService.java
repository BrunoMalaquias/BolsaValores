package br.com.bruno.bolsaValoresSpring.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.bruno.bolsaValoresSpring.enums.StatusPosicao;
import br.com.bruno.bolsaValoresSpring.form.OperacaoForm;
import br.com.bruno.bolsaValoresSpring.interfaces.OperacaoDtoInteface;
import br.com.bruno.bolsaValoresSpring.manager.ManagerAtivo;
import br.com.bruno.bolsaValoresSpring.model.Ativo;
import br.com.bruno.bolsaValoresSpring.model.Carteiras;
import br.com.bruno.bolsaValoresSpring.model.Operacao;
import br.com.bruno.bolsaValoresSpring.model.Usuario;
import br.com.bruno.bolsaValoresSpring.model.dto.OperacaoDto;
import br.com.bruno.bolsaValoresSpring.repository.AtivoRepository;
import br.com.bruno.bolsaValoresSpring.repository.CarteirasRepository;
import br.com.bruno.bolsaValoresSpring.repository.OperacaoRepository;
import br.com.bruno.bolsaValoresSpring.repository.UsuarioRepository;

@Service
public class OperacaoService {
	
	@Autowired
	OperacaoRepository operacaoRepository;

	@Autowired
	AtivoRepository ativoRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	CarteirasRepository carteirasRepository;
	
	@Autowired
	CarteirasService carteirasService;
	
	public ResponseEntity<Operacao> comprar(OperacaoForm form) {

		ResponseEntity<Operacao> retorno = null;
				
		Operacao operacao = new Operacao();
		operacao.setTipo(form.getTipo());
		operacao.setData(LocalDate.now());
		operacao.setQuantidadeAcoes(form.getQuantidadeAcoes());
		operacao.setValorCompraVenda(form.getValorCompraVenda());
		operacao.setValorInvestido(new BigDecimal(String.valueOf(operacao.getValorCompraVenda() * operacao.getQuantidadeAcoes())).setScale(2, RoundingMode.UP).toString());
		operacao.setStatus(StatusPosicao.ABERTA.toString());
		
		Optional<Ativo> optionalAtivo = ativoRepository.findByCodigo(form.getCodigoAtivo());
		
		if (optionalAtivo.isPresent()){
			
			operacao.setAtivo(optionalAtivo.get());
			Optional<Usuario> usuario = usuarioRepository.findById(form.getUsuario().getId());
			operacao.setUsuario(usuario.get());
			operacaoRepository.save(operacao);
			
			retorno = ResponseEntity.ok(operacao); 
			
		}else {
			
			ManagerAtivo ma = new ManagerAtivo();
			
			Ativo ativo = new Ativo();
			ativo.setCodigo(form.getCodigoAtivo());
			ativo.setNome(ma.retornaurlAtivo(form.getCodigoAtivo()).get(0));
			ativo.setValor(ma.retornaValorAtivo(form.getCodigoAtivo()));
			ativoRepository.save(ativo);
			
			operacao.setUsuario(usuarioRepository.findById(form.getUsuario().getId()).get());
			operacao.setAtivo(ativo);
			operacaoRepository.save(operacao);
			
			retorno = ResponseEntity.ok(operacao);
		}
		
		carteirasService.registrarOperacaoNaCarteira(form,"C");
		
		return retorno;
	}

	public ResponseEntity<Operacao> vender(OperacaoForm form) {
		
		Operacao operacao = new Operacao();
		operacao.setTipo(form.getTipo());
		operacao.setData(LocalDate.now());
		operacao.setQuantidadeAcoes(form.getQuantidadeAcoes());
		operacao.setValorCompraVenda(form.getValorCompraVenda());
		operacao.setValorInvestido(new BigDecimal(String.valueOf(operacao.getValorCompraVenda() * operacao.getQuantidadeAcoes())).setScale(2, RoundingMode.UP).toString());
		operacao.setStatus("FINALIZADA");
		Optional<Ativo> optionalAtivo = ativoRepository.findByCodigo(form.getCodigoAtivo());
		
		if (optionalAtivo.isPresent()){
			operacao.setAtivo(optionalAtivo.get());
			Optional<Usuario> usuario = usuarioRepository.findById(form.getUsuario().getId());
			operacao.setUsuario(usuario.get());
			operacaoRepository.save(operacao);
			
		}else {
			
			ManagerAtivo ma = new ManagerAtivo();
			
			Ativo ativo = new Ativo();
			ativo.setCodigo(form.getCodigoAtivo());
			ativo.setNome(ma.retornaurlAtivo(form.getCodigoAtivo()).get(0));
			ativo.setValor(ma.retornaValorAtivo(form.getCodigoAtivo()));
			ativoRepository.save(ativo);
			
			operacao.setUsuario(usuarioRepository.findById(form.getUsuario().getId()).get());
			operacao.setAtivo(ativo);
			operacaoRepository.save(operacao);
		}
		
		carteirasService.registrarOperacaoNaCarteira(form,"V");
		
		return ResponseEntity.ok(operacao);
	}
	
	public ResponseEntity<List<OperacaoDto>> listarOperacoesPorCodigo() {


		List<OperacaoDtoInteface> findByPorCodigo = operacaoRepository.findByPorCodigo();

		List<OperacaoDto> lista = new ArrayList<OperacaoDto>();
		
		ManagerAtivo ma = new ManagerAtivo();
		
		for (OperacaoDtoInteface operacaoDtoInterface  : findByPorCodigo) {
			
			System.out.println("************  INICIO" + operacaoDtoInterface.getValor_investido());
			
			OperacaoDto operacaoDto = new OperacaoDto();
			
			BigDecimal totalInvestido = new BigDecimal(operacaoDtoInterface.getValor_investido()); // total investido
			System.out.println("totalInvestido " + totalInvestido);
			BigDecimal qtd = new BigDecimal(operacaoDtoInterface.getQuantidade_acoes());// qtd total de acoes
			System.out.println("qtd " + qtd);
			BigDecimal divide = totalInvestido.divide(qtd,2,RoundingMode.UP);
			
			operacaoDto.setCodigoAtivo(ativoRepository.findById(operacaoDtoInterface.getAtivo_id()).get().getCodigo());
			
			operacaoDto.setPrecoMedio(divide.toString().replace(",", "."));
			
			operacaoDto.setQtd(operacaoDtoInterface.getQuantidade_acoes());
			
			operacaoDto.setNomeAtivo(ativoRepository.findById(operacaoDtoInterface.getAtivo_id()).get().getNome());
			
			operacaoDto.setUrlAtivo(ma.retornaurlAtivo(operacaoDto.getCodigoAtivo()).get(1));
			
			operacaoDto.setValorAtualAtivo(ma.retornaValorAtivo(operacaoDto.getCodigoAtivo()).replace(",", "."));
			
			BigDecimal diferencaValorCompraEValorAtual = new BigDecimal(operacaoDto.getValorAtualAtivo().toString().replace(",", ".")).setScale(2, RoundingMode.UP).subtract(new BigDecimal(operacaoDto.getPrecoMedio().toString().replace(",", ".")).setScale(2, RoundingMode.UP));
			
			Double lucroPrejuizo = Double.parseDouble(diferencaValorCompraEValorAtual.setScale(2, RoundingMode.UP).toString().replace(",", ".")) * operacaoDto.getQtd();
			
			operacaoDto.setLucroPrejuizo(new BigDecimal(lucroPrejuizo.toString().replace(",", ".")).setScale(2, RoundingMode.UP).toString().replace(",", "."));
			
			BigDecimal porcentagem = new BigDecimal(operacaoDto.getLucroPrejuizo().toString()).setScale(2, RoundingMode.UP).multiply(new BigDecimal("100")).divide(new BigDecimal(totalInvestido.toString()),2,RoundingMode.UP);
			
			operacaoDto.setPorcentagem(porcentagem.toString());
			
			operacaoDto.setUsuario(null);
			
			lista.add(ResponseEntity.ok(operacaoDto).getBody());	
			
			System.out.println("************  FIM " + operacaoDtoInterface.getValor_investido());
		}
		
		return ResponseEntity.ok(lista);
	}
	
//	public ResponseEntity<List<OperacaoDto>> listarOperacoes() {
//
//
//		List<OperacaoDtoInteface> findByPorCodigo = operacaoRepository.findByPorCodigo();
//
//		List<OperacaoDto> lista = new ArrayList<OperacaoDto>();
//		
//		ManagerAtivo ma = new ManagerAtivo();
//		
//		for (OperacaoDtoInteface operacaoDtoInterface  : findByPorCodigo) {
//			
//			System.out.println("************  " + operacaoDtoInterface.getValor_investido());
//			
//			OperacaoDto operacaoDto = new OperacaoDto();
//			
//			BigDecimal totalInvestido = new BigDecimal(operacaoDtoInterface.getValor_investido()); // total investido
//			System.out.println("totalInvestido " + totalInvestido);
//			BigDecimal qtd = new BigDecimal(operacaoDtoInterface.getQuantidade_acoes());// qtd total de acoes
//			System.out.println("qtd " + qtd);
//			BigDecimal divide = totalInvestido.divide(qtd,2,RoundingMode.UP);
//			
//			operacaoDto.setCodigoAtivo(ativoRepository.findById(operacaoDtoInterface.getAtivo_id()).get().getCodigo());
//			
//			operacaoDto.setPrecoMedio(divide.toString().replace(",", "."));
//			
//			operacaoDto.setQtd(operacaoDtoInterface.getQuantidade_acoes());
//			
//			operacaoDto.setNomeAtivo(ativoRepository.findById(operacaoDtoInterface.getAtivo_id()).get().getNome());
//			
//			operacaoDto.setUrlAtivo(ma.retornaurlAtivo(operacaoDto.getCodigoAtivo()).get(1));
//			
//			operacaoDto.setValorAtualAtivo(ma.retornaValorAtivo(operacaoDto.getCodigoAtivo()).replace(",", "."));
//			
//			BigDecimal diferencaValorCompraEValorAtual = new BigDecimal(operacaoDto.getValorAtualAtivo().toString().replace(",", ".")).setScale(2, RoundingMode.UP).subtract(new BigDecimal(operacaoDto.getPrecoMedio().toString().replace(",", ".")).setScale(2, RoundingMode.UP));
//			
//			Double lucroPrejuizo = Double.parseDouble(diferencaValorCompraEValorAtual.setScale(2, RoundingMode.UP).toString().replace(",", ".")) * operacaoDto.getQtd();
//			
//			operacaoDto.setLucroPrejuizo(new BigDecimal(lucroPrejuizo.toString().replace(",", ".")).setScale(2, RoundingMode.UP).toString().replace(",", "."));
//			
//			BigDecimal porcentagem = new BigDecimal(operacaoDto.getLucroPrejuizo().toString()).setScale(2, RoundingMode.UP).multiply(new BigDecimal("100")).divide(new BigDecimal(totalInvestido.toString()),2,RoundingMode.UP);
//			
//			operacaoDto.setPorcentagem(porcentagem.toString());
//			
//			lista.add(ResponseEntity.ok(operacaoDto).getBody());			
//		}
//		
//		return ResponseEntity.ok(lista);
//	}

	public List<Operacao> listarTodas() {
		return 	operacaoRepository.findAll();
	}

	

}
