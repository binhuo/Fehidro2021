import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mobile/components/card_list_item.dart';
import 'package:mobile/components/form_error.dart';
import 'package:mobile/constants.dart';
import 'package:mobile/http/auth_rest.dart';
import 'package:mobile/http/proposta_rest.dart';

import 'package:mobile/main.dart';
import 'package:mobile/models/proposta.dart';
import 'package:mobile/screens/login/login_screen.dart';
import 'package:mockito/mockito.dart';

import '../mocks/rest/auth_rest_mock.dart';

AuthRestClient authRestClient;

void main() {
  setUp(() {
    authRestClient = MockAuthRest();

    when(authRestClient.realizarLogin('42387203690', 'unisantos123'))
        .thenAnswer((_) => Future(() => true));

  });

  testWidgets('A tela possui campos de usuário e senha + botão para login',
      (WidgetTester tester) async {
    await tester.pumpWidget(FehidroApp());

    //usando prop key do widget
    expect(find.byKey(Key(keyTxtUsernameLogin)), findsOneWidget);
    expect(find.byKey(Key(keyTxtPasswordLogin)), findsOneWidget);
    expect(find.byKey(Key(keyBtnLogin)), findsOneWidget);

    //considerando apenas se a quantidade de inputs do tipo TextFormField é igual a 2
    expect(find.byType(TextFormField), findsNWidgets(2));
  });

  testWidgets('Campos usuário e senha preenchidos',
      (WidgetTester tester) async {
    Widget testWidget = new MediaQuery(
        data: new MediaQueryData(),
        child: new MaterialApp(home: new LoginScreen()));

    await tester.pumpWidget(testWidget);

    var usernameText = '42387203690';
    var passwordText = 'unisantos123';

    await tester.enterText(find.byKey(Key(keyTxtUsernameLogin)), usernameText);
    await tester.enterText(find.byKey(Key(keyTxtPasswordLogin)), passwordText);

    await tester.tap(find.byKey(Key(keyBtnLogin)));
    await tester.pump();

    Finder formWidgetFinder = find.byType(Form);
    Form formWidget = tester.widget(formWidgetFinder) as Form;
    GlobalKey<FormState> formKey = formWidget.key as GlobalKey<FormState>;

    expect(formKey.currentState.validate(), isTrue);
  });

  testWidgets('Campos usuário e senha não preenchidos',
      (WidgetTester tester) async {
    await tester.pumpWidget(FehidroApp());

    var usernameText = '';
    var passwordText = '';

    await tester.enterText(find.byKey(Key(keyTxtUsernameLogin)), usernameText);
    await tester.enterText(find.byKey(Key(keyTxtPasswordLogin)), passwordText);

    await tester.tap(find.byKey(Key(keyBtnLogin)));
    await tester.pump();

    Finder formWidgetFinder = find.byType(Form);
    Form formWidget = tester.widget(formWidgetFinder) as Form;
    GlobalKey<FormState> formKey = formWidget.key as GlobalKey<FormState>;

    expect(formKey.currentState.validate(), isFalse);
    expect(find.byType(FormError), findsOneWidget);
  });
}
