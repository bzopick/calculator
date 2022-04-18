import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Calc {
    private final StringBuilder s;
    private final String[] data= {"","",""}; //инициализация

    Calc(StringBuilder s) {
        this.s = s;
    }

    private void pars() throws IOException {
        int dIndex=0;
        char l;
        for (int i = 0; i <s.length() ; i++) {
            l=Character.toUpperCase(s.charAt(i));
            if (Character.isDigit(l) || l=='I'||l=='V'||l=='X'||l=='C'||l=='L') data[dIndex]+=l;
            else if (l == '+' || l == '-' || l == '*' || l == '/')
                if (++dIndex < 2) {
                    data[dIndex] = String.valueOf(l);
                    dIndex++;
                } else throw new IOException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)" + s);
            else throw new IOException("Некорректные данные " + l + " в позиций " + (i + 1));

        } //for
    }
    public Integer calculate(String a, String b, @NotNull String oper){
        int na=Integer.parseInt(a);
        int nb=Integer.parseInt(b);
        return switch (oper) {
            case "+" -> na + nb;
            case "-" -> na - nb;
            case "*" -> na * nb;
            case "/" -> na / nb;
            default -> null;
        };
    }
    public String[] getArr() throws IOException {
        pars();
        return data;
    }
}