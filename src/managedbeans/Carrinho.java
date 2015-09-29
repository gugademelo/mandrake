package managedbeans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import TO.Livro;

public class Carrinho {
	private List<Livro> livros = new ArrayList<Livro>();
	private double subTotal;
	public void adiciona(Livro livro) {
		livros.add(livro);
	}
	
	public void remove(Livro livro) {
		Iterator<Livro> iter = this.livros.iterator();
		while(iter.hasNext()){
		    if(iter.next().getLivro_id() == livro.getLivro_id())
		        iter.remove();
		}
	}
	public List<Livro> getLivros() {
		return livros;
	}
	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public double getSubTotal() {
		this.subTotal = 0;
		for(Livro livro: this.livros) {
			this.subTotal+=livro.getPreco(); 
		}
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	
}
