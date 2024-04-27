package com.example.pieces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Game implements Serializable {
    static long board = 0;
    long[][] bitBoards = new long[2][6];    //bitboards containing positions of all pieces
    static long[][] pawnAttacks = new long[2][64];   //bitboards containing possible pawn attacks from
                                            // all positions for both black and white


    public enum squares {
        a8, b8, c8, d8, e8, f8, g8, h8,
        a7, b7, c7, d7, e7, f7, g7, h7,
        a6, b6, c6, d6, e6, f6, g6, h6,
        a5, b5, c5, d5, e5, f5, g5, h5,
        a4, b4, c4, d4, e4, f4, g4, h4,
        a3, b3, c3, d3, e3, f3, g3, h3,
        a2, b2, c2, d2, e2, f2, g2, h2,
        a1, b1, c1, d1, e1, f1, g1, h1
    }

    public enum pieces{
        pawn,rook,knight,bishop,queen,king
    }

    public enum color{
        white, black
    }

    public static void main(String args[]){
        board = setBit(board, squares.e4);
        System.out.println(board);
        printBitBoard(board);
        pawnAttacks = generatePawnAttackTable();
        printBitBoard(pawnAttacks[color.white.ordinal()][squares.e4.ordinal()]);
    }

    //Printing board with 1 on occupied squares and 0 on free squares
    public static void printBitBoard(long bitboard){
        for (int rank = 0; rank<8;rank++){
            for (int file = 0; file < 8;file++){
                int pos = rank*8+file;
                if (file == 0){
                    System.out.print(String.valueOf(8-rank)+" ");
                }
                if ((bitboard & (long) 1 << pos)!=0){
                    System.out.print("1 ");
                }
                else{
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
        System.out.println("  A B C D E F G H");
    }

    //setting 1 on bit representing given square
    public static long setBit(long bitBoard, squares square){

        bitBoard |= ((long) 1 << square.ordinal());
        return bitBoard;
    }

    //setting 0 on bit representing given square
    public static long removeBit(long bitBoard,squares square){
        if (getbit(square)) { //check if bit on given square is 1
            bitBoard ^= ((long) 1 << square.ordinal());
        }
        return bitBoard;
    }

    public static boolean getbit(squares square){
        return ((board & ((long) 1 << square.ordinal()))!=0);
    }


    //generating moves
    static long notAfile = Long.decode("-72340172838076674");
    static long notHfile = Long.decode("9187201950435737471");
    public static long maskPawnAttacks(squares square, color side){
        long attacks = 0;
        long bitboard = 0;

        bitboard = setBit(bitboard, square);

        if (side == color.white){
            if (((bitboard>>7) & notAfile)!=0) {
                attacks |= (bitboard >> 7);
            }
            if (((bitboard>>9) & notHfile)!=0){
                attacks |= (bitboard >> 9);
            }
        }
        else{
            if (((bitboard<<7) & notHfile)!=0) {
                attacks |= (bitboard << 7);
            }
            if (((bitboard<<9) & notAfile)!=0){
                attacks |= (bitboard << 9);
            }
        }

        return attacks;
    }
    public static long[][] generatePawnAttackTable(){
        long[][] attacks = new long[2][64];
        for (squares i : squares.values()){
            attacks[color.white.ordinal()][i.ordinal()] = maskPawnAttacks(i,color.white);
            attacks[color.black.ordinal()][i.ordinal()] = maskPawnAttacks(i,color.black);
        }
        return attacks;
    }
}
