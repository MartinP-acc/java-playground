package conretepiece;

import chess.ChessPiece;

public class WhiteQueen extends ChessPiece {

    public WhiteQueen(String name) {
        super(name, "8", "d", "white");
    }

    public WhiteQueen(String name, String numberPosition, String letterPosition) {
        super(name, numberPosition, letterPosition, "white");
    }
}
