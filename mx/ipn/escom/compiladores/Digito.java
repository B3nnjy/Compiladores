package mx.ipn.escom.compiladores;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Digito {

    char entrada;

    public Digito(char entrada) {
        this.entrada = entrada;
    }

    public void isdigit (char x){

        Pattern pattern = Pattern.compile("[0-9]");
        //Matcher matcher = pattern.matcher(this.entrada);

        /*while (matcher.find()) {

        }*/

        return;

    }
}



