package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import TO.Usuario;
import TO.Venda;
import database.ConnectionFactory;

public class VendaDAO {
	
	public Venda cria(Venda venda) {
		Connection con = new ConnectionFactory().getConnection();

		if (con == null) {
			return null;
		}
		
		String sql = "INSERT INTO venda (frete, valor_frete, id_usuario) VALUES(?, ?, ?)";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, venda.getFrete());
			st.setDouble(2, venda.getValor_frete());
			st.setInt(3, venda.getId_usuario());
			
			if (st.executeUpdate() == 1) {
				sql = "SELECT id_venda FROM venda ORDER BY id_venda DESC LIMIT 1";
				st = con.prepareStatement(sql);
				java.sql.ResultSet rs = st.executeQuery();
				if(rs.next()) {
					
					venda.setId_venda(rs.getInt("id_venda"));
					
					try{
						con.close();
					}catch(SQLException ex){}
					
					return venda;
				}
			}
			else{
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Venda getVendaPeloId(Integer id_venda) {
		Connection con = new ConnectionFactory().getConnection();
				
		String sql = "select * from venda where id_venda = ? order by id_venda";
	
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, id_venda);
			
			
			ResultSet rs = st.executeQuery();
			
			Venda compra = null;

			if (rs.next()) {
				compra = new Venda();
				compra.setFrete(rs.getString("frete"));
				compra.setId_venda(rs.getInt("id_venda"));
				compra.setValor_frete(rs.getDouble("valor_frete"));
				compra.setUsuario(rs.getInt("id_usuario"));				
				
				
				
			}
			
			try{
				con.close();
			}catch(SQLException ex){}
			
			return compra;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static List<Venda> lista(Usuario usuario) {
		Connection con = new ConnectionFactory().getConnection();
		
		List<Venda> comprasUsuario = null;
		
		String sql = "select * from venda ";
		
		if (usuario.getPerfil().getId() != 1) {
			sql += "where id_usuario = ?";
		}
		
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			if (usuario.getPerfil().getId() != 1) {
				st.setInt(1, usuario.getId());;
			}	
			
			ResultSet rs = st.executeQuery();
			
			comprasUsuario = new ArrayList<Venda>();

			while (rs.next()) {
				Venda compra = new Venda();
				compra.setFrete(rs.getString("frete"));
				compra.setId_venda(rs.getInt("id_venda"));
				compra.setValor_frete(rs.getDouble("valor_frete"));
				compra.setUsuario(rs.getInt("id_usuario"));				
				
				
				comprasUsuario.add(compra);
			}
			
			try{
				con.close();
			}catch(SQLException ex){}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return comprasUsuario;
	}	

}
