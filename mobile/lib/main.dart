import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:mobile/routes.dart';
import 'package:mobile/screens/login/login_screen.dart';
import 'package:mobile/theme.dart';

void main() {
  runApp(FehidroApp());
}

class FehidroApp extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    //ocultando barra de status
    SystemChrome.setEnabledSystemUIOverlays ([SystemUiOverlay.bottom]);

    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Portal CBH-BS',
      theme: theme(),
      initialRoute: LoginScreen.routeName,
      routes: routes,
    );
  }

}
