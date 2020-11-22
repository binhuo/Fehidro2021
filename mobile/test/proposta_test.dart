import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mobile/components/card_list_item.dart';
import 'package:mobile/constants.dart';
import 'package:mobile/http/base_rest.dart';
import 'package:mobile/main.dart';
import 'package:mobile/screens/proposta/proposta_screen.dart';

void main() {
  testWidgets('Não encontra propostas - erro http 400 apenas nos testes', (WidgetTester tester) async {

    Widget testWidget = new MediaQuery(
        data: new MediaQueryData(),
        child: new MaterialApp(home: new PropostaScreen())
    );

    await tester.pumpWidget(testWidget);

    expect(find.byKey(Key(keyListaPropostas)), findsOneWidget);
    expect(find.byType(CircularProgressIndicator), findsOneWidget);

    await tester.pump(new Duration(seconds: 10));

    expect(find.text('Ops! Não foram encontradas propostas para a sua consulta.'), findsOneWidget);
    //expect(find.byType(CardListItem), findsOneWidget);
  });
}
