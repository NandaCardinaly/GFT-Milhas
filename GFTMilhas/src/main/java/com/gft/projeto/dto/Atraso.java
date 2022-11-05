package com.gft.projeto.dto;

import java.util.List;

public class Atraso {
	
	private int pontuacaoGeral;
	
	private List<AtividadeAtraso> atividadesAtraso;
	
	private Long idEvento;
	
	private List<GrupoAtraso> grupoAtraso;
	
	
	
	public Long getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}

	public int getPontuacaoGeral() {
		return pontuacaoGeral;
	}

	public void setPontuacaoGeral(int pontuacaoGeral) {
		this.pontuacaoGeral = pontuacaoGeral;
	}

	public List<AtividadeAtraso> getAtividadesAtraso() {
		return atividadesAtraso;
	}

	public void setAtividadesAtraso(List<AtividadeAtraso> atividadesAtraso) {
		this.atividadesAtraso = atividadesAtraso;
	}

	public List<GrupoAtraso> getGrupoAtraso() {
		return grupoAtraso;
	}

	public void setGrupoAtraso(List<GrupoAtraso> grupoAtraso) {
		this.grupoAtraso = grupoAtraso;
	}
	
}
