package Controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoLivro;
import dao.DaoUser;
import model.Autor;
import model.Livro;
import model.LivroSituacao;
import model.Usuario;

@WebServlet(urlPatterns = { "", "/listagem", "/addBook", "/editBook", "/select", "/excluir", "/add-user"})
public class ControllerLivro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DaoLivro daoLivro = new DaoLivro();
	Livro livro = new Livro();

	public ControllerLivro() {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		
		if (action.equals("/listagem") || action.equals("")) {
			listarLivros(request, response);
		} else if(action.equals("/select")) {
			listarLivro(request, response);
		} else if(action.equals("/editBook")) {
			editBook(request, response);
		} else if(action.equals("/excluir")) {
			excluirLivro(request, response);
		} else if(action.equals("/add-user")) {
			addUser(request, response);
		}else if(action.equals("/addBook")) {
			addBook(request, response);
		}
	}

	protected void listarLivros(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ArrayList<Livro> livros = daoLivro.listarLivros();
		String usuario = (String) request.getSession().getAttribute("usuario");
		if (usuario == null) {
			response.sendRedirect("login.jsp");
		} else {
			request.setAttribute("livros", livros);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}
	
	protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			livro.setId(null);
			livro.setNome(request.getParameter("nome"));
			Date data = Date.valueOf(request.getParameter("dataCriacao"));
			livro.setDataAdd(data);
			livro.setAutor(new Autor(null, request.getParameter("autor")));
			livro.setStatus(LivroSituacao.DISPONIVEL);
			daoLivro.addBook(livro);
			response.sendRedirect("listagem");
		}
	
	protected void listarLivro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		livro.setId(id);
		livro = daoLivro.consultarLivro(Integer.parseInt(id));
		 
		//Setar os atributos do form no objeto
		request.setAttribute("id", livro.getId());
		request.setAttribute("nome", livro.getNome());
		request.setAttribute("dataCriacao", livro.getDataAdd());
		request.setAttribute("nomeAutor", livro.getAutor().getNome());
		request.setAttribute("status", livro.getStatus());
		// Encaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editStatus.jsp");
		rd.forward(request, response);
	}
	
	protected void editBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String status = request.getParameter("status");
		daoLivro.alterarLivro(Integer.parseInt(id), status);
		response.sendRedirect("listagem");
	}
	
	protected void excluirLivro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Integer idLivro = Integer.parseInt(id);
		livro.setId(id);
		daoLivro.excluirLivro(idLivro);
		response.sendRedirect("listagem");
	}
	
	protected void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String tipo = request.getParameter("tipo");
		Usuario usuario = new Usuario("null", nome, email, senha, tipo);
		DaoUser DAO = new DaoUser();
		DAO.inserirUsuario(usuario);
		response.sendRedirect("listagem");
	}
}













