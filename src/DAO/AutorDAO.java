package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import TO.Autor;
import database.ConnectionFactory;

public class AutorDAO {
	
	public boolean salva(Autor autor) {
		Connection con = new ConnectionFactory().getConnection();
		
		if(con == null){
			return false;	
		}

		String sql = "INSERT INTO autor (nome, sobrenome, dt_nascimento, principal_livro, endereco, telefone, email, rg, obs, qtd_livros) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, autor.getNome());
			st.setString(2, autor.getSobrenome());
			java.sql.Date sqlData = new java.sql.Date(autor.getDtNascimento().getTime());
			st.setDate(3, sqlData);
			st.setString(4, autor.getPrincipalLivro());
			st.setString(5, autor.getEndereco());
			st.setString(6, autor.getTelefone());
		    st.setString(7, autor.getEmail());
			st.setString(8, autor.getRg());
			st.setString(9, autor.getObs());
			st.setInt   (10, autor.getQtdLivros());
			if(st.executeUpdate() == 1) return true;
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
				
	}
	
	public boolean atualizar(Autor autor)
	{
		Connection con = new ConnectionFactory().getConnection();
		
		if(con == null){
			return false;	
		}
		String sql = "UPDATE autor SET nome = ?, sobrenome = ?, dt_nascimento = ?, principal_livro = ?, endereco = ?, telefone = ?, email = ?, rg = ?, obs = ?, qtd_livros = ? WHERE id_autor = ?";
		try{
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, autor.getNome());
			st.setString(2, autor.getSobrenome());
			java.sql.Date sqlData = new java.sql.Date(autor.getDtNascimento().getTime());
			st.setDate(3, sqlData);
			st.setString(4, autor.getPrincipalLivro());
			st.setString(5, autor.getEndereco());
			st.setString(6, autor.getTelefone());
		    st.setString(7, autor.getEmail());
			st.setString(8, autor.getRg());
			st.setString(9, autor.getObs());
			st.setInt   (10, autor.getQtdLivros());
			st.setInt   (11, autor.getAutor_Id());
			
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
	
	public static List<Autor> lista() {
		Connection con = new ConnectionFactory().getConnection();
		
		String sql="SELECT * FROM autor";
		
		List<Autor> autores = null;
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			
			autores = new ArrayList<Autor>();
			
			
			while(rs.next()) {
				Autor autor = new Autor(rs.getInt("id_autor"),
				rs.getInt   ("qtd_livros"),
				rs.getString("nome"),
				rs.getString("sobrenome"),				
				rs.getString("principal_livro"),
				rs.getString("endereco"),
				rs.getString("telefone"),
			    rs.getString("email"),
				rs.getString("rg"),
				rs.getString("obs"),
				new java.util.Date());
				
				java.util.Date date = rs.getDate("dt_nascimento");
				autor.setDtNascimento(date);				
				
				autores.add(autor);
			}
			
			try{
				con.close();
			}catch(SQLException ex){}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return autores;
		

	}
	
	public static Autor getAutorPeloId(int id) {
		Connection con = new ConnectionFactory().getConnection();

		String sql = "SELECT * FROM autor WHERE id_autor = ?";

		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();

			Autor autor = null;
			
			if (rs.next()) {
				
					autor = new Autor(rs.getInt("id_autor"),
					rs.getInt   ("qtd_livros"),
					rs.getString("nome"),
					rs.getString("sobrenome"),				
					rs.getString("principal_livro"),
					rs.getString("endereco"),
					rs.getString("telefone"),
				    rs.getString("email"),
					rs.getString("rg"),
					rs.getString("obs"),
					new java.util.Date());
					
					java.util.Date date = rs.getDate("dt_nascimento");
					autor.setDtNascimento(date);				

				
				
			}
			
			try{
				con.close();
			}catch(SQLException ex){}
			
			return autor;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


		return null;
	}

	
	public boolean exclui(int id) {
		Connection con = new ConnectionFactory().getConnection();

		String sql = "DELETE FROM autor WHERE id_autor = ?";

		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			
			if (st.executeUpdate() == 1) {
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

}
