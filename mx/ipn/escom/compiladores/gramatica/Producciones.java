package mx.ipn.escom.compiladores.gramatica;

import mx.ipn.escom.compiladores.NoTerminales;
import mx.ipn.escom.compiladores.TipoToken;

import java.util.ArrayList;

public class Producciones {

    public static Object parteIzquierda(int i){
        switch (i){
            case 0: return NoTerminales.Q;
            case 1, 2: return NoTerminales.D;
            case 3, 4: return NoTerminales.P;
            case 5: return NoTerminales.A;
            case 6, 7: return NoTerminales.A1;
            case 8: return NoTerminales.A2;
            case 9, 10: return NoTerminales.A3;
            case 11: return NoTerminales.T;
            case 12, 13: return NoTerminales.T1;
            case 14: return NoTerminales.T2;
            case 15, 16: return NoTerminales.T3;
            default:
                System.err.println("Error: No se encontro la gramatica");
                System.exit(1);
        }
        return null;
    }
    public static ArrayList<Object> parteDerecha(int i){
        ArrayList<Object> produccion = new ArrayList<>();

        switch (i){
            case 0:
                produccion.add(NoTerminales.T);
                produccion.add(TipoToken.FROM);
                produccion.add(NoTerminales.D);
                produccion.add(TipoToken.SELECT);
                break;
            case 1:
                produccion.add(NoTerminales.P);
                produccion.add(TipoToken.DISTINCT);
                break;
            case 2:
                produccion.add(NoTerminales.P);
                break;
            case 3:
                produccion.add(TipoToken.ASTERISCO);
                break;
            case 4:
                produccion.add(NoTerminales.A);
                break;
            case 5:
                produccion.add(NoTerminales.A1);
                produccion.add(NoTerminales.A2);
                break;
            case 6:
                produccion.add(NoTerminales.A);
                produccion.add(TipoToken.COMA);
                break;
            case 8:
                produccion.add(NoTerminales.A3);
                produccion.add(TipoToken.IDENTIFICADOR);
                break;
            case 9:
                produccion.add(TipoToken.IDENTIFICADOR);
                produccion.add(TipoToken.PUNTO);
                break;
            case 11:
                produccion.add(NoTerminales.T1);
                produccion.add(NoTerminales.T2);
                break;
            case 12:
                produccion.add(NoTerminales.T);
                produccion.add(TipoToken.COMA);
                break;
            case 14:
                produccion.add(NoTerminales.T3);
                produccion.add(TipoToken.IDENTIFICADOR);
                break;
            case 15:
                produccion.add(TipoToken.IDENTIFICADOR);
                break;
            case 7, 10, 13, 16: break;
            default:
                System.err.println("Error: No se enconcontro la gramatica");
                System.exit(1);
                break;
        }
        return produccion;
    }
}
