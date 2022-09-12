<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.LivroSituacao"%>
<%@page import="model.Livro"%>
<% 
Livro livro = new Livro();
LivroSituacao status;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
		<h1 class="title has-text-primary-dark">Alterar livro</h1>
	</div>
		
		
		<div class="container">
			<form name="frmLivro" action="editBook">
			<label class="label">Código</label>
			<input class="input is-hovered" type="text" name="id" readonly value="<%out.print(request.getAttribute("id"));%>">
			<label class="label">Nome</label>
			<input class="input is-hovered" type="text" name="nome" readonly value="<%out.print(request.getAttribute("nome"));%>">	
			<label class="label">Data de Criação</label>
			<input class="input is-hovered" type="date" name="dataCriacao" readonly value="<%out.print(request.getAttribute("dataCriacao"));%>">
			<label class="label">Autor</label>
			<input class="input is-hovered" type="text" name="nomeAutor" readonly value="<%out.print(request.getAttribute("nomeAutor"));%>">
			<label class="label">Status</label>
			<div class="select is-info  is-small">
			<select name="status">
				<%if(request.getAttribute("status").equals(LivroSituacao.DISPONIVEL)) {%> 
				<option value="DISPONIVEL" selected>Disponível</option>
				<option value="EMPRESTADO" >Emprestado</option>
				<option value="INDISPONIVEL">Indisponível</option>
				<%} else if(request.getAttribute("status").equals(LivroSituacao.EMPRESTADO)) {%> 
				<option value="DISPONIVEL">Disponível</option>
				<option value="EMPRESTADO" selected>Emprestado</option>
				<option value="INDISPONIVEL">Indisponível</option>
				<%} else { %> 
				<option value="DISPONIVEL" >Disponível</option>
				<option value="EMPRESTADO" >Emprestado</option>
				<option value="INDISPONIVEL" selected>Indisponível</option>
				<%}%>
			</select>
			</div>
		
			<br>
			<a href="editBook">
				<br>
				<button class="button is-info is-outlined  ">Salvar</button>
			</a>
			
			<a href="listagem" class="button is-info is-outlined is-danger">Cancelar</a>
		</form>
	</div>
	<script type="text/javascript" src="scripts/validador.js"></script>

</body>
</html>