package com.gft.projeto.entities;


import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
@Table(name = "GRUPOS")
public class Grupo extends AbstractEntity<Long> {


	@NotEmpty(message = "Nome não pode ser vazio")
	private String nome;

	private int qtdPessoas;

	@OneToMany(mappedBy = "grupo")
	private List<Participante> participantes;
	

	private int qntAtividades;
	
	private int pontuacaoFinal;
	
		
	@ManyToOne
	@JoinColumn(name="id_evento")
	private Evento evento;




	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQtdPessoas() {
		return qtdPessoas;
	}

	public void setQtdPessoas(int qtdPessoas) {
		this.qtdPessoas = qtdPessoas;
	}

	public List<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Participante> participantes) {
		this.participantes = participantes;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public int getPontuacaoFinal() {
		return pontuacaoFinal;
	}

	public void setPontuacaoFinal(int pontuacaoFinal) {
		this.pontuacaoFinal = pontuacaoFinal;
	}

	public int getQntAtividades() {
		return qntAtividades;
	}

	public void setQntAtividades(int qntAtividades) {
		this.qntAtividades = qntAtividades;
	}









	
	
	
}
