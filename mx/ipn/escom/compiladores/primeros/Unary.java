package mx.ipn.escom.compiladores.primeros;

import mx.ipn.escom.compiladores.TipoToken;

public class Unary extends Primary{
  protected Unary(){
    super.primeros.add(TipoToken.NO);
    super.primeros.add(TipoToken.MENOS);
  }
}
