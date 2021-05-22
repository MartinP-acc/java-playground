import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameGUI extends JFrame implements ActionListener {

    JButton[][] maskButton = new JButton[Minesweeper.getWidth()][Minesweeper.getHeight()];
    ImageIcon bomb = new ImageIcon("D:\\Java\\bomb.png");
    ImageIcon flag = new ImageIcon("D:\\Java\\flag.png");
    JCheckBox flagBox;
    JPanel gridPanel;
    int moveCounter, flagCounter, time;
    JLabel flagLab, statusLab, clock;
    ClockThreat clockThreat;

    GameGUI(){
        setTitle("Saper");
        setSize(Minesweeper.getWidth()*50,Minesweeper.getHeight()*50);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setGrid();
        northPanel();
        southPanel();
        setVisible(true);
    }

    public void northPanel(){
        JPanel north = new JPanel();
        north.setLayout(new GridLayout(1,4));

        flagLab = new JLabel(flag);
        flagLab.setText(" = "+flagCounter);
        north.add(flagLab);

        flagBox = new JCheckBox("Flaga");
        north.add(flagBox);

        clock = new JLabel("CZAS");
        clock.setFont(new Font("Arial",Font.BOLD,14));
        north.add(clock);

        JButton nextGame = new JButton("Nowa Gra");
        nextGame.addActionListener(e -> {
            Minesweeper.prepareNewField(Minesweeper.getWidth(),Minesweeper.getHeight(),Minesweeper.getNumbOfMines());
            for (int x = 0; x < Minesweeper.getWidth(); x++) {
                for (int y = 0; y < Minesweeper.getHeight(); y++) {
                    maskButton[x][y].setText("");
                    maskButton[x][y].setIcon(null);
                    maskButton[x][y].addActionListener(this);
                }
            }
            clockThreat.stopClock  = true;
            moveCounter = 0;
            flagCounter = 0;
            time = 0;
            flagLab.setText(" = "+flagCounter);
        });
        north.add(nextGame);

        add(north, BorderLayout.NORTH);
    }

    public void southPanel(){
        statusLab = new JLabel("Liczba odkrytych pól "+moveCounter);
        add(statusLab,BorderLayout.SOUTH);
    }

    public void incTime(){
        time++;
        clock.setText(time+" sek");
    }


    public void setGrid(){
        moveCounter = 0;
        flagCounter = 0;
        time = 0;
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(Minesweeper.getWidth(),Minesweeper.getHeight()));


        for (int x = 0; x < Minesweeper.getWidth(); x++){
            for (int y = 0; y < Minesweeper.getHeight(); y++){
                maskButton[x][y] = new JButton("");
                maskButton[x][y].setBackground(Color.lightGray);
                maskButton[x][y].addActionListener(this);
                maskButton[x][y].setActionCommand("x"+x+"y"+y);
                maskButton[x][y].setMaximumSize(new Dimension(15,15));
                gridPanel.add(maskButton[x][y]);
            }
        }
        add(gridPanel,BorderLayout.CENTER);
    }

    public void zeroField(int x, int y){
        for (int a = x-1; a<=x+1 ; a++){
            for (int b = y-1; b<=y+1 ; b++){
                if (a>=0&&a<Minesweeper.getWidth()&&b>=0&&b<Minesweeper.getHeight()){
                    if (Minesweeper.getMineField(a,b)==0 && maskButton[a][b].getText().equals("")) {
                        moveCounter++;
                        maskButton[a][b].setText("" + Minesweeper.getMineField(a, b));
                        zeroField(a, b);
                    } else {
                        if (maskButton[a][b].getIcon()==flag){
                            maskButton[a][b].setIcon(null);
                            flagCounter--;
                            flagLab.setText(" = "+flagCounter);
                        }
                        if (maskButton[a][b].getText().equals("")) moveCounter++;
                        maskButton[a][b].setForeground(textColor(a,b));
                        maskButton[a][b].setText("" + Minesweeper.getMineField(a, b));

                    }
                    maskButton[a][b].removeActionListener(this);
                }
            }
        }
    }

    public void detonate(){
        for (int i=0; i<Minesweeper.getMines().length; i++){
            maskButton[Minesweeper.getMines()[i][0]][Minesweeper.getMines()[i][1]].setIcon(bomb);
        }
        for (int x = 0; x < Minesweeper.getWidth(); x++) {
            for (int y = 0; y < Minesweeper.getHeight(); y++) {
                maskButton[x][y].removeActionListener(this);
            }
        }
        clockThreat.stopClock  = true;
    }

    public Color textColor(int a, int b){
        switch(Minesweeper.getMineField(a,b)){
            case 1 -> {return new Color(0x002480);}
            case 2 -> {return new Color(0x5C00CB);}
            case 3 -> {return new Color(0x044E00);}
            case 4 -> {return new Color(0x557400);}
            case 5 -> {return new Color(0x6D4200);}
            case 6 -> {return new Color(0x99331D);}
            case 7 -> {return new Color(0x7D0000);}
            case 8 -> {return new Color(0xC20000);}
            default -> {return Color.BLACK;}
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (moveCounter==0) clockThreat = new ClockThreat();

        for (int x = 0; x < Minesweeper.getWidth(); x++) {
            for (int y = 0; y < Minesweeper.getHeight(); y++) {
                if (e.getActionCommand().equals("x"+x+"y"+y)){
                    if (flagBox.isSelected()){
                        maskButton[x][y].setIcon(flag);
                        flagCounter++;
                        flagLab.setText(" = "+flagCounter);
                        flagBox.setSelected(false);
                    }else{
                        if(Minesweeper.getMineField(x,y)==-1){
                            detonate();
                            statusLab.setText("PRZERAŁEŚ");
                        }else if (Minesweeper.getMineField(x,y)==0){
                            zeroField(x,y);
                        }
                        else{
                            if (maskButton[x][y].getIcon()==flag){
                                maskButton[x][y].setIcon(null);
                                flagCounter--;
                                flagLab.setText(" = "+flagCounter);
                            }
                            if (maskButton[x][y].getText().equals("")) moveCounter++;
                            maskButton[x][y].setForeground(textColor(x,y));
                            maskButton[x][y].setText(""+Minesweeper.getMineField(x,y));
                        }
                        maskButton[x][y].removeActionListener(this);
                        if (moveCounter==Minesweeper.getHeight()*Minesweeper.getWidth()-Minesweeper.getMines().length){
                            statusLab.setText("WYGRAŁEŚ");
                            detonate();
                        } else statusLab.setText("Liczba odkrytych pól "+moveCounter);
                    }
                }
            }
        }
    }
}
