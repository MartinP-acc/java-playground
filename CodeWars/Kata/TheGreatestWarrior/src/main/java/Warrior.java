import java.util.ArrayList;
import java.util.List;

public class Warrior {

    private final String[] availableRanks = new String[]{"Pushover", "Novice", "Fighter", "Warrior", "Veteran", "Sage", "Elite", "Conqueror", "Champion", "Master", "Greatest"};
    private final int MAX_EXP = 1000*(availableRanks.length-1);
    private final int MAX_LVL = 10*(availableRanks.length-1);
    private final int MIN_LVL = 1;

    private List<String> achievementsList;
    private int exp;

    public Warrior() {
        this.achievementsList = new ArrayList<>();
        this.exp = 100;
    }

    public int level() {
        return exp/100;
    }

    public int experience() {
        return exp;
    }

    private int rankIndex(){
        return exp/1000;
    }

    public String rank() {
        return availableRanks[rankIndex()];
    }

    public List<String> achievements() {
        return achievementsList;
    }

    private void addExperience(int addExp){
        this.exp += addExp;
        if (exp>MAX_EXP) exp=MAX_EXP;
    }

    private int enemyRank(int enemyLvl){
        return enemyLvl/10;
    }

    public String training(String achievement, int expReward, int reqLvl) {
        if (level()>=reqLvl){
            addExperience(expReward);
            this.achievementsList.add(achievement);
            return achievement;
        }else {
            return "Not strong enough";
        }
    }

    public String battle(int enemyLvl) {
        if (enemyLvl<MIN_LVL || enemyLvl>MAX_LVL) return "Invalid level";

        int lvlDiff = enemyLvl-level();
        if (rankIndex()<enemyRank(enemyLvl) && lvlDiff>4) return "You've been defeated";
        if (lvlDiff<-1) return "Easy fight";
        switch (lvlDiff){
            case -1: {addExperience(5);
                return "A good fight";}
            case 0 : {addExperience(10);
                return "A good fight";}
            default: {addExperience(lvlDiff * lvlDiff * 20);
                return "An intense fight";}
        }
    }
}
