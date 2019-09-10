package br.com.eraumavez.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.eraumavez.model.Convite;
import br.com.eraumavez.model.StatusConvite;
import br.com.eraumavez.model.Turma;
import br.com.eraumavez.model.Usuario;

@Repository
public class ConviteDAOImpl implements ConviteDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	UsuarioDAO usuarioDAO;
	
	@Autowired
	AlunoDAO alunoDAO;

	@Override
	@Transactional
	public void criarConvite(Convite convite, Usuario usuario, Turma turma) {
		convite.setUsuario(usuario);
		convite.setTurma(turma);
		convite.setStatusConvite(StatusConvite.Espera);
		em.persist(convite);

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Convite> listarPorUsuario() {
		Usuario usuarioLogado = usuarioDAO.obterUsuarioLogado();
		List<Convite> convites = null;
		convites = em.createNamedQuery("Convite.findByUser")
				.setParameter("idUsuario", usuarioLogado)
				.getResultList();
		return convites;
	}

	@Override
	@Transactional
	public void responder(Convite convite, Boolean resposta) {
		if(resposta==true){
			convite.setStatusConvite(StatusConvite.Aceito);
			alunoDAO.adicionar(convite);
		} else {
			convite.setStatusConvite(StatusConvite.Recusado);
		}
		em.merge(convite);
	}

	@Override
	public List<Convite> listarPorTurma(Turma turma) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelar(Convite convite) {
		// TODO Auto-generated method stub
		
	}

}
