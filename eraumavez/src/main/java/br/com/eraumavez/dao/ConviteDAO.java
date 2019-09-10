package br.com.eraumavez.dao;

import java.util.List;

import br.com.eraumavez.model.Convite;
import br.com.eraumavez.model.Turma;
import br.com.eraumavez.model.Usuario;

public interface ConviteDAO {

	public void criarConvite(Convite convite, Usuario usuario, Turma turma);
	
	public List<Convite> listarPorUsuario();
	
	public List<Convite> listarPorTurma(Turma turma);
	
	public void responder(Convite convite, Boolean resposta);
	
	public void cancelar(Convite convite);
}
