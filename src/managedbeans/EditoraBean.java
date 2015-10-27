package managedbeans;


import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

import DAO.AutorDAO;
import DAO.EditoraDAO;
import TO.Autor;
import TO.Editora;



@ManagedBean(name= "EditoraBean")
public class EditoraBean {

	private Editora editora = new Editora();
	private EditoraDAO dao = new EditoraDAO();
	private ArrayList<Editora> listaEditora;
	
	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}



	public String insereEditora()
	{
		
		if (dao.salva(editora)) {
			return "resultado";
		}else{
			return "erro";
		}
		
	}
	
	public String listar(){

		
		EditoraDAO dao = new EditoraDAO();
		
		listaEditora = (ArrayList<Editora>) dao.lista();
		
		return "listaEditora";
		
	}
	
	public String excluir(){

		
		EditoraDAO dao = new EditoraDAO();
		
		System.out.println(editora.getId());
		if (dao.exclui(editora.getId())) {
			return "resultado";
		} else {
			return "erro";
		}
		
	}
	
	public String alterar(){

		EditoraDAO dao = new EditoraDAO();
		
		editora = dao.getEditoraPeloId(editora.getId());
		
		return "editora";
		
	}

	public ArrayList<Editora> getListaEditora() {
		return listaEditora;
	}

	public void setListaEditora(ArrayList<Editora> listaEditora) {
		this.listaEditora = listaEditora;
	}

}
