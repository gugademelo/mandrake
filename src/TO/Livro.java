package TO;

import java.text.ParseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DAO.AutorDAO;
import DAO.CategoriaDAO;
import DAO.EditoraDAO;
import database.ConnectionFactory;

public class Livro {
	private Integer livro_id;
	private String titulo, isbn, colecao, edicao, idioma;
	private Integer ano; 
	private double preco;
	private Autor autor;
	private Editora editora;
	private Categoria categoria;
	
	public Livro()
	{
	
	}
	
	public Livro(int livro_id ,String titulo, String isbn, String colecao, String edicao, String idioma, double preco, Integer ano, int autor, int editora, int categoria)
	{
		this.livro_id = livro_id;
		this.titulo = titulo;
		this.isbn = isbn;
		this.colecao = colecao;
		this.edicao = edicao;
		this.idioma = idioma;
		this.preco = preco;
		this.ano = ano;
		this.autor = AutorDAO.getAutorPeloId(autor);
		this.editora = EditoraDAO.getEditoraPeloId(editora);
		this.categoria = CategoriaDAO.getCategoriaPeloId(categoria);
		
	}
	
	
	
	
	public Integer getLivro_id() {
		return livro_id;
	}

	public void setLivro_id(Integer livro_id) {
		this.livro_id = livro_id;
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getColecao() {
		return colecao;
	}
	public void setColecao(String colecao) {
		this.colecao = colecao;
	}
	public String getEdicao() {
		return edicao;
	}
	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(int autor) {
		this.autor = AutorDAO.getAutorPeloId(autor);
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(int editora) {
		this.editora = EditoraDAO.getEditoraPeloId(editora);
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = CategoriaDAO.getCategoriaPeloId(categoria);
	}
	
	
	
	
}
