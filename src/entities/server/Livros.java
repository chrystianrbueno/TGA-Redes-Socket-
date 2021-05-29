package entities.server;
import java.util.ArrayList;

public class Livros {
	private String tituloLivro;
	private String autorLivro;
	private String editoraLivro;
	private int qtdLivro;
	
	Biblioteca acervo = new Biblioteca();
	
	public Livros(String tituloLivro, String autorLivro, String editoraLivro, int qtdLivro){
		this.tituloLivro = tituloLivro;
		this.autorLivro = autorLivro;
		this.editoraLivro = editoraLivro;
		this.qtdLivro = qtdLivro;
	}
	
	public Livros() {
		// TODO Auto-generated constructor stub
	}
	
	public void setLivro(Livros addLivro) {
		acervo.setAcervo(addLivro);
	}
	
	public void addLivros() {
		Livros livro1 = new Livros ("Livro1", "Autor1", "Editora1", 10);
		Livros livro2 = new Livros ("Livro2", "Autor2", "Editora1", 1);
		Livros livro3 = new Livros ("Livro3", "Autor1", "Editora3", 0);
		Livros livro4 = new Livros ("Livro4", "Autor4", "Editora1", 10);
		Livros livro5 = new Livros ("Livro5", "Autor4", "Editora1", 0);
		Livros livro6 = new Livros ("Livro6", "Autor4", "Editora1", 50);
		Livros livro7 = new Livros ("Livro7", "Autor4", "Editora1", 20);
		Livros livro8 = new Livros ("Livro8", "Autor4", "Editora1", 0);


		acervo.setAcervo(livro1);
		acervo.setAcervo(livro2);
		acervo.setAcervo(livro3);
		acervo.setAcervo(livro4);		
		acervo.setAcervo(livro5);		
		acervo.setAcervo(livro6);		
		acervo.setAcervo(livro7);		
		acervo.setAcervo(livro8);		
	}
	
	public String listaLivros() {
		ArrayList<Livros> lista = acervo.getAcervo();
		String listaRetorno = "";

		for(int i=0; i < lista.size(); i++) {
			listaRetorno += "Titulo: " + lista.get(i).tituloLivro + " " + "Autor: " + lista.get(i).autorLivro + " " + "Editora: "+ lista.get(i).editoraLivro + " Quant: " + lista.get(i).qtdLivro + " #";
		}
		
		return listaRetorno;
	}
	
	public String pesqAutor(String autor) {
		ArrayList<Livros> lista = acervo.getAcervo();
		String listaRetorno = "";
		for(int i=0; i < lista.size(); i++) {
			if(lista.get(i).autorLivro.equals(autor)) {
				listaRetorno += "Titulo: " + lista.get(i).tituloLivro + " " + "Autor: " + lista.get(i).autorLivro + " " + "Editora: "+ lista.get(i).editoraLivro + " Quant: " + lista.get(i).qtdLivro + " #";
			}
		}
		
		if(listaRetorno == "") {
			return "Nenhum Livro encontrado";
		}else {
			return listaRetorno;
			
		}
			
	}
	
	public String pesqTitulo(String titulo) {
		ArrayList<Livros> lista = acervo.getAcervo();
		String listaRetorno = "";
		for(int i=0; i < lista.size(); i++) {
			if(lista.get(i).tituloLivro.equals(titulo)) {
				listaRetorno += "Titulo: " + lista.get(i).tituloLivro + " " + "Autor: " + lista.get(i).autorLivro + " " + "Editora: "+ lista.get(i).editoraLivro + " Quant: " + lista.get(i).qtdLivro + " #";
			}
		}
		
		if(listaRetorno == "") {
			return "Nenhum Livro encontrado";
		}else {
			return listaRetorno;
		}
			
	}
	public String pesqEditora(String editora) {
		ArrayList<Livros> lista = acervo.getAcervo();
		String listaRetorno = "";
		for(int i=0; i < lista.size(); i++) {
			if(lista.get(i).editoraLivro.equals(editora)) {
				listaRetorno += "Titulo: " + lista.get(i).tituloLivro + " " + "Autor: " + lista.get(i).autorLivro + " " + "Editora: "+ lista.get(i).editoraLivro + " Quant: " + lista.get(i).qtdLivro + " #";
			}
		}
		
		if(listaRetorno == "") {
			return "Nenhum Livro encontrado";
		}else {
			return listaRetorno;
		}
			
	}
	
	public String pesqLivrosEmFalta() {
		ArrayList<Livros> lista = acervo.getAcervo();
		String listaRetorno = "";
		for(int i=0; i < lista.size(); i++) {
			if(lista.get(i).qtdLivro == 0) {
				listaRetorno += "Titulo: " + lista.get(i).tituloLivro + " " + "Autor: " + lista.get(i).autorLivro + " " + "Editora: "+ lista.get(i).editoraLivro + " Quant: " + lista.get(i).qtdLivro + " #";
			}
		}
		
		if(listaRetorno == "") {
			return "Nenhum Livro encontrado";
		}else {
			return listaRetorno;
		}
			
	}

	
	public boolean livroExistente(String testaTitulo) {
		for(int i = 0; i < acervo.getAcervo().size();i++) {
			if(acervo.getAcervo().get(i).tituloLivro.equals(testaTitulo)){
				return true;
			}
		}

		return false;

	}
	
	public String retiradaLivro(String tituloLivro) {
		String antesRet = "";
		for(int i = 0; i < acervo.getAcervo().size();i++) {
			if(acervo.getAcervo().get(i).tituloLivro.equals(tituloLivro)){
				if(acervo.getAcervo().get(i).qtdLivro == 0) {
					antesRet = "#Esse livro não se encontra em estoque #";
				}else {
					antesRet = "#Antes da retirada#" + pesqTitulo(tituloLivro) + "#Apos a retirada #";
					acervo.getAcervo().get(i).qtdLivro--;
				}
			}
		}

		return antesRet + pesqTitulo(tituloLivro);

	}

	public String devolucaoLivro(String tituloLivro) {
		String antesRet = "";
		for(int i = 0; i < acervo.getAcervo().size();i++) {
			if(acervo.getAcervo().get(i).tituloLivro.equals(tituloLivro)){
				antesRet = "#Antes da devolucao#" + pesqTitulo(tituloLivro) + "#Apos a devolucao #";
				acervo.getAcervo().get(i).qtdLivro++;
			}
		}

		return antesRet + pesqTitulo(tituloLivro);

	}
}