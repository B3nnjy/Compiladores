package mx.ipn.escom.compiladores;

import mx.ipn.escom.compiladores.automatas.Letra;
import mx.ipn.escom.compiladores.automatas.Numbers;
import mx.ipn.escom.compiladores.automatas.OpeRelacional;
import mx.ipn.escom.compiladores.automatas.simbolos;

import java.util.ArrayList;
import java.util.List;

import static mx.ipn.escom.compiladores.PalabrasReservadas.palabrasReservadas;

public class Scanner {
    private String source;
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
        source = source.replaceAll("/\\*.*?\\*/", "");
        source = source.replaceAll("^//.*?$", "");
        for (int i = 0; i < source.length(); i++) {
            char vistazo = source.charAt(i);
            fLexema = i;

            if (vistazo == '=' && source.charAt(i + 1) == '='){
                tokens.add(new Token(TipoToken.COMPARACION, "==", null, linea));
                i ++;
                iLexema = i;
                continue;
            }

            estado = Numbers.CompIfIsNumber(estado, vistazo);
            estado = Letra.CompIfIsLetter(estado, vistazo);
            estado = OpeRelacional.CompIfIsOpRel(estado, vistazo);

            //System.out.println("flag " + estado);

            switch (estado){
                case 0:
                    if (vistazo == ' ' || vistazo == '\t'){
                        iLexema = fLexema + 1;
                        continue;
                    } else if (vistazo == '\n') {
                        linea++;
                    }

                    if (Character.isDigit(vistazo)){
                      //Entra al diagrama de transicion para los numeros sin signo
                      estado = 12;
                      estado = Numbers.CompIfIsNumber(estado, vistazo);
                    }

                    if(Character.isLetter(vistazo)){
                        //Entra al diagrama de transicion para los identificadores y palabras reservadas
                        estado = 9;
                        estado = Letra.CompIfIsLetter(estado, vistazo);
                    }

                    if(OpeRelacional.isOpRel(vistazo)){
                        //Entra al automata de los operadores relacionales
                        estado = 0;
                        estado = OpeRelacional.CompIfIsOpRel(estado, vistazo);
                    }

                    if (simbolos.isSimbol(vistazo)){
                        tokens.add(new Token(simbolos.CompSimbol(vistazo), String.valueOf(vistazo), null, linea));
                        iLexema = fLexema + 1;
                        continue;
                    }
                    break;
                    //Estados finales
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
                case 5:
                    lexema = source.substring(iLexema, fLexema + 1);
                    tokens.add(new Token(TipoToken.IGUAL, lexema, null , linea));
                    estado = 0; iLexema = fLexema;
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
            }
        }

        //Token de fin de archivo
        tokens.add(new Token(TipoToken.EOF, "", null, linea));

        return tokens;
    }
}

/*
Signos o símbolos del lenguaje:
(
)
{
}
,
.
;
-
+
*
/
!
!=
=
==
<
<=
>
>=
// -> comentarios (no se genera token)
/* ... * / -> comentarios (no se genera token)
Identificador,
Cadena
Numero
Cada palabra reservada tiene su nombre de token

 */