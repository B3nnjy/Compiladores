package mx.ipn.escom.compiladores.primeros;

import mx.ipn.escom.compiladores.TipoToken;

public class Return_stmt extends Primary {
  protected Return_stmt() {
    super.primeros.clear();
    super.primeros.add(TipoToken.RETORNAR);
  }
}
