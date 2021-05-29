package conretepiece;

import chess.ChessPiece;

public class WhiteRook extends ChessPiece {
    public WhiteRook(String name) {
        super(name, "8", "a", "white");
    }

    public WhiteRook(String name, String numberPosition, String letterPosition) {
        super(name, numberPosition, letterPosition, "white");
    }
}
