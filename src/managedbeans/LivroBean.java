package managedbeans;

import javax.faces.bean.ManagedBean;

import DAO.LivroDAO;
import TO.Livro;



@ManagedBean(name= "LivroBean")

public class LivroBean {

	Livro livro = new Livro();
	LivroDAO dao = new LivroDAO();

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	
	public void InsereLivro()
	{
		dao.salva(livro);
	}
	
	
}
