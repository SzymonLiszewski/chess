package pieces;
import javafx.scene.image.Image;
import java.util.List;

public abstract class Piece {
    Position position;
    Image image;
    public abstract List<Position> getPossibleMoves();

    public Piece(Position position, Image image) {
        this.position = position;
        this.image = image;
    }

    public Position getPosition() {
        return position;
    }
    public abstract void Move(Position position);

    public Image getImage() {
        return image;
    }
}
