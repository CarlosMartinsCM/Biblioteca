<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Usuario" %>
<%@ page import="model.Livro"%>
<%@ page import="model.Autor"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.sql.Date"%>

<%
ArrayList<Livro> livros = (ArrayList<Livro>) request.getAttribute("livros");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sistema biblioteca</title>
<link rel="icon" href="imagens/iconeURL.png">
<link href="style.css" rel="stylesheet">
<script src="https://kit.fontawesome.com/5c46874868.js"></script>
</head>
<body>	
		<% String usuario = (String) request.getSession().getAttribute("usuario");
	    if(usuario != null) { %>
		<p class="level"><strong> Usuário: <%= usuario %> </strong></p>
		<%} else { 
			response.sendRedirect("login.jsp");	
		}%>

		<div class="container">
		<a href="novoLivro.jsp">
			<button class="button is-info is-outlined  button is-rounded">Cadastrar Livro</button>
		</a> 
		
		<% if(usuario.equals("admin")) { %>
				<a href="addUsuario.jsp">
					<button class="button is-success is-outlined  button is-rounded" >Cadastrar Usuário</button>
				</a>
		<%}%>
		
		<a href="logout.jsp">
				<button class="button is-info is-outlined  button is-danger button is-rounded" >Sair</button>
			</a>
		
	<div class="container">
		<h1 class="title has-text-primary-dark" >Livros cadastrados</h1>
	</div>

		<table class="table is-bordered is-fullwidth is-narrow is-hoverable" id="tabelaLivros">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nome</th>
					<th>Data De Criação</th>
					<th>Autor</th>
					<th>Status</th>
					<th>Opções</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (Livro livro : livros) {
				%>
				<tr class="is-center">
					<td><%=livro.getId()%></td>
					<td><%=livro.getNome()%></td>
					<td><%=livro.getDataAdd()%></td>
					<td><%=livro.getAutor().getNome()%></td>
					<td id="status"><%=livro.getStatus()%></td>
					
					<td>
					<a 
						href="select?id=<%=livro.getId()%>"><button class="button is-success is-outlined  button is-rounded" >Status</button>
					</a>
					
					<a 
						href="javascript: confirmar(<%=livro.getId()%>)"><button class="button is-danger is-outlined  button is-rounded">Excluir</button>
					</a>
					</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<script type="text/javascript" src="Scripts/confirmador.js"></script>
		
		</div>
</body>
</html>












