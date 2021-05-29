package units;

public class UnitStats {

    private final String name;
    private final int hp;
    private final int armour;
    private final int dmgDealt;
    private final int speed;
    private final int resCost;

    public UnitStats(String name, int hp, int armour, int dmgDealt, int speed, int resCost) {
        this.name = name;
        this.hp = hp;
        this.armour = armour;
        this.dmgDealt = dmgDealt;
        this.speed = speed;
        this.resCost = resCost;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getArmour() {
        return armour;
    }

    public int getDmgDealt() {
        return dmgDealt;
    }

    public int getSpeed() {
        return speed;
    }

    public int getResCost() {
        return resCost;
    }
}
