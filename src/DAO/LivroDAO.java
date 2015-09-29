package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import TO.Livro;
import database.ConnectionFactory;

public class LivroDAO {
	
	public boolean salva(Livro livro) {
		Connection con = new ConnectionFactory().getConnection();
		
		if(con == null){
			return false;	
		}

		String sql = "INSERT INTO livro (titulo, isbn, colecao, edicao, idioma, preco, ano, id_autor, id_editora, id_categoria) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, livro.getTitulo());
			st.setString(2, livro.getIsbn());
			st.setString(3, livro.getColecao());
			st.setString(4, livro.getEdicao());
			st.setString(5, livro.getIdioma());
			st.setDouble(6, livro.getPreco());
			st.setInt(7,   livro.getAno());
			st.setInt(8, livro.getAutor().getAutor_Id());
			st.setInt(9, livro.getEditora().getId());
			st.setInt(10, livro.getCategoria().getCategoria_id());
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
	
	public boolean atualizar(Livro livro)
	{
		Connection con = new ConnectionFactory().getConnection();
		
		if(con == null){
			return false;	
		}
		String sql = "UPDATE livro set titulo = ?, isbn = ?, colecao = ?, edicao = ?, idioma = ?, preco = ?, ano = ?, id_autor = ?, id_editora = ?, id_categoria = ?  WHERE id_livro = ?";
		try{
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, livro.getTitulo());
			st.setString(2, livro.getIsbn());
			st.setString(3, livro.getColecao());
			st.setString(4, livro.getEdicao());
			st.setString(5, livro.getIdioma());
			st.setDouble(6, livro.getPreco());
			st.setInt(7,   livro.getAno());
			st.setInt(8, livro.getAutor().getAutor_Id());
			st.setInt(9, livro.getEditora().getId());
			st.setInt(10, livro.getCategoria().getCategoria_id());
			st.setInt   (11, livro.getLivro_id());
			
			if (st.executeUpdate() == 1) {
				try{
					con.close();
				}catch(SQLException ex){}
				return true;
			}
				return false;
			
		} catch (SQLException e){
			
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean exclui(int id) {
		Connection con = new ConnectionFactory().getConnection();

		if (con == null) {
			return false;
		}

		String sql = "DELETE FROM livro WHERE id_livro = ?";

		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
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
	
	public static List<Livro> lista() {
		Connection con = new ConnectionFactory().getConnection();

		String sql = "SELECT * FROM livro";

		List<Livro> livros = null;

		try {
			PreparedStatement st = con.prepareStatement(sql);

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
	
	public static List<Livro> listaAleatoria() {
		Connection con = new ConnectionFactory().getConnection();

		String sql = "SELECT * FROM livro ORDER BY RAND() LIMIT 10";

		List<Livro> livros = null;

		try {
			PreparedStatement st = con.prepareStatement(sql);

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
	
	public static List<Livro> listaPeloTitulo(String titulo) {
		Connection con = new ConnectionFactory().getConnection();

		String sql = "SELECT * FROM livro WHERE titulo like ?";

		List<Livro> livros = null;

		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + titulo + "%");
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
	
	public static List<Livro> listaGoogle(String titulo, Integer id_autor, Integer id_editora, Integer id_categoria) {
		Connection con = new ConnectionFactory().getConnection();

		String sql = "SELECT * FROM livro WHERE titulo like ? AND id_autor BETWEEN ? AND ? AND id_editora BETWEEN ? AND ? AND id_categoria BETWEEN ? AND ?";

		List<Livro> livros = null;

		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + titulo + "%");
			st.setString(2, id_autor == 0 ? "0" : id_autor.toString());
			st.setString(3, id_autor == 0 ? "9%" : id_autor.toString());
			st.setString(4, id_editora == 0 ? "0" : id_editora.toString());
			st.setString(5, id_editora == 0 ? "9%" : id_editora.toString());
			st.setString(6, id_categoria == 0 ? "0" : id_categoria.toString());
			st.setString(7, id_categoria == 0 ? "9%" : id_categoria.toString());
			
			ResultSet rs = st.executeQuery();
			
			System.out.println("\n\n\n\n\n\n" + st + "\n\n\n\n\n\n");

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
	
	public static Livro getLivroPeloId(int id) {
		Connection con = new ConnectionFactory().getConnection();

		String sql = "SELECT * FROM livro WHERE id_livro = ?";


		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			Livro livro = null;

			if (rs.next()) {
				livro = new Livro(rs.getInt("id_livro"), rs.getString("titulo"),
						rs.getString("isbn"),
						rs.getString("colecao"),
						rs.getString("edicao"),
						rs.getString("idioma"),
						rs.getDouble("preco"),
					    rs.getInt  ("ano"), // verificar formato de data
						rs.getInt("id_autor"),
						rs.getInt("id_editora"),
						rs.getInt("id_categoria"));

			}
			
			try{
				con.close();
			}catch(SQLException ex){}
			
			return livro;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
