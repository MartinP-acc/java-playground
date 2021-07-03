import java.util.Scanner;

public class Runes {

    public static int solveExpression( final String expression ) {
        int missingDigit = -1;

        for (int i=0; i<10; i++){
            if (i==0) {
                String[] checkLeadingZero = expression.split("[-+*=]");
                for (String s : checkLeadingZero) if (s.startsWith("?") && s.length() > 1){
                    i = 1;
                    break;
                }
            }
            if (expression.contains(Integer.toString(i))) continue;

            String test = expression.replace("?",Integer.toString(i));
            Scanner scanner = new Scanner(test);
            if (test.charAt(0)=='-') scanner.findInLine("-");

            int a = Integer.parseInt(scanner.findInLine("[0-9]*"));
            if (test.charAt(0)=='-') a=-a;
            String operator = scanner.findInLine("[^0-9]*");

            int b = Integer.parseInt(scanner.findInLine("[0-9]*"));
            if (operator.length()==2) {
                operator = operator.substring(0,1);
                b=-b;
            }
            String equalOperator = scanner.findInLine("[^0-9]*");

            int c = Integer.parseInt(scanner.findInLine("[0-9]*"));
            if (equalOperator.length()>1) c=-c;

            switch (operator){
                case "+": if (a+b==c) return i;
                break;
                case "-": if (a-b==c) return i;
                break;
                case "*": if (a*b==c) return i;
                break;
            }
        }
        return missingDigit;
    }
}
