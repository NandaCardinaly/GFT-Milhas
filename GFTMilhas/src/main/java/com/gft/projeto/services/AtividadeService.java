package com.gft.projeto.services;

import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.projeto.entities.Atividade;
import com.gft.projeto.repositories.AtividadeRepository;


@Service
public class AtividadeService {
	
	@Autowired
	private AtividadeRepository atividadeRepository;
	
	
	public Atividade salvarAtividade(Atividade atividade) {

		return atividadeRepository.save(atividade);

	}

	public List<Atividade> listarAtividades() {

		return atividadeRepository.findAll();
	}

	
	public Atividade obterAtividade(Long id) throws Exception {

		Optional<Atividade> atividade = atividadeRepository.findById(id);

		if (atividade.isEmpty()) {
			throw new Exception("Atividade não encontrada");
		}

		return atividade.get();

	}

	public void excluirAtividade(Long id) {

		atividadeRepository.deleteById(id);

	}

	
	
	

}
