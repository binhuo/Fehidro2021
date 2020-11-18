import 'package:flutter/material.dart';
import '../../../constants.dart';

class SearchBox extends StatelessWidget {
  const SearchBox({
    Key key,
    @required this.size,
  }) : super(key: key);

  final Size size;

  @override
  Widget build(BuildContext context) {
    return Container(
      margin:
      EdgeInsets.only(bottom: defaultPadding * 0.8),
      // It will cover 20% of our total height
      height: size.height * 0.1,
      child: Stack(
        children: <Widget>[
          Container(
            padding: EdgeInsets.only(
              left: defaultPadding,
              right: defaultPadding,
              bottom: 36,
            ),
            height: size.height * 0.12,
            decoration: BoxDecoration(
              color: primaryColor,
              borderRadius: BorderRadius.only(
                bottomLeft: Radius.circular(36),
                bottomRight: Radius.circular(36),
              ),
            ),
            child: Row(
              children: <Widget>[
                  Text(
                    'Propostas',
                    style: Theme.of(context).textTheme.headline5.copyWith(
                        color: Colors.white, fontWeight: FontWeight.bold),
                    textAlign: TextAlign.center,
                  ),
              ],
            ),
          ),

        ],
      ),
    );
  }
}