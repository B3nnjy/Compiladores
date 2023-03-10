package mx.ipn.escom.compiladores.automatas;

public class OpeRelacional {


    public static boolean isOpRel(char vistazo){
        //Motodo para comprobar si es un operador relacional
        if(vistazo == '<' || vistazo == '=' || vistazo == '>'){
            return true;
        }else{
            return false;
        }
    }

    public static int CompIfIsOpRel(int estado, char vistazo){
        if (estado > 6){
            //Si no es el estado 0 siplemente sale del metodo
            return estado;
        }

        //Comprobacion del estado al que tiene que ir
        switch(estado){
            case 0:
                if(vistazo == '='){
                    estado = 5;
                } else if (vistazo == '<') {
                    estado = 1;
                } else if (vistazo == '>') {
                    estado = 6;
                }
                break;
            case 1:
                if(vistazo == '='){
                    estado = 2;
                } else if (vistazo == '>') {
                    estado = 3;
                    
                }else
                    estado = 4;
                break;
            case 6:

                if (vistazo == '='){
                    estado = 7;
                }else
                    estado = 8;
                break;
        }
        //Retorna el estado final
        return estado;
    }

}
