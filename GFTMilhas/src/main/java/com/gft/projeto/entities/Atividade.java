package com.gft.projeto.entities;

import java.util.Date;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
@Entity
@Table(name="ATIVIDADES")
public class Atividade extends AbstractEntity<Long> {



	@NotEmpty(message = "Nome não pode ser vazio")
	private String nome;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataInicio;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataEntrega;
	
	@ManyToMany
	private List<Grupo> grupos;

	

	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	@Override
	public String toString() {
		return " " + grupos + " ";
	}











	
	

}
