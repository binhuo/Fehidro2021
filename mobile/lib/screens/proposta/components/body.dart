import 'package:flutter/material.dart';
import 'package:mobile/screens/proposta/components/search_box.dart';

import 'list.dart';

class PropostaBody extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    //Obtém a altura e largura da tela atual tela
    Size size = MediaQuery.of(context).size;

    return SingleChildScrollView(
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          SearchBox(size: size),
          PropostaList(size: size)
        ],
      ),
    );
  }
}