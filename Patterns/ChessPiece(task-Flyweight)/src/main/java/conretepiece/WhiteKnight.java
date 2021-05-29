package conretepiece;

import chess.ChessPiece;

public class WhiteKnight extends ChessPiece {

    public WhiteKnight(String name) {
        super(name, "8", "b", "white");
    }

    public WhiteKnight(String name, String numberPosition, String letterPosition) {
        super(name, numberPosition, letterPosition, "white");
    }
}
