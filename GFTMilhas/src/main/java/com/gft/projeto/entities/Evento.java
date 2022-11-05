package com.gft.projeto.entities;

import java.time.LocalDate;



import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;



import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
@Entity
@Table(name="EVENTOS")
public class Evento extends AbstractEntity<Long> {


	@NotEmpty(message = "Nome não pode ser vazio")
	private String nome;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataInicio;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataFinal;
	
	@OneToMany(mappedBy = "evento")
	private List<Grupo> grupos;

	
	@OneToMany(mappedBy = "evento")
	private List<ParticipantePorEvento> participantePorEvento;
	
	
	private long qtdeDias;
	
	


	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}

	public List<ParticipantePorEvento> getParticipantePorEvento() {
		return participantePorEvento;
	}

	public void setParticipantePorEvento(List<ParticipantePorEvento> participantePorEvento) {
		this.participantePorEvento = participantePorEvento;
	}

	public long getQtdeDias() {
		return qtdeDias;
	}

	public void setQtdeDias(long qtdeDias) {
		this.qtdeDias = qtdeDias;
	}
	
	
	
	
	
	
	
}
