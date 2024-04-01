package pieces;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{
    public Pawn(Position position, Image image, Game game) {
        super(position, image, game);
    }

    @Override
    public List<Position> getPossibleMoves() {
        int x = this.position.X;
        int y = this.position.Y;
        List<Position> moves = new ArrayList<>();
        if (y+1<=8){
            moves.add(new Position(x,y+1));
        }
        return moves;
    }
}
