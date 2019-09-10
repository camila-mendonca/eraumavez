package br.com.eraumavez.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.eraumavez.dao.AlunoDAO;
import br.com.eraumavez.dao.AtividadeTurmaDAO;
import br.com.eraumavez.dao.TurmaDAO;
import br.com.eraumavez.model.Aluno;
import br.com.eraumavez.model.AtividadeTurma;
import br.com.eraumavez.model.Estado;
import br.com.eraumavez.model.Turma;

@Component
@Scope("session")
public class TurmaMB {
	
	private Turma turma;
	private Turma turmaSelecionada;
	private Estado[] estados;
	
	@Autowired
	private TurmaDAO dao;
	
	@Autowired
	private AlunoDAO alunoDAO;
	
	@Autowired
	private AtividadeTurmaDAO atividadesDAO;
	
	private List<Turma> turmas;
	private List<Aluno> alunos;
	private List<AtividadeTurma> atividades;
	
	@PostConstruct
	public void init(){
		turma = new Turma();
		turmaSelecionada = null;
		estados = Estado.values();
	}
	
	public void novaTurma(){
		this.turma = new Turma();
	}
	
	public String salvarSair(){
		this.dao.salvar(turma);
		turma = new Turma();
		this.listarPorProfessor();
		return "home";
	}
	
	public void listarPorProfessor(){
		this.turmas = this.dao.listarPorProfessor();
	}
	
	public String carregar(){
		this.turma = dao.carregar(turma);
		this.turmaSelecionada = this.turma;
		return "turma";
	}
	
	public void listarAlunos(){
		this.alunos = this.alunoDAO.listarAlunos(this.turma);
	}
	
	public void listarAtividades(){
		this.atividades = this.atividadesDAO.listarPorTurma(this.turma);
	}
	
	// GETTERS E SETTERS
	
	public Turma getTurma() {
		return turma;
	}
	
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	
	public Turma getTurmaSelecionada() {
		return turmaSelecionada;
	}

	public void setTurmaSelecionada(Turma turmaSelecionada) {
		this.turmaSelecionada = turmaSelecionada;
	}

	public List<Turma> getTurmas() {
		if(turmas==null){
			this.listarPorProfessor();
		}		
		return turmas;
	}
	
	public List<Aluno> getAlunos() {
		if(alunos == null){
			this.listarAlunos();
		}
		return alunos;
	}

	public Estado[] getEstados() {
		return estados;
	}

	public List<AtividadeTurma> getAtividades() {
		return atividades;
	}
	
	

}
