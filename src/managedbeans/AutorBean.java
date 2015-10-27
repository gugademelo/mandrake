package managedbeans;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import DAO.AutorDAO;
import DAO.CategoriaDAO;
import TO.Autor;
import TO.Categoria;




@ManagedBean
public class AutorBean {
	private Autor autor = new Autor();
	private ArrayList<Autor> listaAutor;
	AutorDAO dao = new AutorDAO();
	
	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public String insereAutor(){		
		
		
		if (dao.salva(autor)) {
			return "resultado";			
		}else{
			return "erro";
		}
		
	}
	
	public String listar(){

		
		AutorDAO dao = new AutorDAO();
		
		listaAutor = (ArrayList<Autor>) dao.lista();
		
		return "listaAutor";
		
	}
	
	public String excluir(){

		
		AutorDAO dao = new AutorDAO();
		
		if (dao.exclui(autor.getAutor_Id())) {
			return "resultado";
		} else {
			return "erro";
		}
		
	}
	
	public String alterar(){

		
		AutorDAO dao = new AutorDAO();
		
		autor = dao.getAutorPeloId(autor.getAutor_Id());
		
		return "autor";
		
	}

	public ArrayList<Autor> getListaAutor() {
		return listaAutor;
	}

	public void setListaAutor(ArrayList<Autor> listaAutor) {
		this.listaAutor = listaAutor;
	}
	
	
	
}
