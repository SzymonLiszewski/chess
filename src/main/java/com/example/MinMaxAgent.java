package com.example;

import com.example.pieces.*;
import javafx.geometry.Pos;
import org.apache.commons.lang3.SerializationUtils;

import java.util.*;

public class MinMaxAgent {
    Game game;
    char color;

    public MinMaxAgent(Game game, char color) {
        this.game = game;
        this.color = color;
    }


    public double heuristic(Game state){
        int whiteKing = 0;
        int whiteKnight = 0;
        int whiteRook = 0;
        int whiteBishop = 0;
        int whiteQueen = 0;
        int whitePawn = 0;
        for (Map.Entry<Position,Piece> entry : state.whitePieces.entrySet()){
            if (entry.getValue() instanceof King){
                whiteKing++;
            }
            else if (entry.getValue() instanceof Knight){
                whiteKnight++;
            }
            else if (entry.getValue() instanceof Rook){
                whiteRook++;
            }
            else if (entry.getValue() instanceof Bishop){
                whiteBishop++;
            }
            else if (entry.getValue() instanceof Queen){
                whiteQueen++;
            }
            else if (entry.getValue() instanceof Pawn){
                whitePawn++;
            }
        }
        int blackKing = 0;
        int blackKnight = 0;
        int blackRook = 0;
        int blackBishop = 0;
        int blackQueen = 0;
        int blackPawn = 0;
        for (Map.Entry<Position,Piece> entry : state.blackPieces.entrySet()){
            if (entry.getValue() instanceof King){
                blackKing++;
            }
            else if (entry.getValue() instanceof Knight){
                blackKnight++;
            }
            else if (entry.getValue() instanceof Rook){
                blackRook++;
            }
            else if (entry.getValue() instanceof Bishop){
                blackBishop++;
            }
            else if (entry.getValue() instanceof Queen){
                blackQueen++;
            }
            else if (entry.getValue() instanceof Pawn){
                blackPawn++;
            }
        }
        double eval = 9 * (whiteQueen - blackQueen) + 5 * (whiteRook - blackRook) + 3 * (whiteBishop - blackBishop + whiteKnight - blackKnight) + 1 * (whitePawn - blackPawn);
        return eval/100;
    }
    public double decide(Game state, int flag, int depth, double alpha, double beta){
        if (state.isEnd && state.winner == color){
             return 1;
        }
        else if (state.isEnd && state.winner != color){
            return -1;
        }
        else if (depth == 0){
            return heuristic(state);
        }
        else if (flag == 1){
            double v = Double.NEGATIVE_INFINITY;
            List<Double> l = new ArrayList<>();
            //Game cp = (Game) SerializationUtils.clone(state);
            Game temp = (Game) SerializationUtils.clone(state);
            Set<Map.Entry<Position, Piece>> es = temp.whitePieces.entrySet();
            for (Map.Entry<Position, Piece> element : es){
                //temp = (Game) SerializationUtils.clone(state);
                List<Position> positions = element.getValue().getPossibleMoves();
                for (Position p : positions){
                    //element.getValue().Move(p,true);
                    temp = (Game) SerializationUtils.clone(state);
                    temp.whitePieces.get(element.getKey()).Move(p,true);
                    if (temp.onMove != this.color) {
                        l.add(decide(temp, -1, depth-1, alpha, beta));
                        v = Math.max(v, l.get(l.size()-1));
                        alpha = Math.max(v, alpha);
                        if (v>=beta){
                            break;
                        }
                    }

                }
            }
            return v;
        }
        else{
            double v = Double.POSITIVE_INFINITY;
            List<Double> l = new ArrayList<>();
            //Game cp = (Game) SerializationUtils.clone(state);
            Game temp = (Game) SerializationUtils.clone(state);
            Set<Map.Entry<Position, Piece>> es = temp.blackPieces.entrySet();
            for (Map.Entry<Position, Piece> element : es){
                //temp = (Game) SerializationUtils.clone(state);
                List<Position> positions = element.getValue().getPossibleMoves();
                for (Position p : positions){
                    temp = (Game) SerializationUtils.clone(state);
                    temp.blackPieces.get(element.getValue().getPosition()).Move(p,true);
                    if (temp.onMove == this.color) {
                        l.add(decide(temp, 1, depth-1, alpha, beta));
                        v = Math.min(v, l.get(l.size()-1));
                        beta = Math.min(v, beta);
                        if (v<=alpha){
                            break;
                        }
                    }


                }
            }
            return v;
        }
        //return 0;
    }

    public void makeMove(int depth){
        //List<Double> values = new ArrayList<>();
        //Map<Piece, Position> moves = new HashMap<>();
        Map<Move, Double> moveValue = new HashMap<>();
        //test of deepcopy
        Game cp = (Game) SerializationUtils.clone(game);
        Game temp = (Game) SerializationUtils.clone(cp);
        Set<Map.Entry<Position, Piece>> es = temp.whitePieces.entrySet();
        for (Map.Entry<Position, Piece> element : es){
            Piece p = element.getValue();
            for (Position pos : p.getPossibleMoves()){
                cp.whitePieces.get(p.getPosition()).Move(pos,true);
                //p.Move(pos, true);
                if (cp.onMove != this.color) {
                    double val = decide(cp, -1, depth, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
                    cp = (Game) SerializationUtils.clone(temp);
                    moveValue.put(new Move(p, pos), val);
                }
            }
        }
        double max = Double.NEGATIVE_INFINITY;
        Move maxMove = null;
        for (Map.Entry<Move, Double> element : moveValue.entrySet()){
            if (element.getValue()>max){
                maxMove = element.getKey();
                max = element.getValue();
            }
            System.out.print(element.getKey().getPiece().getClass().getName());
            System.out.print(" ");
            System.out.print(element.getKey().getPosition().getX());
            System.out.print(element.getKey().getPosition().getY());
            System.out.print(" : ");
            System.out.println(element.getValue());
        }
        Position xd = maxMove.getPiece().getPosition();
        Position xd2 = maxMove.getPosition();
        game.whitePieces.get(maxMove.getPiece().getPosition()).Move(maxMove.getPosition(), true);
        //System.out.println(max);
    }

}
