package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Usuario;

public class DaoUser {
	
	// READ Método para consultar usuário cadastrado 
	public Usuario consultar(String nome, String senha) {
		Usuario user = new Usuario();
		try {
			Connection conexao = ConexaoBanco.novaConexao();
			String READ = String.format("SELECT * FROM tbUsuario WHERE nome = '%s' AND senha = '%s';", nome, senha);
			PreparedStatement pst = conexao.prepareStatement(READ);
			ResultSet rs = pst.executeQuery(READ);
			if (rs.next()){
            	user.setId(rs.getString("id"));
                user.setNome(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setSenha(rs.getString("senha"));
                user.setTipo(rs.getString("tipo"));
                conexao.close();
            }
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return user;
	}
	
	public boolean inserirUsuario(Usuario usuario) {
		boolean resultado = false;
		try {
			Connection conexao = ConexaoBanco.novaConexao();
			String SQL = "INSERT INTO tbUsuario(nome, email, senha, tipo) VALUES(?, ?, ?, ?)";
			PreparedStatement pst = conexao.prepareStatement(SQL);
			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getEmail());
			pst.setString(3, usuario.getSenha());
			pst.setString(4, usuario.getTipo());
			pst.executeUpdate();
			resultado = true;
			conexao.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return resultado;
	}
	
	

}
