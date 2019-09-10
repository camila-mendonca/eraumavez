package br.com.eraumavez.dao;

import java.util.List;

import br.com.eraumavez.model.Atividade;
import br.com.eraumavez.model.Questao;

public interface QuestaoDAO {
	
	public void salvar(Questao questao, Atividade atividade);
	public List<Questao> listarPorAtividade(Atividade atividade);
	public void excluir(Long idQuestao);
	public void reordenar(Integer numero, Atividade atividade);

}
