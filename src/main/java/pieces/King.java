package pieces;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{
    public King(Position position, Image image, Game game) {
        super(position, image, game);
    }

    @Override
    public List<Position> getPossibleMoves() {
        int x = this.position.X;
        int y = this.position.Y;
        List<Position> moves = new ArrayList<>();
        if (x+1<=8){moves.add(new Position(x+1, y));}
        if (x+1<=8 && y+1<=8){moves.add(new Position(x+1, y+1));}
        if (y+1<=8){moves.add(new Position(x, y+1));}
        if (x-1>=1){moves.add(new Position(x-1, y));}
        if (x-1>=1 && y+1<=8){moves.add(new Position(x-1, y+1));}
        if (x-1>=1 && y-1>=1){moves.add(new Position(x-1, y-1));}
        if (y-1>=1){moves.add(new Position(x, y-1));}
        if (x+1<=8 && y-1>=1){moves.add(new Position(x+1, y-1));}
        return moves;
    }
}
