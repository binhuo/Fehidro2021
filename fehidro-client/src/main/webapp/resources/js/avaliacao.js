$(document).ready(function () {
	$("#formAvaliacao table td label").addClass("custom-control-label");
	$("#formAvaliacao input[type='checkbox']").addClass("custom-control-input");
	$("#formAvaliacao td").addClass("custom-control custom-checkbox");
	
	document.getElementById("formAvaliacao:txtNota").onchange = function() {
		print("teste");
		//	document.getElementById("formAvaliacao:txtComentario").disabled = (txtNota.value == txtNota.lenght);
	}
	
});

