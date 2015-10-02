package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import TO.ItemVenda;
import TO.Venda;
import database.ConnectionFactory;

public class ItemVendaDAO {
	
	public boolean addLivro(ItemVenda Item_Venda) {
		Connection con = new ConnectionFactory().getConnection();
		
		String sql = "INSERT into item_venda (id_item, id_venda, preco) VALUES (?,?,?)";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, Item_Venda.getLivro().getLivro_id());
			st.setInt(2, Item_Venda.getId_item_venda());
			st.setDouble(3, Item_Venda.getLivro().getPreco());
			
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
				
				itemvenda.getLivro().setPreco(rs.getDouble("preco"));
								
				
				
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
