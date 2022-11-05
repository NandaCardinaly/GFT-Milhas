package com.gft.projeto.services;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.projeto.entities.Grupo;
import com.gft.projeto.entities.Participante;
import com.gft.projeto.repositories.GrupoRepository;
import com.gft.projeto.repositories.ParticipanteRepository;

@Service
public class ParticipanteService {

	@Autowired
	private ParticipanteRepository participanteRepository;

	@Autowired
	private GrupoRepository grupoRepository;

	public Participante salvarParticipante(Participante participante) {

		return participanteRepository.save(participante);

	}

	public List<Participante> listarParticipantes() {

		return participanteRepository.findAll();
	}

	public Participante obterParticipantes(Long id) throws Exception {

		Optional<Participante> participante = participanteRepository.findById(id);

		if (participante.isEmpty()) {
			throw new Exception("Participante não encontrado.");
		}

		return participante.get();

	}

	public void excluirParticipante(Long id) {

		participanteRepository.deleteById(id);

	}

	public void quantidadeDeParticipantes() {
		List<Participante> participante = participanteRepository.findAll();
		List<Grupo> grupo = grupoRepository.findAll();

		for (Participante pa : participante) {
			for (Grupo gr : grupo) {

				if (pa.getGrupo().getId() == gr.getId()) {
					gr.setQtdPessoas(gr.getQtdPessoas() + 1);
					grupoRepository.save(gr);

				}

			}

		}
	}
}
