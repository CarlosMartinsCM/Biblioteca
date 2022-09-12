function confirmar(id) {
	let resposta = confirm("Excluir este Livro?");
	if(resposta === true) {
		window.location.href = "excluir?id=" + id;
	} 
} 
