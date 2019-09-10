package br.com.eraumavez.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.eraumavez.dao.AtividadeDAO;
import br.com.eraumavez.dao.AtividadeTurmaDAO;
import br.com.eraumavez.model.Atividade;
import br.com.eraumavez.model.AtividadeTurma;
import br.com.eraumavez.model.Turma;

@Component
@Scope("session")
public class AtividadeTurmaMB {
	
	private AtividadeTurma atividadeTurma;
	private Atividade atividade;
	private Turma turma;
	
	private List<Atividade> atividades;
	
	@Autowired
	private AtividadeTurmaDAO dao;
	
	@Autowired
	private AtividadeDAO atividadeDAO;
	
	@Autowired
	private TurmaMB turmaMB;
	
	@PostConstruct
	public void init(){
		atividadeTurma = new AtividadeTurma();
		turma = turmaMB.getTurma();
		listarAtividades();
	}
	
	public void atribuir(){
		dao.salvar(atividadeTurma, atividade, turma);
	}
	
	public void listarAtividades(){
		this.atividades = atividadeDAO.listarPorProfessor();		
	}
	
	// GETTERS E SETTERS

	public AtividadeTurma getAtividadeTurma() {
		return atividadeTurma;
	}

	public void setAtividadeTurma(AtividadeTurma atividadeTurma) {
		this.atividadeTurma = atividadeTurma;
	}	

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public List<Atividade> getAtividades() {
		if (atividades == null){
			this.listarAtividades();
		}
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}	

}
