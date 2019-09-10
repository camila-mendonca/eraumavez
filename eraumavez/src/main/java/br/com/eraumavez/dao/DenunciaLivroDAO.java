package br.com.eraumavez.dao;

import java.util.List;

import br.com.eraumavez.model.DenunciaLivro;

public interface DenunciaLivroDAO {
	
	public void gerarDenuncia(DenunciaLivro denuncia);
	public List<DenunciaLivro> listarDenuncia();
	public void responder(DenunciaLivro denuncia);
	public void desconsiderar(DenunciaLivro denuncia);

}
