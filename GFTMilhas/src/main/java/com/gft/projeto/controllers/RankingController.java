package com.gft.projeto.controllers;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;



import com.gft.projeto.entities.Grupo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.gft.projeto.services.ParticipantePorEventoService;

;

@Controller
@RequestMapping("ranking")
public class RankingController {
	
	@Autowired
	private ParticipantePorEventoService participantePorEventoService;
	
	
	private List<Grupo> listaDeGrupos;
	
	
	@RequestMapping(method = RequestMethod.GET, path = "listar")
	public ModelAndView listaDoRanking() {

		ModelAndView mv = new ModelAndView("ranking/listar.html");
		
		listaDeGrupos = participantePorEventoService.pontuarGrupo();
		
		listaDeGrupos.sort((g1, g2) -> Integer.compare(g1.getPontuacaoFinal(), g2.getPontuacaoFinal())*-1);

		mv.addObject("lista", listaDeGrupos);
		
		

		return mv;
		
	}
	
	
	
	
}
