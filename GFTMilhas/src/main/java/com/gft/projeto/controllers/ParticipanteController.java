package com.gft.projeto.controllers;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.gft.projeto.entities.Participante;
import com.gft.projeto.services.GrupoService;
import com.gft.projeto.services.ParticipanteService;

@Controller
@RequestMapping("participante")
public class ParticipanteController {

	@Autowired
	private ParticipanteService participanteService;
	
	@Autowired
	private GrupoService grupoService;

	@RequestMapping(path = "cadastrarEditar")
	public ModelAndView cadastrarEditarParticipante(@RequestParam(required = false) Long id) {

		ModelAndView mv = new ModelAndView("participante/form.html");

		Participante participante;

		if (id == null) {
			participante = new Participante();
		} else {
			try {
				participante = participanteService.obterParticipantes(id);
			} catch (Exception e) {
				participante = new Participante();
				mv.addObject("mensagem", e.getMessage());
			}
		}

		mv.addObject("participante", participante);
		mv.addObject("listaParticipante", participanteService.listarParticipantes());
		mv.addObject("listaGrupo", grupoService.listarGrupos());

		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "cadastrarEditar")
	public ModelAndView salvarParticipante(@Valid Participante participante, BindingResult bindingResult) {

		ModelAndView mv = new ModelAndView("participante/form.html");

		boolean novo = true;

		if (participante.getId() != null) {
			novo = false;
		}

		if (bindingResult.hasErrors()) {
			mv.addObject("participante", participante);
			return mv;
		}

		participanteService.salvarParticipante(participante);
		participanteService.quantidadeDeParticipantes();

		if (novo) {
			mv.addObject("participante", new Participante());
		} else {
			mv.addObject("participante", participante);
		}

		mv.addObject("mensagem", "Participante salvo com sucesso");
		mv.addObject("listaParticipante", participanteService.listarParticipantes());
		mv.addObject("listaGrupo", grupoService.listarGrupos());

		return mv;
	}

	@RequestMapping
	public ModelAndView listarParticipantes() {

		ModelAndView mv = new ModelAndView("participante/listar.html");

		mv.addObject("lista", participanteService.listarParticipantes());

		return mv;
	}

	@RequestMapping("/excluir")
	public ModelAndView excluirParticipante(@RequestParam Long id, RedirectAttributes redirectAttributes) {

		ModelAndView mv = new ModelAndView("redirect:/participante");

		try {
			participanteService.excluirParticipante(id);
			redirectAttributes.addFlashAttribute("mensagem", "Participante excluído com sucesso.");
		} catch (Exception e) {

			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir participante!" + e.getMessage());
		}

		return mv;
	}

	
}
