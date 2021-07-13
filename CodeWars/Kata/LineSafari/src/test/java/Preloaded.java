public class Preloaded {


    public static char[][] makeGrid(String[] strings) {
        char[][] grid = new char[strings.length][strings[0].length()];
        for (int x=0; x< grid.length; x++){
            for (int y=0; y< grid[x].length; y++){
                grid[x][y]=strings[x].charAt(y);
            }
        }
        return grid;
    }

    public static void showGrid(char[][] grid) {
        for (char[] x : grid){
            System.out.println();
            for (char y : x){
                System.out.print(y);
            }
        }
    }
}
