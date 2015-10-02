package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import TO.ListaDesejo;
import database.ConnectionFactory;

public class ListaDesejoDAO {
	
	public boolean salva(ListaDesejo listaDesejo) {
		Connection con = new ConnectionFactory().getConnection();
		
		if(con == null){
			return false;	
		}

		String sql = "INSERT INTO lista_desejo (id_livro, id_usuario) VALUES(?, ?)";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, listaDesejo.getId_livro());
			st.setInt(2, listaDesejo.getId_usuario());
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
	
	public boolean exclui(int id_usuario) {
		Connection con = new ConnectionFactory().getConnection();

		String sql = "DELETE FROM lista_desejo WHERE id_usuario = ?";

		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id_usuario);
			
			

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
		}
		return false;
	}
	
	public static List<ListaDesejo> lista() {
		Connection con = new ConnectionFactory().getConnection();
		
		String sql="SELECT * FROM lista_desejo";
		
		List<ListaDesejo> lst_desejo = null;
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			
			lst_desejo = new ArrayList<ListaDesejo>();
			
			
			while(rs.next()) {
				ListaDesejo listaDesejo = new ListaDesejo(rs.getInt("id_livro"), rs.getInt("id_usuario"));
				
				lst_desejo.add(listaDesejo);
			}
			
			try{
				con.close();
			}catch(SQLException ex){}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return lst_desejo;
		

	}


}
