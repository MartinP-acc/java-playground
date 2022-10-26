public class TargetInfo {

    public Coordinate firstHit;
    public Coordinate lastPart;
    public boolean isHorizontal;
    private int missedHits;
    private int size;
    private boolean isEmpty;

    public TargetInfo(){
        isEmpty = true;
    }

    public TargetInfo(Coordinate firstHit, String alignment){
        this.firstHit = firstHit;
        this.isHorizontal = alignment.equals("H");
        this.missedHits = 0;
        this.isEmpty = false;
    }

    public void addMissed(){
        missedHits++;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void checkOrientation(){
        if (missedHits==2 && size==1) isHorizontal=!isHorizontal;
    }

    public void incSize(){
        size++;
    }

    public int getSize() {
        return size;
    }

    public Coordinate moveFromLast() {
        int movement = missedHits%2==0 ? 1 : -1;
        if (isHorizontal){
            return new Coordinate((char)(lastPart.x+movement), lastPart.y);
        }else {
            return new Coordinate(lastPart.x, lastPart.y+movement);
        }
    }

    public Coordinate moveFromFirst() {
        int movement = missedHits%2==0 ? 1 : -1;
        if (isHorizontal){
            return new Coordinate((char)(firstHit.x+movement), firstHit.y);
        }else {
            return new Coordinate(firstHit.x, firstHit.y+movement);
        }
    }
}
