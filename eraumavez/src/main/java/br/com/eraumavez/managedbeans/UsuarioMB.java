package br.com.eraumavez.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.eraumavez.dao.UsuarioDAO;
import br.com.eraumavez.model.Estado;
import br.com.eraumavez.model.Usuario;

@Component
@Scope("session")
public class UsuarioMB {

	private Usuario usuario;	
	private Estado[] estados;
	private String nomeBusca;
	private List<Usuario> resultadoBusca;
	
	@Autowired
	private UsuarioDAO dao;
	
	@PostConstruct
	public void init(){
		usuario = new Usuario();
		estados = Estado.values();
		resultadoBusca = null;
	}
	
	public void salvar(){
		try{
			this.dao.salvar(usuario);
			usuario = new Usuario();
		} catch (Exception e) {
			System.out.println("Erro ao cadastrar usuario!");
			e.printStackTrace();
		}
	}
	
	public void buscar(){
		this.resultadoBusca = this.dao.buscarPorNome(nomeBusca);
		
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public Estado[] getEstados() {
		return estados;
	}

	public String getNomeBusca() {
		return nomeBusca;
	}

	public void setNomeBusca(String nomeBusca) {
		this.nomeBusca = nomeBusca;
	}

	public List<Usuario> getResultadoBusca() {
		return resultadoBusca;
	}

	public void setResultadoBusca(List<Usuario> resultadoBusca) {
		this.resultadoBusca = resultadoBusca;
	}
		
}
