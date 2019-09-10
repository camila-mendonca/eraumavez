package br.com.eraumavez.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="pagina")
@NamedQueries({
	@NamedQuery(name="Pagina.findByLivro",query="select p from Pagina p where p.livro = :idLivro order by p.numero")
})
public class Pagina implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="pagina_id",sequenceName="pagina_seq",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pagina_id")
	@Column(name="id_pagina",unique=true,nullable=false)
	private Long id;
	
	@Column(unique=false,nullable=false)
	private Integer numero;
	
	@Column(unique=false,nullable=true,length=970)
	private String texto;
	
	@ManyToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name="livro",referencedColumnName="id_livro")
	private Livro livro;
	
	@ManyToOne
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JoinColumn(name="ilustracao",referencedColumnName="id_ilustracao",nullable=true)
	private Ilustracao ilustracao;

	public Pagina() {	}

	public Pagina(Long id, Integer numero, String texto, Livro livro, Ilustracao ilustracao) {
		super();
		this.id = id;
		this.numero = numero;
		this.texto = texto;
		this.livro = livro;
		this.ilustracao = ilustracao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Ilustracao getIlustracao() {
		return ilustracao;
	}

	public void setIlustracao(Ilustracao ilustracao) {
		this.ilustracao = ilustracao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ilustracao == null) ? 0 : ilustracao.hashCode());
		result = prime * result + ((livro == null) ? 0 : livro.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((texto == null) ? 0 : texto.hashCode());
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
		Pagina other = (Pagina) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ilustracao == null) {
			if (other.ilustracao != null)
				return false;
		} else if (!ilustracao.equals(other.ilustracao))
			return false;
		if (livro == null) {
			if (other.livro != null)
				return false;
		} else if (!livro.equals(other.livro))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		return true;
	}
	
}
