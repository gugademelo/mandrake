package TO;

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
	
	
}
