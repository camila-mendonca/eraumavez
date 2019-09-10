package br.com.eraumavez.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.eraumavez.dao.AtividadeDAO;
import br.com.eraumavez.dao.LivroDAO;
import br.com.eraumavez.dao.QuestaoDAO;
import br.com.eraumavez.model.Atividade;
import br.com.eraumavez.model.Livro;
import br.com.eraumavez.model.Questao;

@Component
@Scope("session")
public class AtividadeMB {
	
	private Atividade atividade;
	
	@Autowired
	private AtividadeDAO dao;
	
	@Autowired
	private LivroDAO livroDAO;
	
	@Autowired
	private QuestaoDAO questaoDAO;
	
	private List<Atividade> atividades;
	//para selecionar o livro relacionado a atividade
	private List<Livro> livros;
	private List<Questao> questoes;
	
	@PostConstruct
	public void init(){
		atividade = new Atividade();
		livros = this.listarLivros();
	}
	
	public void novaAtividade(){
		this.atividade = new Atividade();
	}
	
	public String salvarAbrir(){
		Atividade atividadeSalva = this.dao.salvar(atividade);
		this.atividade.setId(atividadeSalva.getId());
		return "administraratividade";
	}
	
	public String salvarSair(){
		this.dao.salvar(atividade);
		return "home";
	}
	
	public void listarPorUsuario() {
		this.atividades = dao.listarPorProfessor();
	}
	
	public String carregar(){
		return "administraratividade";
	}
	
	public List<Livro> listarLivros(){
		List<Livro> livros = livroDAO.listarPorUsuario();
		return livros;
	}
	
	public void listarQuestoes(){
		this.questoes = this.questaoDAO.listarPorAtividade(this.atividade);
	}
	
	public String cancelar(){
		atividade = new Atividade();
		return "home";
	}
	
	//GETTERS E SETTERS

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public List<Atividade> getAtividades() {
		if (atividades == null){
			this.listarPorUsuario();
		}
		return atividades;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public List<Questao> getQuestoes() {
		if(questoes==null){
			this.listarQuestoes();
		}
		return questoes;
	}	
	
}
