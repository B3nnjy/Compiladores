package mx.ipn.escom.compiladores;

import mx.ipn.escom.compiladores.automatas.*;

import java.util.ArrayList;
import java.util.List;

import static mx.ipn.escom.compiladores.PalabrasReservadas.palabrasReservadas;

public class Scanner {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();
    private int linea = 1;

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
            estado = Numbers.CompIfIsNumber(estado, vistazo);
            estado = Letra.CompIfIsLetter(estado, vistazo);
            estado = OpeRelacional.CompIfIsOpRel(estado, vistazo);

            switch (estado){
                case 0:
                    if (vistazo == ' ' || vistazo == '\t'){
                        iLexema = fLexema + 1;
                    }else if (vistazo == '\n') {
                        iLexema = i + 1;
                        linea++;
                    }else if (vistazo == '/' && source.charAt(i + 1) == '*'){
                        estado = 1000;
                    }else if (vistazo == '"' ){
                        estado = 1002;
                    }else if (vistazo == '/' && source.charAt(i + 1) == '/'){
                        estado = 1001;
                    }else if (Character.isDigit(vistazo)){
                      //Entra al diagrama de transicion para los numeros sin signo
                      estado = 12;
                      estado = Numbers.CompIfIsNumber(estado, vistazo);
                    }else if(Character.isLetter(vistazo)){
                        //Entra al diagrama de transicion para los identificadores y palabras reservadas
                        estado = 9;
                        estado = Letra.CompIfIsLetter(estado, vistazo);
                    }else if(OpeRelacional.isOpRel(vistazo)){
                        //Entra al automata de los operadores relacionales
                        estado = OpeRelacional.CompIfIsOpRel(estado, vistazo);
                    }else if (simbolos.isSimbol(vistazo)){
                        tokens.add(new Token(simbolos.CompSimbol(vistazo), String.valueOf(vistazo), null, linea));
                        iLexema = fLexema + 1;
                    } /*if (vistazo == '\0') {
                        break;
                    }else{
                            // caracter ilegal encontrado
                            iLexema = i+1;
                            Interprete.error(linea, "caracter ilegal encontrado [" + vistazo + "]");
                        }*/

                    if(vistazo == ']' || vistazo == '[' || vistazo == '¨' || vistazo == '~' || vistazo == '^' || vistazo == '?' || vistazo == '|' || vistazo == '@' || vistazo == '#' || vistazo == '$' || vistazo == '%' || vistazo == '&'){
                       // caracter ilegal encontrado
                        Interprete.error(linea, "caracter ilegal encontrado:" + vistazo);
                        iLexema = i+1;
                    }
                    break;
                    //Estados finales
                case 2:
                    lexema = source.substring(iLexema, fLexema + 1);
                    tokens.add(new Token(TipoToken.MENOR_EQ, lexema, null, linea));
                    estado = 0;
                    iLexema = fLexema + 1;
                    break;
                case 3:
                    lexema = source.substring(iLexema, fLexema + 1);
                    tokens.add(new Token(TipoToken.NOT_EQ, lexema, null, linea));
                    estado = 0;
                    iLexema = fLexema + 1;
                    break;
                case 4:
                    lexema = source.substring(iLexema, fLexema);
                    tokens.add(new Token(TipoToken.MENOR, lexema, null, linea));
                    estado = 0;
                    iLexema =fLexema;
                    i--;
                    break;
                case 7:
                    lexema = source.substring(iLexema, fLexema + 1);
                    tokens.add(new Token(TipoToken.MAYOR_EQ, lexema, null, linea));
                    estado = 0;
                    iLexema = fLexema + 1;
                    break;
                case 8:
                    lexema = source.substring(iLexema, fLexema);
                    tokens.add(new Token(TipoToken.MAYOR, lexema, null, linea));
                    estado = 0; iLexema = fLexema;
                    i--;
                    break;
                case 11:
                    lexema = source.substring(iLexema, fLexema);
                    TipoToken tt = palabrasReservadas.get(lexema);
                    if(tt == null) {
                        //Crear el token tipo identificador
                        tokens.add( new Token(TipoToken.IDENTIFICADOR, lexema, null, linea) );
                    }
                    else{
                        tokens.add( new Token(tt, lexema, null, linea) );
                    }
                    estado = 0;
                    iLexema = fLexema;
                    i--;
                    break;
                case 19:
                    lexema = source.substring(iLexema, fLexema);
                    int E = lexema.indexOf('E');
                    String entero = lexema.substring(0, E);
                    String potencia = lexema.substring(E+1);
                    float enteroP = Float.parseFloat(entero);
                    int pow = Integer.parseInt(potencia);
                    tokens.add(new Token(TipoToken.NUMERO, lexema, enteroP*(Math.pow(10, pow)), linea));
                    iLexema = fLexema;
                    estado = 0;
                    i--;
                    break;
                case 20:
                    lexema = source.substring(iLexema, fLexema);
                    tokens.add(new Token(TipoToken.NUMERO, lexema, Integer.parseInt(lexema), linea));
                    iLexema = fLexema;
                    estado = 0;
                    i--;
                    break;
                case 21:
                    lexema = source.substring(iLexema, fLexema);
                    tokens.add(new Token(TipoToken.NUMERO, lexema, Float.parseFloat(lexema), linea));
                    iLexema = fLexema;
                    estado = 0;
                    i--;
                    break;
                case 22:
                    tokens.add(new Token(TipoToken.COMPARACION, "==", null, linea));
                    i ++;
                    iLexema = i;
                    estado = 0;
                    break;
                case 23:
                    lexema = source.substring(iLexema, fLexema);
                    tokens.add(new Token(TipoToken.IGUAL, lexema, null , linea));
                    estado = 0;
                    iLexema = fLexema;
                    i--;
                    break;
                case 1000:
                    if (vistazo == '*' && source.charAt(i + 1) == '/'){
                        estado = 0;
                        i++;
                        iLexema = i+1;
                    }
                    break;
                case 1001:
                    if (vistazo == '\n'){
                        estado = 0;
                        iLexema = i+1;
                        linea++;
                    }
                    break;
                case 1002:
                    if (vistazo != '"'){
                        fLexema += vistazo;
                    }else {
                        lexema = source.substring(iLexema + 1, fLexema);
                        tokens.add(new Token(TipoToken.CADENA, lexema, null, linea));
                        estado = 0;
                    }
                    break;
            }
        }

        //Token de fin de archivo
        tokens.add(new Token(TipoToken.EOF, "", null, linea));

        return tokens;
    }
}