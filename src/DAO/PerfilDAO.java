package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import TO.Perfil;
import database.ConnectionFactory;

public class PerfilDAO {
	
	public boolean salva(Perfil perfil) {
		Connection con = new ConnectionFactory().getConnection();
		
		if(con == null){
			return false;	
		}

		String sql = "INSERT INTO perfil (nome) VALUES(?)";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, perfil.getPerfil());
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

	public List<Perfil> lista() {
		Connection con = new ConnectionFactory().getConnection();
		
		String sql="SELECT * FROM perfil";
		
		List<Perfil> perfis = null;
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			
			perfis = new ArrayList<Perfil>();
			
			while(rs.next()) {
				Perfil perfil = new Perfil(rs.getString("nome"), Integer.parseInt(rs.getString("id_perfil")));				
				perfis.add(perfil);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
			con.close();
		}catch(SQLException ex){}
		
		return perfis;
		

	}
	
	public static Perfil getPerfilPeloId(int id) {
		Connection con = new ConnectionFactory().getConnection();
		
		String sql = "SELECT * FROM perfil WHERE id_perfil = ?";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			Perfil perfil = null;
			
			if(rs.next()) {
				 perfil = new Perfil(rs.getString("nome"), Integer.parseInt(rs.getString("id_perfil")));
				
			}
			
			try{
				con.close();
			}catch(SQLException ex){}
			
			return perfil;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
