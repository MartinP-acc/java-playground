package units;

public class Rifleman {

    private int x;
    private int y;
    private int hpLeft;

    public Rifleman(int x, int y) {
        UnitStats stats = UnitStatsRepository.getRiflemanStats();
        this.x = x;
        this.y = y;
        this.hpLeft = stats.getHp();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHpLeft() {
        return hpLeft;
    }

    public void setHpLeft(int hpLeft) {
        this.hpLeft = hpLeft;
    }

    @Override
    public String toString() {
        return "Rifleman{" +
                "x=" + x +
                ", y=" + y +
                ", hpLeft=" + hpLeft +
                '}';
    }
}
