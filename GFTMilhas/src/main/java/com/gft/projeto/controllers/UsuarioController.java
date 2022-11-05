package com.gft.projeto.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.projeto.entities.Usuario;
import com.gft.projeto.services.UsuarioService;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@RequestMapping(path = "cadastrarEditar")
	public ModelAndView cadastrarEditarUsuario(@RequestParam(required = false) Long id) {

		ModelAndView mv = new ModelAndView("usuario/form.html");

		Usuario usuario;

		if (id == null) {
			usuario = new Usuario();
		} else {
			try {
				usuario = usuarioService.obterUsuarios(id);
			} catch (Exception e) {
				usuario = new Usuario();
				mv.addObject("mensagem", e.getMessage());
			}
		}

		mv.addObject("usuario", usuario);
		mv.addObject("listaUsuario", usuarioService.listarUsuarios());

		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "cadastrarEditar")
	public ModelAndView salvarUsuario(@Valid Usuario usuario, BindingResult bindingResult) {

		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		
		ModelAndView mv = new ModelAndView("usuario/form.html");

		boolean novo = true;

		if (usuario.getId() != null) {
			novo = false;
		}

		if (bindingResult.hasErrors()) {
			mv.addObject("usuario", usuario);
			return mv;
		}

		usuarioService.salvarUsuario(usuario);

		if (novo) {
			mv.addObject("usuario", new Usuario());
		} else {
			mv.addObject("usuario", usuario);
		}

		mv.addObject("mensagem", "Usuario salvo com sucesso");
		mv.addObject("listaUsuario", usuarioService.listarUsuarios());

		return mv;
	}

	@RequestMapping
	public ModelAndView listarUsuarios() {

		ModelAndView mv = new ModelAndView("usuario/listar.html");

		mv.addObject("lista", usuarioService.listarUsuarios());

		return mv;
	}

	@RequestMapping("/excluir")
	public ModelAndView excluirUsuario(@RequestParam Long id, RedirectAttributes redirectAttributes) {

		ModelAndView mv = new ModelAndView("redirect:/usuario");

		try {
			usuarioService.excluirUsuario(id);
			redirectAttributes.addFlashAttribute("mensagem", "Usuario excluído com sucesso.");
		} catch (Exception e) {
			
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir usuário!" + e.getMessage());
		}

		return mv;
	}
	

}
