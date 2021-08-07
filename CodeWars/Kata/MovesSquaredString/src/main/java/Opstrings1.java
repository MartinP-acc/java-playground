import java.util.function.Function;

class Opstrings1 {

    public static String rot(String strng) {
        return new StringBuilder(strng).reverse().toString();
    }
    public static String selfieAndRot(String strng) {
        int n = strng.indexOf("\n");
        return strng.replaceAll("([a-zA-Z]+)(\n*)","$1"+".".repeat(n)+"$2")+"\n"+
                rot(strng).replaceAll("([a-zA-Z]+)(\n*)",".".repeat(n)+"$1"+"$2");
    }

    public static String oper(Function<String,String> operator, String s) {
        return operator.apply(s);
    }
}