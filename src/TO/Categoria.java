package TO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.ConnectionFactory;

public class Categoria {
	
	//colocar atributos
	private String categoria;
	private int categoria_id;

	public Categoria(String categoria, int id) {
		this.categoria = categoria;
		this.categoria_id = id;
	}
	
	public Categoria() {
		
	}
	
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String nomeCat) {
		this.categoria = nomeCat;
	}
	

	public int getCategoria_id() {
		return categoria_id;
	}

	public void setCategoria_id(int categoria_id) {
		this.categoria_id = categoria_id;
	}

	

	
}
