public class Travel {
    public static String travel(String r, String zipcode) {

        if (!zipcode.matches("\\p{Upper}{2} \\d{5}")) return zipcode+":/";

        StringBuilder result = new StringBuilder();
        StringBuilder nr = new StringBuilder();
        String[] rArray = r.split(",");
        for (String ad : rArray){
            if (ad.contains(zipcode)){
                if (!result.isEmpty()){
                    result.append(",");
                    nr.append(",");
                }
                result.append(ad.replaceAll("\\d+ ([a-zA-Z .]+) \\p{Upper}{2} \\d{5}","$1"));
                nr.append(ad.replaceAll("(\\d+) [a-zA-Z .0-9]+", "$1"));
            }
        }
        return zipcode+":"+result+"/"+nr;
    }
}