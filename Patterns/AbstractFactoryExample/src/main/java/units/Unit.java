package units;

abstract public class Unit {

    private int hp;
    private int dmg;
    private int exp;

    public Unit(int hp, int dmg, int exp) {
        this.hp = hp;
        this.dmg = dmg;
        this.exp = exp;
    }

    public int getHp() {
        return hp;
    }

    public int getDmg() {
        return dmg;
    }

    public int getExp() {
        return exp;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "hp=" + hp +
                ", dmg=" + dmg +
                ", exp=" + exp +
                '}';
    }
}
