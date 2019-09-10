package br.com.eraumavez.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "livro")
@NamedQueries({ 
	@NamedQuery(name = "Livro.findAll", query = "select l from Livro l"),
	@NamedQuery(name = "Livro.findByUser", query = "select l from Livro l where l.usuario = :idUsuario") })
public class Livro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "livro_id", sequenceName = "livro_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "livro_id")
	@Column(name = "id_livro", unique = true, nullable = false)
	private Long id;

	@Column(unique = true, nullable = false, length = 45)
	private String titulo;

	@Column(unique = false, nullable = true, length = 100)
	private String descricao;

	@Formula(value = "(select count(p.*) from pagina p where p.livro = id_livro)")
	private Integer qtdePaginas;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_criacao")
	private Date dataCriacao;
	
	@Column(unique=false)
	private Boolean publico;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="usuario",referencedColumnName="id_usuario", nullable = false)
	private Usuario usuario;

	public Livro() {
	}

	public Livro(Long id, String titulo, String descricao, Integer qtdePaginas, Date dataCriacao, Boolean publico,
			Usuario usuario) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.qtdePaginas = qtdePaginas;
		this.dataCriacao = dataCriacao;
		this.publico = publico;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQtdePaginas() {
		return qtdePaginas;
	}

	public void setQtdePaginas(Integer qtdePaginas) {
		this.qtdePaginas = qtdePaginas;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Boolean getPublico() {
		return publico;
	}

	public void setPublico(Boolean publico) {
		this.publico = publico;
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
		result = prime * result + ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((publico == null) ? 0 : publico.hashCode());
		result = prime * result + ((qtdePaginas == null) ? 0 : qtdePaginas.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		Livro other = (Livro) obj;
		if (dataCriacao == null) {
			if (other.dataCriacao != null)
				return false;
		} else if (!dataCriacao.equals(other.dataCriacao))
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
		if (publico == null) {
			if (other.publico != null)
				return false;
		} else if (!publico.equals(other.publico))
			return false;
		if (qtdePaginas == null) {
			if (other.qtdePaginas != null)
				return false;
		} else if (!qtdePaginas.equals(other.qtdePaginas))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}	
	
}
