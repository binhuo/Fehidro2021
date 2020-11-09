import 'package:circular_check_box/circular_check_box.dart';
import 'package:flutter/material.dart';
import 'package:grouped_buttons/grouped_buttons.dart';

import '../../../constants.dart';
import '../../../size_config.dart';

class DetalhePropostaBody extends StatelessWidget {
  TextEditingController _nomeProjetoController =
      TextEditingController(text: "teste nome");

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: SizedBox(
        width: double.infinity,
        child: SingleChildScrollView(
          child: Column(
            children: [
              SizedBox(
                height: SizeConfig.screenHeight * 0.03,
              ),
              Column(
                children: [
                  buildTextField("Nome do projeto", _nomeProjetoController),
                  buildDropdown("teste de instituicao", "Instituição"),
                  buildDropdown("teste de subpdc", "SubPDC"),

                ],
              ),
            ],
          ),
        ),
      ),
    );
  }
}

InputDecoration defaultInputDecoration({String label, String hint, Icon icon}) {
  return InputDecoration(
    labelText: label,
    hintText: hint,
    floatingLabelBehavior: FloatingLabelBehavior.always,
    suffixIcon: icon,
  );
}

Padding buildDropdown(String value, String decorationLabel) {
  return Padding(
    padding: EdgeInsets.all(8),
    child: DropdownButtonFormField(
      value: value,
      disabledHint: Text(value),
      decoration: defaultInputDecoration(label: decorationLabel),
      //icon: Icon(Icons.arrow_downward),
      onChanged: null,
      items: <String>[value].map<DropdownMenuItem<String>>((String value) {
        return DropdownMenuItem<String>(
          value: value,
          child: Text(value),
        );
      }).toList(),
    ),
  );
}

Padding buildTextField(
    String decorationLabel, TextEditingController controller) {
  return Padding(
    padding: EdgeInsets.all(8),
    child: TextFormField(
      controller: controller,
      enabled: false,
      decoration: defaultInputDecoration(label: decorationLabel),
    ),
  );
}

buildCheckbox(){
  
}
