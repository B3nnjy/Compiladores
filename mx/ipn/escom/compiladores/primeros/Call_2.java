package mx.ipn.escom.compiladores.primeros;

import mx.ipn.escom.compiladores.TipoToken;

import java.util.ArrayList;

public class Call_2 extends Primary{
  protected Call_2(){
    super.primeros.clear();
    super.primeros.add(TipoToken.PAREN_IZQ);
    super.primeros.add(TipoToken.PUNTO);
  }
}
