import 'package:flutter/material.dart';
import 'package:mobile/screens/login/components/sign_form.dart';

import '../../../size_config.dart';

class Body extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: SizedBox(
        width: double.infinity,
        child: Padding(
          padding:
          EdgeInsets.symmetric(horizontal: getProportionateScreenWidth(20)),
          child: SingleChildScrollView(
            child: Column(
              children: [
                SizedBox(height: SizeConfig.screenHeight * 0.07),
                Image.asset("assets/images/short_logo_cbhbs.png"),
                SizedBox(height: SizeConfig.screenHeight * 0.04),
                Text(
                  "Bem-vindo!",
                  style: TextStyle(
                    color: Colors.black,
                    fontSize: getProportionateScreenWidth(28),
                    fontWeight: FontWeight.bold,
                  ),
                ),
                Text(
                  "Acesse ao Portal CBH-BS usando o seu usuário e senha",
                  textAlign: TextAlign.center,
                ),
                SizedBox(height: SizeConfig.screenHeight * 0.08),
                SignForm(),
              ],
            ),
          ),
        ),
      ),
    );
  }
}


