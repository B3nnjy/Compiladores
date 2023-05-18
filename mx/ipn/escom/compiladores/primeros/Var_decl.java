package mx.ipn.escom.compiladores.primeros;

import mx.ipn.escom.compiladores.TipoToken;

public class Var_decl extends Primary{
  protected Var_decl(){
    super.primeros.clear();
    super.primeros.add(TipoToken.VARIABLE);
  }
}

