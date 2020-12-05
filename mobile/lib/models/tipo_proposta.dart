class TipoProposta {
  final int id;
  final String nome;

  TipoProposta({this.id, this.nome});

  factory TipoProposta.fromJson(Map<String, dynamic> json) {
    if (json != null) {
      return TipoProposta(
        id: json['id'],
        nome: json['nome'],
      );
    }

    return null;
  }

  @override
  String toString() {
    return 'Tipo proposta: ${this.nome}';
  }
}
