package managedbeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import TO.Livro;
import database.ConnectionFactory;

public class ListaDeDesejos {	
	public static boolean adiciona(Usuario usuario, Livro livro) {
		Connection con = new ConnectionFactory().getConnection();
		
		if(con == null){
			return false;	
		}

		String sql = "INSERT INTO lista_desejo (id_usuario, id_livro) VALUES(?, ?)";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, usuario.getId());
			st.setInt(2, livro.getLivro_id());
			if(st.executeUpdate() == 1){
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
	
	public static boolean remove(Usuario usuario, Livro livro) {
		Connection con = new ConnectionFactory().getConnection();
		
		if(con == null){
			return false;	
		}

		String sql = "DELETE FROM lista_desejo WHERE id_usuario = ? AND id_livro = ?";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, usuario.getId());
			st.setInt(2, livro.getLivro_id());
			if(st.executeUpdate() == 1){
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
	
	public static List<Livro> lista(Usuario usuario) {
		Connection con = new ConnectionFactory().getConnection();

		String sql = "select * from lista_desejo ld JOIN livro l ON l.id_livro = ld.id_livro WHERE ld.id_usuario = ?";

		List<Livro> livros = null;

		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, usuario.getId());
			
			ResultSet rs = st.executeQuery();

			livros = new ArrayList<Livro>();

			while (rs.next()) {
				Livro livro = new Livro(rs.getInt("id_livro"), rs.getString("titulo"),
				rs.getString("isbn"),
				rs.getString("colecao"),
				rs.getString("edicao"),
				rs.getString("idioma"),
				rs.getDouble("preco"),
			    rs.getInt  ("ano"), // verificar formato de data
				rs.getInt("id_autor"),
				rs.getInt("id_editora"),
				rs.getInt("id_categoria"));
				
				
				livros.add(livro);
			}

			try{
				con.close();
			}catch(SQLException ex){}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return livros;
	}
}
