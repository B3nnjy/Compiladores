package mx.ipn.escom.compiladores.asa;
import mx.ipn.escom.compiladores.TipoToken;
import mx.ipn.escom.compiladores.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Parser {

  public static boolean HayErrores;
  protected static List<Token> Tokens = new ArrayList<>();
  protected static Stack<Object> Simbolos;
  protected final static Stack<Integer> Estados = new Stack<>();
  protected static int i = 0;
  protected static Object Entrada;

  public Parser(List<Token> tokens) {
    Simbolos = new Stack<>();
    i = 0;
    HayErrores = false;
    Tokens = tokens;
    Simbolos.push(TipoToken.EOF);
    Estados.push(0);
    Entrada = Tokens.get(i).getTipo();
  }

  public void parser() {
    Estados estados = new Estados();

    while (i != Tokens.size()){
      estados.Accion(Estados.peek(), Entrada);
    }
  }
}
