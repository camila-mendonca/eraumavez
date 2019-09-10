package br.com.eraumavez.dao;

import java.util.List;

import br.com.eraumavez.model.Livro;

public interface LivroDAO {
	
public Livro salvar(Livro livro);

	public List<Livro> listarTodos();
	
	public List<Livro> listarPorUsuario();
	
	public Livro carregar(Livro livro);

}
