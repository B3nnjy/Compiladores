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
                    // Crear una variable. Usar tabla de simbolos
                    if (!TablaSimbolos.existeIdentificador(n.getHijos().get(0).getValue().lexema)){
                        if (n.getHijos().size() == 2){
                            Nodo derecho = n.getHijos().get(1);
                            SolverAritmetico solverAritmetico =  new SolverAritmetico(derecho);
                            Object res_derecho = solverAritmetico.resolver();

                            TablaSimbolos.asignar(n.getHijos().get(0).getValue().lexema, res_derecho);
                        } else{
                            TablaSimbolos.asignar(n.getHijos().get(0).getValue().lexema, null);
                        }
                    }
                    break;
                case IMPRIMIR:
                    Nodo hijo = n.getHijos().get(0);
                    SolverAritmetico solver_hijo = new SolverAritmetico(hijo);
                    Object res_hijo = solver_hijo.resolver();

                    System.out.println(res_hijo);
                    break;
                case SI:
                    // Nodo IZQ para la condicion
                    // Nodo DER para la instruccion
                    Nodo condicion = n.getHijos().get(0);
                    SolverAritmetico solverCondicion = new SolverAritmetico(condicion);
                    boolean condicionCumplida = (Boolean) solverCondicion.resolver();

                    if (condicionCumplida){
                        Nodo auxRaiz = new Nodo(null);
                        Nodo instruccion = n.getHijos().get(1);
                        auxRaiz.insertarHijo(instruccion);

                        Arbol arbolInstruccion = new Arbol(auxRaiz);
                        arbolInstruccion.recorrer();
                    } else if (n.getHijos().size() == 3) {
                        Nodo auxRaiz = new Nodo(null);
                        Nodo instruccion = n.getHijos().get(2);
                        auxRaiz.insertarHijo(instruccion);

                        Arbol arbolInstruccion = new Arbol(auxRaiz);
                        arbolInstruccion.recorrer();
                    }
                    break;
                case SI_NO:
                    Nodo auxRaiz = new Nodo(null);
                    Nodo instruccion = n.getHijos().get(0);
                    auxRaiz.insertarHijo(instruccion);

                    Arbol arbolInstruccion = new Arbol(auxRaiz);
                    arbolInstruccion.recorrer();

                    break;
                case PARA:
                    break;
                case MIENTRAS:

            }
        }
    }

}

