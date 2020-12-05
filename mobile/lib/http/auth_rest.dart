import 'dart:convert';

import 'package:mobile/http/base_rest.dart';
import 'package:mobile/models/http/user_credentials.dart';
import 'package:http/http.dart' as http;

class AuthRestClient extends BaseRestClient {

  Future<bool> realizarLogin(String login, String senha) async {
    var creds = UserCredentials(login, senha);
    var body = jsonEncode(creds);

    final http.Response response = await http
        .post("${BaseRestClient.REST_WEBSERVICE_URL}login", body: body);

    if (response.statusCode == 200) {
      String token = response.headers['authentication'];
      //print("Token: $token");
      BaseRestClient.authToken = token;
      return true;
    } else {
      //print('Erro login');
      return false;
    }
  }
}
