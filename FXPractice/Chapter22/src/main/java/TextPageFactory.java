import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.util.Callback;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class TextPageFactory implements Callback<Integer, Node> {
    private final ArrayList<String> text;

    public TextPageFactory(String path) {
        this.text = fileToString(path);
    }

    @Override
    public Node call(Integer page) {
        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setPrefRowCount(10);
        textArea.setPrefColumnCount(60);
        for (int i = page * 10; i < page * 10 + 10; i++){
            if (text.size()>i)textArea.appendText("\n"+text.get(i));
            else textArea.appendText("");
        }
        return textArea;
    }

    private ArrayList<String> fileToString(String path) {
        ArrayList<String> temp = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                temp.add(scanner.nextLine());
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return temp;
    }


}
