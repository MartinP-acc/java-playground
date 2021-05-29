package conretepiece;

import chess.ChessPiece;

public class BlackQueen extends ChessPiece {

    public BlackQueen(String name) {
        super(name, "1", "d", "black");
    }

    public BlackQueen(String name, String numberPosition, String letterPosition) {
        super(name, numberPosition, letterPosition, "black");
    }

}
