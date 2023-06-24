package mx.ipn.escom.compiladores;

public class Arbol {
    private Nodo raiz;

    public Arbol(Nodo raiz){
        this.raiz = raiz;
    }
    public Arbol(){}

    public void setRaiz(Nodo raiz){
        this.raiz = raiz;
    }

    public void recorrer(){
        for(Nodo n : raiz.getHijos()){
            Token t = n.getValue();
            switch (t.tipo){
                // Operadores aritméticos
                case IGUAL:
                    SolverAritmetico solver = new SolverAritmetico();
                    solver.resolver(n);
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
                        for (int i = 1; i < n.getHijos().size() - 1; i++){
                            Nodo auxRaiz = new Nodo(null);
                            Nodo instruccion = n.getHijos().get(i);
                            auxRaiz.insertarHijo(instruccion);
                            Arbol arbolInstruccion = new Arbol(auxRaiz);
                            arbolInstruccion.recorrer();
                        }
                    } else if (n.getHijos().get(n.getHijos().size() - 1).getValue().tipo == TipoToken.SI_NO) {
                        Nodo auxRaiz = new Nodo(null);
                        Nodo sino = n.getHijos().get(n.getHijos().size() - 1);

                        for (int i = 0; i < sino.getHijos().size(); i++){
                            Nodo instruccion = sino.getHijos().get(i);
                            auxRaiz.insertarHijo(instruccion);
                        }

                        Arbol arbolInstruccion = new Arbol(auxRaiz);
                        arbolInstruccion.recorrer();
                    }
                    break;
                case SI_NO:
                    Nodo auxRaiz = new Nodo(null);
                    for (int i = 0; i < n.getHijos().size(); i++){
                        Nodo instruccion = n.getHijos().get(i);
                        auxRaiz.insertarHijo(instruccion);
                    }

                    Arbol arbolInstruccion = new Arbol(auxRaiz);
                    arbolInstruccion.recorrer();
                    break;
                case PARA:
                    //primero hijo para inicializacion
                    //segundo hijo para condicion
                    //tercer hijo para incremento
                    //cuarto hijo para instruccion
                    SolverAritmetico solverPara = new SolverAritmetico();
                    Arbol arbolInstruccionPara = new Arbol();
                    Nodo auxRaizPara = new Nodo(null);

                    Nodo auxdecla = new Nodo(null);
                    Nodo declaracion = n.getHijos().get(0);
                    auxdecla.insertarHijo(declaracion);
                    Arbol arbolDeclaracion = new Arbol(auxdecla);
                    arbolDeclaracion.recorrer();

                    Nodo paracondicion = n.getHijos().get(1);
                    //SolverAritmetico solverParaCondicion = new SolverAritmetico(paracondicion);
                    boolean condicionParaCumplida = (Boolean) solverPara.resolver(paracondicion);

                    while (condicionParaCumplida) {
                        for (int i = 3; i < n.getHijos().size(); i++) {
                            Nodo instruccionPara = n.getHijos().get(i);
                            auxRaizPara.insertarHijo(instruccionPara);

                            arbolInstruccionPara.setRaiz(auxRaizPara);
                            arbolInstruccionPara.recorrer();
                            auxRaizPara.clear();
                        }

                        // Aquí agregar el código para el incremento
                        Nodo incremento = n.getHijos().get(2);
                        //SolverAritmetico solverCondicionwhile = new SolverAritmetico(incremento);
                        solverPara.resolver(incremento);
                        condicionParaCumplida = (Boolean) solverPara.resolver(paracondicion);
                    }
                    break;
                case MIENTRAS:
                    SolverAritmetico solverMientras = new SolverAritmetico();
                    Arbol arbolInstruccionwhile = new Arbol();
                    Nodo auxRaizwhile = new Nodo(null);
                    Nodo condicionwhile = n.getHijos().get(0);
                    boolean condicionCumplidawhile = (Boolean) solverMientras.resolver(condicionwhile);

                    while (condicionCumplidawhile) {
                        for (int i = 1; i < n.getHijos().size(); i++) {
                            Nodo instruccionwhile = n.getHijos().get(i);
                            auxRaizwhile.insertarHijo(instruccionwhile);

                            arbolInstruccionwhile.setRaiz(auxRaizwhile);
                            arbolInstruccionwhile.recorrer();
                            auxRaizwhile.clear();
                        }
                        condicionCumplidawhile = (Boolean) solverMientras.resolver(condicionwhile);
                    }
                    break;
            }
        }
    }

}

