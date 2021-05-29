package units;

abstract public class UnitStatsRepository {

    private static final UnitStats tankStats = new UnitStats("tank", 100, 25, 30, 10, 200);
    private static final UnitStats riflemanStats = new UnitStats("rifleman", 25, 5, 12, 7, 35);
    private static final UnitStats HelicopterStats = new UnitStats("helicopter", 50, 15, 30, 25, 150);

    public static UnitStats getTankStats() {
        return tankStats;
    }

    public static UnitStats getRiflemanStats() {
        return riflemanStats;
    }

    public static UnitStats getHelicopterStats() {
        return HelicopterStats;
    }
}
