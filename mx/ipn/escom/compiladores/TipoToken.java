package mx.ipn.escom.compiladores;

public enum TipoToken {
    // Crear un tipoToken por palabra reservada
    // Crear un tipoToken: identificador, una cadena y numero
    // Crear un tipoToken por cada "Signo del lenguaje" (ver clase Scanner)


    // Palabras clave:
    Y, CLASE, ADEMAS, FALSO, PARA,FUNCION, SI, SI_NO,  NULO, O, IMPRIMIR, RETORNAR, SUPER, ESTE, VERDADERO, VARIABLE, MIENTRAS,

    // identificador, cadena y numero
    IDENTIFICADOR, CADENA, NUMERO,

    //Simbolos del lenguaje

    PAREN_IZQ, PAREN_DER, LLAVE_IZQ, LLAVE_DER, COMA, PUNTO, PUNTO_COMA, MENOS, MAS,
    ASTERISCO, DIAGONAL, NO, IGUAL, COMPARACION, MENOR, MENOR_EQ,
    MAYOR, MAYOR_EQ, NOT_EQ,

    // Final de cadena
    EOF
}


