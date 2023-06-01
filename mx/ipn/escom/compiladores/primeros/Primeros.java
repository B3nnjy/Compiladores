package mx.ipn.escom.compiladores.primeros;

import mx.ipn.escom.compiladores.TipoToken;

public class Primeros {
  public static NoTerminal primary = new NoTerminal();
  public static NoTerminal call = primary;
  public static NoTerminal call_2 = new NoTerminal();
  public static NoTerminal unary = new NoTerminal();
  public static NoTerminal factor_2 = new NoTerminal();
  public static NoTerminal factor = unary;
  public static NoTerminal term_2 = new NoTerminal();
  public static NoTerminal term = factor;
  public static NoTerminal comparison_2 = new NoTerminal();
  public static NoTerminal comparison = term;
  public static NoTerminal equality_2 = new NoTerminal();
  public static NoTerminal equality = comparison;
  public static NoTerminal logic_and_2 = new NoTerminal();
  public static NoTerminal logic_and = equality;
  public static NoTerminal logic_or_2 = new NoTerminal();
  public static NoTerminal logic_or = logic_and;
  public static NoTerminal assigment_2 = new NoTerminal();
  public static NoTerminal assigment = logic_or;
  public static NoTerminal expression = assigment;
  public static NoTerminal statement = new NoTerminal();
  public static NoTerminal expr_stmt = expression;
  public static NoTerminal for_stmt = new NoTerminal();
  public static NoTerminal var_decl = new NoTerminal();
  public static NoTerminal for_stmt_1 = new NoTerminal();
  public static NoTerminal for_stmt_2 = new NoTerminal();
  public static NoTerminal for_stmt_3 = expression;
  public static NoTerminal if_stmt = new NoTerminal();
  public static NoTerminal else_stmt = new NoTerminal();
  public static NoTerminal print_stmt = new NoTerminal();
  public static NoTerminal return_stmt = new NoTerminal();
  public static NoTerminal return_exp_opc = expression;
  public static NoTerminal while_stmt = new NoTerminal();
  public static NoTerminal block = new NoTerminal();
  public static NoTerminal var_init = new NoTerminal();
  public static NoTerminal fun_decl = new NoTerminal();
  public static NoTerminal class_inher = new NoTerminal();
  public static NoTerminal class_decl = new NoTerminal();
  public static NoTerminal declaration = new NoTerminal();
  public static NoTerminal block_decl = declaration;
  public static NoTerminal function = new NoTerminal();
  public static NoTerminal functions = function;
  public static NoTerminal parameters_opc = function;
  public static NoTerminal parameters = functions;
  public static NoTerminal parameters_2 = new NoTerminal();
  public static NoTerminal arguments_opc = expression;
  public static NoTerminal arguments = expression;
  public static NoTerminal arguments_2 = parameters_2;

  static {
    primary.primeros.add(TipoToken.SUPER);
    primary.primeros.add(TipoToken.PAREN_IZQ);
    primary.primeros.add(TipoToken.IDENTIFICADOR);
    primary.primeros.add(TipoToken.CADENA);
    primary.primeros.add(TipoToken.NUMERO);
    primary.primeros.add(TipoToken.ESTE);
    primary.primeros.add(TipoToken.NULO);
    primary.primeros.add(TipoToken.FALSO);
    primary.primeros.add(TipoToken.VERDADERO);

    call_2.primeros.add(TipoToken.PAREN_IZQ);
    call_2.primeros.add(TipoToken.PUNTO);

    unary.primeros.addAll(primary.primeros);
    unary.primeros.add(TipoToken.NO);
    unary.primeros.add(TipoToken.MENOS);

    factor_2.primeros.add(TipoToken.DIAGONAL);
    factor_2.primeros.add(TipoToken.ASTERISCO);

    term_2.primeros.add(TipoToken.MENOS);
    term_2.primeros.add(TipoToken.MAS);

    comparison_2.primeros.add(TipoToken.MAYOR);
    comparison_2.primeros.add(TipoToken.MAYOR_EQ);
    comparison_2.primeros.add(TipoToken.MENOR);
    comparison_2.primeros.add(TipoToken.MENOR_EQ);

    equality_2.primeros.add(TipoToken.NOT_EQ);
    equality_2.primeros.add(TipoToken.COMPARACION);

    logic_and_2.primeros.add(TipoToken.Y);

    logic_or_2.primeros.add(TipoToken.O);

    assigment_2.primeros.add(TipoToken.IGUAL);

    for_stmt.primeros.add(TipoToken.PARA);

    if_stmt.primeros.add(TipoToken.SI);

    print_stmt.primeros.add(TipoToken.IMPRIMIR);

    return_stmt.primeros.add(TipoToken.RETORNAR);

    while_stmt.primeros.add(TipoToken.MIENTRAS);

    block.primeros.add(TipoToken.LLAVE_IZQ);

    else_stmt.primeros.add(TipoToken.SI_NO);

    var_init.primeros.add(TipoToken.IGUAL);

    fun_decl.primeros.add(TipoToken.FUNCION);

    class_inher.primeros.add(TipoToken.MENOR);

    class_decl.primeros.add(TipoToken.CLASE);

    var_decl.primeros.add(TipoToken.VARIABLE);

    function.primeros.add(TipoToken.IDENTIFICADOR);

    parameters_2.primeros.add(TipoToken.COMA);

    statement.primeros.addAll(assigment.primeros);
    statement.primeros.addAll(for_stmt.primeros);
    statement.primeros.addAll(if_stmt.primeros);
    statement.primeros.addAll(print_stmt.primeros);
    statement.primeros.addAll(return_stmt.primeros);
    statement.primeros.addAll(while_stmt.primeros);
    statement.primeros.addAll(block.primeros);

    for_stmt_1.primeros.addAll(var_decl.primeros);
    for_stmt_1.primeros.addAll(expr_stmt.primeros);
    for_stmt_1.primeros.add(TipoToken.PUNTO_COMA);

    for_stmt_2.primeros.addAll(expression.primeros);
    for_stmt_2.primeros.add(TipoToken.PUNTO_COMA);

    declaration.primeros.addAll(class_decl.primeros);
    declaration.primeros.addAll(fun_decl.primeros);
    declaration.primeros.addAll(var_decl.primeros);
    declaration.primeros.addAll(statement.primeros);
  }
}