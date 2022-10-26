package language;

import java.util.ListResourceBundle;

public class Labels_en extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {

        Object[][] label = new Object[27][2];

        // game menu
        label[0][0] = "resume";
        label[0][1] = "Resume";

        label[1][0] = "newGameCPU";
        label[1][1] = "New Game vs CPU";

        label[2][0] = "settings";
        label[2][1] = "Settings";

        label[3][0] = "quit";
        label[3][1] = "Quit";

        // counter name
        label[4][0] = "playerCounter";
        label[4][1] = "Your";

        label[5][0] = "opponentCounter";
        label[5][1] = "Opponent's";

        // hit counter
        label[6][0] = "points";
        label[6][1] = " points";

        // end game pane
        label[7][0] = "won";
        label[7][1] = " WON !";

        // turn info
        label[8][0] = "wait";
        label[8][1] = "Opponent's move\nWAIT...";

        // game log labels
        label[9][0] = "playerMISS";
        label[9][1] = "You MISS at: ";

        label[10][0] = "playerSUNK";
        label[10][1] = "You HIT and SINK ship at: ";

        label[11][0] = "playerHIT";
        label[11][1] = "You HIT ship at: ";

        label[12][0] = "stopped";
        label[12][1] = "Game stopped";

        label[13][0] = "oppHIT";
        label[13][1] = "Opponent HIT your ship at: ";

        label[14][0] = "oppSUNK";
        label[14][1] = "Opponent Hit and SUNK your ship at: ";

        label[15][0] = "oppMISS";
        label[15][1] = "Opponent MISS at: ";

        // ship position log labels
        label[16][0] = "set1";
        label[16][1] = " set at: ";

        label[17][0] = "set2";
        label[17][1] = "ship size: ";

        label[18][0] = "unable";
        label[18][1] = "You can't put there ship";

        // ship position pane
        label[19][0] = "header";
        label[19][1] = "Set ship position";

        label[20][0] = "manual";
        label[20][1] = "Manually";

        label[21][0] = "generate";
        label[21][1] = "Generate";

        // settings pane
        label[22][0] = "volume";
        label[22][1] = "Adjust volume";

        label[23][0] = "on/off";
        label[23][1] = "On/Off sounds";

        label[24][0] = "language";
        label[24][1] = "Language";

        label[25][0] = "mod";
        label[25][1] = "admin mod";

        // player - name
        label[26][0] = "player";
        label[26][1] = "Player";

        return label;
    }
}
