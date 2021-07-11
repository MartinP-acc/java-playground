import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Priorities {

    private PriorityQueue<Student> students = new PriorityQueue<>(
            Comparator.comparing(Student::getCgpa).reversed()
            .thenComparing(Student::getName)
            .thenComparing(Student::getId));

    public List<Student> getStudents(List<String> events) {
        for (String str : events){
            if (str.equals("SERVED")){
                if (!students.isEmpty()) students.poll();
            }else {
                String[] studNew = str.split(" ");
                students.add(new Student(Integer.parseInt(studNew[3]),studNew[1],Double.parseDouble(studNew[2])));
            }
        }
        List<Student> finalList = new ArrayList<>();
        while (!students.isEmpty()){
            finalList.add(students.poll());
        }
        return finalList;
    }
}
