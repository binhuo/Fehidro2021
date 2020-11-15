import 'package:circular_check_box/circular_check_box.dart';
import 'package:flutter/material.dart';
import 'package:mobile/http/proposta_rest.dart';
import 'package:mobile/models/proposta.dart';
import 'package:mobile/models/tipo_proposta.dart';

import '../../../constants.dart';
import '../../../size_config.dart';

class DetalhePropostaBody extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => _DetalhePropostaBody();
}

class _DetalhePropostaBody extends State<DetalhePropostaBody> {
  bool selected = true;

  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    final PropostaRestClient _propostaRest = PropostaRestClient();
    final int id = ModalRoute.of(context).settings.arguments;

    return Container(
      height: size.height * 0.8,
      child: Stack(
        children: <Widget>[
          Padding(
            padding: EdgeInsets.all(2),
            child: FutureBuilder<Proposta>(
                initialData: Proposta(),
                future: _propostaRest.find(id),
                builder: (context, snapshot) {
                  switch (snapshot.connectionState) {
                    case ConnectionState.none:
                      break;
                    case ConnectionState.waiting:
                      return Center(
                        child: Column(
                          children: <Widget>[
                            SizedBox(
                              height: SizeConfig.screenHeight * 0.03,
                            ),
                            CircularProgressIndicator(),
                            Text('Carregando...')
                          ],
                        ),
                      );
                      break;
                    case ConnectionState.active:
                      break;
                    case ConnectionState.done:
                      final Proposta proposta = snapshot.data;
                      return buildDetalhesScreen(proposta);
                  }
                  return Text(
                      "Falha ao carregar informações sobre a proposta.");
                }),
          ),
        ],
      ),
    );
  }

  Column buildDetalhesScreen(Proposta proposta) {
    TextEditingController _nomeProjetoController =
        TextEditingController(text: proposta.nomeProjeto);

    TextEditingController _instituicaoController =
        TextEditingController(text: proposta.instituicao.nome);

    TextEditingController _subPdcController =
        TextEditingController(text: proposta.subPdc.titulo);

    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        SizedBox(
          height: SizeConfig.screenHeight * 0.03,
        ),
        buildTextField("Nome do projeto", _nomeProjetoController,
            enabled: false),
        buildTextField("Instituição", _instituicaoController, enabled: false),
        buildTextField("SubPDC", _subPdcController, enabled: false),
        SizedBox(height: 20,),
        Padding (
            padding: EdgeInsets.only(left: 16),
            child: Text("Tipos da proposta", style: TextStyle(color: primaryDarkColor),)
        ),
        Expanded(child: buildTiposPropostaCheckboxes(proposta.tiposProposta)),
      ],
    );
  }
}

Padding buildTextField(String decorationLabel, TextEditingController controller,
    {bool enabled, bool readOnly, String decorationHint}) {
  return Padding(
    padding: EdgeInsets.all(8),
    child: TextFormField(
      controller: controller,
      enabled: enabled,
      decoration:
          defaultInputDecoration(label: decorationLabel, hint: decorationHint),
      readOnly: true,
    ),
  );
}

ListView buildTiposPropostaCheckboxes(List<TipoProposta> tipos) {
   return ListView.builder(
      itemBuilder: (context, index) {
        final TipoProposta tipo = tipos[index];
        return ListTile(
          leading: CircularCheckBox(
              value: true,
              checkColor: Colors.white,
              activeColor: primaryColor,
              disabledColor: Colors.grey,
              onChanged: null),
          title: Text(tipo.nome),
          onTap: null,
        );
      },
      itemCount: tipos == null ? 0 : tipos.length,
  );
}

InputDecoration defaultInputDecoration({String label, String hint, Icon icon}) {
  return InputDecoration(
    labelText: label,
    hintText: hint,
    floatingLabelBehavior: FloatingLabelBehavior.always,
    suffixIcon: icon,
  );
}
