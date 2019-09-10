package br.com.eraumavez.dao;

import java.util.List;

import br.com.eraumavez.model.Aluno;
import br.com.eraumavez.model.Convite;
import br.com.eraumavez.model.Turma;

public interface AlunoDAO {

	public void adicionar(Convite convite);

	public List<Aluno> listarAlunos(Turma turma);

}
