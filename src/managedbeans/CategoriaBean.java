package managedbeans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;


import DAO.CategoriaDAO;
import TO.*;



@ManagedBean(name= "CategoriaBean")

public class CategoriaBean {
	private Categoria categoria = new Categoria();
	private Result resultado = new Result();
	private ArrayList<Categoria> listaCategoria;
	CategoriaDAO dao = new CategoriaDAO();
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public String insereCategoria()
	{
		if(dao.salva(categoria)){
			return "resultado";
		}else{
			return "erro";
		}
			
	}
	
	public String listar(){

		
		CategoriaDAO dao = new CategoriaDAO();
		
		listaCategoria = (ArrayList<Categoria>) dao.lista();
		
		return "listaCategoria";
		
	}

	public String excluir(){

		
		CategoriaDAO dao = new CategoriaDAO();
		
		if (dao.exclui(categoria.getCategoria_id())) {
			return "resultado";
		} else {
			return "erro";
		}
		
	}
	
	public String alterar(){

		
		CategoriaDAO dao = new CategoriaDAO();
		
		categoria = dao.getCategoriaPeloId(categoria.getCategoria_id());
		
		return "categoria";
		
	}

	
	public ArrayList<Categoria> getListaCategoria() {
		return listaCategoria;
	}

	public void setListaCategoria(ArrayList<Categoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}
	
	

}
