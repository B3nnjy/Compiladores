package mx.ipn.escom.compiladores;

import mx.ipn.escom.compiladores.transition_diagram_of_tokens.UnsignedNumbers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scanner {

    private final String source;

    private final List<Token> tokens = new ArrayList<>();

    private int linea = 1;

    private static final Map<String, TipoToken> palabrasReservadas;
    static {
        palabrasReservadas = new HashMap<>();
        palabrasReservadas.put("y", TipoToken.Y);
        palabrasReservadas.put("clase", TipoToken.CLASE);
        palabrasReservadas.put("ademas", TipoToken.ADEMAS );
        palabrasReservadas.put("falso", TipoToken.FALSO);
        palabrasReservadas.put("para", TipoToken.PARA);
        palabrasReservadas.put("fun", TipoToken.FUNCION); //definir funciones
        palabrasReservadas.put("si", TipoToken.SI);
        palabrasReservadas.put("nulo", TipoToken.NULO);
        palabrasReservadas.put("o", TipoToken.O);
        palabrasReservadas.put("imprimir", TipoToken.IMPRIMIR);
        palabrasReservadas.put("retornar", TipoToken.RETORNAR);
        palabrasReservadas.put("super", TipoToken.SUPER);
        palabrasReservadas.put("este", TipoToken.ESTE);
        palabrasReservadas.put("verdadero", TipoToken.VERDADERO);
        palabrasReservadas.put("var", TipoToken.VARIABLE); //definir variables
        palabrasReservadas.put("mientras", TipoToken.MIENTRAS);
    }

    Scanner(String source){
        this.source = source;
    }

    List<Token> scanTokens(){
        int estado = 0;
        int iLexema = 0;
        int fLexema = 0;
        String lexema;
        //Aquí va el corazón del scanner.
        for (int i = 0; i < source.length(); i++) {
            char vistazo = source.charAt(i);


            fLexema = i;

            //System.out.println("flag " + estado);

            estado = UnsignedNumbers.UnsignedNumbersImpl(estado, vistazo);

            switch (estado){
                case 0:
                    if (vistazo == ' ' || vistazo == '\t'){
                        estado = 0;
                    } else if (vistazo == '\n') {
                        linea++;
                    }
                    break;
                case 19:
                    lexema = source.substring(iLexema, fLexema);
                    int E = lexema.indexOf('E');
                    String entero = lexema.substring(iLexema, E);
                    String potencia = lexema.substring(E+1);
                    System.out.println(entero + potencia);
                    float enteroP = Float.parseFloat(entero);
                    int pow = Integer.parseInt(potencia);
                    tokens.add(new Token(TipoToken.NUMERO, lexema, enteroP*(Math.pow(10, pow)), linea));
                    iLexema = fLexema;
                    estado = 0;
                    break;
                case 20:
                    lexema = source.substring(iLexema, fLexema);
                    tokens.add(new Token(TipoToken.NUMERO, lexema, Integer.parseInt(lexema), linea));
                    iLexema = fLexema;
                    estado = 0;
                    break;
                case 21:
                    lexema = source.substring(iLexema, fLexema);
                    tokens.add(new Token(TipoToken.NUMERO, lexema, Float.parseFloat(lexema), linea));
                    iLexema = fLexema;
                    estado = 0;
                    break;
            }


            if (vistazo == ' ' || vistazo == '\t'){

            } else if (vistazo == '\n') {
                linea++;
            }

            /*switch (vistazo){

                case ('('):
                    tokens.add(new Token(TipoToken.PAREN_IZQ, "(", null, linea));
                    break;
                case (')'):
                    tokens.add(new Token(TipoToken.PAREN_DER, ")",null, linea));
                    break;

                case ('{'):
                    tokens.add(new Token(TipoToken.LLAVE_IZQ, "{", null, linea));
                    break;

                case ('}'):
                    tokens.add(new Token(TipoToken.LLAVE_DER, "}", null, linea));
                    break;

                case (','):
                    tokens.add(new Token(TipoToken.COMA,",",null, linea));
                    break;

                case ('.'):
                    tokens.add(new Token(TipoToken.PUNTO,".",null, linea));
                    break;

                case (';'):
                    tokens.add(new Token(TipoToken.PUTO_COMA,";",null, linea));
                    break;

                case ('-'):
                    tokens.add(new Token(TipoToken.MENOS,"-",null, linea));

                case ("+"):
                    tokens.add(new Token(TipoToken.MAS,"+",null, linea));
            }*/
        }



        /*
        Analizar el texto de entrada para extraer todos los tokens
        y al final agregar el token de fin de archivo
         */
        tokens.add(new Token(TipoToken.EOF, "", null, linea));
        /*tokens.add(new Token(TipoToken.CLASE, "class",null ,linea ));
        tokens.add(new Token(TipoToken.SI, "if", null, linea));*/


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