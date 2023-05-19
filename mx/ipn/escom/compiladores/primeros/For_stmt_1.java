package mx.ipn.escom.compiladores.primeros;

import mx.ipn.escom.compiladores.TipoToken;

public class For_stmt_1 extends Assigment{
  protected For_stmt_1(){
    super.primeros.addAll(Primeros.var_decl.primeros);
    super.primeros.add(TipoToken.PUTO_COMA);
  }
}
