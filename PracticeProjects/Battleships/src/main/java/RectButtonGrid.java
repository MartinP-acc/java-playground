import helper.GridSetup;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;

import java.util.ResourceBundle;

public class RectButtonGrid extends Group implements LanguageObserver{

    private final GridSetup gridSetup;
    private final StringProperty lastId;
    private ObservableMap<String, RectButton> buttonsMap;
    private Ship ship;
    private ResourceBundle language;

    public RectButtonGrid(GridSetup gridSetup){
        this.gridSetup = gridSetup;
        this.lastId = new SimpleStringProperty();
        initRectButtons();
    }

    private void initRectButtons(){
        buttonsMap = FXCollections.observableHashMap();
        for (int i=1; i<=10; i++){
            for (char c = 'a'; c <= 'j'; c++){
                String id = c+""+i;
                double x = gridSetup.getColumn(c);
                double y = gridSetup.getRow(i);
                buttonsMap.put(id,new RectButton(x,y,id));
                this.getChildren().add(buttonsMap.get(id));
                handleMouseClick(buttonsMap.get(id));
            }
        }
    }

    private void handleMouseClick(RectButton rectButton) {
        rectButton.setOnMouseClicked(mouseEvent -> {
            if (!rectButton.isDisabledHandler()) {
                lastId.set(rectButton.getId());
            }
        });
    }

    public StringProperty getLastId() {
        return lastId;
    }

    public RectButton getButtonById(String id){
        return buttonsMap.get(id);
    }

    public void setDisabledAllHandlers(boolean status){
        buttonsMap.forEach((s, rectButton) -> rectButton.setDisabledHandlers(status));
    }

    public void initManualShipSet(Player player){
        player.ownBoard.initEmptyShips();
        ship = player.ownBoard.getUnsetShip();
        getChildren().add(ship);
        ship.setVisible(false);
        ship.toBack();
        this.setOnMouseMoved(mouseEvent -> {
            if (!player.ownBoard.allShipsSet() && mouseEvent.getTarget() instanceof RectButton){
                ship.setLayoutX(((RectButton) mouseEvent.getTarget()).getX());
                ship.setLayoutY(((RectButton) mouseEvent.getTarget()).getY());
                ship.setVisible(true);
            }
        });
        this.setOnMouseExited(mouseEvent -> {
            if (!player.ownBoard.allShipsSet()) ship.setVisible(false);
        });
        this.setOnMouseClicked(mouseEvent -> {
            if (!player.ownBoard.allShipsSet() && mouseEvent.getTarget() instanceof RectButton){
                if (mouseEvent.getButton().compareTo(MouseButton.PRIMARY) == 0){
                    ship.setFirstCoordinate(((RectButton) mouseEvent.getTarget()).getId());
                    boolean available = player.ownBoard.canSetThereShip(ship);
                    if (available){
                        player.ownBoard.markShip(ship);
                        LogTextArea.getTextLog().set(language.getString("set1")+ship.getSize()+language.getString("set2")+ lastId.getValue());
                        player.ownBoard.nextUnsetShip();
                        if (!player.ownBoard.allShipsSet()){
                            ship = player.ownBoard.getUnsetShip();
                            getChildren().add(ship);
                            ship.toBack();
                            ship.setVisible(false);
                        }else {
                            // start game
                        }
                    }else {
                        LogTextArea.getTextLog().set("");
                        LogTextArea.getTextLog().set(language.getString("unable"));
                    }
                }
                if (mouseEvent.getButton().compareTo(MouseButton.SECONDARY) == 0){
                    ship.changeAlignment();
                }
            }
        });
    }

    @Override
    public void update(ResourceBundle languageBundle) {
        language = languageBundle;
    }
}
