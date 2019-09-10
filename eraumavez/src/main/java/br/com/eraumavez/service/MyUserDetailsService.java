package br.com.eraumavez.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.eraumavez.dao.UsuarioDAO;
import br.com.eraumavez.model.Permissao;
import br.com.eraumavez.model.Usuario;

@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioDAO dao;
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = dao.buscarPorLogin(username);
		List<GrantedAuthority> autoridades = construirAutoridade(usuario.getPermissao());

		return construirUsuarioAutenticacao(usuario, autoridades);
	}

	//Pega dados do usuário e mapeia para o objeto User
	private UserDetails construirUsuarioAutenticacao(Usuario usuario, List<GrantedAuthority> autoridades) {
		return new User(usuario.getLogin(), usuario.getSenha(), true, true, true, true, autoridades);
	}

	//Pega as permissões referentes ao usuário e constrói uma lista de autoridades
	private List<GrantedAuthority> construirAutoridade(Set<Permissao> permissoes) {
		Set<GrantedAuthority> setAutoridades = new HashSet<GrantedAuthority>();
		
		for(Permissao permissao : permissoes){
			setAutoridades.add(new SimpleGrantedAuthority(permissao.getPermissao()));
		}
		
		List<GrantedAuthority> Resultado = new ArrayList<GrantedAuthority>(setAutoridades);
		
		return Resultado;
	}

	public UsuarioDAO getDao() {
		return dao;
	}

	public void setDao(UsuarioDAO dao) {
		this.dao = dao;
	}

}
