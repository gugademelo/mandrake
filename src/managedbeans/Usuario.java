package managedbeans;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.ConnectionFactory;

public class Usuario {
	private String nome, email, senha;
	private int id;
	private Perfil perfil;

	public Usuario(int id, String nome, String email, String senha,
			Perfil perfil) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.perfil = perfil;
	}

	public Usuario() {
	}

	public boolean salva() {
		Connection con = new ConnectionFactory().getConnection();

		if (con == null) {
			return false;
		}
		
		Integer id = this.getId();
		
		String sql = null;
		
		if (id == 0) {
			sql = "INSERT INTO usuario (nome, email, senha, id_perfil) VALUES(?, ?, ?, ?)";
		}
		else{
			sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, id_perfil = ? WHERE id_usuario = ?";
		}

		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, this.getNome());
			st.setString(2, this.getEmail());
			st.setString(3, this.getSenha());
			st.setInt(4, this.getPerfil().getId());
			
			if(id != 0) {
				st.setInt(5, id);
			}
			if (st.executeUpdate() == 1){
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

		if (con == null) {
			return false;
		}

		String sql = "DELETE FROM usuario WHERE id_usuario = ?";

		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, this.getId());
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
			return false;
		}

	}

	public static Usuario login(String email, String senha) {
		Connection con = new ConnectionFactory().getConnection();

		String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";

		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, email);
			st.setString(2, senha);

			ResultSet rs = st.executeQuery();
			Usuario usuario = null;

			if (rs.next()) {
				Perfil perfil = Perfil.getPerfilPeloId(rs.getInt("id_perfil"));
				 usuario = new Usuario(rs.getInt("id_usuario"),
						rs.getString("nome"), rs.getString("email"),
						rs.getString("senha"), perfil);
				
			}
			
			
			try{
				con.close();
			}catch(SQLException ex){}
			
			return usuario;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Usuario> lista() {
		Connection con = new ConnectionFactory().getConnection();

		String sql = "SELECT * FROM usuario";

		List<Usuario> usuarios = null;

		try {
			PreparedStatement st = con.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			usuarios = new ArrayList<Usuario>();

			while (rs.next()) {
				Perfil perfil = Perfil.getPerfilPeloId(rs.getInt("id_perfil"));
				Usuario usuario = new Usuario(Integer.parseInt(rs
						.getString("id_usuario")), rs.getString("nome"),
						rs.getString("email"), rs.getString("senha"), perfil);
				usuarios.add(usuario);
			}
			
			try{
				con.close();
			}catch(SQLException ex){}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return usuarios;
	}

	public static Usuario getUsuarioPeloId(int id) {
		Connection con = new ConnectionFactory().getConnection();

		String sql = "SELECT * FROM usuario WHERE id_usuario = ?";

		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			Usuario usuario = null;

			if (rs.next()) {
				Perfil perfil = Perfil.getPerfilPeloId(rs.getInt("id_perfil"));
				 usuario = new Usuario(Integer.parseInt(rs
						.getString("id_usuario")), rs.getString("nome"),
						rs.getString("email"), rs.getString("senha"), perfil);
				
			}
			
			try{
				con.close();
			}catch(SQLException ex){}
			
			return usuario;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
}
