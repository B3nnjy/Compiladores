package mx.ipn.escom.compiladores;

public class Token {

    final TipoToken tipo;
    final String lexema;
    final Object literal;
    final int linea;
    final int col;

    public Token(TipoToken tipo, String lexema, Object literal, int linea, int col) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.literal = literal;
        this.linea = linea;
        this.col = col;
    }

    public String toString(){
        return tipo + " " + lexema + " " + literal;
    }

    public boolean equals(TipoToken tipo){
        return this.tipo == tipo;
    }

    public boolean esOperando(){
        switch (this.tipo){
            case IDENTIFICADOR, NUMERO, CADENA, VERDADERO, FALSO -> {return true;}
            default -> {return false;}
        }
    }

    public boolean esOperador(){
        switch (this.tipo){
            case MAS, MENOS, ASTERISCO, DIAGONAL, IGUAL, NO, COMPARACION, MAYOR, MAYOR_EQ, MENOR, MENOR_EQ,NOT_EQ, Y, O -> {return true;}
            default -> {return false;}
        }
    }

    public boolean esPalabraReservada(){
        if (PalabrasReservadas.palabrasReservadas.containsValue(this.tipo)){
            return true;
        }
        return false;
    }

    public boolean esEstructuraDeControl(){
        switch (this.tipo){
            case SI:
            case SI_NO:
            case MIENTRAS:
            case PARA:
                return true;
            default:
                return false;
        }
    }

    public boolean precedenciaMayorIgual(Token t){
        return this.obtenerPrecedencia() >= t.obtenerPrecedencia();
    }

    private int obtenerPrecedencia(){
        switch (this.tipo){
            case PUNTO:
                return 4;
            case ASTERISCO:
            case DIAGONAL:
                return 3;
            case MAS:
            case MENOS:
                return 2;
            case IGUAL:
            case MAYOR:
            case MAYOR_EQ:
            case MENOR:
            case MENOR_EQ:
            case NOT_EQ:
                return 1;
        }

        return 0;
    }

    public int aridad(){
        switch (this.tipo) {
            case ASTERISCO:
            case DIAGONAL:
            case MAS:
            case MENOS:
            case IGUAL:
            case MAYOR:
            case MAYOR_EQ:
            case COMPARACION:
            case MENOR:
            case MENOR_EQ:
            case Y:
            case O:
                return 2;
            case NO:
                return 1;
        }
        return 0;
    }

}
