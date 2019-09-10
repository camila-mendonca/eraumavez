package br.com.eraumavez.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="convite")
@NamedQueries({
	@NamedQuery(name="Convite.findByUser",query="select c from Convite c where c.usuario = :idUsuario and c.status = 0"),
	@NamedQuery(name="Convite.findByStatus",query="select c from Convite c where c.status = :status")
})
public class Convite implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "convite_id", sequenceName = "convite_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "convite_id")
	@Column(name = "id_convite", unique = true, nullable = false)
	private Long id;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="status",unique=false,nullable=false)
	private StatusConvite status;
	
	@ManyToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name="usuario",referencedColumnName="id_usuario",nullable=false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="turma",referencedColumnName="id_turma",nullable=false)
	private Turma turma;

	public Convite() {
	}

	public Convite(Long id, StatusConvite statusConvite, Usuario usuario, Turma turma) {
		super();
		this.id = id;
		this.status = statusConvite;
		this.usuario = usuario;
		this.turma = turma;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StatusConvite getStatusConvite() {
		return status;
	}

	public void setStatusConvite(StatusConvite statusConvite) {
		this.status = statusConvite;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Convite other = (Convite) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status != other.status)
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
