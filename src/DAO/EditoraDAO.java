package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import TO.Editora;
import database.ConnectionFactory;

public class EditoraDAO {
	
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
	
	
	public boolean salva(Editora editora) {
		Connection con = new ConnectionFactory().getConnection();
		
		if(con == null){
			return false;	
		}
		
		Integer id = editora.getId();
		
		
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
			st.setString(1,editora.getNome());
			st.setString(2,editora.getEndereco());
			st.setString(3,editora.getCidade());
			st.setString(4,editora.getUf());
			st.setString(5,editora.getPais());
			st.setInt(6,editora.getCep());
			st.setInt(7,editora.getFone());
			st.setInt(8,editora.getCnpj());
			st.setString(9,editora.getRazaoSocial());
			st.setString(10,editora.getWeb());
			
			if(!(id == null) ){
				st.setInt(11, editora.getId());
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

	
	public boolean exclui(int Editoraid) {
		Connection con = new ConnectionFactory().getConnection();
		
		if(con == null){
			return false;	
		}
		
		Integer id = Editoraid;
		
		String sql = ""; 

		if(id != 0 ){
			sql = "delete from editora where id_editora=?";
				
			try {
				PreparedStatement st = con.prepareStatement(sql);
							
					st.setInt(1, Editoraid);
				
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
