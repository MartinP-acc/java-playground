import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;

public class FXApp extends Application{

    @Override
    public void start(Stage stage) throws Exception {

        Phone mp = new Phone("Marcin","Paszkowski",662932744);
        Phone dp = new Phone("Dagmara","Paszkowska",725796820);
        Phone ks = new Phone("Konrad","Stasiowski",506394795);

        ObservableList<Phone> phones = FXCollections.observableArrayList(mp,dp,ks);

        TableView<Phone> table = new TableView<>(phones);
        table.getSelectionModel().setCellSelectionEnabled(true);
        TableColumn<Phone,String> nameCol = new TableColumn<>("Imię");
        nameCol.setCellValueFactory( param -> new ReadOnlyStringWrapper(param.getValue().name));
        nameCol.setCellFactory(new Callback<TableColumn<Phone, String>, TableCell<Phone, String>>() {
            @Override
            public TableCell<Phone, String> call(TableColumn<Phone, String> phoneStringTableColumn) {
                return new TableCell<>(){
                    @Override
                    protected void updateItem(String s, boolean b) {
                        super.updateItem(s, b);
                        setText(null);
                        setGraphic(new Rectangle(20,10));
                        if (!b){
                          setText(s);
                          setGraphic(null);
                        }
                    }
                };
            }
        });
        TableColumn<Phone,String> surnameCol = new TableColumn<>("Nazwisko");
        surnameCol.setCellValueFactory( param -> new ReadOnlyStringWrapper(param.getValue().surname));
        surnameCol.setCellFactory(new Callback<TableColumn<Phone, String>, TableCell<Phone, String>>() {
            @Override
            public TableCell<Phone, String> call(TableColumn<Phone, String> phoneStringTableColumn) {
                return new TableCell<>(){
                    @Override
                    protected void updateItem(String s, boolean b) {
                        super.updateItem(s, b);
                        setText(null);
                        setGraphic(null);
                        if (!b){
                            setText(s.toUpperCase());
                        }
                    }
                };
            }
        });

        Button but = new Button("show selection");
        but.setOnAction(actionEvent -> {
            System.out.println(table.getSelectionModel().getSelectedCells().toString());
        });

        TableColumn<Phone,Integer> phoneCol = new TableColumn<>("nr telefonu");
        phoneCol.setCellValueFactory( param -> new ReadOnlyObjectWrapper<>(param.getValue().phoneNr));
        phoneCol.setResizable(false);

        TableColumn<Phone,String> fullNameCol = new TableColumn<>("Dane");
        fullNameCol.getColumns().addAll(nameCol,surnameCol);
        fullNameCol.setResizable(true);

        VBox vBox = new VBox();
        if (table.getColumns().addAll(fullNameCol, phoneCol)) {
            vBox.getChildren().addAll(table,but);
        }
        Scene scene = new Scene(vBox,500,500);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();

    }
}
