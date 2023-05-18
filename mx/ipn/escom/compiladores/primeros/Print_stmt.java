package mx.ipn.escom.compiladores.primeros;

import mx.ipn.escom.compiladores.TipoToken;

public class Print_stmt extends Primary {
  protected Print_stmt() {
    super.primeros.clear();
    super.primeros.add(TipoToken.IMPRIMIR);
  }
}
