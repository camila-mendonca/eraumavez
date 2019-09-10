package br.com.eraumavez.managedbeans;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.eraumavez.dao.QuestaoDAO;
import br.com.eraumavez.model.Atividade;
import br.com.eraumavez.model.Questao;

@Component
@Scope("session")
public class QuestaoMB {
	
	private Questao questao;
	private Atividade atividade;
	
	@Autowired
	private QuestaoDAO dao;
	
	@Autowired
	private AtividadeMB atividadeMB;
	
	@PostConstruct
	public void init(){
		this.questao = new Questao();
	}
	
	public void salvar(){
		this.dao.salvar(questao, atividade);
		this.questao = new Questao();
		atividadeMB.listarQuestoes();
	}
	
	public void excluir(){
		this.dao.excluir(this.questao.getId());
		this.questao = new Questao();
		atividadeMB.listarQuestoes();
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

}
