package com.algaworks.coelhofood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.coelhofood.domain.exception.EntidadeEmUsoException;
import com.algaworks.coelhofood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.coelhofood.domain.model.Estado;
import com.algaworks.coelhofood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	public static final String NÃO_EXISTE_UM_CADASTRO_PARA_O_ESTADO = "Não existe um cadastro para o Estado com código %d";
	public static final String ESTADO_DE_CÓDIGO_D_NÃO_PODE_SER_REMOVIDA = "Estado de código %d não pode ser removida, pois está em uso";
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado salvar (Estado estado) {
		return estadoRepository.save(estado);
	}
	
	public void excluir (Long estadoId) throws EntidadeEmUsoException, EntidadeNaoEncontradaException{
		try {
			estadoRepository.deleteById(estadoId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format(NÃO_EXISTE_UM_CADASTRO_PARA_O_ESTADO, estadoId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(ESTADO_DE_CÓDIGO_D_NÃO_PODE_SER_REMOVIDA, estadoId));
		}
	}

	public Estado buscarOuFalhar (Long estadoId){
		return estadoRepository.findById(estadoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format(NÃO_EXISTE_UM_CADASTRO_PARA_O_ESTADO, estadoId)));
	}
}

