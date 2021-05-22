import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

enum Where{BOOK,LESSON,SUBJECT}

public class MainAPI extends Frame {

    private List books,lessons,subject;
    private TextArea descr;
    InsertFrame addNewKey;

    private DBapi testDB;

    public static void main(String[] args) {
        MainAPI myApp = new MainAPI();
        myApp.setGUI();
    }

    @Override
    public Insets getInsets() {
        return new Insets(40, 15, 15,15);
    }

    public void setGUI(){
        setSize(400,600);
        setTitle("Tematy zajęć");
        setLayout(new GridLayout(4,1));

        initComp();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public void initComp(){

        setLocation(1200,200);

        addNewKey = new InsertFrame();

        Label bookLab = new Label("Programy szkolenia");
        bookLab.setAlignment(Label.CENTER);
        bookLab.setFont(new Font("Arial",Font.BOLD,14));
        Label lessonLab = new Label("Przedmioty");
        lessonLab.setAlignment(Label.CENTER);
        lessonLab.setFont(new Font("Arial",Font.BOLD,14));
        Label subjLab = new Label("Tematy");
        subjLab.setAlignment(Label.CENTER);
        subjLab.setFont(new Font("Arial",Font.BOLD,14));
        Label descLab = new Label("Opisy tematów");
        descLab.setAlignment(Label.CENTER);
        descLab.setFont(new Font("Arial",Font.BOLD,14));

        books = new List();
        lessons = new List();
        subject = new List();

        descr = new TextArea(10,20);
        descr.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_F5 -> saveData();
                    case KeyEvent.VK_F4 -> copyAction();
                }
            }
        });

        if (Files.exists(Path.of("saveSubject.dat"))){
            loadData();
        } else { testDB = new DBapi(); }
        for (String bk : testDB.booksDB.keySet()) books.add(bk);


        JButton bookPlus = new JButton("+");
        bookPlus.addActionListener(e -> addNewKey.childFrame("",Where.BOOK));

        JButton bookMinus = new JButton("-");
        bookMinus.addActionListener(e -> minusBook(books.getSelectedItem()));

        books.addItemListener(e -> refreshLessonList());

        Panel bookPan = new Panel();
        bookPan.setLayout(new BorderLayout());
        bookPan.add(bookPlus,BorderLayout.WEST);
        bookPan.add(books,BorderLayout.CENTER);
        bookPan.add(bookMinus,BorderLayout.EAST);
        bookPan.add(bookLab,BorderLayout.NORTH);

        JButton lessonPlus = new JButton("+");
        lessonPlus.addActionListener(e -> addNewKey.childFrame("",Where.LESSON));

        JButton lessonMinus = new JButton("-");
        lessonMinus.addActionListener(e -> minusLesson(lessons.getSelectedItem()));

        lessons.addItemListener(e -> refreshSubjectList());

        Panel lessonPan = new Panel();
        lessonPan.setLayout(new BorderLayout());
        lessonPan.add(lessonPlus,BorderLayout.WEST);
        lessonPan.add(lessons,BorderLayout.CENTER);
        lessonPan.add(lessonMinus,BorderLayout.EAST);
        lessonPan.add(lessonLab,BorderLayout.NORTH);

        JButton subPlus = new JButton("+");
        subPlus.addActionListener(e -> addNewKey.childFrame("T: ",Where.SUBJECT));

        JButton subMinus = new JButton("-");
        subMinus.addActionListener(e -> minusSubject(subject.getSelectedItem()));

        subject.addItemListener(e -> retypeDescription());
        subject.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_F4) copyAction();
            }
        });

        Panel subPan = new Panel();
        subPan.setLayout(new BorderLayout());
        subPan.add(subPlus,BorderLayout.WEST);
        subPan.add(subject,BorderLayout.CENTER);
        subPan.add(subMinus,BorderLayout.EAST);
        subPan.add(subjLab,BorderLayout.NORTH);

        Checkbox onTop = new Checkbox("Zawsze na wierzchu");
        onTop.addItemListener(e -> setAlwaysOnTop(onTop.getState()));

        JButton saveButton = new JButton("Zapisz dane");
        saveButton.addActionListener(e -> saveData());
        JButton copyText = new JButton("<html><center>Kopiuj<br>tekst</center></html>");
        copyText.addActionListener(e -> copyAction());

        Panel descPan = new Panel();
        descPan.setLayout(new BorderLayout());
        descPan.add(descLab,BorderLayout.NORTH);
        descPan.add(descr,BorderLayout.CENTER);
        descPan.add(copyText,BorderLayout.WEST);

        Panel southPan = new Panel();
        southPan.setLayout(new GridLayout(1,2));
        southPan.add(onTop);
        southPan.add(saveButton);

        descPan.add(southPan,BorderLayout.SOUTH);

        add(bookPan);
        add(lessonPan);
        add(subPan);
        add(descPan);

    }

    public void copyAction(){
        StringSelection selection = new StringSelection(descr.getText().toString());
        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(selection,null);
    }

    public void saveData(){
        try(ObjectOutputStream saving = new ObjectOutputStream(new FileOutputStream("saveSubject.dat"))){
            setNewDescription();
            saving.writeObject(testDB);
        }catch (Exception exception){
            System.out.println("Błąd zapisu bazy danych \n" + exception.toString());
        }
    }

    public void loadData(){
        try(ObjectInputStream loading = new ObjectInputStream(new FileInputStream("saveSubject.dat"))){
            testDB = (DBapi) loading.readObject();
        }catch (Exception exception){
            System.out.println("Błąd przy wczytywaniu bazy danych");
        }
    }

    public void retypeDescription(){
        descr.setText(testDB.booksDB
                .get(books.getSelectedItem())
                .lessonDB.get(lessons.getSelectedItem())
                .subjectDB.get(subject.getSelectedItem()));
    }

    public void setNewDescription(){
        testDB.booksDB
                .get(books.getSelectedItem())
                .lessonDB.get(lessons.getSelectedItem())
                .putDescription(subject.getSelectedItem(),descr.getText());
    }

    public  void refreshBookList(){
        books.removeAll();
        for (String bk : testDB.booksDB.keySet()) books.add(bk);
    }

    public void setNewBook(String newKey){
        testDB.putBook(newKey);
        refreshBookList();
    }

    public void minusBook(String remKey){
        testDB.removeBook(remKey);
        refreshBookList();
    }

    public void setNewLesson(String newKey){
        testDB.booksDB
                .get(books.getSelectedItem())
                .putLesson(newKey);
        refreshLessonList();
    }

    public void minusLesson(String newKey){
        testDB.booksDB
                .get(books.getSelectedItem())
                .removeLesson(newKey);
        refreshLessonList();
    }

    public  void refreshLessonList(){
        lessons.removeAll();
        for (String ls : testDB.booksDB
                .get(books.getSelectedItem())
                .lessonDB.keySet()) lessons.add(ls);
        try{
            refreshSubjectList();
        }catch (NullPointerException ex){
            System.out.println("brak tematu w mapie");
        }

    }

    public void setNewSubject(String newKey){
        testDB.booksDB
                .get(books.getSelectedItem())
                .lessonDB.get(lessons.getSelectedItem())
                .putSubject(newKey);
        refreshSubjectList();
    }

    public void minusSubject(String remKey){
        testDB.booksDB
                .get(books.getSelectedItem())
                .lessonDB.get(lessons.getSelectedItem())
                .removeSubject(remKey);
        refreshSubjectList();
    }

    public void refreshSubjectList() {
        subject.removeAll();
        for (String sub : testDB.booksDB
                .get(books.getSelectedItem())
                .lessonDB.get(lessons.getSelectedItem())
                .subjectDB.keySet())
            subject.add(sub);
        try{
            retypeDescription();
        }catch (NullPointerException ex){
            System.out.println("brak opisu w mapie");
        }
    }

    public class InsertFrame extends Frame {

        private TextField keyName;
        private JButton acceptBook, acceptLesson, acceptSubject;
        private Label warning;

        InsertFrame(){
            setSize(300,100);
            setLayout(new GridLayout(3,1));
            setTitle("Dodaj");

            keyName = new TextField(20);
            acceptBook = new JButton("Dodaj program szkolenia");
            acceptLesson = new JButton("Dodaj przedmiot");
            acceptSubject = new JButton("Dodaj temat");
            warning = new Label("");

            add(keyName);
            add(warning);
        }

        public void childFrame(String info, Where w){

            setLocation(1200,0);

            keyName.setText(info);
            setVisible(true);

            switch (w){
                case BOOK -> add(acceptBook);
                case SUBJECT -> add(acceptSubject);
                case LESSON -> add(acceptLesson);
            }
            acceptBook.addActionListener(e -> {
                try{
                    setNewBook(keyName.getText());
                    warning.setForeground(Color.BLUE);
                    warning.setText("dodano pole \""+keyName.getText()+"\"");
                }catch (NullPointerException ex){
                    warning.setForeground(Color.RED);
                    warning.setText("Ciekawy błąd O.o");
                }
            });

            acceptLesson.addActionListener(e -> {
                try{
                    setNewLesson(keyName.getText());
                    warning.setForeground(Color.BLUE);
                    warning.setText("dodano pole \""+keyName.getText()+"\"");
                }catch (NullPointerException ex){
                    warning.setForeground(Color.RED);
                    warning.setText("Zaznacz program szkolenia w oknie głównym");
                }
            });

            acceptSubject.addActionListener(e -> {
                try{
                    setNewSubject(keyName.getText());
                    warning.setForeground(Color.BLUE);
                    warning.setText("dodano pole \""+keyName.getText()+"\"");
                }catch (NullPointerException ex){
                    warning.setForeground(Color.RED);
                    warning.setText("Zaznacz przedmiot w oknie głównym");
                }
            });

            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    remove(acceptBook);
                    remove(acceptLesson);
                    remove(acceptSubject);
                    warning.setText("");
                    dispose();
                }
            });
        }

    }
}