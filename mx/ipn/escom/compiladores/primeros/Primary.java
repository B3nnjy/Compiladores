package mx.ipn.escom.compiladores.primeros;

import mx.ipn.escom.compiladores.TipoToken;

import java.util.ArrayList;

public class Primary implements NoTerminal{
  protected final ArrayList<TipoToken> primeros = new ArrayList<>();
  protected Primary() {
    primeros.add(TipoToken.SUPER);
    primeros.add(TipoToken.PAREN_IZQ);
    primeros.add(TipoToken.IDENTIFICADOR);
    primeros.add(TipoToken.CADENA);
    primeros.add(TipoToken.NUMERO);
    primeros.add(TipoToken.ESTE);
    primeros.add(TipoToken.NULO);
    primeros.add(TipoToken.FALSO);
    primeros.add(TipoToken.VERDADERO);
  }

  @Override
  public boolean find(TipoToken pre){
    for (TipoToken i : primeros){
      if (pre == i){
        return true;
      }
    }
    return false;
  }

  @Override
  public void show(){
    for (TipoToken i : primeros){
      System.out.println(i);
    }
  }
}
