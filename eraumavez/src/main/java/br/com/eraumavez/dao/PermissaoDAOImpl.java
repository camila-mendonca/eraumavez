package br.com.eraumavez.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.eraumavez.model.Permissao;
import br.com.eraumavez.model.Usuario;

@Repository
public class PermissaoDAOImpl implements PermissaoDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void atribuirPermissao(String permissao, Object usuario) {
		Permissao per = new Permissao();
		per.setUsuario((Usuario) usuario);
		per.setPermissao(permissao);
		em.persist(per);
	}

}
