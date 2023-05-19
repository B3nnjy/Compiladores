package mx.ipn.escom.compiladores.primeros;

import mx.ipn.escom.compiladores.TipoToken;

public class Term_2 extends Primary{
  protected Term_2(){
    super.primeros.clear();
    super.primeros.add(TipoToken.MENOS);
    super.primeros.add(TipoToken.MAS);
  }
}
