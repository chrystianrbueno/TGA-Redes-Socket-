package entities.server;
import java.util.ArrayList;

public class Biblioteca {
	
	private ArrayList<Livros> acervo = new ArrayList<Livros>();
	
	public Biblioteca() {
		
	}

	public ArrayList<Livros> getAcervo() {
		return acervo;
	}

	public void setAcervo(Livros livro) {
		this.acervo.add(livro);
	}
	
	
}
