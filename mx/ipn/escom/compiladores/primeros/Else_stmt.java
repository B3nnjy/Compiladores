package mx.ipn.escom.compiladores.primeros;

import mx.ipn.escom.compiladores.TipoToken;

public class Else_stmt extends Primary {
  protected Else_stmt() {
    super.primeros.clear();
    super.primeros.add(TipoToken.SI_NO);
  }
}
