class Instituicao {
  final int id;
  final String nome;
  final int tipo;

  Instituicao({this.id, this.nome, this.tipo});

  factory Instituicao.fromJson(Map<String, dynamic> json) {
    if (json != null) {
      return Instituicao(
        id: json['id'],
        nome: json['nome'],
        tipo: json['tipo'],
      );
    }

    return null;
  }

  @override
  String toString() {
    return 'Instituicao #${this.id} - ${this.nome}; Tipo #${this.tipo}';
  }
}