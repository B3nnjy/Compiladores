package mx.ipn.escom.compiladores.primeros;

import mx.ipn.escom.compiladores.TipoToken;

public class Var_init extends Primary {
  protected Var_init() {
    super.primeros.clear();
    super.primeros.add(TipoToken.IGUAL);
  }
}
