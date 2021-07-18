import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EasyBal {

    private static BigDecimal balance;
    private static BigDecimal expense;
    private static double numOfExpenses;

    private static String getBalance(String firstRow){
        Matcher balanceMatcher = Pattern.compile("([0-9]+.[0-9]+)").matcher(firstRow);
        if (balanceMatcher.find()){
            return balanceMatcher.group(1);
        } else throw new IllegalArgumentException("Can not find balance ");
    }

    private static String formatNextRow(String row){
        Matcher matcher = Pattern.compile("(\\d+) ([a-z A-Z]+)\\W+([0-9]+.[0-9]+)").matcher(row);
        if (matcher.find()){
            numOfExpenses++;
            expense = expense.add(new BigDecimal(matcher.group(3)));
            balance = balance.subtract(new BigDecimal(matcher.group(3)));
            return matcher.group(1)+" "+matcher.group(2).trim()+" "+matcher.group(3)+" Balance "+balance+"\\r\\n";
        }
        else return "";
    }

    public static String balance(String book) {
        String[] bookRows = book.split("\n");

        StringBuilder result = new StringBuilder();
        balance = new BigDecimal(getBalance(bookRows[0]));
        expense = new BigDecimal(0);
        numOfExpenses = 0.0;

        result.append("Original Balance: ").append(balance).append("\\r\\n");

        for (int i=1; i< bookRows.length; i++){
            result.append(formatNextRow(bookRows[i]));
        }

        result.append("Total expense  ").append(expense).append("\\r\\n")
                .append("Average expense  ").append(expense.divide(BigDecimal.valueOf(numOfExpenses), RoundingMode.HALF_UP));

        return result.toString();
    }
}