package br.com.eraumavez.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.eraumavez.dao.LivroDAO;
import br.com.eraumavez.dao.PaginaDAO;
import br.com.eraumavez.model.Livro;
import br.com.eraumavez.model.Pagina;

@Component
@Scope("session")
public class LivroMB {

	private Livro livro;
	private Livro livroSalvo;

	@Autowired
	private LivroDAO dao;

	@Autowired
	private PaginaDAO pgDAO;

	@Autowired
	private PaginaMB pgMB;

	private List<Livro> livros;
	private List<Livro> meusLivros;
	private List<Pagina> paginas;

	@PostConstruct
	public void init() {
		livro = new Livro();
		// this.listarPorUsuario();
	}

	public String salvarSair() {
		this.dao.salvar(livro);
		livro = new Livro();
		// Lista os livros novamente para puxar o novo cadastro do banco
		this.listarPorUsuario();
		return "meuslivros";
	}

	public String salvarCriarPagina() {
		this.livroSalvo = new Livro();
		livroSalvo = this.dao.salvar(livro);
		this.livro = new Livro();
		pgMB.novaPagina(livroSalvo);
		return "novapagina";
	}

	public String sair() {
		livro = new Livro();
		return "meuslivros";
	}

	public void listarTodos() {
		this.livros = this.dao.listarTodos();
	}

	public void listarPorUsuario() {
		this.meusLivros = this.dao.listarPorUsuario();
	}

	public String carregar() {
		this.livro = dao.carregar(livro);
		return "livro";
	}

	public void listarPaginas() {
		this.paginas = this.pgDAO.listarPaginas(this.livro);
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Livro getLivroSalvo() {
		return livroSalvo;
	}

	public List<Livro> getLivros() {
		if (livros == null) {
			this.listarTodos();
		}
		return livros;
	}

	public List<Livro> getMeusLivros() {
		if (meusLivros == null) {
			this.listarPorUsuario();
		}
		return meusLivros;
	}

	public List<Pagina> getPaginas() {
		if (paginas == null) {
			this.listarPaginas();
		}
		return paginas;
	}

}
