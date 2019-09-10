package br.com.eraumavez.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="permissao")
public class Permissao {
	
	@Id
	@SequenceGenerator(name="permissao_id",sequenceName="permissao_seq",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="permissao_id")
	@Column(name="id_permissao",unique=true,nullable=false)
	private Integer id;
	
	@Column(nullable=false,length=45)
	private String permissao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_usuario",nullable=false)
	private Usuario usuario;

	public Integer getPermissaoId() {
		return id;
	}

	public void setPermissaoId(Integer permissaoId) {
		this.id = permissaoId;
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
