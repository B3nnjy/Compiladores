package mx.ipn.escom.compiladores.primeros;

import mx.ipn.escom.compiladores.TipoToken;

import java.util.ArrayList;

public class NoTerminal {
    protected final ArrayList<TipoToken> primeros = new ArrayList<>();

    protected NoTerminal(){}

    public boolean find(TipoToken t){
        int i = primeros.indexOf(t);
        return i > 0;
    }

    public void show(){
        for (TipoToken i : primeros){
            System.out.println(i);
        }
    }
}
