$(document).ready(function () {
	setCheckboxStyle();
});

function setCheckboxStyle() {
	$("#formCriterioAvaliacao\\:subcriterios table td label").addClass("custom-control-label");
	$("#formCriterioAvaliacao\\:subcriterios input[type='checkbox']").addClass("custom-control-input");
	$("#formCriterioAvaliacao\\:subcriterios td").addClass("custom-control custom-checkbox");
}