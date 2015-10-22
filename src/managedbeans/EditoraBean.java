package managedbeans;


import javax.faces.bean.ManagedBean;

import DAO.EditoraDAO;
import TO.Editora;



@ManagedBean(name= "EditoraBean")
public class EditoraBean {

	Editora editora = new Editora();
	EditoraDAO dao = new EditoraDAO();
	
	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}



	public void InsereEditora()
	{
		dao.salva(editora);
	}
}
