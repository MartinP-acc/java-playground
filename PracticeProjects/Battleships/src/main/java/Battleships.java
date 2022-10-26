import helper.GridSetup;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

import static helper.GameConst.*;

public class Battleships extends Application implements LanguageObserver{

    private Thread game;
    private GridSetup playerGridSetup;
    private GridSetup opponentGridSetup;
    private Pane primaryPane;
    private Group gameNodes;
    private LogTextArea logArea;
    private Label playerPoints;
    private Label opponentPoints;
    private LanguageObservable languageControl;
    private HitCounter playerCounter;
    private HitCounter opponentCounter;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
        languageControl = new LanguageObservable(this);
        Sounds.initSounds();
        Image image = new Image(Objects.requireNonNull(getClass().getResource("assets/map.png")).toURI().toString(), true);
        ImageView background = new ImageView(image);
        background.setViewport(new Rectangle2D(0,0,880,550));
        playerGridSetup = new GridSetup(PLAYER_BOARD_X,PLAYER_BOARD_Y);
        opponentGridSetup = new GridSetup(OPPONENT_BOARD_X,OPPONENT_BOARD_Y);
        BoardDrawing playerBoardShape = new BoardDrawing(playerGridSetup);
        playerBoardShape.initBoard(Color.BLUE);
        BoardDrawing opponentBoardShape = new BoardDrawing(opponentGridSetup);
        opponentBoardShape.initBoard(Color.DARKSEAGREEN);
        playerCounter = new HitCounter(playerGridSetup);
        languageControl.attachObserver(playerCounter);
        opponentCounter = new HitCounter(opponentGridSetup);
        languageControl.attachObserver(opponentCounter);
        playerPoints = playerCounter.getCounterLabel();
        opponentPoints = opponentCounter.getCounterLabel();
        logArea = new LogTextArea();
        primaryPane = new Pane(background,playerBoardShape,opponentBoardShape,
                playerCounter, opponentCounter,
                playerPoints,opponentPoints,
                playerCounter.getHeaderLabel(),opponentCounter.getHeaderLabel(),
                logArea);
        GameMenu.init(primaryPane);
        languageControl.attachObserver(GameMenu.get());
        EndGamePane.init(primaryPane);
        languageControl.attachObserver(EndGamePane.getInstance());
        OppTurnInfo.init(primaryPane);
        languageControl.attachObserver(OppTurnInfo.getInstance());
        SettingsPane.init(primaryPane);
        languageControl.attachObserver(SettingsPane.get());
        ShipSetupPane.init(primaryPane);
        languageControl.attachObserver(ShipSetupPane.get());
        languageControl.notifyObservers();
    }

    @Override
    public void start(Stage stage){
        CrossNode crossNode = new CrossNode(45,430);
        primaryPane.getChildren().add(crossNode);
        Pane root = new Pane(primaryPane, ShipSetupPane.get(), GameMenu.get(), SettingsPane.get(), OppTurnInfo.getInstance(),
                EndGamePane.getInstance());
        root.setOnMouseClicked(mouseEvent -> EndGamePane.getInstance().setVisible(false));
        Scene scene = new Scene(root,880,550);
        stage.setOnCloseRequest(windowEvent -> {if (game !=null) game.stop();});
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setTitle("Battleships");
        stage.show();
        GameMenu.get().show();
        primaryPane.setOnMouseMoved(mouseEvent -> crossNode.setRotation(mouseEvent.getSceneX(),mouseEvent.getSceneY()));
        crossNode.setOnMouseClicked(mouseEvent -> {
            GameMenu.get().show();
            GameMenu.get().resume.setVisible(game.isAlive());
        });
        GameMenu.get().playWithCpu.setOnMouseClicked(mouseEvent -> startGame());
        GameMenu.get().settings.setOnMouseClicked(mouseEvent -> SettingsPane.get().setVisible(!SettingsPane.get().isVisible()));
        GameMenu.get().quit.setOnMouseClicked(mouseEvent -> stage.fireEvent(new WindowEvent(stage,WindowEvent.WINDOW_CLOSE_REQUEST)));
        GameMenu.get().resume.setOnMouseClicked(mouseEvent -> GameMenu.get().hide());
        SettingsPane.get().languageBox.setOnAction(actionEvent -> {
            switch (SettingsPane.get().languageBox.getValue()){
                case "POLSKI" -> languageControl.setLanguage(ResourceBundle.getBundle("language.Labels", Locale.getDefault()));
                case "ENGLISH" -> languageControl.setLanguage(ResourceBundle.getBundle("language.Labels", Locale.ENGLISH));
            }
            languageControl.notifyObservers();
        });
    }

    public void startGame(){
        GameMenu.get().hide();
        if (game != null) game.stop();
        primaryPane.getChildren().remove(gameNodes);
        RectButtonGrid opponentButtonGrid = new RectButtonGrid(opponentGridSetup);
        RectButtonGrid playerButtonGrid = new RectButtonGrid(playerGridSetup);
        languageControl.attachObserver(playerButtonGrid);
        gameNodes = new Group(playerButtonGrid, opponentButtonGrid);
        Player firstPlayer = new Player(playerButtonGrid, opponentButtonGrid);
        languageControl.attachObserver(firstPlayer);
        languageControl.notifyObservers();
        ShipSetupPane.get().manually.setOnMouseClicked(mouseEvent -> {
            playerButtonGrid.initManualShipSet(firstPlayer);
            ShipSetupPane.get().setVisible(false);
        });
        ShipSetupPane.get().generate.setOnMouseClicked(mouseEvent ->{
            Group ships = firstPlayer.ownBoard.generateShips();
            gameNodes.getChildren().add(ships);
            ships.toBack();
            ShipSetupPane.get().setVisible(false);
        });
        ShipSetupPane.get().setVisible(true);
        Player secondPlayer = new AIPlayer();
        firstPlayer.setLabel(playerPoints);
        secondPlayer.setLabel(opponentPoints);
        secondPlayer.ownBoard.generateShips();
        logArea.clear();
        primaryPane.getChildren().add(gameNodes);
        game = GameThread.createAndStart(firstPlayer, secondPlayer);
    }

    @Override
    public void update(ResourceBundle languageBundle) {
        playerCounter.setPlayer(languageBundle.getString("playerCounter"));
        opponentCounter.setPlayer(languageBundle.getString("opponentCounter"));
    }
}
