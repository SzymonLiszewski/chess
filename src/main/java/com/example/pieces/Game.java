package com.example.pieces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.image.Image;
import com.example.pieces.*;
import javafx.scene.image.ImageView;

public class Game {
    public Map<Position, Piece> whitePieces;
    public Map<Position, Piece> blackPieces;
    public char onMove;
    public boolean isEnd;
    public char winner;

    public Game() {
        whitePieces = new HashMap<>();
        blackPieces = new HashMap<>();
        blackPieces.put(new Position(3,1),new Bishop(new Position(3,1),new Image(getClass().getResource("/images/bB.png").toExternalForm()), this, 'b'));
        blackPieces.put(new Position(6,1),new Bishop(new Position(6,1),new Image(getClass().getResource("/images/bB.png").toExternalForm()), this, 'b'));
        blackPieces.put(new Position(8,1),new Rook(new Position(8,1),new Image(getClass().getResource("/images/bR.png").toExternalForm()), this, 'b'));
        blackPieces.put(new Position(1,1),new Rook(new Position(1,1),new Image(getClass().getResource("/images/bR.png").toExternalForm()), this, 'b'));
        blackPieces.put(new Position(2,1),new Knight(new Position(2,1),new Image(getClass().getResource("/images/bN.png").toExternalForm()), this, 'b'));
        blackPieces.put(new Position(7,1),new Knight(new Position(7,1),new Image(getClass().getResource("/images/bN.png").toExternalForm()), this, 'b'));
        blackPieces.put(new Position(4,1),new Queen(new Position(4,1),new Image(getClass().getResource("/images/bQ.png").toExternalForm()), this, 'b'));
        blackPieces.put(new Position(5,1),new King(new Position(5,1),new Image(getClass().getResource("/images/bK.png").toExternalForm()), this, 'b'));
        blackPieces.put(new Position(1,2),new Pawn(new Position(1,2),new Image(getClass().getResource("/images/bP.png").toExternalForm()), this, 'b'));
        blackPieces.put(new Position(2,2),new Pawn(new Position(2,2),new Image(getClass().getResource("/images/bP.png").toExternalForm()), this, 'b'));
        blackPieces.put(new Position(3,2),new Pawn(new Position(3,2),new Image(getClass().getResource("/images/bP.png").toExternalForm()), this, 'b'));
        blackPieces.put(new Position(4,2),new Pawn(new Position(4,2),new Image(getClass().getResource("/images/bP.png").toExternalForm()), this, 'b'));
        blackPieces.put(new Position(5,2),new Pawn(new Position(5,2),new Image(getClass().getResource("/images/bP.png").toExternalForm()), this, 'b'));
        blackPieces.put(new Position(6,2),new Pawn(new Position(6,2),new Image(getClass().getResource("/images/bP.png").toExternalForm()), this, 'b'));
        blackPieces.put(new Position(7,2),new Pawn(new Position(7,2),new Image(getClass().getResource("/images/bP.png").toExternalForm()), this, 'b'));
        blackPieces.put(new Position(8,2),new Pawn(new Position(8,2),new Image(getClass().getResource("/images/bP.png").toExternalForm()), this, 'b'));
        whitePieces.put(new Position(3,8),new Bishop(new Position(3,8),new Image(getClass().getResource("/images/wB.png").toExternalForm()), this, 'w'));
        whitePieces.put(new Position(6,8),new Bishop(new Position(6,8),new Image(getClass().getResource("/images/wB.png").toExternalForm()), this, 'w'));
        whitePieces.put(new Position(8,8),new Rook(new Position(8,8),new Image(getClass().getResource("/images/wR.png").toExternalForm()), this, 'w'));
        whitePieces.put(new Position(1,8),new Rook(new Position(1,8),new Image(getClass().getResource("/images/wR.png").toExternalForm()), this, 'w'));
        whitePieces.put(new Position(2,8),new Knight(new Position(2,8),new Image(getClass().getResource("/images/wN.png").toExternalForm()), this, 'w'));
        whitePieces.put(new Position(7,8),new Knight(new Position(7,8),new Image(getClass().getResource("/images/wN.png").toExternalForm()), this, 'w'));
        whitePieces.put(new Position(4,8),new Queen(new Position(4,8),new Image(getClass().getResource("/images/wQ.png").toExternalForm()), this, 'w'));
        whitePieces.put(new Position(5,8),new King(new Position(5,8),new Image(getClass().getResource("/images/wK.png").toExternalForm()), this, 'w'));
        whitePieces.put(new Position(1,7),new Pawn(new Position(1,7),new Image(getClass().getResource("/images/wP.png").toExternalForm()), this, 'w'));
        whitePieces.put(new Position(2,7),new Pawn(new Position(2,7),new Image(getClass().getResource("/images/wP.png").toExternalForm()), this, 'w'));
        whitePieces.put(new Position(3,7),new Pawn(new Position(3,7),new Image(getClass().getResource("/images/wP.png").toExternalForm()), this, 'w'));
        whitePieces.put(new Position(4,7),new Pawn(new Position(4,7),new Image(getClass().getResource("/images/wP.png").toExternalForm()), this, 'w'));
        whitePieces.put(new Position(5,7),new Pawn(new Position(5,7),new Image(getClass().getResource("/images/wP.png").toExternalForm()), this, 'w'));
        whitePieces.put(new Position(6,7),new Pawn(new Position(6,7),new Image(getClass().getResource("/images/wP.png").toExternalForm()), this, 'w'));
        whitePieces.put(new Position(7,7),new Pawn(new Position(7,7),new Image(getClass().getResource("/images/wP.png").toExternalForm()), this, 'w'));
        whitePieces.put(new Position(8,7),new Pawn(new Position(8,7),new Image(getClass().getResource("/images/wP.png").toExternalForm()), this, 'w'));
        onMove = 'w';
        isEnd = false;
    }

