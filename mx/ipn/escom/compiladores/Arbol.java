package mx.ipn.escom.compiladores;

public class Arbol {
    private final Nodo raiz;

    public Arbol(Nodo raiz){
        this.raiz = raiz;
    }

    public void recorrer(){
        for(Nodo n : raiz.getHijos()){
            Token t = n.getValue();
            switch (t.tipo){
                // Operadores aritméticos
                case MAS:
                case MENOS:
                case ASTERISCO:
                case DIAGONAL:
                case MENOR:
                case MENOR_EQ:
                case MAYOR:
                case MAYOR_EQ:
                case IGUAL:
                case COMPARACION:
                case NOT_EQ:
                case Y:
                case O:
                    SolverAritmetico solver = new SolverAritmetico(n);
                    Object res = solver.resolver();
                    System.out.println(res);
                    break;
                case VARIABLE:
                    if (!TablaSimbolos.existeIdentificador(t.lexema)){
                        TablaSimbolos.asignar();
                    }
                    // Crear una variable. Usar tabla de simbolos
                    break;
                case SI:
                    break;

            }
        }
    }

}

