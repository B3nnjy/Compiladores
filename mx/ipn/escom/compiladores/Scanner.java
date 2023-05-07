package mx.ipn.escom.compiladores;

import mx.ipn.escom.compiladores.automatas.*;

import java.util.ArrayList;
import java.util.List;
import static mx.ipn.escom.compiladores.PalabrasReservadas.palabrasReservadas;

public class Scanner {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();
    private int linea = 1;
    private int col = 1;

    Scanner(String source){
        this.source = source;
    }

    List<Token> scanTokens(){
        int estado = 0;
        int iLexema = 0;
        int fLexema;
        String lexema;

        //Aquí va el corazón del scanner.
        for (int i = 0; i < source.length(); i++) {
            char vistazo = source.charAt(i);
            fLexema = i;
            estado = Letra.CompIfIsLetter(estado, vistazo);


            switch (estado){
                case 0:
                    if (vistazo == '\0'){break;}
                    if (vistazo == ' ' || vistazo == '\t'){
                        iLexema = fLexema + 1;
                    }else if (vistazo == '\n') {
                        iLexema = i + 1;
                        linea++;
                        col  = 0;
                    }else if(Character.isAlphabetic(vistazo)){
                        //Entra al diagrama de transicion para los identificadores y palabras reservadas
                        estado = 9;
                        i--;
                        col--;
                    }else if (simbolos.isSimbol(vistazo)){
                        tokens.add(new Token(simbolos.CompSimbol(vistazo), String.valueOf(vistazo), null, linea, col - 1));
                        iLexema = fLexema + 1;
                    }else{
                        Interprete.error(linea, "caracter ilegal encontrado:" + vistazo);
                        iLexema = i+1;

                    }
                    break;
                    //Estados finales
                case 11:
                    lexema = source.substring(iLexema, fLexema);
                    TipoToken tt = palabrasReservadas.get(lexema);
                    if(tt == null) {
                        //Crear el token tipo identificador
                        tokens.add( new Token(TipoToken.IDENTIFICADOR, lexema, null, linea, col - lexema.length()) );
                    }
                    else{
                        tokens.add( new Token(tt, lexema, null, linea, col - lexema.length()) );
                    }
                    estado = 0;
                    iLexema = fLexema;
                    i--;
                    col--;
                    break;
            }
            col++;
        }

        //Token de fin de archivo
        tokens.add(new Token(TipoToken.EOF, "", null, linea, col));

        return tokens;
    }
}