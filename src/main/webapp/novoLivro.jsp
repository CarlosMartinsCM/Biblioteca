<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Adicionar livro</title>
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
		<h1 class="title has-text-primary-dark" >Cadastrar livro</h1>
	</div>
		<div class="container">
		<form name="frmLivro" action="addBook">		
			<label class="label">Nome do livro:</label>
			<input type="text" name="nome" placeholder="Nome" class="input is-hovered"  required>
			<label class="label">Data de Criação</label>
			<input type="date" name="dataCriacao" placeholder="Data" class="input is-hovered" required>
			<label class="label">Nome do autor: </label>
			<input type="text" name="autor" placeholder="Autor" class="input is-hovered" required>
			<br>
			
			<a href="addBook.jsp">
				<br>
				<button  onclick="validarForm()" class="button is-success is-outlined  button is-rounded">Adicionar</button>
			</a>
			
			<a href="listagem" class="button is-danger is-outlined  button is-rounded">Cancelar</a>
		</form>
	</div>

</body>
</html>