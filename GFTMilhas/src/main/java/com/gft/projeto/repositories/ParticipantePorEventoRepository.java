package com.gft.projeto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gft.projeto.entities.ParticipantePorEvento;

@Repository
public interface ParticipantePorEventoRepository extends  JpaRepository<ParticipantePorEvento, Long>{
	
	
	@Query(value = "select * from participantesporevento p where p.atraso = 1", nativeQuery = true)
	List<ParticipantePorEvento> findAllAtrasos();
	
	

}
