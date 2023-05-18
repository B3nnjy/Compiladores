package mx.ipn.escom.compiladores.primeros;

import mx.ipn.escom.compiladores.TipoToken;

public class Equality_2 extends Primary{
  protected Equality_2(){
    super.primeros.clear();
    super.primeros.add(TipoToken.NOT_EQ);
    super.primeros.add(TipoToken.COMPARACION);
  }
}
