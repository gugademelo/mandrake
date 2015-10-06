package TO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.ConnectionFactory;

public class ListaDesejo {
	
	private int id_livro;
	private int id_usuario;
	
	
	public ListaDesejo()
	{	
	}
	
	public ListaDesejo(int id_livro, int id_usuario) {
		this.id_livro = id_livro;
		this.id_usuario = id_usuario;
	}
	

	public int getId_livro() {
		return id_livro;
	}
	public void setId_livro(int id_livro) {
		this.id_livro = id_livro;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	

}
