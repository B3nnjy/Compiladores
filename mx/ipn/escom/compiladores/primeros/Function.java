package mx.ipn.escom.compiladores.primeros;

import mx.ipn.escom.compiladores.TipoToken;

public class Function extends Primary{
  protected Function(){
    super.primeros.clear();
    super.primeros.add(TipoToken.IDENTIFICADOR);
  }
}

