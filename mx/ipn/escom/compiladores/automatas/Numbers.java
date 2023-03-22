package mx.ipn.escom.compiladores.automatas;

import static java.lang.Character.isDigit;

public class Numbers {
  public static int CompIfIsNumber(int estado, char vistazo){
    if (estado < 12 || estado > 18){
      //Si no es el estado 12 siplemente sale del metodo
      return estado;
    }

    //Comprobacion del estado al que tiene que ir
    // Numeros
    switch(estado){
      case 12:
        if(isDigit(vistazo)){
          estado = 13;
        }
        break;
      case 13:
        if (!isDigit(vistazo)) {
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
        if(isDigit(vistazo)){
          estado = 15;
        }
        break;
      case 15:
        if (!isDigit(vistazo)) {
          if(vistazo == 'E'){
            estado = 16;
          }else{
            estado = 21;
          }
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
        if (!isDigit(vistazo) && vistazo != '0') {
          estado = 19;
        }
        break;
    }
    //Retorna el estado final
    return estado;
  }
}
