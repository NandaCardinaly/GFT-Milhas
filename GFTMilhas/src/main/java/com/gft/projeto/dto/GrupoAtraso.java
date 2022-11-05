package com.gft.projeto.dto;

import java.util.List;

public class GrupoAtraso {

	private Long idGrupo;
	
	private List<ParticipanteAtraso> participantesAtraso;
	

	public Long getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Long idGrupo) {
		this.idGrupo = idGrupo;
	}

	public List<ParticipanteAtraso> getParticipantesAtraso() {
		return participantesAtraso;
	}

	public void setParticipantesAtraso(List<ParticipanteAtraso> participantesAtraso) {
		this.participantesAtraso = participantesAtraso;
	}
	
	
}
