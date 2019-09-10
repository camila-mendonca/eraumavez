package br.com.eraumavez.managedbeans;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.eraumavez.model.Aluno;
import br.com.eraumavez.model.Turma;

@Component
@Scope("session")
public class AlunoMB {
	
	private Aluno aluno;	
	private Turma turma;
	
	@PostConstruct
	public void init(){
		aluno = new Aluno();
	}
	
	public void convidarAluno(){
		
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	
	

}
