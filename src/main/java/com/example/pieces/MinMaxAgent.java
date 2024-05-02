package com.example.pieces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinMaxAgent {
    

    public double minMax(int flag, int depth, double alpha, double beta) {
        Game.color color;
        if (flag == 1) {
            color = Game.engineSide;
        } else {
            color = Game.color.values()[(Game.engineSide.ordinal() + 1) % 2];
        }
        List<Move> moves = Game.generateMovesList(Game.engineSide, Game.lookupTables);
        if (Game.isCheck(Game.engineSide) && Game.isMate(Game.engineSide, moves)) {
            Game.generateMovesList(color, Game.lookupTables);
            Game.generateMovesList(Game.color.values()[(Game.engineSide.ordinal() + 1) % 2], Game.lookupTables);
            return -1;
        }
        List<Move> opponentMoves = Game.generateMovesList(Game.color.values()[(Game.engineSide.ordinal() + 1) % 2], Game.lookupTables);
        if (Game.isCheck(Game.color.values()[(Game.engineSide.ordinal() + 1) % 2]) && Game.isMate(Game.color.values()[(Game.engineSide.ordinal() + 1) % 2], opponentMoves)) {
            Game.generateMovesList(color, Game.lookupTables);
            Game.generateMovesList(Game.color.values()[(Game.engineSide.ordinal() + 1) % 2], Game.lookupTables);
            return 1;
        }
        if (depth == 0) {
            Game.generateMovesList(color, Game.lookupTables);
            Game.generateMovesList(Game.color.values()[(Game.engineSide.ordinal() + 1) % 2], Game.lookupTables);
            return 0;
        } else if (flag == 1) {
            double v = Double.NEGATIVE_INFINITY;
            List<Double> l = new ArrayList<>();
            for (Move m : moves) {
                Game.makeMove(m);
                if (!Game.isCheck(color)) {
                    l.add(minMax(-1, depth - 1, alpha, beta));
                    v = Math.max(v, l.get(l.size() - 1));
                    alpha = Math.max(v, alpha);
                    if (v>=beta) {
                        Game.UndoMove(m);
                        break;
                    }
                } else {
                    //l.add(Double.NEGATIVE_INFINITY);
                }
                Game.UndoMove(m);
            }
            Game.generateMovesList(color, Game.lookupTables);
            Game.generateMovesList(Game.color.values()[(Game.engineSide.ordinal() + 1) % 2], Game.lookupTables);
            return v;
        } else {
            double v = Double.POSITIVE_INFINITY;
            List<Double> l = new ArrayList<>();
            for (Move m : opponentMoves) {
                Game.makeMove(m);
                if (!Game.isCheck(color)) {
                    l.add(minMax(1, depth - 1, alpha, beta));
                    v = Math.min(v, l.get(l.size() - 1));
                    beta = Math.min(v, beta);
                    if (v<=alpha) {
                        Game.UndoMove(m);
                        break;
                    }
                } else {
                    //l.add(Double.POSITIVE_INFINITY);
                }
                Game.UndoMove(m);
            }
            Game.generateMovesList(color, Game.lookupTables);
            Game.generateMovesList(Game.color.values()[(Game.engineSide.ordinal() + 1) % 2], Game.lookupTables);
            return v;
        }
    }

    public Move decide(int depth) {
        Map<Move, Double> moveValue = new HashMap<>();
        List<Move> moves = Game.generateMovesList(Game.engineSide, Game.lookupTables);
        for (Move m : moves) {
            Game.makeMove(m);
            if (!Game.isCheck(Game.engineSide)) {
                Game.generateMovesList(Game.engineSide, Game.lookupTables);
                System.out.println(m);
                Game.printBitBoard(Game.bitBoards[Game.color.black.ordinal()][Game.pieces.king.ordinal()]);
                Game.printBitBoard(Game.control[Game.color.white.ordinal()]);
                moveValue.put(m, minMax(-1, depth - 1, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY));
            }
            else{
                //moveValue.put(m,Double.NEGATIVE_INFINITY);
            }
            Game.UndoMove(m);
            System.out.println(m);
            Game.printBitBoard(Game.occupancy[Game.color.black.ordinal()]);
        }
        double max = Double.NEGATIVE_INFINITY;
        Move maxMove = null;
        for (Map.Entry<Move, Double> element : moveValue.entrySet()) {
            if (element.getValue() >= max) {
                maxMove = element.getKey();
                max = element.getValue();
            }
            System.out.print(element.getKey().toString());
            System.out.println(": "+element.getValue());
        }
        return maxMove;
    }
}
