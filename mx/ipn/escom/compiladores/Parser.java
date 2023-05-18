package mx.ipn.escom.compiladores;

import mx.ipn.escom.compiladores.primeros.Primeros;

public class Parser {
    public Parser(){
    }

    public void Declaration(TipoToken pre){
        if (Primeros.class_decl.find(pre)){
            Class_decl(pre);
        } else if (Primeros.fun_decl.find(pre)) {
            Fun_decl(pre);
        } else if (Primeros.var_decl.find(pre)) {
            Var_decl(pre);
        } else if (Primeros.statement.find(pre)) {
            Statement(pre);
        }else{
            System.out.println("ERROR!!");
        }
    }

    public void Class_decl(TipoToken pre){

    }

    public void Fun_decl(TipoToken pre){

    }

    public void Var_decl(TipoToken pre){

    }

    public void Statement(TipoToken pre){

    }
}
