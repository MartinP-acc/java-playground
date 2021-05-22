public class ClockThreat implements Runnable{

    ClockThreat(){
        new Thread(this).start();
    }

    boolean stopClock = false;

    @Override
    public void run() {
        while (!stopClock){
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.println(e.toString());
            }
            Minesweeper.gameGUI.incTime();
        }
    }
}
