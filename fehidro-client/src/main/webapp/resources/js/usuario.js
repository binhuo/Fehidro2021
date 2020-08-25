$(document).ready(function () {

	if ($("#formUsuario\\:txtPerfilAcesso").val() == "1") {
		$("#formUsuario\\:infoSecretaria").show();
		$("#formUsuario\\:infoCTPG").hide();
	} else if ($("#formUsuario\\:txtPerfilAcesso").val() == "2") {
		$("#formUsuario\\:infoCTPG").show();
		$("#formUsuario\\:infoSecretaria").hide();
	}
	
	$("#formUsuario\\:txtPerfilAcesso").on('change', function () {
    	var perfilAcesso = $("#formUsuario\\:txtPerfilAcesso").val();
    	
		if (perfilAcesso == "2") {
    		$("#formUsuario\\:infoCTPG").show();
    	}
    	else {
    		$("#formUsuario\\:infoCTPG").hide();
    		$("#formUsuario\\:txtNascimento").val("");
    		$("#formUsuario\\:txtInstituicao").val("");
    		$("#formUsuario\\:txtDataInicioMandato").val("");
    	}
		
		if (perfilAcesso == "1") {
    		$("#formUsuario\\:infoSecretaria").show();
    	}
    	else {
    		$("#formUsuario\\:infoSecretaria").hide();
    		$("#formUsuario\\:btAdmSecretaria"). prop("checked", false);

    	}
    });
	
	$('.alpha').keydown(function (e) {
        if (e.shiftKey || e.ctrlKey || e.altKey) {
            e.preventDefault();
        } else {
            var key = e.keyCode;
            if (!((key == 8) || (key == 9) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
                e.preventDefault();
            }
        }
    });

	$(document).on("input", ".numeric", function() {
	    this.value = this.value.replace(/\D/g,'');
	});
	
	$("#formUsuario\\:tipoAvaliador input[type='radio']").addClass("custom-control-input");
	$("#formUsuario\\:tipoAvaliador label").addClass("custom-control-label");
	$("#formUsuario\\:tipoAvaliador td").addClass("custom-control custom-radio");

});