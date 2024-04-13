package com.example;

import com.example.pieces.Game;
import com.example.pieces.Piece;
import com.example.pieces.Position;
import org.apache.commons.lang3.SerializationUtils;

import java.util.*;

public class MinMaxAgent {
    Game game;
    char color;

    public MinMaxAgent(Game game, char color) {
        this.game = game;
        this.color = color;
    }

    public double decide(Game state, int flag, int depth, double alpha, double beta){
        if (state.isEnd && state.winner == color){
             return 1;
        }
        else if (state.isEnd && state.winner != color){
            return -1;
        }
        else if (depth == 0){
            return 0;
        }
        else if (flag == 1){
            double v = Double.NEGATIVE_INFINITY;
            List<Double> l = new ArrayList<>();
            Game cp = (Game) SerializationUtils.clone(state);
            Game temp = (Game) SerializationUtils.clone(cp);
            Set<Map.Entry<Position, Piece>> es = temp.whitePieces.entrySet();
            for (Map.Entry<Position, Piece> element : es){
                cp = (Game) SerializationUtils.clone(state);
                List<Position> positions = element.getValue().getPossibleMoves();
                for (Position p : positions){
                    element.getValue().Move(p,true);
                    l.add(decide(cp, -1, depth-1, alpha, beta));
                    v = Math.max(v, Collections.max(l));
                    alpha = Math.max(v, alpha);
                    if (v>=beta){
                        break;
                    }
                    return v;
                }
            }

        }
        else{ //testing
            double v = Double.POSITIVE_INFINITY;
            List<Double> l = new ArrayList<>();
            Game cp = (Game) SerializationUtils.clone(state);
            Game temp = (Game) SerializationUtils.clone(cp);
            Set<Map.Entry<Position, Piece>> es = temp.whitePieces.entrySet();
            for (Map.Entry<Position, Piece> element : es){
                cp = (Game) SerializationUtils.clone(state);
                List<Position> positions = element.getValue().getPossibleMoves();
                for (Position p : positions){
                    element.getValue().Move(p,true);
                    l.add(decide(cp, -1, depth-1, alpha, beta));
                    v = Math.min(v, Collections.max(l));
                    beta = Math.min(v, beta);
                    if (v<=alpha){
                        break;
                    }
                    return v;
                }
            }

        }
        return 0;
    }

    public void makeMove(){
        //List<Double> values = new ArrayList<>();
        //Map<Piece, Position> moves = new HashMap<>();
        Map<Move, Double> moveValue = new HashMap<>();

        for (Piece p : game.whitePieces.values()){
            for (Position pos : p.getPossibleMoves()){
                double val = decide(game, 1, 2, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
                moveValue.put(new Move(p, pos), val);
            }
        }
        double max = Double.NEGATIVE_INFINITY;
        Move maxMove = null;
        for (Map.Entry<Move, Double> element : moveValue.entrySet()){
            if (element.getValue()>max){
                maxMove = element.getKey();
            }
        }
        game.whitePieces.get(maxMove.getPiece().getPosition()).Move(maxMove.getPosition(), true);
    }

}
