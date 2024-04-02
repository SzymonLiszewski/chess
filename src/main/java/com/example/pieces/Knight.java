package com.example.pieces;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{
    public Knight(Position position, Image image, Game game, char color) {
        super(position, image, game, color);
    }

    @Override
    public List<Position> getPossibleMoves() {
        int x = this.position.X;
        int y = this.position.Y;
        List<Position> moves = new ArrayList<>();
        if(x+2<=8){
            if (y+1<=8){
                if (this.game.whitePieces.get(new Position(x+2,y+1))!=null){
                    if (this.color == 'b'){moves.add(new Position(x+2,y+1));}
                }
                else if (this.game.blackPieces.get(new Position(x+2,y+1))!=null){
                    if (this.color == 'w'){moves.add(new Position(x+2,y+1));}
                }
                else{
                    moves.add(new Position(x+2,y+1));
                }
            }
            if (y-1>=1){
                if (this.game.whitePieces.get(new Position(x+2,y-1))!=null){
                    if (this.color == 'b'){moves.add(new Position(x+2,y-1));}
                }
                else if (this.game.blackPieces.get(new Position(x+2,y-1))!=null) {
                    if (this.color == 'w') {moves.add(new Position(x + 2, y - 1));}
                }
                else{
                    moves.add(new Position(x+2,y-1));
                }
            }
        }
        if (x-2>=1){
            if (y+1<=8){
                if (this.game.whitePieces.get(new Position(x-2,y+1))!=null){
                    if (this.color == 'b'){moves.add(new Position(x-2,y+1));}
                }
                else if (this.game.blackPieces.get(new Position(x-2,y+2))!=null) {
                    if (this.color == 'w') {moves.add(new Position(x - 2, y + 1));}
                }
                else{
                    moves.add(new Position(x-2,y+1));
                }
            }
            if (y-1>=1){
                if (this.game.whitePieces.get(new Position(x-2,y-1))!=null){
                    if (this.color == 'b'){moves.add(new Position(x-2,y-1));}
                }
                else if (this.game.blackPieces.get(new Position(x-2,y-2))!=null) {
                    if (this.color == 'w') {moves.add(new Position(x - 2, y - 1));}
                }
                else{
                    moves.add(new Position(x-2,y-1));
                }
            }
        }
        if(y+2<=8){
            if (x+1<=8){
                if (this.game.whitePieces.get(new Position(x+1,y+2))!=null){
                    if (this.color == 'b'){moves.add(new Position(x+1,y+2));}
                }
                else if (this.game.blackPieces.get(new Position(x+1,y+2))!=null) {
                    if (this.color == 'w') {moves.add(new Position(x+1,y+2));}
                }
                else{
                    moves.add(new Position(x+1,y+2));
                }
            }
            if (x-1>=1){
                if (this.game.whitePieces.get(new Position(x-1,y+2))!=null){
                    if (this.color == 'b'){moves.add(new Position(x-1,y+2));}
                }
                else if (this.game.blackPieces.get(new Position(x-1,y+2))!=null) {
                    if (this.color == 'w') {moves.add(new Position(x-1,y+2));}
                }
                else{
                    moves.add(new Position(x-1,y+2));
                }
            }
        }
        if (y-2>=1){
            if (x+1<=8){
                if (this.game.whitePieces.get(new Position(x+1,y-2))!=null){
                    if (this.color == 'b'){moves.add(new Position(x+1,y-2));}
                }
                else if (this.game.blackPieces.get(new Position(x+1,y-2))!=null) {
                    if (this.color == 'w') {moves.add(new Position(x+1,y-2));}
                }
                else{
                    moves.add(new Position(x+1,y-2));
                }
            }
            if (x-1>=1){
                if (this.game.whitePieces.get(new Position(x-1,y-2))!=null){
                    if (this.color == 'b'){moves.add(new Position(x-1,y-2));}
                }
                else if (this.game.blackPieces.get(new Position(x-1,y-2))!=null) {
                    if (this.color == 'w') {moves.add(new Position(x-1,y-2));}
                }
                else{
                    moves.add(new Position(x-1,y-2));
                }
            }
        }
        return moves;
    }
}
