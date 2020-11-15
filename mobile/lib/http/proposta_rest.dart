import 'dart:convert';
import 'dart:io';

import 'package:mobile/http/base_rest.dart';
import 'package:mobile/models/proposta.dart';

import 'package:http/http.dart' as http;

class PropostaRestClient extends BaseRestClient {
  Future<Proposta> find(int id) async {
    final response = await http.get(
        "${BaseRestClient.REST_WEBSERVICE_URL}${BaseRestClient.REST_PROPOSTA_URL}$id",
        headers: {HttpHeaders.authorizationHeader: BaseRestClient.authToken},
    );

    if (response.statusCode == 200) {
      return Proposta.fromJson(jsonDecode(utf8.decode(response.bodyBytes)));
    } else {
      throw Exception('Falha ao obter dados da proposta.');
    }
  }

  Future<List<Proposta>> findAll() async {
    final response = await http.get(
        "${BaseRestClient.REST_WEBSERVICE_URL}${BaseRestClient.REST_PROPOSTA_URL}",
        headers: {HttpHeaders.authorizationHeader: BaseRestClient.authToken},
    );

    if (response.statusCode == 200) {
      var arr = json.decode(utf8.decode(response.bodyBytes)) as List;
      return arr.map((e) => Proposta.fromJson(e)).toList();
    } else {
      return List();
    }
  }
}
