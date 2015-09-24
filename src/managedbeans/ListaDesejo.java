package managedbeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.ConnectionFactory;

public class ListaDesejo {
	
	private int id_livro;
	private int id_usuario;
	
	
	public ListaDesejo()
	{	
	}
	
	public ListaDesejo(int id_livro, int id_usuario) {
		this.id_livro = id_livro;
		this.id_usuario = id_usuario;
	}
	
	public boolean salva() {
		Connection con = new ConnectionFactory().getConnection();
		
		if(con == null){
			return false;	
		}

		String sql = "INSERT INTO lista_desejo (id_livro, id_usuario) VALUES(?, ?)";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, this.id_livro);
			st.setInt(2, this.id_usuario);
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
	
	public boolean exclui() {
		Connection con = new ConnectionFactory().getConnection();

		String sql = "DELETE FROM lista_desejo WHERE id_usuario = ?";

		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, this.id_usuario);
			
			

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


	public int getId_livro() {
		return id_livro;
	}
	public void setId_livro(int id_livro) {
		this.id_livro = id_livro;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	

}
