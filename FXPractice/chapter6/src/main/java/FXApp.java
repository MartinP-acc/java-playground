import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.SnapshotResult;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.Mnemonic;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class FXApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Button but = new Button("Snapshot");
        but.setCursor(Cursor.HAND);
        KeyCombination kk = new KeyCodeCombination(KeyCode.S,KeyCombination.ALT_DOWN);
        KeyCombination kkAcc = new KeyCodeCombination(KeyCode.C,KeyCombination.CONTROL_DOWN);
        Mnemonic mnemonic = new Mnemonic(but,kk);
        HBox hBox = new HBox();
        hBox.getChildren().add(but);
        Callback<SnapshotResult,Void> c = sr -> {
            File f = new File("C:/snapshotFX.png");
            try {
                f.createNewFile();
                RenderedImage ri = SwingFXUtils.fromFXImage(sr.getImage(),null);
                ImageIO.write(ri,"png",f);
            }catch (IOException ex){
                System.out.println(ex.toString());
            }
            return null;
        };
        Scene scene = new Scene(hBox,300,300);
        scene.addMnemonic(mnemonic);
        scene.getAccelerators().put(kkAcc, stage::close);
        stage.setScene(scene);

        but.setOnAction(e -> scene.snapshot(c,new WritableImage(200,200)));
        stage.show();
    }
}
