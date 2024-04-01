package pieces;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece{
    public Queen(Position position, Image image, Game game, char color) {
        super(position, image, game, color);
    }

    @Override
    public List<Position> getPossibleMoves() {
        int x = this.position.X;
        int y = this.position.Y;
        List<Position> moves = new ArrayList<>();
        while (x<8 && y<8){
            x+=1;
            y+=1;
            moves.add(new Position(x,y));
        }
        x = this.position.X;
        y = this.position.Y;
        while (x>1 && y>1){
            x-=1;
            y-=1;
            moves.add(new Position(x,y));
        }
        x = this.position.X;
        y = this.position.Y;
        while (x<8 && y>1){
            x+=1;
            y-=1;
            moves.add(new Position(x,y));
        }
        x = this.position.X;
        y = this.position.Y;
        while (x>1 && y<8){
            x-=1;
            y+=1;
            moves.add(new Position(x,y));
        }
        x = this.position.X;
        y = this.position.Y;
        while (x<8){
            x+=1;
            moves.add(new Position(x,y));
        }
        x = this.position.X;
        y = this.position.Y;
        while (x>1){
            x-=1;
            moves.add(new Position(x,y));
        }
        x = this.position.X;
        y = this.position.Y;
        while (y<8){
            y+=1;
            moves.add(new Position(x,y));
        }
        x = this.position.X;
        y = this.position.Y;
        while (y>1){
            y-=1;
            moves.add(new Position(x,y));
        }
        return moves;
    }
}
