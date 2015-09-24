package managedbeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.ConnectionFactory;

public class Editora {
	
	private Integer id,cep,fone,cnpj;
	private String nome, endereco, cidade, uf, pais,razaoSocial,web;
	
	public static List<Editora> lista() {
		Connection con = new ConnectionFactory().getConnection();
		
		String sql="SELECT * FROM editora";
		
		List<Editora> editoras = null;
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			
			editoras = new ArrayList<Editora>();
			
			while(rs.next()) {
				Editora editora = new Editora(Integer.parseInt(rs.getString("id_editora")), Integer.parseInt(rs.getString("cep")), Integer.parseInt(rs.getString("fone")), Integer.parseInt(rs.getString("cnpj")), rs.getString("nome"), rs.getString("endereco"), rs.getString("cidade"), rs.getString("uf"), rs.getString("pais"), rs.getString("razao"), rs.getString("web"));
				editoras.add(editora);
			}
			
			try{
				con.close();
			}catch(SQLException ex){}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return editoras;
	}
	
	
	
	
	public Editora(Integer id, Integer cep, Integer fone, Integer cnpj, String nome,
			String endereco, String cidade, String uf, String pais,
			String razaoSocial, String web) {
		this.id = id;
		this.cep = cep;
		this.fone = fone;
		this.cnpj = cnpj;
		this.nome = nome;
		this.endereco = endereco;
		this.cidade = cidade;
		this.uf = uf;
		this.pais = pais;
		this.razaoSocial = razaoSocial;
		this.web = web;
	}
	
	public Editora() {
		
	}

	public Integer getCep() {
		return cep;
	}
	public void setCep(Integer cep) {
		this.cep = cep;
	}
	public Integer getFone() {
		return fone;
	}
	public void setFone(Integer fone) {
		this.fone = fone;
	}
	public Integer getCnpj() {
		return cnpj;
	}
	public void setCnpj(Integer cnpj) {
		this.cnpj = cnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}




	public boolean salva() {
		Connection con = new ConnectionFactory().getConnection();
		
		if(con == null){
			return false;	
		}
		
		Integer id = this.getId();
		
		
		String sql = ""; 

		if(id == null ){
			sql = "INSERT INTO editora "
					+ "(nome, endereco,cidade,uf,pais,cep,fone,cnpj,razao,web) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
		}else{
			sql = "UPDATE editora SET "
					+ "nome=?, endereco=?,cidade=?,uf=?,pais=?,cep=?,fone=?,cnpj=?,razao=?,web=? "
					+" WHERE id_editora = ?";
		}
		
		
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,this.getNome());
			st.setString(2,this.getEndereco());
			st.setString(3,this.getCidade());
			st.setString(4,this.getUf());
			st.setString(5,this.getPais());
			st.setInt(6,this.getCep());
			st.setInt(7,this.getFone());
			st.setInt(8,this.getCnpj());
			st.setString(9,this.getRazaoSocial());
			st.setString(10,this.getWeb());
			
			if(!(id == null) ){
				st.setInt(11, this.getId());
			}
			
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

	public static Editora getEditoraPeloId(int id) {
		Connection con = new ConnectionFactory().getConnection();

		String sql = "SELECT * FROM editora WHERE id_editora = ?";

		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			Editora editora = null;

			if (rs.next()) {
				
				 editora = new Editora(Integer.parseInt(rs.getString("id_editora")), Integer.parseInt(rs.getString("cep")), Integer.parseInt(rs.getString("fone")), Integer.parseInt(rs.getString("cnpj")), rs.getString("nome"), rs.getString("endereco"), rs.getString("cidade"), rs.getString("uf"), rs.getString("pais"), rs.getString("razao"), rs.getString("web"));

				
				
			}
			
			try{
				con.close();
			}catch(SQLException ex){}
			
			return editora;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	public boolean exclui() {
		Connection con = new ConnectionFactory().getConnection();
		
		if(con == null){
			return false;	
		}
		
		Integer id = this.getId();
		
		
		String sql = ""; 

		if(id != 0 ){
			sql = "delete from editora where id_editora=?";
				
			
			try {
				PreparedStatement st = con.prepareStatement(sql);
				
				
					st.setInt(1, this.getId());
				
			
				
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
		
		return false;
		
	}
				
	

}
