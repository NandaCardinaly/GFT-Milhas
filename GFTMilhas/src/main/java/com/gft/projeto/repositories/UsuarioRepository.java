package com.gft.projeto.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.projeto.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Optional<Usuario> findByQuatroLetras(String quatroLetras);
}
