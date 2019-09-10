package br.com.eraumavez.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "atividade_turma")
@NamedQueries({
		@NamedQuery(name = "AtividadeTurma.findByTurma", query = "select atv from AtividadeTurma atv where atv.turma = :idTurma") })
public class AtividadeTurma implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AtividadeTurmaPK atividadeTurmaPK;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_inicio", unique = false, nullable = false)
	private Date dataInicio;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_termino", unique = false, nullable = false)
	private Date dataTermino;
	
	@ManyToOne
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JoinColumn(name="atividade",referencedColumnName="id_atividade",nullable=false,insertable=false,updatable=false)
	private Atividade atividade;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JoinColumn(name = "turma", referencedColumnName = "id_turma", nullable = false, insertable = false, updatable = false)
	private Turma turma;	

	public AtividadeTurma() {
	}

	public AtividadeTurmaPK getAtividadeTurmaPK() {
		return atividadeTurmaPK;
	}

	public void setAtividadeTurmaPK(AtividadeTurmaPK atividadeTurmaPK) {
		this.atividadeTurmaPK = atividadeTurmaPK;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	
	

		

}
