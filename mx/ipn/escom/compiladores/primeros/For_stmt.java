package mx.ipn.escom.compiladores.primeros;

import mx.ipn.escom.compiladores.TipoToken;

public class For_stmt extends Primary{
  protected For_stmt(){
    super.primeros.clear();
    super.primeros.add(TipoToken.PARA);
  }
}
