package br.com.eraumavez.dao;

import java.util.List;

import br.com.eraumavez.model.Turma;

public interface TurmaDAO {
	
	public void salvar(Turma turma);
	
	public Turma carregar(Turma turma);
	
	public List<Turma> listarPorProfessor();

}
