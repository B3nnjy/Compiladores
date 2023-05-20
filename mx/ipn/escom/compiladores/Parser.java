package mx.ipn.escom.compiladores;

import mx.ipn.escom.compiladores.primeros.Class_inher;
import mx.ipn.escom.compiladores.primeros.Parameters_2;
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
            Functions();
            coincidir(TipoToken.LLAVE_DER);

        }else {
            System.out.println("Error en la linea " + preanalisis.linea + " posicion " + preanalisis.col + ". Se esperaba un  " + TipoToken.CLASE);
        }
    }

    public void Fun_decl(){
        if (preanalisis.equals(TipoToken.FUNCION)){
            coincidir(TipoToken.FUNCION);
            Function();
        }
    }

    public void Var_decl(){

    }

    public void Statement(){

    }

    public void Var_init(){
        if (preanalisis.equals(TipoToken.IGUAL)){
            coincidir(TipoToken.IGUAL);
        }
    }
    public void Expression(){
        if (Primeros.assigment.find(preanalisis.tipo)){
            Assignment();
        }
    }

    private void Assignment() {
        if (Primeros.call_opc.find(preanalisis.tipo)){
            Call_opc();
        }
    }

    private void Call_opc() {
        if (Primeros.call.find(preanalisis.tipo)){
            Call();
        }

    }

    private void Call() {
        if (Primeros.primary.find(preanalisis.tipo)){
            Primary();
        }
    }

    private void Primary() {
        if (preanalisis.equals(TipoToken.VERDADERO)){
            coincidir(TipoToken.VERDADERO);
            coincidir(TipoToken.FALSO);
            coincidir(TipoToken.NULO);
            coincidir(TipoToken.ESTE);
            coincidir(TipoToken.NUMERO);
            coincidir(TipoToken.CADENA);
            coincidir(TipoToken.IDENTIFICADOR);
            coincidir(TipoToken.PAREN_IZQ);
            Expression();
            coincidir(TipoToken.PAREN_DER);
            coincidir(TipoToken.SUPER);
            coincidir(TipoToken.PUNTO);
            coincidir(TipoToken.IDENTIFICADOR);
        }
    }

    public void Class_inher(){
        if (preanalisis.equals(TipoToken.MENOR)){
            coincidir(TipoToken.MENOR);
            coincidir(TipoToken.IDENTIFICADOR);
        }

    }

    public void Functions(){
        if (Primeros.function.find(preanalisis.tipo)){
            Function();
            Functions();
        }
    }
    public void Function(){
        if (preanalisis.equals(TipoToken.IDENTIFICADOR)){
            coincidir(TipoToken.IDENTIFICADOR);
            coincidir(TipoToken.PAREN_IZQ);
            Parameters_opc();
            coincidir(TipoToken.PAREN_DER);
            Block();
        }
    }

    public void Parameters_opc(){
        if (Primeros.parameters_opc.find(preanalisis.tipo)){
            Parameters();
        }
    }

    public void Parameters(){
        if (preanalisis.equals(TipoToken.IDENTIFICADOR)){
            coincidir(TipoToken.IDENTIFICADOR);
            Parameters_2();
        }
    }

    public void Parameters_2(){
        if (preanalisis.equals(TipoToken.COMA)){
            coincidir(TipoToken.COMA);
            coincidir(TipoToken.IDENTIFICADOR);
            Parameters_2();
        }
    }

    public void Block(){
        if (preanalisis.equals(TipoToken.LLAVE_IZQ)){
            coincidir(TipoToken.LLAVE_IZQ);
            Block_decl();
            coincidir(TipoToken.LLAVE_DER);
        }
    }
    public void Block_decl(){
        if (Primeros.declaration.find(preanalisis.tipo)){
            Declaration();
            Block_decl();
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
