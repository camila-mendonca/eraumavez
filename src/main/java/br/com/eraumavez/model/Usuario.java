package br.com.eraumavez.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.ListIndexBase;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import br.com.eraumavez.model.Estado;

@Entity
@Indexed
@Table(name = "usuario")
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQueries({ @NamedQuery(name = "Usuario.findAll", query = "select u from Usuario u"),
		@NamedQuery(name = "Usuario.findByLogin", query = "select u from Usuario u where u.login = :login") })
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "usuario_id", sequenceName = "usuario_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_id")
	@Column(name = "id_usuario", unique = true, nullable = false)
	private Long id;

	@Column(unique = true, nullable = false, length = 15)
	private String login;

	@Column(unique = false, nullable = false, length = 60)
	private String senha;

	@Field(index=Index.YES,analyze=Analyze.YES,store=Store.NO)
	@Column(unique = false, nullable = false, length = 45)
	private String nome;

	@Column(unique = true, nullable = false, length = 80)
	private String email;

	@Column(unique = false, nullable = false, length = 45)
	private String cidade;

	@Column(unique = false, nullable = false)
	private boolean ativo;

	@Enumerated(EnumType.STRING)
	@Column(unique = false, nullable = false)
	private Estado estado;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	private Set<Permissao> permissao;

	public Usuario() {
	}

	public Usuario(Long id, String login, String senha, String nome, String email, String cidade, boolean ativo,
			Estado estado, Set<Permissao> permissao) {
		super();
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.email = email;
		this.cidade = cidade;
		this.ativo = ativo;
		this.estado = estado;
		this.permissao = permissao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Set<Permissao> getPermissao() {
		return permissao;
	}

	public void setPermissao(Set<Permissao> permissao) {
		this.permissao = permissao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ativo ? 1231 : 1237);
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((permissao == null) ? 0 : permissao.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
		Usuario other = (Usuario) obj;
		if (ativo != other.ativo)
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (estado != other.estado)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (permissao == null) {
			if (other.permissao != null)
				return false;
		} else if (!permissao.equals(other.permissao))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}
}
