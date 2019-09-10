package br.com.eraumavez.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.eraumavez.model.Permissao;
import br.com.eraumavez.model.Professor;

@Repository
public class ProfessorDAOImpl implements ProfessorDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	PermissaoDAO permDAO;

	@Override
	@Transactional
	public void salvar(Professor professor) {
		professor.setAtivo(true);
		
		em.persist(professor);
		em.flush();
		
		permDAO.atribuirPermissao("ROLE_USUARIO", professor);
		permDAO.atribuirPermissao("ROLE_PROFESSOR", professor);
		
	}

}
