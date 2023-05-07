package mx.ipn.escom.compiladores.automatas;

import mx.ipn.escom.compiladores.TipoToken;

import java.util.HashMap;
import java.util.Map;

public class simbolos {

  private static final Map<String, TipoToken> simbolos = new HashMap<>();

  static{
    simbolos.put(",", TipoToken.COMA);
    simbolos.put(".", TipoToken.PUNTO);
    simbolos.put("*", TipoToken.ASTERISCO);
  }

  public static boolean isSimbol(char vistazo){
    //Comprueba si es un simbolo
    return simbolos.get(Character.toString(vistazo)) != null;
  }

  public static TipoToken CompSimbol(char vistazo){
    return simbolos.get(Character.toString(vistazo));
  }
}
