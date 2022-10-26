import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FXApp extends Application{

    @Override
    public void start(Stage stage) throws Exception {

        ObservableList<XYChart.Data<String,Number>> game1Data = FXCollections.observableArrayList();
        game1Data.add(new XYChart.Data<>("2000",200));
        game1Data.add(new XYChart.Data<>("2001",150));
        game1Data.add(new XYChart.Data<>("2002",50));
        game1Data.add(new XYChart.Data<>("2003",80));
        game1Data.add(new XYChart.Data<>("2004",77));
        game1Data.add(new XYChart.Data<>("2005",58));
        game1Data.add(new XYChart.Data<>("2006",99));
        game1Data.add(new XYChart.Data<>("2007",172));
        game1Data.add(new XYChart.Data<>("2008",113));

        XYChart.Series<String,Number> game1 = new XYChart.Series<>("gameA",game1Data);

        ObservableList<XYChart.Data<String,Number>> game2Data = FXCollections.observableArrayList();
        game2Data.add(new XYChart.Data<>("2000",130));
        game2Data.add(new XYChart.Data<>("2001",159));
        game2Data.add(new XYChart.Data<>("2002",111));
        game2Data.add(new XYChart.Data<>("2003",9));
        game2Data.add(new XYChart.Data<>("2004",94));
        game2Data.add(new XYChart.Data<>("2005",30));
        game2Data.add(new XYChart.Data<>("2006",12));
        game2Data.add(new XYChart.Data<>("2007",22));
        game2Data.add(new XYChart.Data<>("2008",214));

        XYChart.Series<String,Number> game2 = new XYChart.Series<>("gameB",game2Data);

        ObservableList<XYChart.Series<String,Number>> games = FXCollections.observableArrayList(game1,game2);

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Years");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("sold copies");
        //BarChart<String,Number> barChart = new BarChart<>(xAxis,yAxis,games);
        //barChart.setTitle("Games Sold");

        LineChart<String,Number> lineChart = new LineChart<>(xAxis,yAxis,games);

        VBox pane =new VBox(lineChart);
        Scene scene = new Scene(pane,600,400);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();

    }
}
