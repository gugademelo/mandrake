package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import TO.Perfil;
import TO.Usuario;
import database.ConnectionFactory;

public class UsuarioDAO {
	
	public boolean salva(Usuario usuario) {
		Connection con = new ConnectionFactory().getConnection();

		if (con == null) {
			return false;
		}
		
		Integer id = usuario.getId();
		
		String sql = null;
		
		if (id == 0) {
			sql = "INSERT INTO usuario (nome, email, senha, id_perfil) VALUES(?, ?, ?, ?)";
		}
		else{
			sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, id_perfil = ? WHERE id_usuario = ?";
		}

		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, usuario.getNome());
			st.setString(2, usuario.getEmail());
			st.setString(3, usuario.getSenha());
			st.setInt(4, usuario.getPerfil().getId());
			
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

	public boolean exclui(int id) {
		Connection con = new ConnectionFactory().getConnection();

		if (con == null) {
			return false;
		}

		String sql = "DELETE FROM usuario WHERE id_usuario = ?";

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
				Perfil perfil = PerfilDAO.getPerfilPeloId(rs.getInt("id_perfil"));
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
				Perfil perfil = PerfilDAO.getPerfilPeloId(rs.getInt("id_perfil"));
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
				Perfil perfil = PerfilDAO.getPerfilPeloId(rs.getInt("id_perfil"));
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

}
