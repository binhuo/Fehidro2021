import 'package:flutter/material.dart';

import 'constants.dart';

ThemeData theme() {
  return ThemeData(
    scaffoldBackgroundColor: Colors.grey[50],
    fontFamily: "Muli",
    primaryColor: primaryColor,
    primaryColorLight: primaryLightColor,
    primaryColorDark: primaryDarkColor,
    accentColor: primaryColor,
    textTheme: textTheme(),
    inputDecorationTheme: inputDecorationTheme(),
    visualDensity: VisualDensity.adaptivePlatformDensity,
  );
}

InputDecorationTheme inputDecorationTheme() {
  OutlineInputBorder outlineInputBorder = OutlineInputBorder(
    borderRadius: BorderRadius.circular(28),
    gapPadding: 10,
    borderSide: BorderSide(color: enabledInputColor),
  );

  OutlineInputBorder outlineEnabledInputBorder = OutlineInputBorder(
    borderRadius: outlineInputBorder.borderRadius,
    gapPadding: outlineInputBorder.gapPadding,
    borderSide: BorderSide(color: enabledInputColor),
  );

  OutlineInputBorder outlineDisabledInputBorder = OutlineInputBorder(
    borderRadius: outlineInputBorder.borderRadius,
    gapPadding: outlineInputBorder.gapPadding,
    borderSide: BorderSide(color: disabledInputColor),
  );

  OutlineInputBorder outlineFocusedInputBorder = OutlineInputBorder(
    borderRadius: outlineInputBorder.borderRadius,
    gapPadding: outlineInputBorder.gapPadding,
    borderSide: BorderSide(color: focusedInputColor),
  );

  return InputDecorationTheme(
      contentPadding: EdgeInsets.symmetric(horizontal: 42, vertical: 20),
      border: outlineInputBorder,
      enabledBorder: outlineEnabledInputBorder,
      disabledBorder: outlineDisabledInputBorder,
      focusedBorder: outlineFocusedInputBorder,
      labelStyle: TextStyle(
          color: primaryDarkColor
      ),
  );
}

TextTheme textTheme() {
  return TextTheme(
    bodyText1: TextStyle(color: textColor),
    bodyText2: TextStyle(color: textColor),
  );
}
