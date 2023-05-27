package mx.ipn.escom.compiladores.primeros;

import mx.ipn.escom.compiladores.TipoToken;

import java.util.ArrayList;

public class NoTerminal {
    protected final ArrayList<TipoToken> primeros = new ArrayList<>();

    protected NoTerminal(){}

    public boolean find(TipoToken t){
        for (TipoToken i: primeros) {
            if (t == i){
                return true;
            }
        }
        return false;
    }

    public void show(){
        for (TipoToken i : primeros){
            System.out.println(i);
        }
    }

    public ArrayList<TipoToken> getPrimeros(){
        return this.primeros;
    }
}
