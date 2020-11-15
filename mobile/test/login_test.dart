import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mobile/components/default_button.dart';

import 'package:mobile/main.dart';
import 'package:mobile/screens/login/login_screen.dart';

void main() {
  testWidgets('A tela possui campos de usuário e senha',
      (WidgetTester tester) async {
    await tester.pumpWidget(FehidroApp());

    //usando prop key do widget
    expect(find.byKey(Key('txtUsernameLogin')), findsOneWidget);
    expect(find.byKey(Key('txtPasswordLogin')), findsOneWidget);

    //considerando apenas se a quantidade de inputs do tipo TextFormField é igual a 2
    expect(find.byType(TextFormField), findsNWidgets(2));
  });

  testWidgets('A tela possui botão para login', (WidgetTester tester) async {
    await tester.pumpWidget(FehidroApp());
    expect(find.byKey(Key('btnLogin')), findsOneWidget);
  });

  testWidgets('A tela recebe valores nos campos de usuário e senha',
      (WidgetTester tester) async {
    await tester.pumpWidget(FehidroApp());

    var usernameText = 'teste';
    var passwordText = 'teste';

    await tester.enterText(find.byKey(Key('txtUsernameLogin')), usernameText);
    await tester.enterText(find.byKey(Key('txtPasswordLogin')), passwordText);

    expect(find.text(usernameText), findsOneWidget);
    expect(find.text(passwordText), findsOneWidget);
  });

  testWidgets('Campos usuário e senha preenchidos',
      (WidgetTester tester) async {
    await tester.pumpWidget(FehidroApp());

    var usernameText = 'teste';
    var passwordText = 'teste';

    await tester.enterText(find.byKey(Key('txtUsernameLogin')), usernameText);
    await tester.enterText(find.byKey(Key('txtPasswordLogin')), passwordText);

    await tester.tap(find.byKey(Key('btnLogin')));
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

        await tester.enterText(find.byKey(Key('txtUsernameLogin')), usernameText);
        await tester.enterText(find.byKey(Key('txtPasswordLogin')), passwordText);

        await tester.tap(find.byKey(Key('btnLogin')));
        await tester.pump();

        Finder formWidgetFinder = find.byType(Form);
        Form formWidget = tester.widget(formWidgetFinder) as Form;
        GlobalKey<FormState> formKey = formWidget.key as GlobalKey<FormState>;

        expect(formKey.currentState.validate(), isFalse);
      });
}
