package br.com.eraumavez.dao;

import java.util.List;

import br.com.eraumavez.model.Usuario;

public interface UsuarioDAO {
	
	public void salvar(Usuario usuario) throws Exception;
	
	public Usuario obterUsuarioLogado();
	
	public Usuario buscarPorLogin(String login);
	
	public List<Usuario> buscarPorNome(String nome);
	
	public void desativar(Usuario usuario);

}
