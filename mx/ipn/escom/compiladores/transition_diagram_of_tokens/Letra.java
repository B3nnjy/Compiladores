package mx.ipn.escom.compiladores.transition_diagram_of_tokens;

import static mx.ipn.escom.compiladores.transition_diagram_of_tokens.Numbers.isDigit;

public class Letra {

    public static boolean isLetter(char vistazo){
        //Motodo para comprobar si es una letra de la 'a' a la 'z' o de la 'A' a la 'Z'
        if(Character.isLetter(vistazo)){
            return true;
        }else{
            return false;
        }
    }


    public static int CompIfIsLetter(int estado, char vistazo){
        if (estado < 9){
            //Si no es el estado 9 siplemente sale del metodo
            return estado;
        }

        //Comprobacion del estado al que tiene que ir
        // Letra
        switch(estado){
            case 9:
                if(isLetter(vistazo)){
                    estado = 10;
                }
                break;
            case 10:
                if(isDigit(vistazo) || isLetter(vistazo)){
                    estado = 10;
                }else{
                    estado = 11;
                }
                break;
        }
        //Retorna el estado final
        return estado;
    }

}
