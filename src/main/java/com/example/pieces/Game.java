package com.example.pieces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.example.pieces.*;

import org.apache.commons.lang3.SerializationUtils;

public class Game implements Serializable {
    public Map<Position, Piece> whitePieces;
    public Map<Position, Piece> blackPieces;
    public char onMove;
    public boolean isEnd;
    public char winner;

    public Game() {
        whitePieces = new HashMap<>();
        blackPieces = new HashMap<>();
        whitePieces.put(new Position(3,1),new Bishop(new Position(3,1), getClass().getResource("/images/wB.png").toExternalForm(), this, 'w'));
        whitePieces.put(new Position(6,1),new Bishop(new Position(6,1), getClass().getResource("/images/wB.png").toExternalForm(), this, 'w'));
        whitePieces.put(new Position(8,1),new Rook(new Position(8,1), getClass().getResource("/images/wR.png").toExternalForm(), this, 'w'));
        whitePieces.put(new Position(1,1),new Rook(new Position(1,1), getClass().getResource("/images/wR.png").toExternalForm(), this, 'w'));
        whitePieces.put(new Position(2,1),new Knight(new Position(2,1), getClass().getResource("/images/wN.png").toExternalForm(), this, 'w'));
        whitePieces.put(new Position(7,1),new Knight(new Position(7,1), getClass().getResource("/images/wN.png").toExternalForm(), this, 'w'));
        whitePieces.put(new Position(4,1),new Queen(new Position(4,1), getClass().getResource("/images/wQ.png").toExternalForm(), this, 'w'));
        whitePieces.put(new Position(5,1),new King(new Position(5,1), getClass().getResource("/images/wK.png").toExternalForm(), this, 'w'));
        whitePieces.put(new Position(1,2),new Pawn(new Position(1,2), getClass().getResource("/images/wP.png").toExternalForm(), this, 'w'));
        whitePieces.put(new Position(2,2),new Pawn(new Position(2,2), getClass().getResource("/images/wP.png").toExternalForm(), this, 'w'));
        whitePieces.put(new Position(3,2),new Pawn(new Position(3,2), getClass().getResource("/images/wP.png").toExternalForm(), this, 'w'));
        whitePieces.put(new Position(4,2),new Pawn(new Position(4,2), getClass().getResource("/images/wP.png").toExternalForm(), this, 'w'));
        whitePieces.put(new Position(5,2),new Pawn(new Position(5,2), getClass().getResource("/images/wP.png").toExternalForm(), this, 'w'));
        whitePieces.put(new Position(6,2),new Pawn(new Position(6,2), getClass().getResource("/images/wP.png").toExternalForm(), this, 'w'));
        whitePieces.put(new Position(7,2),new Pawn(new Position(7,2), getClass().getResource("/images/wP.png").toExternalForm(), this, 'w'));
        whitePieces.put(new Position(8,2),new Pawn(new Position(8,2), getClass().getResource("/images/wP.png").toExternalForm(), this, 'w'));
        blackPieces.put(new Position(3,8),new Bishop(new Position(3,8), getClass().getResource("/images/bB.png").toExternalForm(), this, 'b'));
        blackPieces.put(new Position(6,8),new Bishop(new Position(6,8), getClass().getResource("/images/bB.png").toExternalForm(), this, 'b'));
        blackPieces.put(new Position(8,8),new Rook(new Position(8,8), getClass().getResource("/images/bR.png").toExternalForm(), this, 'b'));
        blackPieces.put(new Position(1,8),new Rook(new Position(1,8), getClass().getResource("/images/bR.png").toExternalForm(), this, 'b'));
        blackPieces.put(new Position(2,8),new Knight(new Position(2,8), getClass().getResource("/images/bN.png").toExternalForm(), this, 'b'));
        blackPieces.put(new Position(7,8),new Knight(new Position(7,8), getClass().getResource("/images/bN.png").toExternalForm(), this, 'b'));
        blackPieces.put(new Position(4,8),new Queen(new Position(4,8), getClass().getResource("/images/bQ.png").toExternalForm(), this, 'b'));
        blackPieces.put(new Position(5,8),new King(new Position(5,8), getClass().getResource("/images/bK.png").toExternalForm(), this, 'b'));
        blackPieces.put(new Position(1,7),new Pawn(new Position(1,7), getClass().getResource("/images/bP.png").toExternalForm(), this, 'b'));
        blackPieces.put(new Position(2,7),new Pawn(new Position(2,7), getClass().getResource("/images/bP.png").toExternalForm(), this, 'b'));
        blackPieces.put(new Position(3,7),new Pawn(new Position(3,7), getClass().getResource("/images/bP.png").toExternalForm(), this, 'b'));
        blackPieces.put(new Position(4,7),new Pawn(new Position(4,7), getClass().getResource("/images/bP.png").toExternalForm(), this, 'b'));
        blackPieces.put(new Position(5,7),new Pawn(new Position(5,7), getClass().getResource("/images/bP.png").toExternalForm(), this, 'b'));
        blackPieces.put(new Position(6,7),new Pawn(new Position(6,7), getClass().getResource("/images/bP.png").toExternalForm(), this, 'b'));
        blackPieces.put(new Position(7,7),new Pawn(new Position(7,7), getClass().getResource("/images/bP.png").toExternalForm(), this, 'b'));
        blackPieces.put(new Position(8,7),new Pawn(new Position(8,7), getClass().getResource("/images/bP.png").toExternalForm(), this, 'b'));
        onMove = 'w';
        isEnd = false;
    }

