class SubPdc {
  final int id;
  final int numero;
  final String titulo;

  SubPdc({this.id, this.numero, this.titulo});

  factory SubPdc.fromJson(Map<String, dynamic> json) {
    return SubPdc(
        id: json['id'],
        numero: json['numero'],
        titulo: json['titulo'],
    );
  }

  @override
  String toString(){
    return 'SubPdc #${this.id}: ${this.numero}.${this.titulo}';
  }
}