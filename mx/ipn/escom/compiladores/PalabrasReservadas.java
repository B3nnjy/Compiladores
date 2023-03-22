package mx.ipn.escom.compiladores;

import java.util.HashMap;
import java.util.Map;

public class PalabrasReservadas {
  static final Map<String, TipoToken> palabrasReservadas;
  static {
    palabrasReservadas = new HashMap<>();
    palabrasReservadas.put("y", TipoToken.Y);
    palabrasReservadas.put("clase", TipoToken.CLASE);
    palabrasReservadas.put("ademas", TipoToken.ADEMAS );
    palabrasReservadas.put("falso", TipoToken.FALSO);
    palabrasReservadas.put("para", TipoToken.PARA);
    palabrasReservadas.put("fun", TipoToken.FUNCION); //definir funciones
    palabrasReservadas.put("si", TipoToken.SI);
    palabrasReservadas.put("nulo", TipoToken.NULO);
    palabrasReservadas.put("o", TipoToken.O);
    palabrasReservadas.put("imprimir", TipoToken.IMPRIMIR);
    palabrasReservadas.put("retornar", TipoToken.RETORNAR);
    palabrasReservadas.put("super", TipoToken.SUPER);
    palabrasReservadas.put("este", TipoToken.ESTE);
    palabrasReservadas.put("verdadero", TipoToken.VERDADERO);
    palabrasReservadas.put("var", TipoToken.VARIABLE); //definir variables
    palabrasReservadas.put("mientras", TipoToken.MIENTRAS);
  }
}
