import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:mobile/components/base.dart';

import 'components/detalhes_body.dart';

class DetalhesPropostaScreen extends StatefulWidget {
  static String routeName = "/detalhes_proposta";

  @override
  State<StatefulWidget> createState() => _DetalhesPropostaScreenState();
}

class _DetalhesPropostaScreenState extends State<DetalhesPropostaScreen> {
  @override
  Widget build(BuildContext context) {
    return BaseComponent(
      body: DetalhePropostaBody(),
    );
  }
}
