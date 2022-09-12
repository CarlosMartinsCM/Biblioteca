package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Autor;
import model.Livro;
import model.LivroSituacao;

public class DaoLivro {
	
	// CRUD CREAT
	public boolean addBook(Livro livro) {
		boolean resultado = false;
		try {
			Connection conexao = ConexaoBanco.novaConexao();
			String sqlAutor = String.format("INSERT INTO tbAutor VALUES (null,'%s');", livro.getAutor().getNome());
			PreparedStatement pst = conexao.prepareStatement(sqlAutor, Statement.RETURN_GENERATED_KEYS);
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();

			int idAutor = 0;

			if (rs.next()) {
				idAutor = rs.getInt(1);
				String sqlLivro = String.format(
						"INSERT INTO tbLivro VALUES(null, '%s', '%s', %d, '%s')", 
						livro.getNome(), livro.getDataAdd(), idAutor, livro.getStatus());
				PreparedStatement pstLivro = conexao.prepareStatement(sqlLivro, Statement.RETURN_GENERATED_KEYS);
				pstLivro.executeUpdate();
			}

			resultado = true;
			conexao.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return resultado;
	}
	
	// CRUD READ
	public ArrayList<Livro> listarLivros() {
		ArrayList<Livro> livros = new ArrayList<>();
		String SQL = "SELECT tl.id, tl.nome, tl.dataCriacao, tl.status, ta.id, ta.nome as nomeAutor"
				+ " FROM tbLivro AS tl " + " INNER JOIN tbAutor AS ta ON tl.autor = ta.id";
		
		try {
			Connection conexao = ConexaoBanco.novaConexao();
			PreparedStatement pst = conexao.prepareStatement(SQL);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// Definindo o status conforme as descrições do ENUM
				LivroSituacao status;
				if (rs.getString("status").equals("DISPONIVEL")) {
					status = LivroSituacao.DISPONIVEL;
				} else if (rs.getString("status").equals("INDISPONIVEL")) {
					status = LivroSituacao.INDISPONIVEL;
				} else {
					status = LivroSituacao.EMPRESTADO;
				}
				
				String idLivro = rs.getString("id");
				String nomeLivro = rs.getString("nome");
				Date dataCriacao = rs.getDate("dataCriacao");
				LivroSituacao statusLivro = status;
				
				
				String idAutor = rs.getString("id");
				String nomeAutor = rs.getString("nomeAutor");
				
				Autor autor = new Autor(idAutor, nomeAutor);
				Livro livro = new Livro(idLivro, nomeLivro, dataCriacao, autor, statusLivro);
				livros.add(livro);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return livros;
	}
	
	// CRUD READ para parâmetro alterar status
	public Livro consultarLivro(int cod) {
		Livro livro = new Livro();
		try {
			Connection conexao = ConexaoBanco.novaConexao();
			String SQL = String.format(
					"SELECT tl.id, tl.nome, tl.dataCriacao, tl.status, ta.id, ta.nome as nomeAutor FROM tbLivro AS tl " +
					"INNER JOIN tbAutor AS ta ON tl.autor = ta.id WHERE tl.id = %s", cod
			);
			PreparedStatement pst = conexao.prepareStatement(SQL);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				Autor autor = new Autor();
				LivroSituacao status;
				livro.setId(rs.getString("id"));
				livro.setNome(rs.getString("nome"));
				livro.setDataAdd(rs.getDate("dataCriacao"));
				if (rs.getString("status").equals("DISPONIVEL")) {
	            	status = LivroSituacao.DISPONIVEL;
	            }else if (rs.getString("status").equals("INDISPONIVEL")) {
	            	status = LivroSituacao.INDISPONIVEL;
	            }else {
	            	status = LivroSituacao.EMPRESTADO;
	            }
				livro.setStatus(status);
				autor.setId(rs.getString("id"));
				autor.setNome(rs.getString("nomeAutor"));
				livro.setAutor(autor);
			}
			conexao.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return livro;
	}
	
	//CRUD UPDATE
	public int alterarLivro(int idLivro, String status) {
		int quantidade = 0;
		try {
			Connection conexao = ConexaoBanco.novaConexao();
			String SQL = String.format("UPDATE tbLivro SET status = '%s' WHERE id = %d;", status, idLivro);
			PreparedStatement pst = conexao.prepareStatement(SQL);
			pst.executeUpdate(SQL);
			quantidade = pst.getUpdateCount();
			conexao.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return quantidade;
	}
	
	//CRUD delete
	public int excluirLivro(int cod) {
		int quantidade = 0;
		try {
			Connection conexao = ConexaoBanco.novaConexao();
			String SQL = String.format("DELETE FROM tbLivro WHERE id = %d;", cod);
			PreparedStatement pst = conexao.prepareStatement(SQL);
			pst.executeUpdate();
			quantidade = pst.getUpdateCount();
			conexao.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return quantidade;
	}
}


