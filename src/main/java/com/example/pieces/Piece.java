package com.example.pieces;
import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.List;

public abstract class Piece implements Serializable {
    Position position;
    String image;
    Game game;
    char color;
    public abstract List<Position> getPossibleMoves();

    public Piece(Position position, String image, Game game, char color) {
        this.position = position;
        this.image = image;
        this.game = game;
        this.color = color;
    }

    public Position getPosition() {
        return position;
    }
    public void Move(Position position, Boolean check){
        Boolean isPossible = false;
        for (Position p : this.getPossibleMoves()){
            if (position.getX() == p.getX() && position.getY() == p.getY()){
                isPossible = true;
                break;
            }
        }
        if (isPossible){
            if (this.game.onMove == 'w'){
                game.whitePieces.remove(this.position);
                if (game.blackPieces.get(position)!=null){game.blackPieces.remove(position);}
                this.position.X = position.getX();
                this.position.Y = position.getY();
                game.whitePieces.put(position,this);
                this.game.onMove = 'b';
            }
            else if (this.game.onMove == 'b'){
                game.blackPieces.remove(this.position);
                if (game.whitePieces.get(position)!=null){game.whitePieces.remove(position);}
                this.position.X = position.getX();
                this.position.Y = position.getY();
                game.blackPieces.put(position,this);
                this.game.onMove = 'w';
            }
            if(this instanceof Pawn){((Pawn) this).isFirstMove = false;}
        }
        if (check){game.checkIfEnd();}
    };

    public String getImage() {
        return image;
    }
}
