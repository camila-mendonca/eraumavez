package br.com.eraumavez.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.eraumavez.dao.ConviteDAO;
import br.com.eraumavez.model.Convite;
import br.com.eraumavez.model.Turma;
import br.com.eraumavez.model.Usuario;

@Component
@Scope("session")
public class ConviteMB {

	private Convite convite;
	private Usuario usuario;
	private Turma turma;

	@Autowired
	private ConviteDAO dao;

	@Autowired
	private TurmaMB turmaMB;

	private List<Convite> meusConvites;

	@PostConstruct
	public void init() {
		convite = new Convite();
		turma = turmaMB.getTurmaSelecionada();
	}

	public void criarConvite() {
		dao.criarConvite(convite, usuario, turma);
		this.convite = new Convite();
	}

	public void listarPorUsuario() {
		this.meusConvites = this.dao.listarPorUsuario();
	}
	
	public void aceitar(){
		dao.responder(convite, true);
	}
	
	public void recusar(){
		dao.responder(convite, false);
	}
	
	//GETTERS E SETTERS

	public Convite getConvite() {
		return convite;
	}

	public void setConvite(Convite convite) {
		this.convite = convite;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<Convite> getMeusConvites() {
		if (meusConvites == null) {
			this.listarPorUsuario();
		}
		return meusConvites;
	}

}
