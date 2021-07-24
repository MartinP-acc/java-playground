public class Solution{
    public static String makeAWindow(int num)
    {
        StringBuilder window = new StringBuilder();
        String border = "-".repeat(num*2+3);
        String glass = ".".repeat(num);

        window.append(border).append("\n");
        for (int i=0; i<num*2+1; i++){
            String build;
            if (i!=num) build = "|"+glass+"|"+glass+"|";
            else build = "|"+"-".repeat(num)+"+"+"-".repeat(num)+"|";
            window.append(build).append("\n");
        }
        window.append(border);
       return window.toString();
    }
}