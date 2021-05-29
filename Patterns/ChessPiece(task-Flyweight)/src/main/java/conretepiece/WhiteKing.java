package conretepiece;

import chess.ChessPiece;

public class WhiteKing extends ChessPiece {

    public WhiteKing(String name) {
        super(name, "8", "e", "white");
    }

    public WhiteKing(String name, String numberPosition, String letterPosition) {
        super(name, numberPosition, letterPosition, "white");
    }
}
