package mx.ipn.escom.compiladores.automatas;

import mx.ipn.escom.compiladores.TipoToken;

import java.util.HashMap;
import java.util.Map;

public class simbolos {

  private static final Map<String, TipoToken> simbolos = new HashMap<>();

  static{
    simbolos.put("(", TipoToken.PAREN_IZQ);
    simbolos.put(")", TipoToken.PAREN_DER);
    simbolos.put("{", TipoToken.LLAVE_IZQ);
    simbolos.put("}", TipoToken.LLAVE_DER);
    simbolos.put(",", TipoToken.COMA);
    simbolos.put(".", TipoToken.PUNTO);
    simbolos.put(";", TipoToken.PUTO_COMA);
    simbolos.put("-", TipoToken.MENOS);
    simbolos.put("+", TipoToken.MAS);
    simbolos.put("*", TipoToken.ASTERISCO);
    simbolos.put("/", TipoToken.DIAGONAL);
    simbolos.put("!", TipoToken.NO);
  }

  public static boolean isSimbol(char vistazo){
    //Comprueba si es un simbolo
    return simbolos.get(Character.toString(vistazo)) != null;
  }

  public static TipoToken CompSimbol(char vistazo){
    return simbolos.get(Character.toString(vistazo));
  }
}
