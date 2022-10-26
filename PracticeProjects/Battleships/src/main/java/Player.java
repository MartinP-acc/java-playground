import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;

import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Stack;

public class Player implements LanguageObserver{

    private final Sounds sounds;
    private RectButtonGrid opponentGrid;
    private Thread game;
    private ResourceBundle language;
    protected Set<Coordinate> shipParts;
    protected Board ownBoard;
    protected Board enemyBoard;
    protected SimpleIntegerProperty hitPoints;
    protected String name;
    protected Label label;
    protected boolean mainPlayer = false;
    public RectButtonGrid playerGrid;

    protected enum HitState{
        MISS,
        HIT,
        SUNK,
        MARKED
    }

    protected Player(){
        ownBoard = new Board();
        enemyBoard = new Board();
        shipParts = new HashSet<>();
        sounds = Sounds.getInstance();
        hitPoints = new SimpleIntegerProperty(20);
    }

    public Player(RectButtonGrid playerGrid, RectButtonGrid opponentGrid){
        this.opponentGrid = opponentGrid;
        this.playerGrid = playerGrid;
        ownBoard = new Board();
        enemyBoard = new Board();
        shipParts = new HashSet<>();
        hitPoints = new SimpleIntegerProperty(20);
        name = "Player";
        mainPlayer = true;
        sounds = Sounds.getInstance();
    }

    public void setLabel(Label label){
        this.label = label;
    }

    public void setHitPointsListener(){
        hitPoints.addListener(observable -> Platform.runLater( () -> label.setText(hitPoints.getValue().toString())));
        hitPoints.setValue(0);
    }

    public void setListenerToNotify(Thread game){
        this.game = game;
        opponentGrid.getLastId().addListener(observable -> {
            synchronized (game) {
                game.notify();
            }
        });
    }

    public void prepareNextMove(){
        OppTurnInfo.getInstance().setVisible(false);
        opponentGrid.setDisabledAllHandlers(false);
        synchronized (game) {
            try {
                game.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        OppTurnInfo.getInstance().setVisible(true);
    }

    public void shoot(Player attackedPlayer){
        Coordinate coords;
        HitState hitResponse;
        try {
            coords  = Coordinate.parseCoordinate(opponentGrid.getLastId().getValue());
            hitResponse = attackedPlayer.check(coords);
        if (hitResponse == HitState.MISS){
            enemyBoard.markShot(coords);
            opponentGrid.getButtonById(coords.toString()).setImage(RectButton.MISS);
            LogTextArea.getTextLog().set(language.getString("playerMISS")+coords.toString().toUpperCase());
        }
        else {
            hitPoints.set(hitPoints.intValue()+1);
            enemyBoard.enemyHit(coords);
            opponentGrid.getButtonById(coords.toString()).setImage(RectButton.HIT);
            if (hitResponse == HitState.SUNK){
                LogTextArea.getTextLog().set(language.getString("playerSUNK")+coords.toString().toUpperCase());
                getSunkenShip(coords);
                markSunkenShip();
            }
            else LogTextArea.getTextLog().set(language.getString("playerHIT")+coords.toString().toUpperCase());
        }
        opponentGrid.setDisabledAllHandlers(true);
        }catch (IllegalArgumentException e){
            LogTextArea.getTextLog().set(language.getString("stopped"));
        }
    }

    protected void markSunkenShip(){
        for (Coordinate part : shipParts){
            for (char x = (char)(part.x-1); x<=(char)(part.x+1); x++){
                for (int y = part.y-1; y<= part.y+1; y++){
                    if (Validator.isValidLine(x+""+y)){
                        Coordinate toMark = new Coordinate(x,y);
                        if (!enemyBoard.isThereAShip(toMark)){
                            enemyBoard.markShot(toMark);
                            if (mainPlayer) opponentGrid.getButtonById(toMark.toString()).setImage(RectButton.MISS);
                        }
                    }
                }
            }
        }
    }

    protected void getSunkenShip(Coordinate shot){
        shipParts = new HashSet<>();
        Stack<Coordinate> queue = new Stack<>();
        shipParts.add(shot);
        queue.addAll(enemyBoard.fourDirections(shot));
        while (!queue.isEmpty()){
            Coordinate actual = queue.pop();
            if (enemyBoard.isThereAShip(actual) && !shipParts.contains(actual)){
                shipParts.add(actual);
                queue.addAll(enemyBoard.fourDirections(actual));
            }
        }
    }

    public HitState check(Coordinate coords){
        HitState state;
        if (ownBoard.alreadyMarked(coords)){
            return HitState.MARKED;
        }
        if (ownBoard.isThereAShip(coords)){
            ownBoard.markHit(coords);
            if (mainPlayer) {
                playerGrid.getButtonById(coords.toString()).setImage(RectButton.HIT);
                LogTextArea.getTextLog().set(language.getString("oppHIT")+coords.toString().toUpperCase());
            }
            sounds.playHit();
            if (ownBoard.isDestroyed(coords)){
                state = HitState.SUNK;
                sounds.playSunk();
                if (mainPlayer) {
                    LogTextArea.getTextLog().set(language.getString("oppSUNK")+coords.toString().toUpperCase());
                }
            }
            else state = HitState.HIT;
        } else{
            state = HitState.MISS;
            sounds.playMiss();
            ownBoard.markShot(coords);
            if (mainPlayer){
                playerGrid.getButtonById(coords.toString()).setImage(RectButton.MISS);
                LogTextArea.getTextLog().set(language.getString("oppMISS")+coords.toString().toUpperCase());
            }
        }
        return state;
    }

    public String getName() {
        return name;
    }

    @Override
    public void update(ResourceBundle languageBundle) {
        language = languageBundle;
        name = language.getString("player");
    }
}
