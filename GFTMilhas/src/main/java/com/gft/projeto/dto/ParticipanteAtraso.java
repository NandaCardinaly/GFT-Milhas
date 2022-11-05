package com.gft.projeto.dto;

public class ParticipanteAtraso {

	private Long idParticipante;
	
	private String nomeParticipante;
	
	private boolean atraso;
	
	private boolean presenca;

	public Long getIdParticipante() {
		return idParticipante;
	}

	public void setIdParticipante(Long idParticipante) {
		this.idParticipante = idParticipante;
	}

	public String getNomeParticipante() {
		return nomeParticipante;
	}

	public void setNomeParticipante(String nomeParticipante) {
		this.nomeParticipante = nomeParticipante;
	}

	public boolean isAtraso() {
		return atraso;
	}

	public void setAtraso(boolean atraso) {
		this.atraso = atraso;
	}

	public boolean isPresenca() {
		return presenca;
	}

	public void setPresenca(boolean presenca) {
		this.presenca = presenca;
	}
	
	
	
}
