package br.com.eraumavez.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.eraumavez.model.Atividade;
import br.com.eraumavez.model.Usuario;

@Repository
public class AtividadeDAOImpl implements AtividadeDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Override
	@Transactional
	public Atividade salvar(Atividade atividade) {
		if (atividade.getId()==null){
			Usuario usuarioLogado = usuarioDAO.obterUsuarioLogado();		
			atividade.setProfessor(usuarioLogado);
			atividade.setDataCriacao(new Date());
			em.persist(atividade);
			em.flush();
		}
		return atividade;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Atividade> listarPorProfessor() {
		Usuario usuarioLogado = usuarioDAO.obterUsuarioLogado();
		
		List<Atividade> atividades = null;
		atividades = em.createNamedQuery("Atividade.findByProfessor")
				.setParameter("idUsuario", usuarioLogado)
				.getResultList();
		return atividades;
	}

	@Override
	public Atividade carregar(Atividade atividade) {
		// TODO Auto-generated method stub
		return null;
	}

}
