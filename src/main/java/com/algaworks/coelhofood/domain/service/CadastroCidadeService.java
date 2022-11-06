package com.algaworks.coelhofood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.coelhofood.domain.exception.EntidadeEmUsoException;
import com.algaworks.coelhofood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.coelhofood.domain.model.Cidade;
import com.algaworks.coelhofood.domain.model.Estado;
import com.algaworks.coelhofood.domain.repository.CidadeRepository;
import com.algaworks.coelhofood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {
	public static final String NÃO_EXISTE_UM_CADASTRO_PARA_CIDADE = "Não existe um cadastro para cidade com código %d";
	public static final String CIDADE_DE_CÓDIGO_D_NÃO_PODE_SER_REMOVIDA = "Cidade de código d% não pode ser removida, pois, está em uso";
	//TODO TESTAR MÉTODOS TANTO CIDADE QUANTO ESTADO
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CadastroEstadoService cadastroEstadoService;
	
	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();

		Estado estado = cadastroEstadoService.buscarOuFalhar(estadoId);

		cidade.setEstado(estado);
		
		return cidadeRepository.save(cidade);
	}
	
	public void excluir (Long cidadeId) throws EntidadeEmUsoException, EntidadeNaoEncontradaException{
		try {
			
			cidadeRepository.deleteById(cidadeId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeEmUsoException(
					String.format(NÃO_EXISTE_UM_CADASTRO_PARA_CIDADE, cidadeId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeNaoEncontradaException(
					String.format(CIDADE_DE_CÓDIGO_D_NÃO_PODE_SER_REMOVIDA, cidadeId));
		}
	}

	public Cidade bucarOuFalhar (Long cidadeId){
		return cidadeRepository.findById(cidadeId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format(NÃO_EXISTE_UM_CADASTRO_PARA_CIDADE, cidadeId)));
	}
}
