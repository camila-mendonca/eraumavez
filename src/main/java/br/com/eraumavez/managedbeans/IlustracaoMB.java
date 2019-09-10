package br.com.eraumavez.managedbeans;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.eraumavez.dao.IlustracaoDAO;
import br.com.eraumavez.model.Ilustracao;

@Component
@Scope("session")
public class IlustracaoMB {
	
	private Ilustracao ilustracao;	
	private String assinatura;
	//private StreamedContent imagem;
	
	private String corLinha = "#000";
	private String tamanhoPincel = "3";
	private String corFundo = "#FFF";
	
	@Autowired
	private IlustracaoDAO dao;
	
	private List<Ilustracao> minhasIlustracoes;
	
	@PostConstruct
	public void init(){
		ilustracao = new Ilustracao();
	}
	
	public void novaIlustracao(){
		this.ilustracao = new Ilustracao();
		this.assinatura = null;
	}
	
	public String salvar() throws IOException{
		this.dao.salvar(ilustracao, assinatura);
		this.ilustracao = new Ilustracao();
		this.listarPorUsuario();
		return "minhasilustracoes";
	}
	
	public String cancelar(){
		this.novaIlustracao();
		return "minhasilustracoes";
	}
	
	public void listarPorUsuario() throws IOException{
		List<Ilustracao> ilustracoes = this.dao.listarPorUsuario();
		for(Ilustracao i : ilustracoes){
			String caminho;
			caminho = this.obterImagemIlustracao(i);
			i.setCaminho(caminho);
		}
		this.minhasIlustracoes = ilustracoes;
	}
	
	public String obterImagemIlustracao(Ilustracao ilustracao) throws IOException{
		/*Fonte do código exemplo:
		http://www.programcreek.com/2009/02/java-convert-image-to-byte-array-convert-byte-array-to-image/
*/		
		byte[] arquivoImg = ilustracao.getArquivo();
		ByteArrayInputStream bais = new ByteArrayInputStream(arquivoImg);
		Iterator<?> readers = ImageIO.getImageReadersByFormatName("png");
		
		ImageReader reader = (ImageReader) readers.next();
		Object source = bais;
		ImageInputStream iis = ImageIO.createImageInputStream(source);
		reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
 
        //cria arquivo de imagem
        Image image = reader.read(0, param);
        //imagem renderizada a ser escrita
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
         
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(image, null, null);
        
        //Obtém o path em que o projeto está sendo depurado
        String wildfly = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "/resources/img/user/";
        //Cria o caminho com  o path obtido, o id da ilustracao como nome do arquivo e a extensão .png
        File imageFile = new File(wildfly+ilustracao.getId()+".png");
        //Escreve o arquivo, utilizando o Graphics2D gerado a partir do arquivoImg, na extensão png, dentro do caminho gerado no imageFile
        ImageIO.write(bufferedImage, "png", imageFile);
        
        //Gera o caminho para o h:graphicImage
        String caminho = ("/resources/img/user/"+ilustracao.getId()+".png");
        return caminho;
	}
	
	//GETTERS E SETTERS

	public Ilustracao getIlustracao() {
		return ilustracao;
	}

	public void setIlustracao(Ilustracao ilustracao) {
		this.ilustracao = ilustracao;
	}

	public String getAssinatura() {
		return assinatura;
	}

	public void setAssinatura(String assinatura) {
		this.assinatura = assinatura;
	}
	
	public String getCorLinha() {
		return corLinha;
	}

	public void setCorLinha(String corLinha) {
		this.corLinha = corLinha;
	}

	public String getTamanhoPincel() {
		return tamanhoPincel;
	}

	public void setTamanhoPincel(String tamanhoPincel) {
		this.tamanhoPincel = tamanhoPincel;
	}

	public String getCorFundo() {
		return corFundo;
	}

	public void setCorFundo(String corFundo) {
		this.corFundo = corFundo;
	}

	public List<Ilustracao> getMinhasIlustracoes() throws IOException {
		if(minhasIlustracoes==null){
			this.listarPorUsuario();
		}
		return minhasIlustracoes;
	}

}
