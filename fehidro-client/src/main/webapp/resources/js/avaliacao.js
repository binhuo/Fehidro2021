$(document).ready(function () {
	$("#formAvaliacao table td label").addClass("custom-control-label");
	$("#formAvaliacao input[type='checkbox']").addClass("custom-control-input");
	$("#formAvaliacao td").addClass("custom-control custom-checkbox");
	
	//--CAIXA DE COMENTARIO DE AVALIACAO
	var txtNota = document.getElementById("formAvaliacao:txtNota");
	//Efetivamente desabilita a caixa quando carregar a pagina se tiver somente 1 opção
	document.getElementById("formAvaliacao:txtComentario").disabled = ((txtNota.options[txtNota.selectedIndex].index+1) == txtNota.length);
	//Desabilita caixa de comentario se for o ultimo campo
	txtNota.onchange = function() {
		document.getElementById("formAvaliacao:txtComentario").disabled = ((txtNota.options[txtNota.selectedIndex].index+1) == txtNota.length);
	}
	
	
});

