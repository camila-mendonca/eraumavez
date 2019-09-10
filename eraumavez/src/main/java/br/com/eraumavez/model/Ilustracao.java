package br.com.eraumavez.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.primefaces.model.StreamedContent;

@Entity
@Table(name="ilustracao")
@NamedQueries({
	@NamedQuery(name="Ilustracao.findByUser",query="select i from Ilustracao i where i.usuario = :idUsuario")
})
public class Ilustracao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="ilustracao_id",sequenceName="ilustracao_seq",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ilustracao_id")
	@Column(name="id_ilustracao",unique=true,nullable=false)
	private Long id;
	
	@Column(unique=false,nullable=true,length=45)
	private String descricao;
	
	@Lob
	@Column(unique=false,nullable=false)
	private byte[] arquivo;
	
	@ManyToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name="usuario",referencedColumnName="id_usuario",nullable=false)
	private Usuario usuario;
	
	@Transient
	private String caminho;

	public Ilustracao() {
		
	}

	public Ilustracao(Long id, String descricao, byte[] arquivo, Usuario usuario, String caminho) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.arquivo = arquivo;
		this.usuario = usuario;
		this.caminho = caminho;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(arquivo);
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Ilustracao other = (Ilustracao) obj;
		if (!Arrays.equals(arquivo, other.arquivo))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}	

}
