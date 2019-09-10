package br.com.eraumavez.dao;

import java.util.List;

import br.com.eraumavez.model.Atividade;

public interface AtividadeDAO {
	
	public Atividade salvar(Atividade atividade);
	
	public List<Atividade> listarPorProfessor();
	
	public Atividade carregar(Atividade atividade);

}
