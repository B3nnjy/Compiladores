package mx.ipn.escom.compiladores.asa;
import mx.ipn.escom.compiladores.NoTerminales;
import mx.ipn.escom.compiladores.TipoToken;
import mx.ipn.escom.compiladores.Token;
import mx.ipn.escom.compiladores.gramatica.Producciones;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Estados {
  private Object t;
  private ArrayList<TipoToken> error;
  protected Estados(){}

  public void Accion(int i, Object t){
    this.t = t;

    switch (i){
      case 0 -> State0();
      case 1 -> State1();
      case 2 -> State2();
      case 3 -> State3();
      case 4 -> State4();
      case 5 -> State5();
      case 6 -> State6();
      case 7 -> State7();
      case 8 -> State8();
      case 9 -> State9();
      case 10 -> State10();
      case 11 -> State11();
      case 12 -> State12();
      case 13 -> State13();
      case 14 -> State14();
      case 15 -> State15();
      case 16 -> State16();
      case 17 -> State17();
      case 18 -> State18();
      case 19 -> State19();
      case 20 -> State20();
      case 21 -> State21();
      case 22 -> State22();
      case 23 -> State23();
      case 24 -> State24();
      case 25 -> State25();
      default -> {
        System.err.println("ERROR");
        System.exit(1);
      }
    }
  }

  private void Error(TipoToken ...t){
    System.err.println("[posicion: " + (Parser.Tokens.get(Parser.i).getCol()-1) + "] Se esperaba " + Arrays.toString(t));
    Parser.i = Parser.Tokens.size();
  }

  private void Reducir(int produccion){
    for (Object o : Producciones.parteDerecha(produccion)){
      if (o.equals(Parser.Simbolos.peek())){
        Parser.Simbolos.pop();
      }else {
        System.err.println("ERROR!! Se esperaba " + o);
        System.exit(1);
      }
    }
    Parser.Simbolos.push(Producciones.parteIzquierda(produccion));
    Parser.Entrada = Parser.Simbolos.peek();
  }

  private void Avanzar(){
    Parser.Simbolos.push(t);
    Parser.i ++;
    Parser.Entrada = Parser.Tokens.get(Parser.i).getTipo();
  }

  private void State0(){
    if (t instanceof TipoToken){
      if (t == TipoToken.SELECT) {
        Parser.Estados.push(2);
        Avanzar();
      }else {
        Error(TipoToken.SELECT);
      }
    } else if (t.equals(NoTerminales.Q)) {
      Parser.Estados.push(1);
      Parser.Entrada = Parser.Tokens.get(Parser.i).getTipo();
    }
  }

  private void State1(){
    if (t.equals(TipoToken.EOF)) {
      System.out.print("Consulta valida!!");
      Parser.i = Parser.Tokens.size();
    }else {
      Error(TipoToken.EOF);
    }
  }

  private void State2(){
    if (t instanceof NoTerminales){
      if (t.equals(NoTerminales.D)) {
        Parser.Estados.push(3);
      } else if (t.equals(NoTerminales.P)) {
        Parser.Estados.push(25);
      } else if (t.equals(NoTerminales.A)) {
        Parser.Estados.push(7);
      } else if (t.equals(NoTerminales.A2)) {
        Parser.Estados.push(8);
      }else {
        Parser.Estados.pop();
        return;
      }
      Parser.Entrada = Parser.Tokens.get(Parser.i).getTipo();
    } else if (t instanceof TipoToken){
      if (t.equals(TipoToken.DISTINCT)) {
        Parser.Estados.push(4);
        Avanzar();
      } else if (t.equals(TipoToken.ASTERISCO)) {
        Parser.Estados.push(6);
        Avanzar();
      } else if (t.equals(TipoToken.IDENTIFICADOR)) {
        Parser.Estados.push(9);
        Avanzar();
      } else{
        Error(TipoToken.ASTERISCO, TipoToken.IDENTIFICADOR, TipoToken.DISTINCT);
      }
    }
  }

  private void State3(){
    if (t instanceof TipoToken){
      if (t.equals(TipoToken.FROM)){
        Parser.Estados.push(10);
        Avanzar();
      } else {
        Error(TipoToken.FROM);
      }
    }else Parser.Estados.pop();
  }

  private void State4(){
    if (t instanceof NoTerminales){
      if (t.equals(NoTerminales.P)){
        Parser.Estados.push(5);
      } else if (t.equals(NoTerminales.A)) {
        Parser.Estados.push(7);
      } else if (t.equals(NoTerminales.A2)) {
        Parser.Estados.push(8);
      }else {
        Parser.Estados.pop();
        return;
      }
      Parser.Entrada = Parser.Tokens.get(Parser.i).getTipo();
    }else if (t instanceof TipoToken){
      if (t.equals(TipoToken.IDENTIFICADOR)){
        Parser.Estados.push(9);
        Avanzar();
      } else {
        Error(TipoToken.IDENTIFICADOR);
      }
    }
  }

  private void State5(){
    if (t instanceof TipoToken) {
      if (t.equals(TipoToken.FROM)){
        Reducir(1);
      }else {
        Error(TipoToken.FROM);
      }
    } else {
      Parser.Estados.pop();
    }
  }

  private void State6(){
    if (t instanceof TipoToken){
      if (t.equals(TipoToken.FROM)){
        Reducir(3);
      }else{
        Error(TipoToken.FROM);
      }
    }else {
      Parser.Estados.pop();
    }
  }

  private void State7(){
    if (t instanceof TipoToken){
      if (t.equals(TipoToken.FROM)){
        Reducir(4);
      }else {
        Error(TipoToken.FROM);
      }
    }else {
      Parser.Estados.pop();
    }
  }

  private void State8(){
    if (t instanceof TipoToken){
      if (t.equals(TipoToken.FROM)){
        Reducir(7);
      } else if (t.equals(TipoToken.COMA)) {
        Parser.Estados.push(12);
        Avanzar();
      } else {
        Error(TipoToken.FROM, TipoToken.COMA);
      }
    } else if (t.equals(NoTerminales.A1)) {
      Parser.Estados.push(11);
      Parser.Entrada = Parser.Tokens.get(Parser.i).getTipo();
    } else {
      Parser.Estados.pop();
    }
  }

  private void State9(){
    if (t instanceof TipoToken) {
      if (t.equals(TipoToken.FROM) || t.equals(TipoToken.COMA)){
        Reducir(10);
      } else if (t.equals(TipoToken.PUNTO)) {
        Parser.Estados.push(14);
        Avanzar();
      }else {
        Error(TipoToken.FROM, TipoToken.COMA, TipoToken.PUNTO);
      }
    } else if (t.equals(NoTerminales.A3)) {
      Parser.Estados.push(13);
      Parser.Entrada = Parser.Tokens.get(Parser.i).getTipo();
    } else {
      Parser.Estados.pop();
    }
  }

  private void State10(){
    if (t instanceof NoTerminales){
      if (t.equals(NoTerminales.T)) {
        Parser.Estados.push(15);
      }else if (t.equals(NoTerminales.T2)){
        Parser.Estados.push(16);
      } else {
        Parser.Estados.pop();
        return;
      }
      Parser.Entrada = Parser.Tokens.get(Parser.i).getTipo();
    }else if (t instanceof TipoToken){
      if (t.equals(TipoToken.IDENTIFICADOR)){
        Parser.Estados.push(17);
        Avanzar();
      }else {
        Error(TipoToken.IDENTIFICADOR);
      }
    }
  }
  private void State11(){
    if (t instanceof TipoToken){
      if (t.equals(TipoToken.FROM)){
        Reducir(5);
      }else {
        Error(TipoToken.FROM);
      }
    }else Parser.Estados.pop();
  }

  private void State12(){
    if (t instanceof NoTerminales){
      if (t.equals(NoTerminales.A)){
        Parser.Estados.push(23);
      } else if (t.equals(NoTerminales.A2)) {
        Parser.Estados.push(8);
      }else {
        Parser.Estados.pop();
        return;
      }
      Parser.Entrada = Parser.Tokens.get(Parser.i).getTipo();
    }else if (t instanceof TipoToken){
      if (t.equals(TipoToken.IDENTIFICADOR)){
        Parser.Estados.push(9);
        Avanzar();
      } else {
        Error(TipoToken.IDENTIFICADOR);
      }
    }
  }

  private void State13(){
    if (t instanceof TipoToken){
      if (t.equals(TipoToken.FROM) || t.equals(TipoToken.COMA)){
        Reducir(8);
      }else {
        Error(TipoToken.FROM, TipoToken.COMA);
      }
    }else {
      Parser.Estados.pop();
    }
  }

  private void State14() {
    if (t instanceof TipoToken) {
      if (t.equals(TipoToken.IDENTIFICADOR)) {
        Parser.Estados.push(24);
        Avanzar();
      } else {
        Error(TipoToken.IDENTIFICADOR);
      }
    } else Parser.Estados.pop();
  }

  private void State15(){
    if (t instanceof TipoToken){
      if (t.equals(TipoToken.EOF)){
        Reducir(0);
      }else {
        Error(TipoToken.EOF);
      }
    }else Parser.Estados.pop();
  }

  private void State16(){
    if (t instanceof NoTerminales){
      if (t.equals(NoTerminales.T1)){
        Parser.Estados.push(18);
        Parser.Entrada = Parser.Tokens.get(Parser.i).getTipo();
      }else Parser.Estados.pop();
    } else if (t instanceof TipoToken){
      if (t.equals(TipoToken.COMA)) {
        Parser.Estados.push(19);
        Avanzar();
      } else if (t.equals(TipoToken.EOF)) {
        Reducir(13);
      } else {
        Error(TipoToken.COMA, TipoToken.EOF);
      }
    }
  }

  private void State17(){
    if (t instanceof NoTerminales){
      if (t.equals(NoTerminales.T3)){
        Parser.Estados.push(21);
        Parser.Entrada = Parser.Tokens.get(Parser.i).getTipo();
      }else Parser.Estados.pop();
    }else if (t instanceof TipoToken){
      if (t.equals(TipoToken.COMA) || t.equals(TipoToken.EOF)){
        Reducir(16);
      } else if (t.equals(TipoToken.IDENTIFICADOR)) {
        Parser.Estados.push(22);
        Avanzar();
      }else {
        Error(TipoToken.COMA, TipoToken.EOF, TipoToken.IDENTIFICADOR);
      }
    }
  }

  private void State18(){
    if (t instanceof TipoToken){
      if (t.equals(TipoToken.EOF)){
        Reducir(11);
      }else {
        Error(TipoToken.EOF);
      }
    }else Parser.Estados.pop();
  }

  private void State19(){
    if (t instanceof NoTerminales){
      if (t.equals(NoTerminales.T)){
        Parser.Estados.push(20);
      } else if (t.equals(NoTerminales.T2)) {
        Parser.Estados.push(16);
      }else {
        Parser.Estados.pop();
        return;
      }
      Parser.Entrada = Parser.Tokens.get(Parser.i).getTipo();
    } else if (t instanceof TipoToken){
      if (t.equals(TipoToken.IDENTIFICADOR)) {
        Parser.Estados.push(17);
        Avanzar();
      }else {
        Error(TipoToken.IDENTIFICADOR);
      }
    }
  }

  private void State20(){
    if (t instanceof TipoToken){
      if (t.equals(TipoToken.EOF)){
        Reducir(12);
      }else {
        Error(TipoToken.EOF);
      }
    }else Parser.Estados.pop();
  }

  private void State21(){
    if (t instanceof TipoToken){
      if (t.equals(TipoToken.COMA) || t.equals(TipoToken.EOF)){
        Reducir(14);
      }else {
        Error(TipoToken.COMA, TipoToken.EOF);
      }
    }else Parser.Estados.pop();
  }

  private void State22(){
    if (t instanceof TipoToken){
      if (t.equals(TipoToken.COMA) || t.equals(TipoToken.EOF)){
        Reducir(15);
      }else {
        Error(TipoToken.COMA, TipoToken.EOF);
      }
    }else Parser.Estados.pop();
  }

  private void State23(){
    if (t instanceof TipoToken){
      if (t.equals(TipoToken.FROM)){
        Reducir(6);
      }else {
        Error(TipoToken.FROM);
      }
    }else Parser.Estados.pop();
  }

  private void State24(){
    if (t instanceof TipoToken){
      if (t.equals(TipoToken.COMA) || t.equals(TipoToken.FROM)){
        Reducir(9);
      }else {
        Error(TipoToken.COMA, TipoToken.FROM);
      }
    }else Parser.Estados.pop();
  }

  private void State25(){
    if (t instanceof TipoToken){
      if (t.equals(TipoToken.FROM)){
        Reducir(2);
      } else {
        Error(TipoToken.FROM);
      }
    }else Parser.Estados.pop();
  }
}
