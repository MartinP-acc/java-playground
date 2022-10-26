public class AIPlayer extends Player{

    private final int[] targetsLeft;
    private TargetInfo currentTarget;
    private boolean wasHit;
    private Coordinate coords;

    public AIPlayer(){
        super();
        targetsLeft = new int[]{0,4,3,2,1};
        currentTarget = new TargetInfo();
        name = "CPU";
    }

    @Override
    public void prepareNextMove() {
        if (currentTarget.isEmpty()) {
            currentTarget = findNextTarget();
            coords = currentTarget.firstHit;
        }else{
            do {
                coords = aimNextPart();
                if (isTaken(coords)){
                    currentTarget.addMissed();
                    wasHit = false;
                }
            }while (isTaken(coords));
        }
    }

    public void shoot(Player player) {
        HitState hitResponse = player.check(coords);
        if (hitResponse.equals(HitState.MISS)){
            if (currentTarget.getSize()==0) currentTarget = new TargetInfo();
            else currentTarget.addMissed();
            enemyBoard.markShot(coords);
        }
        if (hitResponse.equals(HitState.HIT)){
            currentTarget.lastPart = coords;
            currentTarget.incSize();
            wasHit = true;
            enemyBoard.enemyHit(coords);
            hitPoints.set(hitPoints.intValue()+1);
        }
        if (hitResponse.equals(HitState.SUNK)){
            currentTarget.incSize();
            targetsLeft[currentTarget.getSize()] -= 1;
            currentTarget = new TargetInfo();
            getSunkenShip(coords);
            markSunkenShip();
            wasHit = false;
            hitPoints.set(hitPoints.intValue()+1);
        }
    }

    private TargetInfo findNextTarget() {
        Generator gen = new Generator();
        boolean isCorrect;
        do {
            int size = biggestShip();
            gen.generate();
            Ship ship = Ship.createGenerated(gen,size);
            isCorrect = enemyBoard.canSetThereShip(ship);
        } while (!isCorrect);
        return new TargetInfo(gen.getCoordinate(),gen.getAlignment());
    }

    private Coordinate aimNextPart(){
        currentTarget.checkOrientation();
        if (wasHit){
            return currentTarget.moveFromLast();
        }else {
            return currentTarget.moveFromFirst();
        }
    }

    private int biggestShip(){
        for (int size = 4; size>0; size--){
            if (targetsLeft[size]>0) return size;
        }
        return 0;
    }

    private boolean isTaken(Coordinate coordinate){
        return enemyBoard.alreadyMarkedAny(coordinate) || !Validator.isValidCoordinate(coordinate);
    }
}
