package mx.ipn.escom.compiladores.automatas;

import mx.ipn.escom.compiladores.TipoToken;

public class simbolos {
  public static boolean isSimbol(char vistazo){
    //Comprueba si es un simbolo
    if (vistazo == '(' || vistazo == ')' || vistazo == '{' || vistazo == '}' || vistazo == ',' || vistazo == '.' || vistazo == ';' || vistazo == '-' || vistazo == '+'){
      return true;
    }else{
      return false;
    }
  }

  public static TipoToken CompSimbol(char vistazo){
    switch (vistazo){
      case ('('):
        return TipoToken.PAREN_IZQ;
      case (')'):
        return TipoToken.PAREN_DER;
      case ('{'):
        return TipoToken.LLAVE_IZQ;
      case ('}'):
        return TipoToken.LLAVE_DER;
      case (','):
        return TipoToken.COMA;
      case ('.'):
        return TipoToken.PUNTO;
      case (';'):
        return TipoToken.PUTO_COMA;
      case ('-'):
        return TipoToken.MENOS;
      case ('+'):
        return TipoToken.MAS;
      default: return null;
    }
  }

}
