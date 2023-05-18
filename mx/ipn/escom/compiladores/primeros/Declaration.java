package mx.ipn.escom.compiladores.primeros;

public class Declaration extends Class_decl{
  protected Declaration(){
    super.primeros.addAll(Primeros.fun_decl.primeros);
    super.primeros.addAll(Primeros.var_decl.primeros);
    super.primeros.addAll(Primeros.statement.primeros);
  }
}
