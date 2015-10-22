package managedbeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import DAO.AutorDAO;
import TO.Autor;




@ManagedBean
public class AutorBean {
	private Autor autor = new Autor();
	AutorDAO dao = new AutorDAO();
	
	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public void InsereAutor(){		
		  dao.salva(autor);
	}
	
	
	
}