    public Game deepCopy() {
        Game copy = new Game();
        copy.onMove = this.onMove;
        copy.winner = this.winner;
        copy.isEnd = this.isEnd;
        copy.whitePieces = new HashMap<>();
        for (Map.Entry<Position, Piece> entry : whitePieces.entrySet()) {
            copy.whitePieces.put(entry.getKey(), entry.getValue());
        }
        copy.blackPieces = new HashMap<>();
        for (Map.Entry<Position, Piece> entry : blackPieces.entrySet()) {
            copy.whitePieces.put(entry.getKey(), entry.getValue());
        }
        return copy;
    }
    public boolean checkIfAttacked(){
        King k = new King(new Position(5,8),new Image(getClass().getResource("/images/wK.png").toExternalForm()), this, 'w');
        if (onMove == 'w'){
            for (Map.Entry<Position, Piece> element : this.whitePieces.entrySet()){
                if (element.getValue() instanceof King){
                    k = (King)element.getValue();
                }
            }
            for (Map.Entry<Position, Piece> element : this.blackPieces.entrySet()){
                for (Position p : element.getValue().getPossibleMoves()){
                    if (p.equals(k.position)){
                        return true;
                    }
                }
            }
        }
        else if (onMove == 'b'){
            for (Map.Entry<Position, Piece> element : this.blackPieces.entrySet()){
                if (element.getValue() instanceof King){
                    k = (King)element.getValue();
                }
            }
            for (Map.Entry<Position, Piece> element : this.whitePieces.entrySet()){
                for (Position p : element.getValue().getPossibleMoves()){
                    if (p.equals(k.position)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void checkIfEnd(){
        if (checkIfAttacked() == false){return;}
        King k;
        List <Position> kingMoves = new ArrayList<>();
        Game cp = this.deepCopy();
        if (onMove == 'w'){
            for (Map.Entry<Position, Piece> element : this.whitePieces.entrySet()){
                if (element.getValue() instanceof King){
                    k = (King)element.getValue();
                    kingMoves = k.getPossibleMoves();
                }
            }
            for (Map.Entry<Position, Piece> element : this.blackPieces.entrySet()){
                for (Position p : element.getValue().getPossibleMoves()){
                    kingMoves.removeIf(el -> el.equals(p));
                }
            }
            if (kingMoves.isEmpty()){

                isEnd = true;
                System.out.println("mate");
            }
        }
        if (onMove == 'b'){
            for (Map.Entry<Position, Piece> element : this.blackPieces.entrySet()){
                if (element.getValue() instanceof King){
                    k = (King)element.getValue();
                    kingMoves = k.getPossibleMoves();
                }
            }
            for (Map.Entry<Position, Piece> element : this.whitePieces.entrySet()){
                for (Position p : element.getValue().getPossibleMoves()){
                    kingMoves.removeIf(el -> el == p);
                }
            }
            if (kingMoves.isEmpty()){
                isEnd = true;
            }
        }
    }
}
