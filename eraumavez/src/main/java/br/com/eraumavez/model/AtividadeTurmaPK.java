package br.com.eraumavez.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AtividadeTurmaPK implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column
	private Long atividade;
	
	@Column
	private Long turma;

	public AtividadeTurmaPK() {
	}

	public AtividadeTurmaPK(Long atividade, Long turma) {
		super();
		this.atividade = atividade;
		this.turma = turma;
	}

	public Long getAtividade() {
		return atividade;
	}

	public void setAtividade(Long atividade) {
		this.atividade = atividade;
	}

	public Long getTurma() {
		return turma;
	}

	public void setTurma(Long turma) {
		this.turma = turma;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((atividade == null) ? 0 : atividade.hashCode());
		result = prime * result + ((turma == null) ? 0 : turma.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtividadeTurmaPK other = (AtividadeTurmaPK) obj;
		if (atividade == null) {
			if (other.atividade != null)
				return false;
		} else if (!atividade.equals(other.atividade))
			return false;
		if (turma == null) {
			if (other.turma != null)
				return false;
		} else if (!turma.equals(other.turma))
			return false;
		return true;
	}

}
