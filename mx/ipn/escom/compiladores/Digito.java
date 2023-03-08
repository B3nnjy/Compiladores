package mx.ipn.escom.compiladores;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Digito {

    char entrada;

    public Digito(char entrada) {
        this.entrada = entrada;
    }

    public isdigit (char x){

        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(entrada);

        while (matcher.find()) {

        }

        return;

    }
}



