package com.gft.projeto.services;

import java.util.List;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.projeto.entities.Evento;
import com.gft.projeto.entities.Grupo;
import com.gft.projeto.entities.ParticipantePorEvento;
import com.gft.projeto.repositories.EventoRepository;
import com.gft.projeto.repositories.GrupoRepository;
import com.gft.projeto.repositories.ParticipantePorEventoRepository;

@Service
public class ParticipantePorEventoService {

	@Autowired
	private ParticipantePorEventoRepository participantePorEventoRepository;

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private GrupoRepository grupoRepository;

	public ParticipantePorEvento salvarAlteracao(ParticipantePorEvento participantePorEvento) {

		return participantePorEventoRepository.save(participantePorEvento);

	}

	public List<ParticipantePorEvento> listarParticipantePorEvento() {

		return participantePorEventoRepository.findAll();
	}

	public ParticipantePorEvento obterParticipantePorEvento(Long id) throws Exception {

		Optional<ParticipantePorEvento> participantePorEvento = participantePorEventoRepository.findById(id);

		if (participantePorEvento.isEmpty()) {
			throw new Exception("Registro não encontrado");
		}

		return participantePorEvento.get();

	}

	public void excluirParticipantePorEvento(Long id) {

		participantePorEventoRepository.deleteById(id);

	}
	

	public List<Grupo> pontuarGrupo() {
		List<ParticipantePorEvento> participantePorEvento = participantePorEventoRepository.findAll();
		List<Grupo> grupo = grupoRepository.findAll();

		calcularPresencaAtividadeTotalDoParticipante(participantePorEvento);
		calcularBonusPresenca(participantePorEvento);
		calcularBonusAtividade(participantePorEvento, grupo);
		subtrairAtraso(participantePorEvento, grupo);

		for (ParticipantePorEvento pe : participantePorEvento) {
			for (Grupo gr : grupo) {

				if (pe.getParticipantes().getGrupo().getId() == gr.getId()) {
					gr.setPontuacaoFinal(pe.getPontuacaoGeral());

					participantePorEventoRepository.save(pe);

				}

			}
		}
		
		
		 

		return grupoRepository.findAll();
	}

	public void calcularPresencaAtividadeTotalDoParticipante(List<ParticipantePorEvento> participantePorEvento) {

		int presencatotal, atvtotal;

		for (ParticipantePorEvento pe : participantePorEvento) {
			presencatotal = pe.getPresenca() * 10;
			atvtotal = pe.getQtdeAtividadesFeitas() * 10;
			pe.setPontuacaoGeral(presencatotal + atvtotal);
			participantePorEventoRepository.save(pe);

		}
	}

	public void calcularBonusPresenca(List<ParticipantePorEvento> participantePorEvento) {

		List<Evento> evento = eventoRepository.findAll();

		for (ParticipantePorEvento pe : participantePorEvento) {
			for (Evento ev : evento) {

				if (pe.getEvento().getId() == ev.getId() && ev.getQtdeDias() == pe.getPresenca()) {
					pe.setPontuacaoGeral(pe.getPontuacaoGeral() + 5);
					participantePorEventoRepository.save(pe);

				}

			}
		}

	}

	public void calcularBonusAtividade(List<ParticipantePorEvento> participantePorEvento, List<Grupo> grupo) {

		for (ParticipantePorEvento pe : participantePorEvento) {
			for (Grupo gr : grupo) {

				if (pe.getParticipantes().getGrupo().getId() == gr.getId()
						&& gr.getQntAtividades() == pe.getQtdeAtividadesFeitas()) {
					pe.setPontuacaoGeral(pe.getPontuacaoGeral() + 3);
					participantePorEventoRepository.save(pe);

				}

			}
		}
	}

public void subtrairAtraso(List<ParticipantePorEvento> participantePorEvento, List<Grupo> grupo) {

		participantePorEvento = participantePorEventoRepository.findAllAtrasos();
		
		for (ParticipantePorEvento pe : participantePorEvento) {
			for (Grupo gr : grupo) {

				if (pe.getParticipantes().getGrupo().getId() == gr.getId()) {
					gr.setPontuacaoFinal(gr.getPontuacaoFinal() - 2);
					grupoRepository.save(gr);

				}

			}

		}
	}
}
