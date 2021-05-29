package conretepiece;

import chess.ChessPiece;

public class WhiteBishop extends ChessPiece {

    public WhiteBishop(String name) {
        super(name, "8", "c", "white");
    }

    public WhiteBishop(String name, String numberPosition, String letterPosition) {
        super(name, numberPosition, letterPosition, "white");
    }
}
