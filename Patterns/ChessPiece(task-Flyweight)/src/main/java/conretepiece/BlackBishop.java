package conretepiece;

import chess.ChessPiece;

public class BlackBishop extends ChessPiece {

    public BlackBishop(String name) {
        super(name, "1", "c", "black");
    }

    public BlackBishop(String name, String numberPosition, String letterPosition) {
        super(name, numberPosition, letterPosition, "black");
    }
}
