package com.gft.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gft.projeto.entities.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

}
