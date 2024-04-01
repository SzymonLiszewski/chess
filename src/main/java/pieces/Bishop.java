package pieces;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{
    public Bishop(Position position, Image image, Game game) {
        super(position, image, game);
    }

    @Override
    public void Move(Position position) {
        Boolean isPossible = false;
        for (Position p : this.getPossibleMoves()){
            if (position.getX() == p.getX() && position.getY() == p.getY()){
                isPossible = true;
                break;
            }
        }
        if (isPossible){
            System.out.println("test");
            game.pieces.remove(this.position);
            this.position.X = position.getX();
            this.position.Y = position.getY();
            game.pieces.put(position,this);
        }
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
        return moves;
    }
}