    public boolean checkIfAttacked(){
        King k = new King(new Position(5,8),new String(getClass().getResource("/images/wK.png").toExternalForm()), this, 'w');
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
        King k = new King(new Position(1,1),"1",this,'w');
        List <Position> kingMoves = new ArrayList<>();
        Game cp = (Game) SerializationUtils.clone(this);
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
            if (true){ //previously if kingmoves==0
                for (Map.Entry<Position, Piece> element : this.whitePieces.entrySet()){
                    for (Position p : element.getValue().getPossibleMoves()){
                        cp.whitePieces.get(element.getKey()).Move(p, false);
                        cp.onMove = 'w';
                        if (cp.checkIfAttacked() == false){
                            return;}
                        cp = (Game) SerializationUtils.clone(this);
                    }
                }
                isEnd = true;
                winner = 'b';
                System.out.println("mate");
            }
            else{
                int size = kingMoves.size();
                for (Position p : kingMoves){
                    cp = (Game) SerializationUtils.clone(this);
                    cp.whitePieces.get(k.position).Move(p, false);
                    cp.onMove = 'w';
                    if (cp.checkIfAttacked()==true) {
                        size-=1;
                    }
                }
                if (size == 0) {
                    isEnd = true;
                    winner = 'b';
                    System.out.println("mate");
                }
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
                    kingMoves.removeIf(el -> el.equals(p));
                }
            }
            if (true){
                for (Map.Entry<Position, Piece> element : this.blackPieces.entrySet()){
                    for (Position p : element.getValue().getPossibleMoves()){
                        cp.blackPieces.get(element.getKey()).Move(p, false);
                        cp.onMove = 'b';
                        if (cp.checkIfAttacked() == false){
                            return;}
                        cp = (Game) SerializationUtils.clone(this);
                    }
                }
                isEnd = true;
                winner = 'w';
                System.out.println("mate");
            }
            else{
                int size = kingMoves.size();
                for (Position p : kingMoves){
                    cp = (Game) SerializationUtils.clone(this);
                    cp.blackPieces.get(k.position).Move(p, false);
                    cp.onMove = 'b';
                    if (cp.checkIfAttacked()==true) {
                        size-=1;
                    }
                }
                if (size == 0) {
                    isEnd = true;
                    winner = 'w';
                    System.out.println("mate");
                }
            }
        }
    }
    public void readFen(String fen){
        int x = 1;
        int y = 8;
        this.whitePieces.clear();
        this.blackPieces.clear();
        for (int i=0;i<fen.length();i++){
            char currentChar = fen.charAt(i);
            if (currentChar == 'r'){
                this.blackPieces.put(new Position(x,y),new Rook(new Position(x,y), getClass().getResource("/images/bR.png").toExternalForm(), this, 'b'));
            }
            else if (currentChar == 'n'){
                this.blackPieces.put(new Position(x,y),new Knight(new Position(x,y), getClass().getResource("/images/bN.png").toExternalForm(), this, 'b'));
            }
            else if (currentChar == 'b'){
                this.blackPieces.put(new Position(x,y),new Bishop(new Position(x,y), getClass().getResource("/images/bB.png").toExternalForm(), this, 'b'));
            }
            else if (currentChar == 'q'){
                this.blackPieces.put(new Position(x,y),new Queen(new Position(x,y), getClass().getResource("/images/bQ.png").toExternalForm(), this, 'b'));
            }
            else if (currentChar == 'k'){
                this.blackPieces.put(new Position(x,y),new King(new Position(x,y), getClass().getResource("/images/bK.png").toExternalForm(), this, 'b'));
            }
            else if (currentChar == 'p'){
                this.blackPieces.put(new Position(x,y),new Pawn(new Position(x,y), getClass().getResource("/images/bP.png").toExternalForm(), this, 'b'));
            }
            else if (currentChar == 'R'){
                this.whitePieces.put(new Position(x,y),new Rook(new Position(x,y), getClass().getResource("/images/wR.png").toExternalForm(), this, 'w'));
            }
            else if (currentChar == 'N'){
                this.whitePieces.put(new Position(x,y),new Knight(new Position(x,y), getClass().getResource("/images/wN.png").toExternalForm(), this, 'w'));
            }
            else if (currentChar == 'B'){
                this.whitePieces.put(new Position(x,y),new Bishop(new Position(x,y), getClass().getResource("/images/wB.png").toExternalForm(), this, 'w'));
            }
            else if (currentChar == 'Q'){
                this.whitePieces.put(new Position(x,y),new Queen(new Position(x,y), getClass().getResource("/images/wQ.png").toExternalForm(), this, 'w'));
            }
            else if (currentChar == 'K'){
                this.whitePieces.put(new Position(x,y),new King(new Position(x,y), getClass().getResource("/images/wK.png").toExternalForm(), this, 'w'));
            }
            else if (currentChar == 'P'){
                this.whitePieces.put(new Position(x,y),new Pawn(new Position(x,y), getClass().getResource("/images/wP.png").toExternalForm(), this, 'w'));
            }
            else if (currentChar>='1' && currentChar<='8'){
                x+=currentChar-'0'-1;
            }
            if (currentChar != '/'){
                x+=1;
                if (x>8){
                    x=1;
                    y-=1;
                }
            }
            if(currentChar == ' '){
                i++;
                currentChar = fen.charAt(i);
                    if (currentChar == 'w'){
                        onMove = 'w';
                    }
                    else if (currentChar == 'b'){
                        onMove = 'b';
                    }
                    i+=2;
                    currentChar = fen.charAt(i);
                    if (currentChar == '-'){
                        i+=2;
                        currentChar = fen.charAt(i);
                    }
                    else{
                        while (currentChar != ' '){
                            if (currentChar == 'K'){

                            }
                            else if (currentChar == 'Q'){

                            }
                            else if (currentChar == 'k'){

                            }
                            else if (currentChar == 'q'){

                            }
                            i++;
                            currentChar = fen.charAt(i);
                        }
                    }
                    i++;
                    currentChar = fen.charAt(i);
                    int halfMove = currentChar-'0';
                    i+=2;
                    currentChar = fen.charAt(i);
                    int moves = currentChar-'0';
                    i++;
                    if (i<fen.length()){
                        currentChar = fen.charAt(i);
                        moves=moves*10;
                        moves+=currentChar-'0';
                    }

            }
        }
    }

}
