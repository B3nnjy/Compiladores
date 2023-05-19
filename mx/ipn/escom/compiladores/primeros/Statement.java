package mx.ipn.escom.compiladores.primeros;

public class Statement extends Assigment{
  protected Statement(){
    super.primeros.addAll(Primeros.for_stmt.primeros);
    super.primeros.addAll(Primeros.if_stmt.primeros);
    super.primeros.addAll(Primeros.print_stmt.primeros);
    super.primeros.addAll(Primeros.return_stmt.primeros);
    super.primeros.addAll(Primeros.while_stmt.primeros);
    super.primeros.addAll(Primeros.block.primeros);
  }
}
