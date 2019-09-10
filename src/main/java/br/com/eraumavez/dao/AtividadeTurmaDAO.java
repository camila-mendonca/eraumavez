package br.com.eraumavez.dao;

import java.util.List;

import br.com.eraumavez.model.Atividade;
import br.com.eraumavez.model.AtividadeTurma;
import br.com.eraumavez.model.Turma;

public interface AtividadeTurmaDAO {
	
	public void salvar(AtividadeTurma atividadeTurma, Atividade atividade, Turma turma);
	public void cancelar(AtividadeTurma atividadeTurma);
	public List<AtividadeTurma> listarPorTurma(Turma turma);

}
