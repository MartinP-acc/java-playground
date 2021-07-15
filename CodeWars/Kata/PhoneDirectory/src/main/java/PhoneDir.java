import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PhoneDir {

    public static  String phone(String strng, String num) {

        String[] phoneBook = strng.split("\n");
        String name = "";
        String address = "";

        for (String l : phoneBook){
            if (l.contains("+"+num)){
                if (!address.equals("")) return "Error => Too many people: "+num;
                address=l;
            }
        }
        if (address.equals("")) return "Error => Not found: "+num;

        Matcher matcher = Pattern.compile("<(.+)>").matcher(address);
        while (matcher.find()){
            name =  matcher.group(1);
        }

        address = address.replace("+"+num,"")
                .replace("<"+name+">","" )
                .replaceAll("[;/&*$#@!_]"," ")
                .replaceAll("\s+"," ").trim();

        return "Phone => "+num+", Name => "+name+", Address => "+address;
    }
}