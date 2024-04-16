package com.example.pieces;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class King extends Piece{
    public King(Position position, String image, Game game, char color) {
        super(position, image, game, color);
    }

    @Override
    public List<Position> getPossibleMoves() {
        int x = this.position.X;
        int y = this.position.Y;
        List<Position> moves = new ArrayList<>();
        if (this.color == 'w'){
            if (x+1<=8){
                if (this.game.whitePieces.get(new Position(x+1, y))==null){
                    moves.add(new Position(x+1, y));
                }
            }
            if (x+1<=8 && y+1<=8){
                if (this.game.whitePieces.get(new Position(x+1, y+1))==null){
                    moves.add(new Position(x+1, y+1));
                }
            }
            if (y+1<=8){
                if (this.game.whitePieces.get(new Position(x, y+1))==null){
                    moves.add(new Position(x, y+1));
                }
            }
            if (x-1>=1){
                if (this.game.whitePieces.get(new Position(x-1, y))==null){
                    moves.add(new Position(x-1, y));
                }
            }
            if (x-1>=1 && y+1<=8){
                if (this.game.whitePieces.get(new Position(x-1, y+1))==null){
                    moves.add(new Position(x-1, y+1));
                }
            }
            if (x-1>=1 && y-1>=1){
                if (this.game.whitePieces.get(new Position(x-1, y-1))==null){
                    moves.add(new Position(x-1, y-1));
                }
            }
            if (y-1>=1){
                if (this.game.whitePieces.get(new Position(x, y-1))==null){
                    moves.add(new Position(x, y-1));
                }
            }
            if (x+1<=8 && y-1>=1){
                if (this.game.whitePieces.get(new Position(x+1, y-1))==null){
                    moves.add(new Position(x+1, y-1));
                }
            }
            /*for (Map.Entry<Position, Piece> element : game.blackPieces.entrySet()){
                for (Position p : element.getValue().getPossibleMoves()){
                    moves.removeIf(el -> el.equals(p));
                }
            }*/
        }
        else if (this.color == 'b'){
            if (x+1<=8){
                if (this.game.blackPieces.get(new Position(x+1, y))==null){
                    moves.add(new Position(x+1, y));
                }
            }
            if (x+1<=8 && y+1<=8){
                if (this.game.blackPieces.get(new Position(x+1, y+1))==null){
                    moves.add(new Position(x+1, y+1));
                }
            }
            if (y+1<=8){
                if (this.game.blackPieces.get(new Position(x, y+1))==null){
                    moves.add(new Position(x, y+1));
                }
            }
            if (x-1>=1){
                if (this.game.blackPieces.get(new Position(x-1, y))==null){
                    moves.add(new Position(x-1, y));
                }
            }
            if (x-1>=1 && y+1<=8){
                if (this.game.blackPieces.get(new Position(x-1, y+1))==null){
                    moves.add(new Position(x-1, y+1));
                }
            }
            if (x-1>=1 && y-1>=1){
                if (this.game.blackPieces.get(new Position(x-1, y-1))==null){
                    moves.add(new Position(x-1, y-1));
                }
            }
            if (y-1>=1){
                if (this.game.blackPieces.get(new Position(x, y-1))==null){
                    moves.add(new Position(x, y-1));
                }
            }
            if (x+1<=8 && y-1>=1){
                if (this.game.blackPieces.get(new Position(x+1, y-1))==null){
                    moves.add(new Position(x+1, y-1));
                }
            }
            /*(for (Map.Entry<Position, Piece> element : game.whitePieces.entrySet()){
                for (Position p : element.getValue().getPossibleMoves()){
                    moves.removeIf(el -> el.equals(p));
                }
            }*/
        }

        return moves;
    }
}
