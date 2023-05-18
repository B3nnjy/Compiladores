package mx.ipn.escom.compiladores.primeros;

import mx.ipn.escom.compiladores.TipoToken;

public class Factor_2 extends Primary{
  protected Factor_2(){
    super.primeros.clear();
    super.primeros.add(TipoToken.DIAGONAL);
    super.primeros.add(TipoToken.ASTERISCO);
  }
}
