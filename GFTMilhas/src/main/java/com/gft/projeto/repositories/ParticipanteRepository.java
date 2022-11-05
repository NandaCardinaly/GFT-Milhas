package com.gft.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.gft.projeto.entities.Participante;


@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
	
	

}
