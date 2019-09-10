package br.com.eraumavez.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.eraumavez.model.Aluno;
import br.com.eraumavez.model.AlunoPK;
import br.com.eraumavez.model.Convite;
import br.com.eraumavez.model.Turma;
import br.com.eraumavez.model.Usuario;

@Repository
public class AlunoDAOImpl implements AlunoDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void adicionar(Convite convite) {
		Aluno aluno = new Aluno();
		Usuario usuario = convite.getUsuario();
		Turma turma = convite.getTurma();
		
		aluno.setUsuario(usuario);
		aluno.setTurma(turma);
		aluno.setAlunoPK(new AlunoPK(usuario.getId(), turma.getId()));
		
		em.persist(aluno);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Aluno> listarAlunos(Turma turma) {
		List<Aluno> alunos = null;
		alunos = em.createNamedQuery("Aluno.findByTurma")
				.setParameter("idTurma", turma)
				.getResultList();
		return alunos;
	}

}
