package mx.ipn.escom.compiladores.transition_diagram_of_tokens;

import mx.ipn.escom.compiladores.TipoToken;
import mx.ipn.escom.compiladores.Token;

public class UnsignedNumbers {

  private static boolean isDigit(char vistazo){
    if(vistazo == '1' || vistazo == '2' || vistazo== '3' || vistazo=='4' || vistazo == '5'
        || vistazo == '6' || vistazo == '7' || vistazo == '8'|| vistazo == '9'){
      return true;
    }else{
      return false;
    }
  }

  public static int UnsignedNumbersImpl(int estado, char vistazo){
    switch(estado){
      case 0:
        // Numeros
        if(isDigit(vistazo)){
          estado = 13;
        }
        break;
      case 13:
        if(isDigit(vistazo)){
          estado = 13;
          break;
        }else{
          if(vistazo ==  '.'){
            estado =  14;
          }else if (vistazo == 'E') {
            estado = 16;
          }else{
            estado = 20;
          }
        }
        break;
      case 14:
        if(isDigit(vistazo) || vistazo == '0'){
          estado = 15;
        }
        break;
      case 15:
        if(isDigit(vistazo) || vistazo == '0'){
          estado = 15;
        }else if(vistazo == 'E'){
          estado = 16;
        }else{
          estado = 21;
        }
        break;
      case 16:
        if(vistazo == '+' || vistazo == '-'){
          estado = 17;
        }else if(isDigit(vistazo)){
          estado = 18;
        }
        break;
      case 17:
        if(isDigit(vistazo)){
          estado = 18;
        }
        break;
      case 18:
        if(isDigit(vistazo) || vistazo == '0'){
          estado = 18;
        }else{
          estado = 19;
        }
        break;

    }
    return estado;
  }
}
