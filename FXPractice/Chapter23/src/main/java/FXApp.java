import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FXApp extends Application{

    @Override
    public void start(Stage stage) throws Exception {

        ObservableList<PieChart.Data> company = FXCollections.observableArrayList();
        company.add(new PieChart.Data("1st command company", 116));
        company.add(new PieChart.Data("2nd command company", 63));
        company.add(new PieChart.Data("logistic company", 70));
        company.add(new PieChart.Data("recon company", 60));
        company.add(new PieChart.Data("chemistry platoon", 40));
        company.add(new PieChart.Data("30th sappers patrol ", 8));
        company.add(new PieChart.Data("33th sappers patrol ", 8));

        PieChart bdowChart = new PieChart(company);
        for (PieChart.Data n : bdowChart.getData()){
            Node node = n.getNode();
            Tooltip.install(node,new Tooltip("soldiers "+n.getPieValue()));
        }


        Pane pane = new Pane(bdowChart);
        Scene scene = new Scene(pane,600,500);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
