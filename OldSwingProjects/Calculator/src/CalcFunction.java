public class CalcFunction {

    private static String strValue="", strReadyVal="", operator="";
    private static final String ERROR = "Nie można dzielić przez zero";

    public static String putDigit(String digit){
        strValue+=digit;
        return strValue;
    }

    public static String getStrValue() {
        return strValue;
    }

    public static String toOpposite(){
        if (strValue.startsWith("-")) strValue = strValue.substring(1);
        else strValue = "-" + strValue;
        return strValue;
    }

    public static String coma(){
        if (!strValue.contains(".")&&!strValue.equals("")) strValue+=".";
        return strValue;
    }

    public static String basicOperator(String op){
        if (!operator.equals("")){
            strReadyVal = equal();
        }else {
            strReadyVal = strValue;
        }
        operator = op;
        strValue = "";
        return Double.parseDouble(strReadyVal)+" "+ operator;
    }

    public static String mathFunc(String funcName){
        Double result = Double.parseDouble(strValue);
        switch (funcName){
            case "sqrt" -> result = Math.sqrt(result);
            case "pow" -> result = Math.pow(result,2);
            case "1/x" -> {
                if (result!=0){
                    result = 1/result;
                }else{
                    strValue = "";
                    return ERROR;
                }
            }
        }
        strValue = result.toString();
        return strValue;
    }

    public static String deleteDigit(){
        int last = strValue.length()-1;
        if (last>-1) strValue = strValue.substring(0,last);
        return strValue;
    }

    public static String equal(){
        Double result = (double) 0;
        if (!operator.equals("")&& !strValue.equals("")){
                switch (operator) {
                    case "+" -> result = Double.parseDouble(strValue) + Double.parseDouble(strReadyVal);
                    case "-" -> result = Double.parseDouble(strReadyVal) - Double.parseDouble(strValue);
                    case "*" -> result = Double.parseDouble(strValue) * Double.parseDouble(strReadyVal);
                    case "/" -> {
                        if (Double.parseDouble(strValue)!=0){
                            result = Double.parseDouble(strReadyVal) / Double.parseDouble(strValue);
                        }else {
                            operator = "";
                            strReadyVal = "";
                            strValue = "";
                            return ERROR;
                        }
                    }
                    case "mod" -> {
                        if (Double.parseDouble(strValue)!=0) {
                            result = Double.parseDouble(strReadyVal) % Double.parseDouble(strValue);
                        }else {
                            operator = "";
                            strReadyVal = "";
                            strValue = "";
                            return ERROR;
                        }
                    }
                }
            strReadyVal = result.toString();
            strValue = "";
        }
        operator = "";
        return strReadyVal;
    }

    public static String resetReadyVal(){
        operator = "";
        strReadyVal = "";
        return strReadyVal;
    }

    public static String resetVal(){
        strValue = "";
        return strValue;
    }

}
