package conretepiece;

import chess.ChessPiece;

public class BlackKing extends ChessPiece {

    public BlackKing(String name) {
        super(name, "1", "e", "black");
    }

    public BlackKing(String name, String numberPosition, String letterPosition) {
        super(name, numberPosition, letterPosition, "black");
    }
}
