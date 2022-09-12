<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login biblioteca</title>
<link rel="icon" href="imagens/iconeURL.png">
<link href="style.css" rel="stylesheet">
<script src="https://kit.fontawesome.com/5c46874868.js"></script>

</head>
<body>
	<div class="content is-normal">
		<figure class="img">
			<img src="imagens/imgMaster.png">
		</figure>
		<h1 class="level-item  title has-text-primary-dark">Biblioteca </h1>
	</div>

	<div class="level-item">
		<form  method="post" action="login">
			<label>Usuário </label>
			 	<input class="input is-info" type="text" name="usuario" required><br>
			 <label>Senha <input
				class="input is-info" type="password" name="senha" required><br>
			</label> <br>
			<button class="button is-success is-outlined  button is-rounded" type="submit" value="submit">Acessar </button>
			<p>* Login de acesso ao sistema</p>
		</form>
	</div>
</body>
</html>