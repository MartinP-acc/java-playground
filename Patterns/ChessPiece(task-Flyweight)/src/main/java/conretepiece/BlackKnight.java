package conretepiece;

import chess.ChessPiece;

public class BlackKnight extends ChessPiece {

    public BlackKnight(String name) {
        super(name, "1", "b", "black");
    }

    public BlackKnight(String name, String numberPosition, String letterPosition) {
        super(name, numberPosition, letterPosition, "black");
    }
}
