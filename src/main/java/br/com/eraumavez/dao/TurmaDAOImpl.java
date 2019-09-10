package br.com.eraumavez.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.eraumavez.model.Professor;
import br.com.eraumavez.model.Turma;
import br.com.eraumavez.model.Usuario;

@Repository
public class TurmaDAOImpl implements TurmaDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	@Override
	@Transactional
	public void salvar(Turma turma) {
		Usuario usuarioLogado = usuarioDAO.obterUsuarioLogado();
		
		turma.setProfessor(usuarioLogado);
		
		em.persist(turma);
	}

	@Override
	@Transactional
	public List<Turma> listarPorProfessor() {
		Usuario usuarioLogado = usuarioDAO.obterUsuarioLogado();
		
		List<Turma> turmas = null;
		turmas = em.createNamedQuery("Turma.findByProfessor")
				.setParameter("idProfessor", usuarioLogado)
				.getResultList();
		
		return turmas;
	}

	@Override
	@Transactional
	public Turma carregar(Turma turma) {
		turma = em.find(Turma.class, turma.getId());
		return turma;
	}

}
