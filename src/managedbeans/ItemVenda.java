package managedbeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.LivroDAO;
import TO.Livro;
import managedbeans.Venda;
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
		this.venda = Venda.getVendaPeloId(id_venda);;
	}
	
	public ItemVenda(){
		
	}
	
	public ItemVenda(Livro livro,Venda venda){
		this.livro = livro;
		this.venda = venda;
	}
	
	public boolean addLivro() {
		Connection con = new ConnectionFactory().getConnection();
		
		String sql = "INSERT into item_venda (id_item, id_venda, preco) VALUES (?,?,?)";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, this.livro.getLivro_id());
			st.setInt(2, this.venda.getId_venda());
			st.setDouble(3, this.livro.getPreco());
			
			if (st.executeUpdate() == 1) {
				try{
					con.close();
				}catch(SQLException ex){}
				return true;
			}
			else{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static List<ItemVenda> lista(Venda venda) {
		Connection con = new ConnectionFactory().getConnection();
		
		List<ItemVenda> itensVenda = null;
		
		String sql = "select * from item_venda where id_venda = ? order by id_item_venda,id_venda";
		
		
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, venda.getId_venda());	
			
			ResultSet rs = st.executeQuery();
			
			itensVenda = new ArrayList<ItemVenda>();

			while (rs.next()) {
				ItemVenda itemvenda = new ItemVenda();
				itemvenda.setVendaPeloId(rs.getInt("id_venda"));
				itemvenda.setLivroPeloId(rs.getInt("id_item"));
				itemvenda.setId_item_venda((rs.getInt("id_item_venda")));
				
				itemvenda.livro.setPreco(rs.getDouble("preco"));
								
				
				
				itensVenda.add(itemvenda);
			}
			
			try{
				con.close();
			}catch(SQLException ex){}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return itensVenda;
	}
	
}
