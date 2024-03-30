import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import pieces.*;
public class Game {
    List<Piece> pieces;

    public Game() {
        pieces = new ArrayList<>();
        pieces.add(new Bishop(new Position(3,1),new Image(getClass().getResource("/images/bB.png").toExternalForm())));
    }
}
