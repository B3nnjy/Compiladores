package mx.ipn.escom.compiladores;

import mx.ipn.escom.compiladores.primeros.Class_inher;
import mx.ipn.escom.compiladores.primeros.Primeros;

import java.util.List;

public class Parser {
    private final List<Token> tokens;
    private int i = 0;
    private boolean hayErrores = false;
    public Token preanalisis;

    public Parser(List<Token> tokens){
        this.tokens = tokens;
    }

    public void parser(){
        i = 0;
        preanalisis = tokens.get(i);
        Declaration();

        if(!hayErrores && !preanalisis.equals(TipoToken.EOF)){

        }
        else if(!hayErrores && preanalisis.equals(TipoToken.EOF)){
            System.out.println("Consulta válida");
        }System.out.println("Error en la linea " + preanalisis.linea + " posicion " + preanalisis.col + ". No se esperaba el token " + preanalisis.tipo);

        /*if(!preanalisis.equals(finCadena)){
            System.out.println("Error en la posición " + preanalisis.posicion + ". No se esperaba el token " + preanalisis.tipo);
        }else if(!hayErrores){
            System.out.println("Consulta válida");
        }*/
    }

    public void Declaration(){
        if (Primeros.class_decl.find(preanalisis.tipo)){
            Class_decl();
        } else if (Primeros.fun_decl.find(preanalisis.tipo)) {
            Fun_decl();
        } else if (Primeros.var_decl.find(preanalisis.tipo)) {
            Var_decl();
        } else if (Primeros.statement.find(preanalisis.tipo)) {
            Statement();
        }else{
            hayErrores = true;
            System.out.println("ERROR!!");
        }
    }

    public void Class_decl(){
        if(preanalisis.equals(TipoToken.CLASE)){
            coincidir(TipoToken.CLASE);
            coincidir(TipoToken.IDENTIFICADOR);
            Class_inher();
            coincidir(TipoToken.LLAVE_IZQ);

        }else {
            System.out.println("Error en la linea " + preanalisis.linea + " posicion " + preanalisis.col + ". Se esperaba un  " + TipoToken.CLASE);
        }


    }

    public void Fun_decl(){

    }

    public void Var_decl(){

    }

    public void Statement(){

    }

    public void Class_inher(){
        coincidir(TipoToken.MENOR);
    }

    public void Funtions(){
        if (Primeros.function.find(preanalisis.tipo)){
            Funtions();
        }
    }
    public void Funtion(){
        if (preanalisis.equals(TipoToken.IDENTIFICADOR)){
            coincidir(TipoToken.IDENTIFICADOR);
            coincidir(TipoToken.PAREN_IZQ);
        }
    }

    void coincidir(TipoToken t){
        if(hayErrores) return;

        if(preanalisis.tipo == t){
            i++;
            preanalisis = tokens.get(i);
        }
        else{
            hayErrores = true;
            System.out.println("Error en la linea " + preanalisis.linea + " posicion " + preanalisis.col + ". Se esperaba un  " + t);

        }
    }
}
