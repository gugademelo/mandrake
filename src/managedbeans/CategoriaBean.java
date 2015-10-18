package managedbeans;

import javax.faces.bean.ManagedBean;

import DAO.CategoriaDAO;
import TO.Categoria;



@ManagedBean(name= "CategoriaBean")

public class CategoriaBean {
	private Categoria categoria = new Categoria();
	CategoriaDAO dao = new CategoriaDAO();
	
	public void InsereCategoria()
	{
		dao.salva(categoria);
	}

}
