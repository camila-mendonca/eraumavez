package br.com.eraumavez.model;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="denuncia_livro")
@NamedQueries({
	@NamedQuery(name="DenunciaLivro.findAll",query="select d from DenunciaLivro d order by d.data, d.status")
})
public class DenunciaLivro implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="denuncia_livro_id",sequenceName="denuncia_livro_seq",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="denuncia_livro_id")
	@Column(name="denuncia_livro_id",unique=true,nullable=false)
	private Long id;	
	
	@Column(unique=false,nullable=false,length=150)
	private String informacoes;
	
	@Temporal(TemporalType.DATE)
	@Column(unique=false,nullable=false)
	private Date data;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="status",unique=false,nullable=false)
	private StatusDenuncia status;
	
	@ManyToOne
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JoinColumn(name="usuario",referencedColumnName="id_usuario",nullable=false)
	private Usuario usuario;
	
	@ManyToOne
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JoinColumn(name="livro",referencedColumnName="id_livro",nullable=false)
	private Livro livro;

	public DenunciaLivro() {
	}

	public DenunciaLivro(Long id, String informacoes, Date data, StatusDenuncia status, Usuario usuario, Livro livro) {
		super();
		this.id = id;
		this.informacoes = informacoes;
		this.data = data;
		this.status = status;
		this.usuario = usuario;
		this.livro = livro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public StatusDenuncia getStatus() {
		return status;
	}

	public void setStatus(StatusDenuncia status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((informacoes == null) ? 0 : informacoes.hashCode());
		result = prime * result + ((livro == null) ? 0 : livro.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		DenunciaLivro other = (DenunciaLivro) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (informacoes == null) {
			if (other.informacoes != null)
				return false;
		} else if (!informacoes.equals(other.informacoes))
			return false;
		if (livro == null) {
			if (other.livro != null)
				return false;
		} else if (!livro.equals(other.livro))
			return false;
		if (status != other.status)
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
	
	
}
