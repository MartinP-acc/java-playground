package conretepiece;

import chess.ChessPiece;

public class BlackRook extends ChessPiece {

    public BlackRook(String name) {
        super(name, "1", "a", "black");
    }

    public BlackRook(String name, String numberPosition, String letterPosition) {
        super(name, numberPosition, letterPosition, "black");
    }
}
