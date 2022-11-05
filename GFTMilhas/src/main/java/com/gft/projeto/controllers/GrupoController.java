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

import com.gft.projeto.entities.Grupo;
import com.gft.projeto.services.EventoService;
import com.gft.projeto.services.GrupoService;
import com.gft.projeto.services.ParticipanteService;


@Controller
@RequestMapping(path = "grupo")
public class GrupoController {

	@Autowired
	private GrupoService grupoService;

	@Autowired
	private ParticipanteService participanteService;
	
	@Autowired
	private EventoService eventoService;

	@RequestMapping(path = "cadastrarEditar")
	public ModelAndView cadastrarEditarGrupo(@RequestParam(required = false) Long id) {

		ModelAndView mv = new ModelAndView("grupo/form.html");

		Grupo grupo;
		

		if (id == null) {
			grupo = new Grupo();
		} else {
			try {
				grupo = grupoService.obterGrupo(id);
			} catch (Exception e) {
				grupo = new Grupo();
				mv.addObject("mensagem", e.getMessage());
			}
		}

		mv.addObject("grupo", grupo);
		mv.addObject("listaParticipante", participanteService.listarParticipantes());
		mv.addObject("lista", eventoService.listarEvento());
		
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "cadastrarEditar")
	public ModelAndView salvarGrupo(@Valid Grupo grupo, BindingResult bindingResult) {

		ModelAndView mv = new ModelAndView("grupo/form.html");

		boolean novo = true;

		if (grupo.getId() != null) {
			novo = false;
		}

		if (bindingResult.hasErrors()) {
			mv.addObject("grupo", grupo);
			return mv;
		}

		grupoService.salvarGrupo(grupo);

		if (novo) {
			mv.addObject("grupo", new Grupo());
		} else {
			mv.addObject("grupo", grupo);
		}

		mv.addObject("mensagem", "Grupo salvo com sucesso");
		mv.addObject("listaParticipante", participanteService.listarParticipantes());

		return mv;
	}

	@RequestMapping
	public ModelAndView listarGrupos() {

		ModelAndView mv = new ModelAndView("grupo/listar.html");

		mv.addObject("lista", grupoService.listarGrupos());

		return mv;
	}

	@RequestMapping("/excluir")
	public ModelAndView excluirGrupo(@RequestParam Long id, RedirectAttributes redirectAttributes) {

		ModelAndView mv = new ModelAndView("redirect:/grupo");

		try {
			grupoService.excluirGrupo(id);
			redirectAttributes.addFlashAttribute("mensagem", "Grupo excluído com sucesso");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir grupo!" + e.getMessage());
		}

		return mv;
	}
	
	
}
