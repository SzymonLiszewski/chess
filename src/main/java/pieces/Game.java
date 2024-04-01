package pieces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.image.Image;
import pieces.*;
public class Game {
    public Map<Position, Piece> pieces;

    public Game() {
        pieces = new HashMap<>();
        pieces.put(new Position(3,1),new Bishop(new Position(3,1),new Image(getClass().getResource("/images/bB.png").toExternalForm()), this));
    }
}
