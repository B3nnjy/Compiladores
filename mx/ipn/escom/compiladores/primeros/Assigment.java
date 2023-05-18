package mx.ipn.escom.compiladores.primeros;

import mx.ipn.escom.compiladores.TipoToken;

public class Assigment extends Call_3{
  protected Assigment(){
    super.primeros.add(TipoToken.NO);
    super.primeros.add(TipoToken.MENOS);
  }
}
