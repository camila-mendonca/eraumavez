package br.com.eraumavez.managedbeans;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.eraumavez.dao.ProfessorDAO;
import br.com.eraumavez.model.Estado;
import br.com.eraumavez.model.Professor;

@Component
@Scope("session")
public class ProfessorMB {
	
	private Professor professor;
	
	private Estado[] estados;
	
	@Autowired
	private ProfessorDAO dao;
	
	@PostConstruct
	public void init(){
		professor = new Professor();
		estados = Estado.values();
	}
	
	public String salvar(){
		try {
			this.dao.salvar(professor);
			professor = new Professor();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "index";
	}

	public Professor getProfessor() {
		return professor;
	}

	public Estado[] getEstados() {
		return estados;
	}

}
