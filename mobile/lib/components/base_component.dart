import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';
import 'package:mobile/screens/proposta/proposta_screen.dart';

import '../constants.dart';

class BaseComponent extends StatelessWidget {
  BaseComponent({
    Key key,
    @required this.body,
  }) : super(key: key);

  final Widget body;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        iconTheme: IconThemeData(color: Colors.white),
        elevation: 0,
      ),
      body: body,
      drawer: Drawer(
        child: ListView(
          padding: EdgeInsets.zero,
          children: <Widget>[
            DrawerHeader(
              child: Center(child: Text('Bem-vindo(a) ao Portal CBH-BS!')),
              decoration: BoxDecoration(
                color: primaryColor,
                borderRadius: BorderRadius.only(
                    bottomLeft: Radius.circular(36),
                    bottomRight: Radius.circular(36)),
              ),
            ),
            ListTile(
              title: Text('Propostas'),
              onTap: () {
                Navigator.pushNamed(context, PropostaScreen.routeName);
              },
            ),
            ListTile(
              title: Text('Menu X'),
              onTap: () {},
            ),
          ],
        ),
      ),
    );
  }
}
