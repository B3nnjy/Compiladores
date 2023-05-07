package mx.ipn.escom.compiladores;

import java.util.HashMap;
import java.util.Map;

public class PalabrasReservadas {
  static final Map<String, TipoToken> palabrasReservadas;
  static {
    palabrasReservadas = new HashMap<>();
    palabrasReservadas.put("select", TipoToken.SELECT);
    palabrasReservadas.put("from", TipoToken.FROM);
    palabrasReservadas.put("distinct", TipoToken.DISTINCT);
  }
}
