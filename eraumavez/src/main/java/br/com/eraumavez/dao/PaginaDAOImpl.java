package br.com.eraumavez.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.eraumavez.model.Livro;
import br.com.eraumavez.model.Pagina;

@Repository
public class PaginaDAOImpl implements PaginaDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void salvar(Pagina pagina, Livro livro) {
		pagina.setNumero(1);
		pagina.setLivro(livro);
		em.persist(pagina);
		em.flush();

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Pagina> listarPaginas(Livro livro) {
		List<Pagina> paginas = null;
		paginas = em.createNamedQuery("Pagina.findByLivro").setParameter("idLivro", livro).getResultList();
		return paginas;
	}

}
