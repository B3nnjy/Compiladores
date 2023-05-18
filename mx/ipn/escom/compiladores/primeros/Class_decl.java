package mx.ipn.escom.compiladores.primeros;

import mx.ipn.escom.compiladores.TipoToken;

public class Class_decl extends Primary {
  protected Class_decl() {
    super.primeros.clear();
    super.primeros.add(TipoToken.CLASE);
  }
}
