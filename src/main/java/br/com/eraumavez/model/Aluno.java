package br.com.eraumavez.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="aluno")
@NamedQueries({
	@NamedQuery(name="Aluno.findByTurma",query="select a from Aluno a where a.turma = :idTurma")
})
public class Aluno implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private AlunoPK alunoPK;
	
	@Column(nullable=true,length=45)
	private String ra;
	
	@ManyToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name="turma",referencedColumnName="id_turma",insertable = false, updatable = false)	
	private Turma turma;
	
	@ManyToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name="usuario",referencedColumnName="id_usuario",insertable = false, updatable = false)
	private Usuario usuario;

	
	public Aluno() {
	}


	public AlunoPK getAlunoPK() {
		return alunoPK;
	}

	public void setAlunoPK(AlunoPK alunoPK) {
		this.alunoPK = alunoPK;
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alunoPK == null) ? 0 : alunoPK.hashCode());
		result = prime * result + ((ra == null) ? 0 : ra.hashCode());
		result = prime * result + ((turma == null) ? 0 : turma.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Aluno other = (Aluno) obj;
		if (alunoPK == null) {
			if (other.alunoPK != null)
				return false;
		} else if (!alunoPK.equals(other.alunoPK))
			return false;
		if (ra == null) {
			if (other.ra != null)
				return false;
		} else if (!ra.equals(other.ra))
			return false;
		if (turma == null) {
			if (other.turma != null)
				return false;
		} else if (!turma.equals(other.turma))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
	
	
}
