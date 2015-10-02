package TO;

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

	
	
	
	
}
