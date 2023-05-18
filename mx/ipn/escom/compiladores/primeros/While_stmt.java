package mx.ipn.escom.compiladores.primeros;

import mx.ipn.escom.compiladores.TipoToken;

public class While_stmt extends Primary {
  protected While_stmt() {
    super.primeros.clear();
    super.primeros.add(TipoToken.MIENTRAS);
  }
}
