import 'package:flutter/cupertino.dart';
import 'package:mobile/screens/login/login_screen.dart';
import 'package:mobile/screens/proposta/detalhes_proposta_screen.dart';
import 'package:mobile/screens/proposta/proposta_screen.dart';

final Map<String, WidgetBuilder> routes = {
  LoginScreen.routeName: (context) => LoginScreen(),
  PropostaScreen.routeName: (context) => PropostaScreen(),
  DetalhesPropostaScreen.routeName: (context) => DetalhesPropostaScreen()
};