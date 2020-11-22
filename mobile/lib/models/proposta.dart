import 'package:mobile/models/subpdc.dart';
import 'package:mobile/models/tipo_proposta.dart';

import 'instituicao.dart';

class Proposta {
  final int id;
  final String nomeProjeto;
  final Instituicao instituicao;
  final SubPdc subPdc;
  final List<TipoProposta> tiposProposta;

  Proposta(
      {this.id,
      this.nomeProjeto,
      this.instituicao,
      this.subPdc,
      this.tiposProposta});

  factory Proposta.fromJson(Map<String, dynamic> json) {
    if (json != null) {
      var tiposProposta = (json['tiposProposta'])
          .map<TipoProposta>((item) => TipoProposta.fromJson(item))
          .toList();

      return Proposta(
          id: json['id'],
          nomeProjeto: json['nomeProjeto'],
          instituicao: Instituicao.fromJson(json['instituicao']),
          subPdc: SubPdc.fromJson(json['subPDC']),
          tiposProposta: tiposProposta);
    }

    return null;
  }

  @override
  String toString() {
    return 'Proposta #${this.id} - ${this.nomeProjeto}';
  }
}
