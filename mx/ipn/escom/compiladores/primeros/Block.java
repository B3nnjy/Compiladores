package mx.ipn.escom.compiladores.primeros;

import mx.ipn.escom.compiladores.TipoToken;

public class Block extends Primary {
  protected Block() {
    super.primeros.clear();
    super.primeros.add(TipoToken.LLAVE_IZQ);
  }
}
