package managedbeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




import DAO.UsuarioDAO;
import TO.Usuario;
import database.ConnectionFactory;

public class Venda {
	int id_venda;
	Usuario usuario;
	String frete;
	double valor_frete;
	double valor_total;
	
	public Venda(int id_usuario, String frete, double valor_frete) {
		super();
		this.usuario = UsuarioDAO.getUsuarioPeloId(id_usuario);
		this.frete = frete;
		this.valor_frete = valor_frete;
	}
	
	public Venda() {}
	
	public Venda cria() {
		Connection con = new ConnectionFactory().getConnection();

		if (con == null) {
			return null;
		}
		
		String sql = "INSERT INTO venda (frete, valor_frete, id_usuario) VALUES(?, ?, ?)";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, this.getFrete());
			st.setDouble(2, this.getValor_frete());
			st.setInt(3, this.getId_usuario());
			
			if (st.executeUpdate() == 1) {
				sql = "SELECT id_venda FROM venda ORDER BY id_venda DESC LIMIT 1";
				st = con.prepareStatement(sql);
				java.sql.ResultSet rs = st.executeQuery();
				if(rs.next()) {
					
					this.setId_venda(rs.getInt("id_venda"));
					
					try{
						con.close();
					}catch(SQLException ex){}
					
					return this;
				}
			}
			else{
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	public int getId_venda() {
		return id_venda;
	}
	public void setId_venda(int id_venda) {
		this.id_venda = id_venda;
	}
	public int getId_usuario() {
		return usuario.getId();
	}
	public Usuario getUsuario() {
		return this.usuario;
	}
	public void setUsuario(int id_usuario) {
		this.usuario = UsuarioDAO.getUsuarioPeloId(id_usuario);
	}
	public String getFrete() {
		return frete;
	}
	public void setFrete(String frete) {
		this.frete = frete;
	}
	public double getValor_frete() {
		return valor_frete;
	}
	public void setValor_frete(double valor_frete) {
		this.valor_frete = valor_frete;
	}
	
	public double getValor_total() {
		return valor_total;
	}

	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}

	public static Venda getVendaPeloId(Integer id_venda) {
		Connection con = new ConnectionFactory().getConnection();
				
		String sql = "select * from venda where id_venda = ? order by id_venda";
	
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, id_venda);
			
			
			ResultSet rs = st.executeQuery();
			
			Venda compra = null;

			if (rs.next()) {
				compra = new Venda();
				compra.setFrete(rs.getString("frete"));
				compra.setId_venda(rs.getInt("id_venda"));
				compra.setValor_frete(rs.getDouble("valor_frete"));
				compra.setUsuario(rs.getInt("id_usuario"));				
				
				
				
			}
			
			try{
				con.close();
			}catch(SQLException ex){}
			
			return compra;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static List<Venda> lista(Usuario usuario) {
		Connection con = new ConnectionFactory().getConnection();
		
		List<Venda> comprasUsuario = null;
		
		String sql = "select * from venda ";
		
		if (usuario.getPerfil().getId() != 1) {
			sql += "where id_usuario = ?";
		}
		
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			if (usuario.getPerfil().getId() != 1) {
				st.setInt(1, usuario.getId());;
			}	
			
			ResultSet rs = st.executeQuery();
			
			comprasUsuario = new ArrayList<Venda>();

			while (rs.next()) {
				Venda compra = new Venda();
				compra.setFrete(rs.getString("frete"));
				compra.setId_venda(rs.getInt("id_venda"));
				compra.setValor_frete(rs.getDouble("valor_frete"));
				compra.setUsuario(rs.getInt("id_usuario"));				
				
				
				comprasUsuario.add(compra);
			}
			
			try{
				con.close();
			}catch(SQLException ex){}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return comprasUsuario;
	}	
	
	
	
}
