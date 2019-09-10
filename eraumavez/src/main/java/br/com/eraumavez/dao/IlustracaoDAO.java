package br.com.eraumavez.dao;

import java.io.IOException;
import java.util.List;

import br.com.eraumavez.model.Ilustracao;

public interface IlustracaoDAO {
	
	public void salvar(Ilustracao ilustracao,String assinatura) throws IOException;
	public List<Ilustracao> listarPorUsuario();

}
