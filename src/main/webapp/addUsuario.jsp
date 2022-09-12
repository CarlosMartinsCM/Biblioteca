<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Adicionar usuario</title>
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
	<h1 class="title has-text-primary-dark" >Cadastrar usuário</h1>
	</div>
	
			
	<div class="container">
		<form name="frmUser" action="add-user">		
			<label class="label">Nome</label>
			<input class="input is-hovered" type="text" name="nome" placeholder="Nome" required>
			<label class="label">E-mail</label>
			<input class="input is-hovered" type="email" name="email" placeholder="E-mail" required>
			<label class="label">Tipo</label>
			<input class="input is-hovered" type="text" name="tipo" placeholder="Adm/User" required>
			<label class="label">Senha</label>
			<input class="input is-hovered" type="password" name="senha" placeholder="Senha" required>
			<br>
			
			<a href="inserir-user.jsp">
				<br>
				<button class="button is-success is-outlined  button is-rounded"  onclick="validarForm()">Adicionar</button>
			</a>
			
			<a class="button is-info is-outlined  button is-rounded  button is-danger" href="listagem" >Cancelar</a>
		</form>
	</div>

<script type="text/javascript" src="scripts/validador-user.js"></script>

</body>
</html>