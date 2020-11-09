import 'package:flutter/material.dart';
import 'package:mobile/components/base.dart';
import 'components/body.dart';

class PropostaScreen extends StatelessWidget {
  static String routeName = "/propostas";

  @override
  Widget build(BuildContext context) {
    return BaseComponent(
      body: Body(),
    );
  }

}