package mx.ipn.escom.compiladores.primeros;

import mx.ipn.escom.compiladores.TipoToken;

public class Fun_decl extends Primary {
  protected Fun_decl() {
    super.primeros.clear();
    super.primeros.add(TipoToken.FUNCION);
  }
}
