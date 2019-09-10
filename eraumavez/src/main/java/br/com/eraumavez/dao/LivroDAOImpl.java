package br.com.eraumavez.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.eraumavez.model.Livro;
import br.com.eraumavez.model.Usuario;

@Repository
public class LivroDAOImpl implements LivroDAO {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Override
	@Transactional
	public Livro salvar(Livro livro) {

		Usuario usuarioLogado = usuarioDAO.obterUsuarioLogado();
		/*
		 * if (livro.getId() == null) { livro.setQtdePaginas(0); }
		 */
		livro.setUsuario(usuarioLogado);
		livro.setDataCriacao(new Date());

		em.persist(livro);
		em.flush();

		return livro;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Livro> listarTodos() {
		List<Livro> livros = null;
		livros = em.createNamedQuery("Livro.findAll").getResultList();
		return livros;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Livro> listarPorUsuario() {
		Usuario usuarioLogado = usuarioDAO.obterUsuarioLogado();
		
		List<Livro> livros = null;
		livros = em.createNamedQuery("Livro.findByUser")
				.setParameter("idUsuario", usuarioLogado)
				.getResultList();

		return livros;
	}

	@Override
	@Transactional
	public Livro carregar(Livro livro) {
		livro = em.find(Livro.class, livro.getId());
		return livro;
	}

}
