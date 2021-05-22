public class TicTacToe {

    private static String player = "X";
    private static final String[] grid = new String[9];
    private static int scoreX = 0, scoreY = 0;

    public static void main(String[] args) {
        GameGUI game = new GameGUI();
        clearGrid();
        game.runGUI();
    }
    // dodanie punktu dla wygranego
    public static void incScore(){
        if (player.equals("X")) scoreX++;
        else scoreY++;
    }
    // gettery punktacji
    public static int getScoreX() {
        return scoreX;
    }
    public static int getScoreY() {
        return scoreY;
    }
    // 'czyści' tablicę (wypełnia cyframi 0-8)
    public static void clearGrid(){
        for (int i=0; i<9; i++){
            grid[i] = i+"";
        }
    }
    // wstawia do tablicy znak X lub O
    public static void setGrid(int index){
        grid[index] = player;
    }
    // zmiana gracza
    public static void changePlayer(){
        if (player.equals("O")) player = "X";
        else player = "O";
    }
    //zwrot aktualnego gracza
    public static String getPlayer(){
        return player;
    }
    // metoda sprawdzająca czy doszło do wygranej
    public static boolean isWinner (){
        return  (grid[0].equals(grid[3]) & grid[3].equals(grid[6])) |
                (grid[1].equals(grid[4]) & grid[4].equals(grid[7])) |
                (grid[2].equals(grid[5]) & grid[5].equals(grid[8])) |
                (grid[0].equals(grid[1]) & grid[1].equals(grid[2])) |
                (grid[3].equals(grid[4]) & grid[4].equals(grid[5])) |
                (grid[6].equals(grid[7]) & grid[7].equals(grid[8])) |
                (grid[0].equals(grid[4]) & grid[4].equals(grid[8])) |
                (grid[2].equals(grid[4]) & grid[4].equals(grid[6]));
    }
}
