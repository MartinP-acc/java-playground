import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class FXApp extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Image image = new Image(new FileInputStream("src/main/resources/Abstract-cool-colors-depth-pictures.jpg"));
        Rectangle rect = new Rectangle(485,270);
        rect.setFill(new ImagePattern(image));
        TitledPane tpane = new TitledPane("Colors pic",rect);

        Image image2 = new Image(new FileInputStream("src/main/resources/Abstract-Minimalistic-Tree-Backgrounds-HD-Wallpapers.png"));
        Rectangle rect2 = new Rectangle(485,270);
        rect2.setFill(new ImagePattern(image2));
        TitledPane tpane2 = new TitledPane("Tree pic",rect2);

        Image image3 = new Image(new FileInputStream("src/main/resources/deer_tree_art_131315_1920x1080.jpg"));
        Rectangle rect3 = new Rectangle(485,270);
        rect3.setFill(new ImagePattern(image3));
        TitledPane tpane3 = new TitledPane("Deer pic",rect3);

        ScrollBar sbar = new ScrollBar();
        sbar.setMin(0);
        sbar.setMax(52);
        sbar.setValue(12);
        sbar.setPrefWidth(485);
        sbar.setOrientation(Orientation.HORIZONTAL);

        TextArea textArea = new TextArea("ZADADA \"FIRST\"\n" +
                "Fast - powinny wykonywać się szybko\n" +
                "Isolated - skupianie się na jak najmniejszej składowej \n" +
                "Repeatable - test powinien dawać ten sam wynik przy wielkorotnym uruchamianiu\n" +
                "Self-validating - samo sprawdzający się , automatyczne uruchamianie testów\n" +
                "Timely - pisanie testów w czasie pisania nowej funkcjonalności i nie zostawianie ich na później\n" +
                "\n" +
                "ZASADA\" CORRECT\"\n" +
                "Conformance - zgodność (test powinien sprawdzać zgodność podanych danych)\n" +
                "Ordering - test powinien sprawdzać jak zachowa się program przy posortowanej lub nieposortowanej liście\n" +
                "Range - testowanie przekroczenia maksymalnej wartości i minimalnego zakresu\n" +
                "Reference - testowanie jak zachowa się obiekt bez innych referencji \n" +
                "Existence - co się stanie w przypadku wartości null;\n" +
                "Cardinality - sprawdzanie czy dany zbiór ma odpowiednią ilość elementów\n" +
                "Time - kolejność wywołania metod i sprawdzanie metod wykorzystujących wielowątkowość\n" +
                "\n" +
                "\n" +
                "Code Coverage\n" +
                "\n" +
                "plugin\n" +
                "org.jacoco\n" +
                "execution goal prepare-agent\n" +
                "execution default-report\n" +
                "          phase test\n" +
                "\t\n" +
                "\tgoal report\n" +
                "\n" +
                "\n" +
                "Antywzorce\n" +
                "nastawianie się na pokrycie kodu w 100% testami\n" +
                "nieintuicyjne nazwy metod testowych\n" +
                "długowykonujące się testy\n" +
                "testy, które nie sprzątają po sobie (nie usuwają pliku lub danych z bazy danych)\n" +
                "pisanie niepowiązanych ze sobą asercji w jednej metodzie testowej\n" +
                "ignorowanie testów kiedy zgłasza błąd\n" +
                "traktowanie testu jako niepotrzebnego kodu\n" +
                "brak automatyzacji uruchamiania testów");
        ScrollPane spane = new ScrollPane(textArea);
        spane.setPrefWidth(485);
        spane.setPrefHeight(300);

        Tab tab1 = new Tab("color pic", new ImageView(image));
        Tab tab2 = new Tab("tree pic", new ImageView(image2));
        Tab tab3 = new Tab("deer pic", new ImageView(image3));

        Accordion accordion = new Accordion(tpane,tpane2,tpane3);
        VBox pane = new VBox(accordion,sbar,spane);
        Tab tab = new Tab("panes", pane);
        TabPane tabPane = new TabPane(tab,tab1,tab2,tab3);
        Pane root = new Pane(tabPane);
        Scene scene = new Scene(root,550,400);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
