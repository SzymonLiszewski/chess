package com.example.pieces;
import javafx.scene.image.Image;
import org.apache.commons.lang3.SerializationUtils;

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
                Position prev = new Position(this.position.X, this.position.Y);
                this.position.X = position.getX();
                this.position.Y = position.getY();
                game.whitePieces.put(position,this);
                if (check && game.checkIfAttacked()){
                    game.whitePieces.remove(this.position);
                    this.position = prev;
                    game.whitePieces.put(this.position,this);
                }
                else{
                    this.game.onMove = 'b';
                }
            }
            else if (this.game.onMove == 'b'){
                game.blackPieces.remove(this.position);
                if (game.whitePieces.get(position)!=null){game.whitePieces.remove(position);}
                Position prev = new Position(this.position.X, this.position.Y);
                this.position.X = position.getX();
                this.position.Y = position.getY();
                game.blackPieces.put(position,this);
                if (check && game.checkIfAttacked()){
                    game.blackPieces.remove(this.position);
                    this.position = prev;
                    game.blackPieces.put(this.position,this);
                }
                else{
                    this.game.onMove = 'w';
                }
            }
            if(this instanceof Pawn){((Pawn) this).isFirstMove = false;}
        }
        if (check){game.checkIfEnd();}
    };

    public String getImage() {
        return image;
    }
}
