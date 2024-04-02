package com.example.pieces;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{
    boolean isFirstMove;
    public Pawn(Position position, Image image, Game game, char color) {
        super(position, image, game, color);
        this.isFirstMove = true;
    }

    @Override
    public List<Position> getPossibleMoves() {
        int x = this.position.X;
        int y = this.position.Y;
        List<Position> moves = new ArrayList<>();
        if (isFirstMove){
            if (this.color == 'b'){moves.add(new Position(x,y+2));}
            if (this.color == 'w'){moves.add(new Position(x,y-2));}
        }
        if (color == 'b'){
            if (y+1<=8){
                moves.add(new Position(x,y+1));
            }
        }
        else if (color == 'w'){
            if (y-1>=1){
                moves.add(new Position(x,y-1));
            }
        }
        if (color == 'b'){
            if (y+1<=8 && x+1<=8 && this.game.whitePieces.get(new Position(x+1, y+1))!=null){
                moves.add(new Position(x+1,y+1));
            }
            if (y+1<=8 && x-1<=8 && this.game.whitePieces.get(new Position(x-1, y+1))!=null){
                moves.add(new Position(x-1,y+1));
            }
        }
        if (color == 'w'){
            if (y-1<=8 && x+1<=8 && this.game.blackPieces.get(new Position(x+1, y-1))!=null){
                moves.add(new Position(x+1,y-1));
            }
            if (y-1<=8 && x-1<=8 && this.game.blackPieces.get(new Position(x-1, y-1))!=null){
                moves.add(new Position(x-1,y-1));
            }
        }
        return moves;
    }
}
