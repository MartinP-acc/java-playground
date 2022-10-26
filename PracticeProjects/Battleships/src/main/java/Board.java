import javafx.scene.Group;

import java.util.*;
import static helper.GameConst.*;

public class Board {

    private final HashMap<Coordinate,Character> markedField;
    private Group ships;
    private ArrayList<Ship> emptyShips;
    private int index;

    public Board(){
        ships = new Group();
        markedField = new HashMap<>();
    }

    public boolean isThereAShip(Coordinate shot){
        return markedField.containsKey(shot) && markedField.get(shot).equals(SHIP);
    }

    public boolean isDestroyed(Coordinate shot){
        Set<Coordinate> visted = new HashSet<>();
        Stack<Coordinate> queue = new Stack<>();
        visted.add(shot);
        queue.addAll(fourDirections(shot));
        while (!queue.isEmpty()){
            Coordinate actual = queue.pop();
            if (markedField.get(actual).equals(SHIP)) return false;
            if (markedField.get(actual).equals(HIT) && !visted.contains(actual)){
                queue.addAll(fourDirections(actual));
                visted.add(actual);
            }
        }
        return true;
    }

    public List<Coordinate> fourDirections(Coordinate shot) {
        List<Coordinate> list = new ArrayList<>();
        list.add(Coordinate.rightSide(shot));
        list.add(Coordinate.leftSide(shot));
        list.add(Coordinate.above(shot));
        list.add(Coordinate.below(shot));
        return list;
    }

    public Group generateShips(){
        ships.getChildren().clear();
        markedField.clear();
        Generator gen = new Generator();
        for (int size=4; size>0; size--){
            for (int shipNr=4-size; shipNr>=0; shipNr--){
                boolean isCorrect;
                Ship ship;
                do {
                    gen.generate();
                    ship = Ship.createGenerated(gen,size);
                    isCorrect = canSetThereShip(ship);
                }while (!isCorrect);
                markShip(ship);
                ships.getChildren().add(ship);
            }
        }
        return ships;
    }

    public Group getShips() {
        return ships;
    }

    public boolean allShipsSet(){
        return index > 9;
    }

    public void initEmptyShips(){
        emptyShips = new ArrayList<>();
        index = 0;
        for (int size = 4; size>0; size--){
            for (int quantity = 1; quantity<= 5 - size; quantity++){
                emptyShips.add(Ship.createEmptyShip(size));
            }
        }
    }

    public void nextUnsetShip(){
        index++;
    }

    public Ship getUnsetShip(){
        return emptyShips.get(index);
    }


    public void markShip(Ship ship){
        markOnField(ship);
        markBorder(ship);
    }

    public void markShot(Coordinate shot){
        markedField.put(shot,SHOT);
    }

    public void enemyHit(Coordinate shot){
        markedField.put(shot,SHIP);
    }

    public boolean canSetThereShip(Ship ship) {
        for (int i=0; i<ship.getSize(); i++) {
            char x = ship.getShipCoords()[0].x;
            int y = ship.getShipCoords()[0].y;
            if (ship.getAlignment().equals("V")) y+=i;
            else x+=i;
            ship.getShipCoords()[i] = new Coordinate(x,y);
            if (!Validator.isValidLine(x+""+y)) return false;
            if (markedField.containsKey(ship.getShipCoords()[i])) return false;
        }
        return true;
    }

    private void markOnField(Ship ship) {
        for (Coordinate c : ship.getShipCoords()){
            markedField.put(c,SHIP);
        }
    }

    private void markBorder(Ship ship) {
        int firstX = ship.getShipCoords()[0].x-1;
        int lastX = ship.getShipCoords()[ship.getSize()-1].x+1;
        int firstY = ship.getShipCoords()[0].y-1;
        int lastY = ship.getShipCoords()[ship.getSize()-1].y+1;
        for (int x=firstX; x<=lastX; x++){
            for (int y=firstY; y<=lastY; y++){
                Coordinate border = new Coordinate((char)x,y);
                markedField.putIfAbsent(border,BORDER);
            }
        }
    }

    public void printBoard(){
        System.out.print(" \t");
        for (char l = 'A'; l<='J'; l++) System.out.print("\u001B[33m"+l+"\t"+"\u001B[0m");
        System.out.println();
        for (int y=1; y<=10; y++){
            System.out.print("\u001B[33m"+y+"\t"+"\u001B[0m");
            for (char x='a'; x<='j'; x++){
                Coordinate field = new Coordinate(x,y);
                if (markedField.containsKey(field))
                    switch (markedField.get(field)){
                        case HIT -> System.out.print("\u001B[31m"+markedField.get(field)+"\t"+"\u001B[0m");
                        case SHOT -> System.out.print("\u001B[35m"+markedField.get(field)+"\t"+"\u001B[0m");
                        case SHIP -> System.out.print("\u001B[32m"+markedField.get(field)+"\t"+"\u001B[0m");
                        case BORDER -> System.out.print("\u001B[33m"+markedField.get(field)+"\t"+"\u001B[0m");
                    }
                else System.out.print("\u001B[34m"+".\t"+"\u001B[0m");
            }
            System.out.println();
        }
    }

    public boolean alreadyMarked(Coordinate coords) {
        return markedField.containsKey(coords) && markedField.get(coords).equals(SHOT);
    }

    public boolean alreadyMarkedAny(Coordinate coords) {
        return markedField.containsKey(coords);
    }

    public void markHit(Coordinate coords) {
        markedField.put(coords,HIT);
    }
}
