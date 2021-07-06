import java.awt.*;
import java.util.LinkedList;

public class Boggle {

    char[][] board;
    String word;
    int index;                      //index of the letter to be checked
    LinkedList<Point> usedLetters;  //save x,y used letters

    public Boggle(final char[][] board, final String word) {
        this.board = board;
        this.word = word;
        this.index = 0;
        this.usedLetters = new LinkedList<>();
    }

    private boolean checkNext(){       //seek next letter in board 3x3 size
        if (index==word.length()) return true; //return true if all letters are found
        for (int y=usedLetters.peek().y-1 ; y<=usedLetters.peek().y+1; y++) {
            if (y<0 || y>board.length-1) continue; // ignore if y out of bound
            for (int x = usedLetters.peek().x-1; x <= usedLetters.peek().x+1; x++) {
                if (x<0 || x>board[y].length-1) continue; //ignore if x out of bound
                if (word.charAt(index) == board[y][x] && !usedLetters.contains(new Point(x,y))) {  //if letter is found and don't repeats
                    usedLetters.push(new Point(x, y));  //save x,y this letter
                    index++;                            //set index +1
                    if (checkNext()){                   //check next letter
                        return true;
                    }else{
                        index--;
                        usedLetters.pop();              //remove this letter point
                    }
                }
            }
        }
        return false;
    }

    public boolean check() {                    //seek first letter
        for (int y=0 ; y<board.length; y++){
            for (int x=0; x<board[0].length; x++){
                if (word.charAt(index)==board[y][x]){
                    usedLetters.push(new Point(x,y));  //save x,y - first letter
                    index++;                           //set index +1
                    if (checkNext()){
                        return true;
                    }else {
                        index--;
                        usedLetters.pop();   //remove first letter point
                    }
                }
            }
        }
        return false;
    }
}
