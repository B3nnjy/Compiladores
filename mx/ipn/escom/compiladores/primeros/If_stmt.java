package mx.ipn.escom.compiladores.primeros;

import mx.ipn.escom.compiladores.TipoToken;

public class If_stmt extends Primary{
  protected If_stmt(){
    super.primeros.clear();
    super.primeros.add(TipoToken.SI);
  }
}

