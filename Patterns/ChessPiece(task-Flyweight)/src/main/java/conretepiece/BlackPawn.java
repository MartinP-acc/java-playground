package conretepiece;

import chess.ChessPiece;

public class BlackPawn extends ChessPiece {

    public BlackPawn(String name) {
        super(name, "2", "a", "black");
    }

    public BlackPawn(String name, String numberPosition, String letterPosition) {
        super(name, numberPosition, letterPosition, "black");
    }
}
