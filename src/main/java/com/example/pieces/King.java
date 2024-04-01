package com.example.pieces;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{
    public King(Position position, Image image, Game game, char color) {
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
        }

        return moves;
    }
}