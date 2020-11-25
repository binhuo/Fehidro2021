import 'package:flutter_driver/flutter_driver.dart';
import 'package:test/test.dart';

void main() {
  group('Portal CBH-BS App', () {

    FlutterDriver driver;
    final usernameLoginFinder = find.byValueKey('txtUsernameLogin');
    final passwordLoginFinder = find.byValueKey('txtPasswordLogin');
    final loginButtonFinder = find.byValueKey('btnLogin');
    final arrowIconFinder = find.byValueKey('detalhe_proposta_11');

    final username = '42387203690';
    final password = 'unisantos123';

    // Connect to the Flutter driver before running any tests.
    setUpAll(() async {
      driver = await FlutterDriver.connect();
    });

    // Close the connection to the driver after the tests have completed.
    tearDownAll(() async {
      if (driver != null) {
        driver.close();
      }
    });

    test('Lista propostas depois de efetuar login', () async {
      await driver.tap(usernameLoginFinder);
      await driver.enterText(username);

      await driver.tap(passwordLoginFinder);
      await driver.enterText(password);

      await driver.tap(loginButtonFinder);

      await driver.waitFor(find.byType('CardListItem'));

      await driver.tap(arrowIconFinder);
    });

  });

}