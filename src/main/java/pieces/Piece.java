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
    public abstract void Move(Position position);

    public Image getImage() {
        return image;
    }
}
