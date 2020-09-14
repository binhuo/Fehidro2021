$(document).ready(function () {
	startMoneyMask();
});

function startMoneyMask(){
	$("input[name*='hdnValorMeta']").each(function(index) {
		var idHidden = $(this).attr("id");
		var idTxt = idHidden.replace("hdnValorMeta", "txtValorMeta");
		idTxt = idTxt.replace(/:\s*/g, "\\:");
		$(`#${idTxt}`).val($(this).val());
	});
	//configurar a máscara
	$("input[name='valorMeta']").maskMoney({prefix:'R$ ', thousands:'.', decimal:','});
	//triggar a mascara
	$("input[name='valorMeta']").maskMoney('mask');

	$("input[name='valorMeta']").on("keyup", function(){
		var idTxt = $(this).attr("id");
		var idHidden = idTxt.replace("txtValorMeta", "hdnValorMeta");
		idHidden = idHidden.replace(/:\s*/g, "\\:");
		//pegar valor sem mascara
		var valueUnmasked = $(this).maskMoney("unmasked")[0];
		
		//setar no hidden referente
		$(`#${idHidden}`).val(valueUnmasked);
	});
}