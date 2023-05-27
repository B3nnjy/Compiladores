package mx.ipn.escom.compiladores;

import mx.ipn.escom.compiladores.primeros.Primeros;

import java.util.ArrayList;
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

        if(!hayErrores && !preanalisis.equals(TipoToken.EOF)){
            Declaration();
        }
        if(!hayErrores && preanalisis.equals(TipoToken.EOF)){
            System.out.println("Consulta válida");
        }

        //System.out.println("Error en la linea " + preanalisis.linea + " posicion " + preanalisis.col + ". No se esperaba el token " + preanalisis.tipo);

        /*if(!preanalisis.equals(finCadena)){
            System.out.println("Error en la posición " + preanalisis.posicion + ". No se esperaba el token " + preanalisis.tipo);
        }else if(!hayErrores){
            System.out.println("Consulta válida");
        }*/
    }

    public void Program(){
        if (Primeros.declaration.find(preanalisis.tipo)){
            Declaration();
        }
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
            Error(TipoToken.CLASE);
        }
    }

    public void Fun_decl(){
        if (preanalisis.equals(TipoToken.FUNCION)){
            coincidir(TipoToken.FUNCION);
            Function();
        }else {
            Error(TipoToken.FUNCION);
        }
    }

    public void Var_decl(){
        if (preanalisis.equals(TipoToken.VARIABLE)){
            coincidir(TipoToken.VARIABLE);
            coincidir(TipoToken.IDENTIFICADOR);
            Var_init();
        }else {
            Error(TipoToken.VARIABLE);
        }
    }

    public void Statement(){
        if (Primeros.expr_stmt.find(preanalisis.tipo)){
            Expr_stmt();
        } else if (Primeros.for_stmt.find(preanalisis.tipo)) {
            For_stmt();
        } else if (Primeros.if_stmt.find(preanalisis.tipo)) {
            If_stmt();
        } else if (Primeros.print_stmt.find(preanalisis.tipo)) {
            Print_stmt();
        } else if (Primeros.return_stmt.find(preanalisis.tipo)) {
            Return_stmt();
        } else if (Primeros.while_stmt.find(preanalisis.tipo)) {
            While_stmt();
        } else if (Primeros.block.find(preanalisis.tipo)) {
            Block();
        }

    }

    public void Var_init(){
        if (preanalisis.equals(TipoToken.IGUAL)){
            coincidir(TipoToken.IGUAL);
            Expression();
        }
    }

    public void Expression(){
        if (Primeros.assigment.find(preanalisis.tipo)){
            Assignment();
        }
    }

    private void Assignment() {
        if (Primeros.logic_or.find(preanalisis.tipo)){
            Logic_or();
            Assignment_2();
        }
    }

    private void Assignment_2(){
        if (preanalisis.equals(TipoToken.IGUAL)){
            coincidir(TipoToken.IGUAL);
            Expression();
        }
    }




    private void Logic_or() {
        if (Primeros.logic_and.find(preanalisis.tipo)){
            Logic_and();
            Logic_or_2();
        }
    }

    private void Logic_or_2() {
        if (preanalisis.equals(TipoToken.O)){
            coincidir(TipoToken.O);
            Logic_and();
            Logic_or_2();
        }
    }

    private void Logic_and() {
        if (Primeros.equality.find(preanalisis.tipo)){
            Equality();
            Logic_and_2();
        }
    }

    private void Equality() {
        if (Primeros.comparison.find(preanalisis.tipo)){
            Comparison();
            Equality_2();
        }
    }

    private void Equality_2() {
        if (preanalisis.equals(TipoToken.NOT_EQ)){
            coincidir(TipoToken.NOT_EQ);
            Comparison();
            Equality_2();
        } else if (preanalisis.equals(TipoToken.COMPARACION)) {
            coincidir(TipoToken.COMPARACION);
            Comparison();
            Equality_2();
        }
    }

    private void Comparison() {
        if (Primeros.term.find(preanalisis.tipo)){
            Term();
            Comparison_2();
        }
    }

    private void Comparison_2() {
        if (preanalisis.equals(TipoToken.MAYOR)){
            coincidir(TipoToken.MAYOR);
            Term();
            Comparison_2();
        } else if (preanalisis.equals(TipoToken.MAYOR_EQ)) {
            coincidir(TipoToken.MAYOR_EQ);
            Term();
            Comparison_2();
        } else if (preanalisis.equals(TipoToken.MENOR)) {
            coincidir(TipoToken.MENOR);
            Term();
            Comparison_2();
        } else if (preanalisis.equals(TipoToken.MENOR_EQ)) {
            coincidir(TipoToken.MENOR_EQ);
            Term();
            Comparison_2();
        }
    }

    private void Term() {
        if (Primeros.factor.find(preanalisis.tipo)){
            Factor();
            Term_2();
        }
    }

    private void Term_2() {
        if (preanalisis.equals(TipoToken.MENOS)){
            coincidir(TipoToken.MENOS);
            Factor();
            Term_2();
        } else if (preanalisis.equals(TipoToken.MAS)) {
            Factor();
            Term_2();
        }
    }

    private void Factor() {
        if (Primeros.unary.find(preanalisis.tipo)){
            Unary();
            Factor_2();
        }
    }

    private void Factor_2() {
        if (preanalisis.equals(TipoToken.DIAGONAL)){
            coincidir(TipoToken.DIAGONAL);
            Unary();
            Factor_2();
        } else if (preanalisis.equals(TipoToken.ASTERISCO)) {
            coincidir(TipoToken.ASTERISCO);
            Unary();
            Factor_2();
        }
    }

    private void Expr_stmt(){
        if (Primeros.expression.find(preanalisis.tipo)){
            Expression();
            coincidir(TipoToken.PUNTO_COMA);
        }
    }

    private void For_stmt(){
        if (preanalisis.equals(TipoToken.PARA)){
            coincidir(TipoToken.PARA);
            coincidir(TipoToken.PAREN_IZQ);
            For_stmt_1();
            For_stmt_2();
            For_stmt_3();
            coincidir(TipoToken.PAREN_DER);
            Statement();
        }
    }

    public void If_stmt(){
        if (preanalisis.equals(TipoToken.SI)){
            coincidir(TipoToken.SI);
            coincidir(TipoToken.PAREN_IZQ);
            Expression();
            coincidir(TipoToken.PAREN_DER);
            Statement();
            Else_statement();
        }
    }

    private void Else_statement() {
        if (preanalisis.equals(TipoToken.SI_NO)){
            coincidir(TipoToken.SI_NO);
            Statement();
        }
    }

    private void For_stmt_1(){
        if (Primeros.var_decl.find(preanalisis.tipo)){
            Var_decl();
        } else if (Primeros.expr_stmt.find(preanalisis.tipo)) {
            Expr_stmt();
        } else if (preanalisis.equals(TipoToken.PUNTO_COMA)) {
            coincidir(TipoToken.PUNTO_COMA);
        }
    }

    private void For_stmt_2(){
        if (Primeros.expression.find(preanalisis.tipo)){
            Expression();
            coincidir(TipoToken.PUNTO_COMA);
        } else if (preanalisis.equals(TipoToken.PUNTO_COMA)){
            coincidir(TipoToken.PUNTO_COMA);
        }
    }

    private void For_stmt_3(){
        if (Primeros.expression.find(preanalisis.tipo)){
            Expression();
        }
    }

    public void Print_stmt(){
        if (preanalisis.equals(TipoToken.IMPRIMIR)){
            coincidir(TipoToken.IMPRIMIR);
            Expression();
            coincidir(TipoToken.PUNTO_COMA);
        }
    }

    public void Return_stmt(){
        if (preanalisis.equals(TipoToken.RETORNAR)){
            coincidir(TipoToken.RETORNAR);
            Return_exp_opc();
            coincidir(TipoToken.PUNTO_COMA);
        }
    }

    private void Return_exp_opc() {
        if (Primeros.expression.find(preanalisis.tipo)){
            Expression();
        }
    }

    private void While_stmt(){
        if (preanalisis.equals(TipoToken.MIENTRAS)){
            coincidir(TipoToken.MIENTRAS);
            coincidir(TipoToken.PAREN_IZQ);
            Expression();
            coincidir(TipoToken.PAREN_DER);
            Statement();
        }
    }

    private void Unary() {
        if (preanalisis.equals(TipoToken.NO)){
            coincidir(TipoToken.NO);
            Unary();
        } else if (preanalisis.equals(TipoToken.MENOS)) {
            coincidir(TipoToken.MENOS);
            Unary();
        } else if (Primeros.call.find(preanalisis.tipo)) {
            Call();
        }
    }

    private void Logic_and_2() {
        if (preanalisis.equals(TipoToken.Y)){
            coincidir(TipoToken.Y);
            Equality();
            Logic_and_2();
        }
    }

    private void Call() {
        if (Primeros.primary.find(preanalisis.tipo)){
            Primary();
            Call_2();
        }
    }

    private void Call_2() {
        if (preanalisis.equals(TipoToken.PAREN_IZQ)){
            coincidir(TipoToken.PAREN_IZQ);
            Arguments_opc();
            coincidir(TipoToken.PAREN_DER);
            Call_2();
        } else if (preanalisis.equals(TipoToken.PUNTO)) {
            coincidir(TipoToken.PUNTO);
            coincidir(TipoToken.IDENTIFICADOR);
            Call_2();
        }
    }
    private void Call_opc() {
        if (Primeros.call.find(preanalisis.tipo)){
            Call();
            coincidir(TipoToken.PUNTO);
        }
    }

    private void Primary() {
        if (preanalisis.equals(TipoToken.VERDADERO)){
            coincidir(TipoToken.VERDADERO);
        } else if (preanalisis.equals(TipoToken.FALSO)) {
            coincidir(TipoToken.FALSO);
        } else if (preanalisis.equals(TipoToken.NULO)) {
            coincidir(TipoToken.NULO);
        } else if (preanalisis.equals(TipoToken.ESTE)) {
            coincidir(TipoToken.ESTE);
        } else if (preanalisis.equals(TipoToken.NUMERO)) {
            coincidir(TipoToken.NUMERO);
        } else if (preanalisis.equals(TipoToken.CADENA)) {
            coincidir(TipoToken.CADENA);
        } else if (preanalisis.equals(TipoToken.IDENTIFICADOR)) {
            coincidir(TipoToken.IDENTIFICADOR);
        } else if (preanalisis.equals(TipoToken.PAREN_IZQ)) {
            coincidir(TipoToken.PAREN_IZQ);
            Expression();
            coincidir(TipoToken.PAREN_DER);
        } else if (preanalisis.equals(TipoToken.SUPER)) {
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

    private void Arguments_opc() {
        if (Primeros.arguments.find(preanalisis.tipo)){
            Arguments();
        }
    }

    private void Arguments() {
        if (Primeros.expression.find(preanalisis.tipo)){
            Expression();
            Arguments_2();
        }
    }

    private void Arguments_2() {
        if (preanalisis.equals(TipoToken.COMA)){
            coincidir(TipoToken.COMA);
            Expression();
            Arguments_2();
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
            Error(t);
        }
    }

    private void Error(TipoToken t){
        hayErrores = true;
        System.out.println("Error en la linea " + preanalisis.linea + " posicion " + preanalisis.col + ". Se esperaba un  " + t);
    }

    private void Error(ArrayList<TipoToken> lT){
        hayErrores = true;
        System.out.println("Error en la linea " + preanalisis.linea + " posicion " + preanalisis.col + ". Se esperaba un  ");

        for (TipoToken t : lT){
            System.out.print(t);

            if (lT.indexOf(t) != lT.size() - 1){
                System.out.print(" o ");
            }
        }
    }
}
