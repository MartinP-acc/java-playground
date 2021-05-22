import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameGUI extends JFrame implements ActionListener {

    private JButton[] gridField;
    private JLabel msg, score;
    private int moves = 0;
    private final ImageIcon O = new ImageIcon("O.png");
    private final ImageIcon X = new ImageIcon("X.png");

    // uruchomienie okna głównego
    public void runGUI(){
        setSize(400,500);
        setLocation(500,300);
        setTitle("Kółko i krzyżyk");
        setLayout(new BorderLayout());
        setResizable(false);

        showGrid();

        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    // ustawienie akcji po naciśnięciu pola na siatce
    @Override
    public void actionPerformed(ActionEvent e) {
        int index = Integer.parseInt(e.getActionCommand());
        gridField[index].setIcon(TicTacToe.getPlayer().equals("O")?O:X);
        gridField[index].removeActionListener(this);
        TicTacToe.setGrid(index);
        moves++;
        setMsgField();
    }
    // sprawdzenie wygranej i ustawienie komunikatu gry
    public void setMsgField(){
        if (TicTacToe.isWinner()){
            msg.setText("Wygrana \"" + TicTacToe.getPlayer()+"\"");
            for (JButton b : gridField) b.removeActionListener(this);
            TicTacToe.incScore();
            refreshScore();
        }
        else {
            TicTacToe.changePlayer();
            msg.setText( (moves<9) ?
                    "Tura \"" + TicTacToe.getPlayer()+"\"" :
                    "Remis");
        }
    }
    // odświeża wyświetlany wynik
    public void refreshScore(){
        score.setText("'X' : "+TicTacToe.getScoreX()+
                    "            WYNIKI          "+
                    "'O' : "+TicTacToe.getScoreY());
    }
    // początkowa inicjalizacja pól gry i etykiet
    public void initFields(){
        for (int i = 0; i<9; i++){
            gridField[i] = new JButton();
            gridField[i].addActionListener(this);
            gridField[i].setActionCommand(i+"");
        }
        refreshScore();
        setMsgField();
    }
    // reset pól gry
    public void restartGame(){
        for (JButton b : gridField){
            b.addActionListener(this);
            b.setIcon(null);
            TicTacToe.clearGrid();
        }
        moves = 0;
        setMsgField();
    }
    // wstawienie komponentów do okna i ich ustawienia
    public void showGrid(){

        JPanel north = new JPanel();
        north.setBackground(Color.BLACK);
        north.setLayout(new GridLayout(2,1));

        score = new JLabel();
        score.setFont(new Font("Arial", Font.ITALIC, 24));
        score.setForeground(Color.CYAN);

        msg = new JLabel();
        msg.setForeground(Color.YELLOW);
        msg.setHorizontalAlignment(JTextField.CENTER);
        msg.setFont(new Font("Arial", Font.ITALIC, 24));

        north.add(score);
        north.add(msg);

        JPanel board = new JPanel();
        board.setLayout(new GridLayout(3,3));

        gridField = new JButton[9];
        initFields();

        for (JButton b : gridField) board.add(b);

        JButton restart = new JButton("Restart");
        restart.addActionListener(e -> restartGame());

        add(north,BorderLayout.NORTH);
        add(board,BorderLayout.CENTER);
        add(restart,BorderLayout.SOUTH);
    }
}
