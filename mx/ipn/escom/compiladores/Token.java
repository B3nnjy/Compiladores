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
}
