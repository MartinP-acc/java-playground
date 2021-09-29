public class Dinglemouse {

    String name;
    int age;
    char sex;
    String helloText = "Hello.";

    public Dinglemouse() {
    }

    public Dinglemouse setAge(int age) {
        if (this.age == 0) helloText += " I am "+age+".";
        else helloText = helloText.replace(this.age+".",age+".");
        this.age = age;
        return this;
    }

    public Dinglemouse setSex(char sex) {
        if (this.sex == 0) helloText += " I am "+(sex=='M'?"male":"female")+".";
        else if (this.sex != sex)
            helloText = sex=='M'? helloText.replace("female","male") :
                                    helloText.replace("male","female");
        this.sex = sex;
        return this;
    }

    public Dinglemouse setName(String name) {
        if (this.name == null) helloText += " My name is "+name+".";
        else helloText = helloText.replace(this.name,name);
        this.name = name;
        return this;
    }

    public String hello() {
        return helloText;
    }
}
