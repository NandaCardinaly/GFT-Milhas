package com.gft.projeto.entities;



import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;






@SuppressWarnings("serial")
@Entity
@Table(name="PARTICIPANTESPOREVENTO")
public class ParticipantePorEvento extends AbstractEntity<Long>{
	

		
		private int presenca;
		private int atraso;
		private int QtdeAtividadesFeitas;
		
		
		private int pontuacaoGeral;
		
		
		@ManyToOne
		@JoinColumn(name="id_evento")
		private Evento evento;
		
		@ManyToOne
		@JoinColumn(name="id_participante")
		private Participante participantes;



		public Evento getEvento() {
			return evento;
		}
		
		public void setEvento(Evento evento) {
			this.evento = evento;
		}
		
		public Participante getParticipantes() {
			return participantes;
		}
		
		public void setParticipantes(Participante participantes) {
			this.participantes = participantes;
		}
		
		
		public int getPresenca() {
			return presenca;
		}

		public void setPresenca(int presenca) {
			this.presenca = presenca;
		}

		public int getPontuacaoGeral() {
			return pontuacaoGeral;
		}

		public void setPontuacaoGeral(int pontuacaoGeral) {
			this.pontuacaoGeral = pontuacaoGeral;
		}
		
		public int getAtraso() {
			return atraso;
		}
		
		public void setAtraso(int atraso) {
			this.atraso = atraso;
		}

		public int getQtdeAtividadesFeitas() {
			return QtdeAtividadesFeitas;
		}

		public void setQtdeAtividadesFeitas(int qtdeAtividadesFeitas) {
			QtdeAtividadesFeitas = qtdeAtividadesFeitas;
		}


		
		
		
	}
