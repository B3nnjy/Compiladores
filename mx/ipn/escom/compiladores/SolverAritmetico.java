package mx.ipn.escom.compiladores;

public class SolverAritmetico {

    private final Nodo nodo;

    public SolverAritmetico(Nodo nodo) {
        this.nodo = nodo;
    }

    public Object resolver(){
        return resolver(nodo);
    }
    private Object resolver(Nodo n){
        // No tiene hijos, es un operando
        if(n.getHijos() == null){
            if(n.getValue().tipo == TipoToken.NUMERO || n.getValue().tipo == TipoToken.CADENA) {
                return n.getValue().literal;
            }
            else if(n.getValue().tipo == TipoToken.IDENTIFICADOR){
                // Ver la tabla de símbolos
                if(TablaSimbolos.existeIdentificador(n.getValue().lexema)){
                    return TablaSimbolos.obtener(n.getValue().lexema);
                }
            } else if (n.getValue().tipo == TipoToken.FALSO) {
                return false;
            } else if (n.getValue().tipo == TipoToken.VERDADERO) {
                return true;
            }
        }

        // Por simplicidad se asume que la lista de hijos del nodo tiene dos elementos
        Nodo izq = n.getHijos().get(0);
        Nodo der = n.getHijos().get(1);


        Object resultadoIzquierdo = resolver(izq);
        Object resultadoDerecho = resolver(der);


        if (resultadoIzquierdo instanceof Boolean && resultadoDerecho instanceof Boolean){
            switch (n.getValue().tipo){
                case Y:
                    return ((Boolean)resultadoIzquierdo && (Boolean) resultadoDerecho);
                case O:
                    return ((Boolean)resultadoIzquierdo || (Boolean) resultadoDerecho);
            }
        }else if(resultadoIzquierdo instanceof Double && resultadoDerecho instanceof Double){
            switch (n.getValue().tipo){
                case MAS:
                    return ((Double)resultadoIzquierdo + (Double) resultadoDerecho);
                case MENOS:
                    return ((Double)resultadoIzquierdo - (Double) resultadoDerecho);
                case ASTERISCO:
                    return ((Double)resultadoIzquierdo * (Double) resultadoDerecho);
                case DIAGONAL:
                    return ((Double)resultadoIzquierdo / (Double) resultadoDerecho);
                case MENOR:
                    return ((Double)resultadoIzquierdo < (Double) resultadoDerecho);
                case MENOR_EQ:
                    return ((Double)resultadoIzquierdo <= (Double) resultadoDerecho);
                case MAYOR:
                    return ((Double)resultadoIzquierdo > (Double) resultadoDerecho);
                case MAYOR_EQ:
                    return ((Double)resultadoIzquierdo >= (Double) resultadoDerecho);
                case COMPARACION:
                    return ((Double)resultadoIzquierdo == (Double) resultadoDerecho);
                case NOT_EQ:
                    return ((Double)resultadoIzquierdo != (Double) resultadoDerecho);
                case IGUAL:
                    // Asignar el valor de la derecha a la variable de la izquierda
                    if (izq.getValue().tipo == TipoToken.IDENTIFICADOR){
                        TablaSimbolos.asignar(izq.getValue().lexema, resultadoDerecho);
                    }
                    break;

            }
        }
        else if(resultadoIzquierdo instanceof String && resultadoDerecho instanceof String){
            if (n.getValue().tipo == TipoToken.MAS){
                // Ejecutar la concatenación
                return (String)resultadoIzquierdo + (String) resultadoDerecho;
            }
        }
        return null;
    }
}
