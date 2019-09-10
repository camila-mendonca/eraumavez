package br.com.eraumavez.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.eraumavez.model.Ilustracao;
import br.com.eraumavez.model.Usuario;
import br.com.eraumavez.util.GerarImg;

@Repository
public class IlustracaoDAOImpl implements IlustracaoDAO{
	
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private GerarImg gerarImg;
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	@Override
	@Transactional
	public void salvar(Ilustracao ilustracao, String assinatura) throws IOException {
		Usuario usuarioLogado = usuarioDAO.obterUsuarioLogado();
		byte[] arquivo = gerarImg.gerarArquivo(assinatura);
		
		ilustracao.setArquivo(arquivo);
		ilustracao.setUsuario(usuarioLogado);
		
		em.persist(ilustracao);		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Ilustracao> listarPorUsuario() {
		Usuario usuarioLogado = usuarioDAO.obterUsuarioLogado();
		
		List<Ilustracao> ilustracoes;
		ilustracoes = em.createNamedQuery("Ilustracao.findByUser")
				.setParameter("idUsuario", usuarioLogado)
				.getResultList();
		return ilustracoes;
	}

}
