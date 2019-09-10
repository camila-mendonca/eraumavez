package br.com.eraumavez.dao;

import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.eraumavez.model.Usuario;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	PermissaoDAO permDAO;

	@Override
	@Transactional
	public void salvar(Usuario usuario) throws Exception {
		/*
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		String senhaCodificada = pe.encode(usuario.getSenha());
		usuario.setSenha(senhaCodificada);
		*/
		usuario.setAtivo(true);
		
		// this.enviarEmailCadastro(usuario);

		em.persist(usuario);
		// para poder pegar o id do usuario persistido
		em.flush();

		permDAO.atribuirPermissao("ROLE_USUARIO", usuario);
	}

	@Override
	@Transactional
	public Usuario buscarPorLogin(String login) {
		Usuario usuario = null;
		usuario = (Usuario) em.createNamedQuery("Usuario.findByLogin").setParameter("login", login).getSingleResult();
		return usuario;
	}

	@Override
	@Transactional
	public Usuario obterUsuarioLogado() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		String login = external.getRemoteUser();
		System.out.println("Login do usuário ativo: " + login);
		
		if (login != null) {
			return this.buscarPorLogin(login);
		} else
			return null;
	}

	@Override
	@Transactional
	public List<Usuario> buscarPorNome(String nome) {
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
		try{
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (InterruptedException e){
			e.printStackTrace();
		}
		
		QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Usuario.class).get();
		Query query = qb.keyword().onFields("nome").matching(nome).createQuery();
		
		javax.persistence.Query persistenceQuery = fullTextEntityManager.createFullTextQuery(query, Usuario.class);
		
		List<Usuario> resultado = persistenceQuery.getResultList();
		
		return resultado;
	}

	@Override
	@Transactional
	public void desativar(Usuario usuario) {
		usuario.setAtivo(false);
		em.persist(usuario);		
	}

}
