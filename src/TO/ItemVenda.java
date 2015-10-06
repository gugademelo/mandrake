package TO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.LivroDAO;
import DAO.VendaDAO;
import database.ConnectionFactory;

public class ItemVenda {

	Livro livro;
	Venda venda;
	int id_item_venda ;
	
	
	public int getId_item_venda() {
		return id_item_venda;
	}
	public void setId_item_venda(int id_item_venda) {
		this.id_item_venda = id_item_venda;
	}
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	public void setLivroPeloId(int id_livro) {		
		this.livro =  LivroDAO.getLivroPeloId(id_livro);
	}
	public void setVendaPeloId(int id_venda) {
		this.venda = VendaDAO.getVendaPeloId(id_venda);;
	}
	
	public ItemVenda(){
		
	}
	
	public ItemVenda(Livro livro,Venda venda){
		this.livro = livro;
		this.venda = venda;
	}
	
	
	
}
