package mx.ipn.escom.compiladores.asd;

import mx.ipn.escom.compiladores.NoTerminales;
import mx.ipn.escom.compiladores.TipoToken;
import mx.ipn.escom.compiladores.Token;

import java.util.Stack;

import static mx.ipn.escom.compiladores.asd.Parser.HayErrores;

public class TablaSintactica {
  private static final Stack<Object> resp = new Stack<>();

  public static Stack<Object> Find(NoTerminales N, Token R) {
    TipoToken T = R.getTipo();
    resp.clear();

    switch (N) {
      case Q -> Q(T);
      case D -> D(T);
      case P -> P(T);
      case A -> A(T);
      case A1 -> A1(T);
      case A2 -> A2(T);
      case A3 -> A3(T);
      case T -> T(T);
      case T1 -> T1(T);
      case T2 -> T2(T);
      case T3 -> T3(T);
    }

    return resp;
  }

  private static void Q(TipoToken T){
    if (T == TipoToken.SELECT){
      resp.push(TipoToken.SELECT);
      resp.push(NoTerminales.D);
      resp.push(TipoToken.FROM);
      resp.push(NoTerminales.T);
    }else{
      System.out.println("ERROR: Se esperaba SELECT");
      HayErrores = true;
    }
  }

  private static void D(TipoToken T){
    if (T == TipoToken.DISTINCT){
      resp.push(TipoToken.DISTINCT);
      resp.push(NoTerminales.P);
    }else if (T == TipoToken.ASTERISCO){
      resp.push(NoTerminales.P);
    }else if (T == TipoToken.IDENTIFICADOR){
      resp.push(NoTerminales.P);
    }else{
      HayErrores = true;
      System.out.println("ERROR: Se esperaba DISTINCT, ASTERISCO o IDENTIFICADOR");
    }
  }

  private static void P(TipoToken T){
    if (T == TipoToken.ASTERISCO){
      resp.push(TipoToken.ASTERISCO);
    }else if (T == TipoToken.IDENTIFICADOR){
      resp.push(NoTerminales.A);
    }else{
      HayErrores = true;
      System.out.println("ERROR: Se esperaba ASTERISCO o IDENTIFICADOR");
    }
  }
  private static void A(TipoToken T){
    if (T == TipoToken.IDENTIFICADOR) {
      resp.push(NoTerminales.A2);
      resp.push(NoTerminales.A1);
    }else{
      HayErrores = true;
      System.out.println("ERROR: Se esperaba IDENTIFICADOR");
    }
  }
  private static void A1(TipoToken T){
    if (T == TipoToken.COMA){
      resp.push(TipoToken.COMA);
      resp.push(NoTerminales.A);
    } else if (T == TipoToken.FROM || T == TipoToken.IDENTIFICADOR){
      return;
    }else{
      HayErrores = true;
      System.out.println("ERROR: Se esperaba COMA");
    }
  }
  private static void A2(TipoToken T){
    if (T == TipoToken.IDENTIFICADOR){
      resp.push(TipoToken.IDENTIFICADOR);
      resp.push(NoTerminales.A3);
    }else {
      HayErrores = true;
      System.out.println("ERROR: Se esperaba IDENTIFICADOR");
    }
  }
  private static void A3(TipoToken T){
    if (T == TipoToken.PUNTO){
      resp.push(TipoToken.PUNTO);
      resp.push(TipoToken.IDENTIFICADOR);
    }else if (T == TipoToken.FROM || T == TipoToken.COMA || T == TipoToken.IDENTIFICADOR) {
      return;
    }else {
      HayErrores = true;
      System.out.println("ERROR: Se esperaba PUNTO");
    }
  }
  private static void T(TipoToken T){
    if (T == TipoToken.IDENTIFICADOR){
      resp.push(NoTerminales.T2);
      resp.push(NoTerminales.T1);
    }else{
      HayErrores = true;
      System.out.println("ERROR: Se esperaba IDENTIFICADOR");
    }
  }
  private static void T1(TipoToken T){
    if (T == TipoToken.COMA){
      resp.push(TipoToken.COMA);
      resp.push(NoTerminales.T);
    }else if (T == TipoToken.EOF){
      return;
    }else {
      HayErrores = true;
      System.out.println("ERROR: Se esperaba COMA");
    }
  }
  private static void T2(TipoToken T){
    if (T == TipoToken.IDENTIFICADOR){
      resp.push(TipoToken.IDENTIFICADOR);
      resp.push(NoTerminales.T3);
    }else {
      HayErrores = true;
      System.out.println("ERROR: Se esperaba IDENTIFICADOR");
    }
  }
  private static void T3(TipoToken T){
    if (T == TipoToken.IDENTIFICADOR){
      resp.push(TipoToken.IDENTIFICADOR);
    }else if (T == TipoToken.COMA || T == TipoToken.EOF){
      return;
    }else {
      HayErrores = true;
      System.out.println("ERROR: Se esperaba IDENTIFICADOR");
    }
  }
}
