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
        pieces.put(new Position(6,1),new Bishop(new Position(6,1),new Image(getClass().getResource("/images/bB.png").toExternalForm()), this));
        pieces.put(new Position(8,1),new Rook(new Position(8,1),new Image(getClass().getResource("/images/bR.png").toExternalForm()), this));
        pieces.put(new Position(1,1),new Rook(new Position(1,1),new Image(getClass().getResource("/images/bR.png").toExternalForm()), this));
        pieces.put(new Position(2,1),new Knight(new Position(2,1),new Image(getClass().getResource("/images/bN.png").toExternalForm()), this));
        pieces.put(new Position(7,1),new Knight(new Position(7,1),new Image(getClass().getResource("/images/bN.png").toExternalForm()), this));
        pieces.put(new Position(4,1),new Queen(new Position(4,1),new Image(getClass().getResource("/images/bQ.png").toExternalForm()), this));
        pieces.put(new Position(5,1),new King(new Position(5,1),new Image(getClass().getResource("/images/bK.png").toExternalForm()), this));
        pieces.put(new Position(1,2),new Pawn(new Position(1,2),new Image(getClass().getResource("/images/bP.png").toExternalForm()), this));
        pieces.put(new Position(2,2),new Pawn(new Position(2,2),new Image(getClass().getResource("/images/bP.png").toExternalForm()), this));
        pieces.put(new Position(3,2),new Pawn(new Position(3,2),new Image(getClass().getResource("/images/bP.png").toExternalForm()), this));
        pieces.put(new Position(4,2),new Pawn(new Position(4,2),new Image(getClass().getResource("/images/bP.png").toExternalForm()), this));
        pieces.put(new Position(5,2),new Pawn(new Position(5,2),new Image(getClass().getResource("/images/bP.png").toExternalForm()), this));
        pieces.put(new Position(6,2),new Pawn(new Position(6,2),new Image(getClass().getResource("/images/bP.png").toExternalForm()), this));
        pieces.put(new Position(7,2),new Pawn(new Position(7,2),new Image(getClass().getResource("/images/bP.png").toExternalForm()), this));
        pieces.put(new Position(8,2),new Pawn(new Position(8,2),new Image(getClass().getResource("/images/bP.png").toExternalForm()), this));
    }
}
