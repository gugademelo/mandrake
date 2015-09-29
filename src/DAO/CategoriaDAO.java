package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import TO.Categoria;
import database.ConnectionFactory;

public class CategoriaDAO {
	
	public boolean salva(Categoria categoria) {
		Connection con = new ConnectionFactory().getConnection();
		
		if(con == null){
			return false;	
		}

		String sql = "INSERT INTO categoria (categoria) VALUES(?)";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, categoria.getCategoria());
			if(st.executeUpdate() == 1) {
				try{
					con.close();
				}catch(SQLException ex){}
				return true;
			}
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
				
	}
	
	public boolean atualizar(Categoria categoria)
	{
		Connection con = new ConnectionFactory().getConnection();
		
		if(con == null){
			return false;	
		}
		String sql = "UPDATE categoria SET categoria=? WHERE id_categoria = ?";
		try{
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, categoria.getCategoria());
			st.setInt(2, categoria.getCategoria_id());
			
			if(st.executeUpdate() == 1){
				try{
					con.close();
				}catch(SQLException ex){}
				return true;
			}
			return true;
			
		} catch (SQLException e){
			
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean exclui(int Categoria_id) {
		Connection con = new ConnectionFactory().getConnection();

		if (con == null) {
			return false;
		}

		String sql = "DELETE FROM categoria WHERE id_categoria = ?";

		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, Categoria_id);
			if (st.executeUpdate() == 1){
				try{
					con.close();
				}catch(SQLException ex){}
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
	

	

	public static List<Categoria> lista() {
		Connection con = new ConnectionFactory().getConnection();
		
		String sql="SELECT * FROM categoria";
		
		List<Categoria> categorias = null;
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			
			categorias = new ArrayList<Categoria>();
			
			while(rs.next()) {
				Categoria categoria = new Categoria(rs.getString("categoria"), Integer.parseInt(rs.getString("id_categoria")));
				categorias.add(categoria);
			}
			
			try{
				con.close();
			}catch(SQLException ex){}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return categorias;
		

	}
	
	public static Categoria getCategoriaPeloId(int id) {
		Connection con = new ConnectionFactory().getConnection();

		String sql = "SELECT * FROM categoria WHERE id_categoria = ?";

		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();

			Categoria categoria = null;
			
			if (rs.next()) {
				
				 categoria = new Categoria(rs.getString("categoria"), Integer.parseInt(rs.getString("id_categoria")));

				
				
			}
			
			try{
				con.close();
			}catch(SQLException ex){}
			
			return categoria;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
