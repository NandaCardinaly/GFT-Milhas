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

import com.gft.projeto.entities.Atividade;
import com.gft.projeto.services.AtividadeService;
import com.gft.projeto.services.GrupoService;



@Controller
@RequestMapping("atividade")
public class AtividadeController {
	
	@Autowired
	private AtividadeService atividadeService;
	
	@Autowired
	private GrupoService grupoService;

	

	
	@RequestMapping(path = "cadastrarEditar")
	public ModelAndView editarAtividade(@RequestParam(required = false) Long id) {
		
		ModelAndView mv = new ModelAndView("atividade/form.html");
		
		Atividade atividade;
		
		if(id==null) {
			atividade = new Atividade();
		}else {
			try {
				atividade = atividadeService.obterAtividade(id);
			}catch(Exception e) {
				atividade = new Atividade();
				mv.addObject("mensagem", e.getMessage());
			}
		}
		
		mv.addObject("atividade", atividade);
		mv.addObject("listaGrupos", grupoService.listarGrupos());
	
	
		
		return mv;
		
	}
	
	@RequestMapping(method = RequestMethod.POST , path = "cadastrarEditar")
	public ModelAndView salvarAtividade( @Valid Atividade atividade, BindingResult bindingResult){
		
		ModelAndView mv = new ModelAndView("atividade/form.html");
		
		boolean novo = true;
		
		if(atividade.getId() != null) {
			novo = false;
		}
		
		if(bindingResult.hasErrors()) {
			mv.addObject("atividade", atividade);
			return mv;
		}
		
		
				
		atividadeService.salvarAtividade(atividade);
		
		
	
		if(novo) {
			mv.addObject("atividade", new Atividade());
		}else {
			mv.addObject("atividade", atividade);
		}
		
		mv.addObject("mensagem", "Atividade salva com sucesso");
		
		
		return mv;
		
	}
	
	@RequestMapping
	public ModelAndView listarAtividades() {
		
		ModelAndView mv = new ModelAndView("atividade/listar.html");
		
		mv.addObject("lista", atividadeService.listarAtividades());
		
		return mv;
		
	}
	
	@RequestMapping("/excluir")
	public ModelAndView excluirAtividades(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/atividade");

		try {
			atividadeService.excluirAtividade(id);
			 redirectAttributes.addFlashAttribute("mensagem", "Atividade excluída com sucesso.");
		}catch(Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir atividade!"+e.getMessage());
		}
				
		return mv;
	}

}
