package br.com.eraumavez.dao;

import br.com.eraumavez.model.Resposta;

public interface RespostaDAO {
	
	public void salvar(Resposta resposta);
	public void corrigir(Resposta resposta);
}
