package com.gft.projeto.security;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gft.projeto.entities.Usuario;
import com.gft.projeto.repositories.UsuarioRepository;

@Service
@Transactional 
public class UserDetailsServiceImpl implements UserDetailsService{

	final UsuarioRepository usuarioRepository;
	
	public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String quatroLetras) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByQuatroLetras(quatroLetras)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
		return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());
	}

}
