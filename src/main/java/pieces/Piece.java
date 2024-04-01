package pieces;
import javafx.scene.image.Image;

import javax.management.monitor.GaugeMonitor;
import java.util.List;

public abstract class Piece {
    Position position;
    Image image;
    Game game;
    public abstract List<Position> getPossibleMoves();

    public Piece(Position position, Image image, Game game) {
        this.position = position;
        this.image = image;
        this.game = game;
    }

    public Position getPosition() {
        return position;
    }
    public void Move(Position position){
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
    };

    public Image getImage() {
        return image;
    }
}
