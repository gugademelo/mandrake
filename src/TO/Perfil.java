package TO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.ConnectionFactory;

public class Perfil {
	
	private String perfil;
	private int id;
	
	public Perfil(String perfil, int id) {
		this.perfil = perfil;
		this.id = id;
	}
	
	public Perfil(){}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil, int id) {
		this.perfil = perfil;
		this.id = id;
	}
	

}
