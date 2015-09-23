package managedbeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.ConnectionFactory;

public class Autor {
	private Integer autor_Id, qtdLivros;
	private String nome, sobrenome, principalLivro, endereco, telefone, email, rg, obs; 
	private Date dtNascimento;
	
	public Autor()
	{
	
	}
	
	
	
	public Autor(Integer autor_Id, Integer qtdLivros, String nome,
			String sobrenome, String principalLivro, String endereco,
			String telefone, String email, String rg, String obs,
			Date dtNascimento) {
		super();
		this.autor_Id = autor_Id;
		this.qtdLivros = qtdLivros;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.principalLivro = principalLivro;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		this.rg = rg;
		this.obs = obs;
		this.dtNascimento = dtNascimento;
	}
	
	public boolean salva() {
		Connection con = new ConnectionFactory().getConnection();
		
		if(con == null){
			return false;	
		}

		String sql = "INSERT INTO autor (nome, sobrenome, dt_nascimento, principal_livro, endereco, telefone, email, rg, obs, qtd_livros) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, this.nome);
			st.setString(2, this.sobrenome);
			java.sql.Date sqlData = new java.sql.Date(this.dtNascimento.getTime());
			st.setDate(3, sqlData);
			st.setString(4, this.principalLivro);
			st.setString(5, this.endereco);
			st.setString(6, this.telefone);
		    st.setString(7, this.email);
			st.setString(8, this.rg);
			st.setString(9, this.obs);
			st.setInt   (10, this.qtdLivros);
			if(st.executeUpdate() == 1) return true;
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
				
	}
	
	public boolean atualizar()
	{
		Connection con = new ConnectionFactory().getConnection();
		
		if(con == null){
			return false;	
		}
		String sql = "UPDATE autor SET nome = ?, sobrenome = ?, dt_nascimento = ?, principal_livro = ?, endereco = ?, telefone = ?, email = ?, rg = ?, obs = ?, qtd_livros = ? WHERE id_autor = ?";
		try{
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, this.nome);
			st.setString(2, this.sobrenome);
			java.sql.Date sqlData = new java.sql.Date(this.dtNascimento.getTime());
			st.setDate(3, sqlData);
			st.setString(4, this.principalLivro);
			st.setString(5, this.endereco);
			st.setString(6, this.telefone);
		    st.setString(7, this.email);
			st.setString(8, this.rg);
			st.setString(9, this.obs);
			st.setInt   (10, this.qtdLivros);
			st.setInt   (11, this.autor_Id);
			
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



	public Integer getAutor_Id() {
		return autor_Id;
	}
	public void setAutor_Id(Integer autor_Id) {
		this.autor_Id = autor_Id;
	}
	public Integer getQtdLivros() {
		return qtdLivros;
	}
	public void setQtdLivros(Integer qtdLivros) {
		this.qtdLivros = qtdLivros;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getPrincipalLivro() {
		return principalLivro;
	}
	public void setPrincipalLivro(String principalLivro) {
		this.principalLivro = principalLivro;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public Date getDtNascimento() {
		return dtNascimento;
	}
	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
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

	
	public boolean exclui() {
		Connection con = new ConnectionFactory().getConnection();

		String sql = "DELETE FROM autor WHERE id_autor = ?";

		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, this.autor_Id);
			
			

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
