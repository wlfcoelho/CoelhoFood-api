package com.algaworks.coelhofood.domain.service;

import java.util.Optional;

import com.algaworks.coelhofood.domain.exception.CidadeNaoEncontradoException;
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

	private static final String MSG_CIDADE_EM_USO
			= "Cidade de código %d não pode ser removida, pois está em uso";

	public static final String NÃO_EXISTE_UM_CADASTRO_PARA_CIDADE =
			"Não existe um cadastro para cidade com código %d";
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
			throw new CidadeNaoEncontradoException(cidadeId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_CIDADE_EM_USO, cidadeId));
		}
	}

	public Cidade bucarOuFalhar (Long cidadeId){
		return cidadeRepository.findById(cidadeId)
				.orElseThrow(() -> new CidadeNaoEncontradoException(
						String.format(NÃO_EXISTE_UM_CADASTRO_PARA_CIDADE, cidadeId)));
	}
}
