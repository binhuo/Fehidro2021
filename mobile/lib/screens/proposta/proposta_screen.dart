import 'package:flutter/material.dart';
import 'package:mobile/components/base_component.dart';
import 'components/proposta_body.dart';

class PropostaScreen extends StatelessWidget {
  static String routeName = "/propostas";

  @override
  Widget build(BuildContext context) {
    return BaseComponent(
      body: PropostaBody(),
    );
  }

}