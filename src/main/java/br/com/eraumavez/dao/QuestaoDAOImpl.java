package br.com.eraumavez.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.eraumavez.model.Atividade;
import br.com.eraumavez.model.Questao;

@Repository
public class QuestaoDAOImpl implements QuestaoDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void salvar(Questao questao, Atividade atividade) {
		if (questao.getId() == null) {
			Integer n = ((Number) em.createNamedQuery("Questao.countQuestoesAtividade")
					.setParameter("idAtividade", atividade).getSingleResult()).intValue();
			questao.setNumero(n+1);
			questao.setAtividade(atividade);
			em.persist(questao);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Questao> listarPorAtividade(Atividade atividade) {
		List<Questao> atividades = null;
		atividades = em.createNamedQuery("Questao.findByAtividade").setParameter("idAtividade", atividade)
				.getResultList();
		return atividades;
	}

	@Override
	@Transactional
	public void excluir(Long idQuestao) {
		Questao questao = em.getReference(Questao.class, idQuestao);
		Integer numero = questao.getNumero();
		Atividade atividade = questao.getAtividade();
		em.remove(questao);
		this.reordenar(numero, atividade);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void reordenar(Integer numero, Atividade atividade) {
		List<Questao> questoes = null;
		questoes = em.createNamedQuery("Questao.findAfterNumeroPagina").setParameter("idAtividade", atividade)
				.setParameter("numero", numero).getResultList();
		for (Questao q : questoes) {
			Integer pastNum = q.getNumero();
			Integer newNum = pastNum - 1;
			q.setNumero(newNum);
			em.merge(q);
		}
	}

}
