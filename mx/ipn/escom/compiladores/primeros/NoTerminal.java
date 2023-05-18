package mx.ipn.escom.compiladores.primeros;

import mx.ipn.escom.compiladores.TipoToken;

import java.util.ArrayList;

public interface NoTerminal {
  boolean find(TipoToken pre);
  void show();
}
