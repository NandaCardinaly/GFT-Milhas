package com.gft.projeto.entities;


import java.util.List;

import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;



@SuppressWarnings("serial")
@Entity
@Table(name="PARTICIPANTES")
public class Participante extends AbstractEntity<Long> {
	
	
	@Column(nullable = false)
	@NotEmpty(message = "Nome não pode ser vazio")
	private String nome;

	@Email
	private String email;

	@Column(nullable = false)
	@NotEmpty(message = "Quatro letras não pode ser vazio")
	@Size(min = 4, max = 4, message = "Devem ser quatro letras")
	private String quatroLetras;

	@NotEmpty(message = "Nome não pode ser vazio")
	private String nivel;
	
	
	@ManyToOne
	@JoinColumn(name="id_grupo")
	private Grupo grupo;
	
	@OneToMany(mappedBy = "participantes")
	private List<ParticipantePorEvento> participantePorEvento;
	


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQuatroLetras() {
		return quatroLetras;
	}

	public void setQuatroLetras(String quatroLetras) {
		this.quatroLetras = quatroLetras;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
		
	
	}
	


	public List<ParticipantePorEvento> getParticipantePorEvento() {
		return participantePorEvento;
	}

	public void setParticipantePorEvento(List<ParticipantePorEvento> participantePorEvento) {
		this.participantePorEvento = participantePorEvento;
	}

	@Override
	public String toString() {
		return "" + nome + "";
	}




	

}
