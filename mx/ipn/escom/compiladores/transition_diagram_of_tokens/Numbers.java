package mx.ipn.escom.compiladores.transition_diagram_of_tokens;

public class Numbers {

  public static boolean isDigit(char vistazo){
    //Motodo para comprobar si es un digito del 1 al 9
    if(vistazo == '0' || vistazo == '1' || vistazo == '2' || vistazo== '3' || vistazo=='4' || vistazo == '5'
        || vistazo == '6' || vistazo == '7' || vistazo == '8'|| vistazo == '9'){
      return true;
    }else{
      return false;
    }
  }

  public static int CompIfIsNumber(int estado, char vistazo){
    if (estado < 12){
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
        if(isDigit(vistazo) || vistazo == '0'){
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
    //Retorna el estado final
    return estado;
  }
}
