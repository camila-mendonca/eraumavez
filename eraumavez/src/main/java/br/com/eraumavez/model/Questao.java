package br.com.eraumavez.model;

import java.io.Serializable;

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
@Table(name="questao")
@NamedQueries({
	@NamedQuery(name="Questao.findByAtividade",query="select q from Questao q where q.atividade = :idAtividade order by q.numero"),
	@NamedQuery(name="Questao.findAfterNumeroPagina",query="select q from Questao q where q.atividade = :idAtividade and q.numero > :numero"),
	@NamedQuery(name="Questao.countQuestoesAtividade",query="select count(q) from Questao q where q.atividade = :idAtividade")
})
public class Questao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="questao_id",sequenceName="questao_seq",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="questao_id")
	@Column(name="id_questao",unique=true,nullable=false)
	private Long id;
	
	@Column(unique=false,nullable=false)
	private Integer numero;
	
	@Column(unique=false,nullable=false,length=150)
	private String enunciado;
	
	@ManyToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name="atividade",referencedColumnName="id_atividade",nullable=false)
	private Atividade atividade;

	public Questao() {
	}

	public Questao(Long id, Integer numero, String enunciado, Atividade atividade) {
		super();
		this.id = id;
		this.numero = numero;
		this.enunciado = enunciado;
		this.atividade = atividade;
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

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((atividade == null) ? 0 : atividade.hashCode());
		result = prime * result + ((enunciado == null) ? 0 : enunciado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		Questao other = (Questao) obj;
		if (atividade == null) {
			if (other.atividade != null)
				return false;
		} else if (!atividade.equals(other.atividade))
			return false;
		if (enunciado == null) {
			if (other.enunciado != null)
				return false;
		} else if (!enunciado.equals(other.enunciado))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}
}
