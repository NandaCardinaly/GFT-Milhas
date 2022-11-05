package com.gft.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gft.projeto.entities.Atividade;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

	
}
