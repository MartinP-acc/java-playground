package conretepiece;

import chess.ChessPiece;

public class WhitePawn extends ChessPiece {

    public WhitePawn(String name) {
        super(name, "7", "a", "white");
    }

    public WhitePawn(String name, String numberPosition, String letterPosition) {
        super(name, numberPosition, letterPosition, "white");
    }
}
