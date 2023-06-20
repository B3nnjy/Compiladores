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
                    SolverAritmetico solver = new SolverAritmetico(n);
                    Object res = solver.resolver();
                    System.out.println(res);
                break;

                case VARIABLE:
                    // Crear una variable. Usar tabla de simbolos
                    break;
                case SI:
                    break;

            }
        }
    }

}

