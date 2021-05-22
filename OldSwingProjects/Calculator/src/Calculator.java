import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

    GridBagLayout layout;
    GridBagConstraints gBC;
    JTextField mainDisplay;
    JTextField resultDisp;
    String[] bNames = {"1/x","pow","sqrt","mod","+","-","*","/"};

    public void initFrame(){
        setSize(600, 700);
        setTitle("Kalkulator");

        layout = new GridBagLayout();
        layout.rowHeights = new int[7];
        layout.columnWidths = new int[5];
        gBC = new GridBagConstraints();
        gBC.fill = GridBagConstraints.BOTH;
        gBC.weightx = 1;
        gBC.weighty = 1;
        gBC.insets = new Insets(0,0,0,0);

        setLayout(layout);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JButton setButton(String name, int posX, int posY, int width){
        JButton jb = new JButton(name);
        jb.addActionListener(this);
        jb.setFont(new Font("Arial", Font.PLAIN,24));
        jb.setBackground(Color.BLACK);
        jb.setForeground(Color.white);
        jb.setBorderPainted(false);
        jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jb.setForeground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jb.setForeground(Color.WHITE);
            }
        });
        gBC.gridx = posX;
        gBC.gridy = posY;
        gBC.gridwidth = width;
        layout.setConstraints(jb,gBC);
        return jb;
    }

    public void addDigitButtons(){
        add(setButton("0",1,7,1));
        int digit = 1;
        for (int y=5; y>2; y--){
            for (int x=0; x<3; x++){
                add(setButton(digit+"",x,y,1 ));
                digit++;
            }
        }
    }

    public void addOperatorButtons(){
        add(setButton("+/-",0,7,1));
        add(setButton(",",2,7,1 ));
        add(setButton("=",3,7,2));
        int index=0;
        for (int y=2 ; y<6; y++){
            for (int x=3; x<5; x++){
                add(setButton(bNames[index],x,y,1 ));
                index++;
            }
        }
    }

    public void addDisplay(){
        gBC.gridx = 0;
        gBC.gridy = 0;
        gBC.gridwidth = 5;

        resultDisp = new JTextField();
        resultDisp.setBackground(Color.BLACK);
        resultDisp.setForeground(Color.GRAY);
        resultDisp.setEditable(false);
        resultDisp.setBorder(null);
        resultDisp.setFont(new Font("Arial",Font.PLAIN,26));
        layout.setConstraints(resultDisp,gBC);
        add(resultDisp);

        gBC.gridy = 1;
        mainDisplay= new JTextField();
        mainDisplay.setEditable(true);
        mainDisplay.setBackground(new Color(0x151515));
        mainDisplay.setForeground(Color.WHITE);
        mainDisplay.setBorder(null);
        mainDisplay.setHorizontalAlignment(JTextField.RIGHT);
        mainDisplay.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
                    mainDisplay.setEditable(true);
                    CalcFunction.putDigit(e.getKeyChar()+"");
                } else if (e.getKeyChar() == '.') {
                    mainDisplay.setText(CalcFunction.coma());
                }else {
                    mainDisplay.setEditable(false);
                }
            }
        });
        mainDisplay.setFont(new Font("Arial",Font.PLAIN,30));
        layout.setConstraints(mainDisplay,gBC);
        add(mainDisplay);
    }

    public void addOtherButtons(){
        add(setButton("CLEAR",1,2,1));
        add(setButton("UNDO ",2,2,1));
        add(setButton("EXIT ",0,2,1));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int digit=0; digit<10; digit++){
            if (e.getActionCommand().equals(digit+"")){
                mainDisplay.setText(CalcFunction.putDigit(digit+""));
            }
        }

        for (int i=3; i<8;i++){
            if (e.getActionCommand().equals(bNames[i])){
                resultDisp.setText(CalcFunction.basicOperator(bNames[i]));
                mainDisplay.setText("");
            }
        }

        for (int i=0; i<3;i++){
            if (e.getActionCommand().equals(bNames[i])){
                mainDisplay.setText(CalcFunction.mathFunc(bNames[i]));
            }
        }

        switch (e.getActionCommand()){
            case "+/-" -> mainDisplay.setText(CalcFunction.toOpposite());
            case "," -> mainDisplay.setText(CalcFunction.coma());
            case "UNDO " -> mainDisplay.setText(CalcFunction.deleteDigit());
            case "CLEAR" -> {
                mainDisplay.setText(CalcFunction.resetVal());
                resultDisp.setText(CalcFunction.resetReadyVal());
            }
            case "=" -> {
                resultDisp.setText(CalcFunction.equal());
                mainDisplay.setText(CalcFunction.getStrValue());
            }
            case "EXIT " -> System.exit(0);
        }
    }

    public static void main(String[] args) {
        Calculator app = new Calculator();

        app.initFrame();
        app.addDisplay();
        app.addOtherButtons();
        app.addDigitButtons();
        app.addOperatorButtons();

        app.setVisible(true);

    }
}
