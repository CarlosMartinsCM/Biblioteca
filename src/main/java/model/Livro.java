package model;

import java.sql.Date;

public class Livro {
	private String id;
	private String nome;
	private Date dataAdd;
	private Autor autor;
	private LivroSituacao status;
	
	public Livro() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Livro(String id, String nome, Date dataAdd, Autor autor, LivroSituacao status) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataAdd = dataAdd;
		this.autor = autor;
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataAdd() {
		return dataAdd;
	}
	public void setDataAdd(Date dataAdd) {
		this.dataAdd = dataAdd;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	public LivroSituacao getStatus() {
		return status;
	}
	public void setStatus(LivroSituacao status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Livro [id=" + id + ", nome=" + nome + ", dataAdd=" + dataAdd + ", autor=" + autor + ", status=" + status
				+ "]";
	}
	
	
	
	
	
}
