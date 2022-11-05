package com.gft.projeto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.projeto.entities.Usuario;
import com.gft.projeto.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario salvarUsuario(Usuario usuario) {

		return usuarioRepository.save(usuario);

	}

	public List<Usuario> listarUsuarios() {

		return usuarioRepository.findAll();
	}

	public Usuario obterUsuarios(Long id) throws Exception {

		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if (usuario.isEmpty()) {
			throw new Exception("Usuário não encontrado.");
		}

		return usuario.get();

	}

	public void excluirUsuario(Long id) {

		usuarioRepository.deleteById(id);
		

	}
	
	
}
