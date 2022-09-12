package model;

public class Autor {
	private String id;
	private String nome;

	public Autor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Autor(String id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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

}
