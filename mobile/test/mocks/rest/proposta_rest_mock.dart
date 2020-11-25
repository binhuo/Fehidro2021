import 'package:mobile/http/proposta_rest.dart';
import 'package:mobile/models/instituicao.dart';
import 'package:mobile/models/proposta.dart';
import 'package:mobile/models/subpdc.dart';
import 'package:mobile/models/tipo_proposta.dart';
import 'package:mockito/mockito.dart';

class MockPropostaRest extends Mock implements PropostaRestClient {

  List<TipoProposta> mockTiposPropostas() {
    var tipoProposta1 = TipoProposta(id: 1, nome: 'Obras');
    var tipoProposta2 = TipoProposta(id: 2, nome: 'Serviços');

    var tiposProposta = List<TipoProposta>();
    tiposProposta.add(tipoProposta1);
    tiposProposta.add(tipoProposta2);
  }

  Proposta mockProposta1() {
    var instituicao1 = Instituicao(nome: 'Governo de São Paulo');
    var instituicao2 = Instituicao(nome: 'Universidade Católica de Santos');

    var subpdc1 = SubPdc(id: 1, numero: 1, titulo: 'SubPdc 1');
    var subpdc2 = SubPdc(id: 1, numero: 1, titulo: 'SubPdc 2');

    var proposta1 = new Proposta(
        id: 1,
        nomeProjeto: 'Projeto A',
        instituicao: instituicao1,
        subPdc: subpdc1,
        tiposProposta: mockTiposPropostas()
    );
    return proposta1;
  }

  Proposta mockProposta2() {
    var instituicao2 = Instituicao(nome: 'Universidade Católica de Santos');

    var subpdc2 = SubPdc(id: 1, numero: 1, titulo: 'SubPdc 2');

    var proposta2 = new Proposta(
        id: 1,
        nomeProjeto: 'Projeto A',
        instituicao: instituicao2,
        subPdc: subpdc2,
        tiposProposta: mockTiposPropostas()
    );
    return proposta2;
  }

  List<Proposta> mockListaPropostas() {
    var lista = List<Proposta>();

    lista.add(mockProposta1());
    lista.add(mockProposta2());

    return lista;
  }

}
