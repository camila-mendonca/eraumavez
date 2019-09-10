package br.com.eraumavez.managedbeans;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.eraumavez.dao.PaginaDAO;
import br.com.eraumavez.model.Ilustracao;
import br.com.eraumavez.model.Livro;
import br.com.eraumavez.model.Pagina;

@Component
@Scope("session")
public class PaginaMB {
	
	private Pagina pagina;
	private Livro livro;
	private Ilustracao ilustracao;
	private String caminhoIlustracao;
	
	@Autowired
	private PaginaDAO dao;
	
	@Autowired
	private IlustracaoMB iluMB;
	
	@PostConstruct
	public void init(){
		pagina = new Pagina();
		
	}
	
	public String novaPagina(Livro livroSalvo){
		if(livroSalvo == null){
			return "meuslivros";
		} else {
			this.pagina = new Pagina();
			this.livro = livroSalvo;
			return "novapagina";
		}		
	}
	
	public void adicionarIlustracao(){
		RequestContext context = RequestContext.getCurrentInstance();
		boolean sucesso = false;
		try {
			this.pagina.setIlustracao(this.ilustracao);
			
			sucesso = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		context.addCallbackParam("sucesso", sucesso);
	}
	
	public void exibirIlustracao(){		
		try {
			caminhoIlustracao = this.iluMB.obterImagemIlustracao(ilustracao);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void removerIlustracao(){
		this.ilustracao = null;
		this.caminhoIlustracao = null;
		
	}
	
	public void adicionar(){
		this.salvar();
		this.pagina = new Pagina();
	}
	
	public void salvar(){
		this.dao.salvar(pagina, livro);
	}
	
	public String sair(){
		pagina = new Pagina();
		return "meuslivros";
	}
	
	//GETTERS E SETTERS

	public Pagina getPagina() {
		return pagina;
	}

	public Livro getLivro() {
		return livro;
	}
	
	public Ilustracao getIlustracao() {
		return ilustracao;
	}

	public void setIlustracao(Ilustracao ilustracao) {
		this.ilustracao = ilustracao;
	}

	public String getCaminhoIlustracao() {
		if(caminhoIlustracao==null){
			this.exibirIlustracao();
		}
		return caminhoIlustracao;
	}

	public void setCaminhoIlustracao(String caminhoIlustracao) {
		this.caminhoIlustracao = caminhoIlustracao;
	}	
	
}
