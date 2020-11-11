class TipoProposta {
  final int id;
  final String nome;

  TipoProposta({this.id, this.nome});

  factory TipoProposta.fromJson(Map<String, dynamic> json) {
    return TipoProposta(
      id: json['id'],
      nome: json['nome'],
    );
  }

  @override
  String toString() {
    return 'Tipo proposta: ${this.nome}';
  }
}