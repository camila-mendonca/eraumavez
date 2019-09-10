package br.com.eraumavez.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.eraumavez.model.Atividade;
import br.com.eraumavez.model.AtividadeTurma;
import br.com.eraumavez.model.AtividadeTurmaPK;
import br.com.eraumavez.model.Turma;

@Repository
public class AtividadeTurmaDAOImpl implements AtividadeTurmaDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void salvar(AtividadeTurma atividadeTurma, Atividade atividade, Turma turma) {
		
		atividadeTurma.setAtividadeTurmaPK(new AtividadeTurmaPK(atividade.getId(),turma.getId()));
		atividadeTurma.setDataInicio(new Date());
		
		em.persist(atividadeTurma);

	}

	@Override
	@Transactional
	public void cancelar(AtividadeTurma atividadeTurma) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<AtividadeTurma> listarPorTurma(Turma turma) {
		List<AtividadeTurma> atividades = null;
		atividades = em.createNamedQuery("AtividadeTurma.findByTurma")
				.setParameter("idTurma", turma)
				.getResultList();
		return atividades;
	}

}
