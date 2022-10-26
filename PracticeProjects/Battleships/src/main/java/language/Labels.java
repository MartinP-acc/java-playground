package language;

import java.util.ListResourceBundle;

public class Labels extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {

        Object[][] label = new Object[27][2];

        // game menu
        label[0][0] = "resume";
        label[0][1] = "Wznów";

        label[1][0] = "newGameCPU";
        label[1][1] = "Nowa Gra z CPU";

        label[2][0] = "settings";
        label[2][1] = "Ustawienia";

        label[3][0] = "quit";
        label[3][1] = "Opuść grę";

        // counter name
        label[4][0] = "playerCounter";
        label[4][1] = "Twoje";

        label[5][0] = "opponentCounter";
        label[5][1] = "Przeciwnika";

        //hit counter
        label[6][0] = "points";
        label[6][1] = " punkty";

        //end game pane
        label[7][0] = "won";
        label[7][1] = " WYGRAŁ !";

        //turn info
        label[8][0] = "wait";
        label[8][1] = "Ruch przeciwnika\nCZEKAJ...";

        //game log labels
        label[9][0] = "playerMISS";
        label[9][1] = "Spudłowałeś na: ";

        label[10][0] = "playerSUNK";
        label[10][1] = "Zatopiłeś statek na: ";

        label[11][0] = "playerHIT";
        label[11][1] = "Trafiłeś na: ";

        label[12][0] = "stopped";
        label[12][1] = "Gra zakończona";

        label[13][0] = "oppHIT";
        label[13][1] = "Przeciwnik trafił na: ";

        label[14][0] = "oppSUNK";
        label[14][1] = "Przeciwnik zatopił statek na: ";

        label[15][0] = "oppMISS";
        label[15][1] = "Przeciwnik spudłował na: ";

        //ship position log labels
        label[16][0] = "set1";
        label[16][1] = "statek ";

        label[17][0] = "set2";
        label[17][1] = " masztowy ustawiony na: ";

        label[18][0] = "unable";
        label[18][1] = "Niepoprawne miejsce";

        //ship position pane
        label[19][0] = "header";
        label[19][1] = "Ustaw swoje statki";

        label[20][0] = "manual";
        label[20][1] = "Ręcznie";

        label[21][0] = "generate";
        label[21][1] = "Losowo";

        //settings pane
        label[22][0] = "volume";
        label[22][1] = "Dostosuj głośność";

        label[23][0] = "on/off";
        label[23][1] = "Wł./Wył. dźwięki";

        label[24][0] = "language";
        label[24][1] = "Język";

        label[25][0] = "mod";
        label[25][1] = "modyfikacja";

        //player - name

        label[26][0] = "player";
        label[26][1] = "Gracz";

        return label;
    }
}
