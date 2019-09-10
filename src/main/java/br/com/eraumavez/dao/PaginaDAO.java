package br.com.eraumavez.dao;

import java.util.List;

import br.com.eraumavez.model.Livro;
import br.com.eraumavez.model.Pagina;

public interface PaginaDAO {
	
	public void salvar(Pagina pagina, Livro livro);
	
	public List<Pagina> listarPaginas(Livro livro);

}
