package mx.ipn.escom.compiladores.asd;

import mx.ipn.escom.compiladores.NoTerminales;
import mx.ipn.escom.compiladores.TipoToken;
import mx.ipn.escom.compiladores.Token;

import java.util.List;
import java.util.Stack;

public class Parser {
  private final List<Token> Tokens;
  private final Stack<Object> Pila = new Stack<>();
  public static boolean HayErrores;
  public Parser(List<Token> Tokens) {
    HayErrores = false;
    this.Tokens = Tokens;
    this.Pila.push(TipoToken.EOF);
    this.Pila.push(NoTerminales.Q);
  }

  public boolean Validar(){
    Stack<Object> aux;

    if (this.Tokens.get(0).getTipo() == TipoToken.EOF) {
      System.out.println("ERROR: Nada que hacer");
      return false;
    }

    for (Token token: this.Tokens){
      while(true){
        if (this.Pila.peek() == token.getTipo()){
          this.Pila.pop();
          break;
        }else if (this.Pila.peek() instanceof NoTerminales){
          aux = TablaSintactica.Find((NoTerminales) this.Pila.pop(), token);

          if (HayErrores){
            System.out.println("ERROR: Token invalido en la posicion " + token.getCol());
            return false;
          }

          while(!aux.empty()){
            this.Pila.push(aux.pop());
          }
        }else {
          System.out.println("ERROR: Se esperaba " + this.Pila.peek());
          System.out.println("ERROR: Token invalido en la posicion " + token.getCol());
          return false;
        }
      }
    }
    System.out.println("EXITO: Consulta valida");
    return true;
  }
}
