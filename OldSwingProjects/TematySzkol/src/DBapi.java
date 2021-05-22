import java.io.Serializable;
import java.util.HashMap;

public class DBapi implements Serializable {

    HashMap<String,LessonClass> booksDB;

    DBapi(){
        booksDB = new HashMap<>();
        booksDB.put("Test", new LessonClass());
    }

    public void putBook(String book){
        booksDB.put(book,new LessonClass());
    }

    public void removeBook(String book){
        booksDB.remove(book);
    }

    public class LessonClass implements Serializable{

        HashMap<String,SubjectClass> lessonDB;

        LessonClass(){
            lessonDB = new HashMap<>();
            lessonDB.put("przedmiot testowy", new SubjectClass());
        }

        public void putLesson(String les){
            lessonDB.put(les,new SubjectClass());
        }

        public void removeLesson(String les){
            lessonDB.remove(les);
        }

        public class SubjectClass implements Serializable{

            HashMap<String,String> subjectDB;

            SubjectClass(){
                subjectDB = new HashMap<>();
                subjectDB.put("T:?", "Przykładowy opis");
            }

            public void putSubject(String sub){
                subjectDB.remove("");
                subjectDB.put(sub,sub+" Tutaj wpisz opis tematu");
            }

            public void  putDescription(String sub, String subDescr){
                subjectDB.put(sub,subDescr);
            }

            public void removeSubject(String sub){
                subjectDB.remove(sub);
            }
        }
    }

}
