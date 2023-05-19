package mx.ipn.escom.compiladores.primeros;

import mx.ipn.escom.compiladores.TipoToken;

public class Comparison_2 extends Primary{
  protected Comparison_2(){
    super.primeros.clear();
    super.primeros.add(TipoToken.MAYOR);
    super.primeros.add(TipoToken.MAYOR_EQ);
    super.primeros.add(TipoToken.MENOR);
    super.primeros.add(TipoToken.MENOR_EQ);
  }
}
