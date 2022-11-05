package com.gft.projeto.controllers;

import java.time.LocalDate;


import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.projeto.dto.Atraso;
import com.gft.projeto.dto.ParticipanteAtraso;
import com.gft.projeto.entities.Evento;
import com.gft.projeto.entities.Participante;
import com.gft.projeto.entities.ParticipantePorEvento;
import com.gft.projeto.services.AtividadeService;
import com.gft.projeto.services.EventoService;
import com.gft.projeto.services.GrupoService;
import com.gft.projeto.services.ParticipantePorEventoService;
import com.gft.projeto.services.ParticipanteService;

@Controller
@RequestMapping("evento")
public class EventoController {

	@Autowired
	private EventoService eventoService;

	@Autowired
	private GrupoService grupoService;

	@Autowired
	private ParticipanteService participanteService;

	@Autowired
	private AtividadeService atividadeService;

	@Autowired
	private ParticipantePorEventoService participantePorEventoService;

	@RequestMapping(path = "novo")
	public ModelAndView novoEvento() {

		ModelAndView mv = new ModelAndView("evento/novo.html");
		mv.addObject("evento", new Evento());

		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "novo")
	public ModelAndView salvarEvento(@Valid Evento evento, BindingResult bindingResult) {

		ModelAndView mv = new ModelAndView("evento/novo.html");

		if (bindingResult.hasErrors()) {
			mv.addObject("evento", evento);
			return mv;
		}
		
		Long dias =  evento.getDataInicio().until(evento.getDataFinal(), ChronoUnit.DAYS);
		evento.setQtdeDias(dias);
		Evento eventoSalvo = eventoService.salvarEvento(evento);

		if (evento.getId() == null) {
			mv.addObject("evento", new Evento());
		} else {
			mv.addObject("evento", eventoSalvo);
		}

		mv.addObject("mensagem", "Evento Salvo com Sucesso!");

		return mv;
	}

	@RequestMapping("/listar")
	public ModelAndView listarEvento() {

		ModelAndView mv = new ModelAndView("evento/listar.html");
		mv.addObject("lista", eventoService.listarEvento());

		return mv;
	}

	@RequestMapping("/editar")
	public ModelAndView editarEvento(@RequestParam Long id) {

		ModelAndView mv = new ModelAndView("evento/novo.html");
		Evento evento;

		try {
			evento = eventoService.obterEvento(id);
		} catch (Exception e) {
			evento = new Evento();
			mv.addObject("mensagem", e.getMessage());
		}

		mv.addObject("evento", evento);

		return mv;
	}

	@RequestMapping("/excluir")
	public ModelAndView excluirEvento(@RequestParam Long id, RedirectAttributes redirectAttributes) {

		ModelAndView mv = new ModelAndView("redirect:/evento/listar");

		try {
			eventoService.excluirEvento(id);
			redirectAttributes.addFlashAttribute("mensagem", "Evento excluído com sucesso!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir evento!" + e.getMessage());
		}

		return mv;
	}

	@RequestMapping(path = "controle")
	public ModelAndView controle(@RequestParam(required = false) Long id) {

		ModelAndView mv = new ModelAndView("evento/controle.html");

		Evento evento;

		if (id == null) {
			evento = new Evento();
		} else {
			try {
				evento = eventoService.obterEvento(id);
			} catch (Exception e) {
				evento = new Evento();
				mv.addObject("mensagem", e.getMessage());
			}
		}

		mv.addObject("evento", evento);
		mv.addObject("lista", grupoService.listarGrupos());
		mv.addObject("listaParticipante", participanteService.listarParticipantes());
		mv.addObject("lista", eventoService.listarEvento());

		return mv;
	}

	@RequestMapping(path = "atraso")
	public ModelAndView gerenciarEvento(@RequestParam(required = false) Long id) {

		ModelAndView mv = new ModelAndView("evento/atraso.html");

		Evento evento;

		if (id == null) {
			evento = new Evento();
		} else {
			try {
				evento = eventoService.obterEvento(id);
			} catch (Exception e) {
				evento = new Evento();
				mv.addObject("mensagem", e.getMessage());
			}
		}

		LocalDate dataInicial = evento.getDataInicio();
		LocalDate dataFinal = evento.getDataFinal();

		List<LocalDate> datas = new ArrayList<>();

		LocalDate data = dataInicial;

		while (data.isBefore(dataFinal)) {
			datas.add(data);
			data = data.plusDays(1);
		}

		mv.addObject("evento", evento);
		mv.addObject("grupos", evento.getGrupos());
		mv.addObject("datas", datas);
		mv.addObject("listaAtividades", atividadeService.listarAtividades());

		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "atraso")
	public ModelAndView salvarAlteracao(@Valid Atraso atraso, BindingResult bindingResult) throws Exception {

		if (atraso == null) {
			return new ModelAndView("/evento/atraso");
		}

		List<List<ParticipanteAtraso>> lista = atraso.getGrupoAtraso().stream()
				.map(grupo -> grupo.getParticipantesAtraso()).toList();

		List<ParticipanteAtraso> participantes = lista.stream().flatMap(List::stream).collect(Collectors.toList());

		List<ParticipantePorEvento> listar = participantePorEventoService.listarParticipantePorEvento();

		for (ParticipanteAtraso participanteAtraso : participantes) {

			if (!listar.isEmpty()) {

				Optional<ParticipantePorEvento> optionalParticipantePorEvento = listar.stream()
						.filter(participante -> participante.getParticipantes().getId()
								.equals(participanteAtraso.getIdParticipante()))
						.findFirst();

				if (optionalParticipantePorEvento.isPresent()) {
					
					int converterAtraso = participanteAtraso.isAtraso() ? 1 : 0;
					int converterPresenca = participanteAtraso.isPresenca() ? 1 : 0;

					ParticipantePorEvento participantePorEvento = optionalParticipantePorEvento.get();

					int presenca = participantePorEvento.getPresenca();
					participantePorEvento.setPresenca(presenca + converterPresenca);

					int qtdAtraso = participantePorEvento.getAtraso();
					participantePorEvento.setAtraso(qtdAtraso + converterAtraso);
					
					participantePorEvento.setQtdeAtividadesFeitas((int) atraso.getAtividadesAtraso().stream().filter(e -> e.isChecked()).count());

					participantePorEventoService.salvarAlteracao(participantePorEvento);

				}
			}

			else {

				Evento evento = eventoService.obterEvento(atraso.getIdEvento());
				Participante participante = participanteService.obterParticipantes(participanteAtraso.getIdParticipante());
				
				int converterAtraso = participanteAtraso.isAtraso() ? 1 : 0;
				int converterPresenca = participanteAtraso.isPresenca() ? 1 : 0;
				
				
				ParticipantePorEvento participantePorEvento = new ParticipantePorEvento();

				participantePorEvento.setAtraso(converterAtraso);
				participantePorEvento.setPresenca(converterPresenca);

				participantePorEvento.setQtdeAtividadesFeitas((int) atraso.getAtividadesAtraso().stream().filter(e -> e.isChecked()).count());
				participantePorEvento.setEvento(evento);
				participantePorEvento.setParticipantes(participante);
				
				
				participantePorEventoService.salvarAlteracao(participantePorEvento);
			}
		}

		return new ModelAndView("redirect:/evento/atraso?id=" + atraso.getIdEvento());
	}

}
