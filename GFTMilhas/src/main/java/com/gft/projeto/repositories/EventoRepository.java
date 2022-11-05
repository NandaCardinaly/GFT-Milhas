package com.gft.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.projeto.entities.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long>{
	
}
