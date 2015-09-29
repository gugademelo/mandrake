package TO;

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

}
