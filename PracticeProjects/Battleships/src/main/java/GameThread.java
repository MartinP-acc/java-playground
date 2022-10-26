import javafx.application.Platform;

public class GameThread implements Runnable{

    private Player current;
    private Player attacked;

    private GameThread(Player firstPlayer, Player secondPlayer){
        current = firstPlayer;
        current.setListenerToNotify(Thread.currentThread());
        attacked = secondPlayer;
    }

    public static Thread createAndStart(Player playerGrid, Player opponentGrid){
        GameThread game = new GameThread(playerGrid,opponentGrid);
        Thread gameThread = new Thread(game);
        gameThread.start();
        return gameThread;
    }

    @Override
    public void run() {

        current.setHitPointsListener();
        attacked.setHitPointsListener();

        do{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            current.prepareNextMove();
            current.shoot(attacked);
            swapPlayer();
        }while (attacked.hitPoints.getValue() != 20);
        OppTurnInfo.getInstance().setVisible(false);
        LogTextArea.getTextLog().set(attacked.getName()+" WON");
        Platform.runLater(()-> EndGamePane.getInstance().showWinner(attacked.getName()));
    }

    private void swapPlayer() {
        Player temp = current;
        current = attacked;
        attacked = temp;
    }
}
