public class Printer {

    public <T> void printArray(T[] array){
        for (T t : array) System.out.println(t);
    }
}
