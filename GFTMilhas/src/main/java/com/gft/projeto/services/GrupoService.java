package com.gft.projeto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.projeto.entities.Grupo;
import com.gft.projeto.repositories.GrupoRepository;

@Service
public class GrupoService {

	@Autowired
	private GrupoRepository grupoRepository;

	public Grupo salvarGrupo(Grupo grupo) {

		return grupoRepository.save(grupo);

	}

	public List<Grupo> listarGrupos() {

		return grupoRepository.findAll();
	}

	public Grupo obterGrupo(Long id) throws Exception {

		Optional<Grupo> grupo = grupoRepository.findById(id);

		if (grupo.isEmpty()) {
			throw new Exception("Grupo não encontrado.");
		}

		return grupo.get();

	}

	public void excluirGrupo(Long id) {

		grupoRepository.deleteById(id);

	}

	

}
