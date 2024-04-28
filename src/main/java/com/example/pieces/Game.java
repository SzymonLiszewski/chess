package com.example.pieces;

import java.io.Serializable;
import java.util.*;


public class Game implements Serializable {
    static long board = 0;
    static long[] occupancy = new long[2];   //occupancy bitboard for white and black
    static long[][] bitBoards = new long[2][6];    //bitboards containing positions of all pieces


    color engineSide = color.white;

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
       LookupTables lookupTables = new LookupTables();

        readFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        printBitBoard(bitBoards[color.white.ordinal()][pieces.knight.ordinal()]);
        //UCI();
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
    public static long setBit(long bitBoard, int square){

        bitBoard |= ((long) 1 << square);
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




    //connecting to gui using UCI
    public static void UCI(){
        Scanner scanner = new Scanner(System.in);

        while (true) {
            //System.out.print("Podaj polecenie: ");
            String command = scanner.nextLine();
            if (command.startsWith("uci")) {
                System.out.println("id name test");
                System.out.println("uciok");
                //handleUCICommand();
            } else if (command.equals("isready")) {
                System.out.println("readyok");
                //handleIsReadyCommand();
            } else if (command.equals("ucinewgame")) {
                //handleUCINewGameCommand();
            } else if (command.startsWith("position")) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("bestmove e2e4");
                //handlePositionCommand(command);
            } else if (command.startsWith("go")) {
                //handleGoCommand(command);
            } else if (command.equals("stop")) {
                //handleStopCommand();
            } else if (command.equals("ponderhit")) {
                //handlePonderHitCommand();
            } else if (command.equals("quit")) {
                //handleQuitCommand();
                break;
            } else {
                //System.out.println("Nieznane polecenie: " + command);
            }
        }

        scanner.close();
    }

    public static void readFen(String fen){
        String[] parts = fen.split(" ");
        String[] rows = parts[0].split("/");

        int squareIdx = 0;
        int rowIdx = 0;
        for (String row : rows) {
            int colIdx = 0;
            for (char c : row.toCharArray()) {
                if (Character.isDigit(c)) {
                    int emptySpaces = Character.getNumericValue(c);
                    for (int i = 0; i < emptySpaces; i++) {
                        squareIdx+=1;
                    }
                } else {
                    switch (c){
                        case 'P':
                            bitBoards[color.white.ordinal()][pieces.pawn.ordinal()] = setBit(bitBoards[color.white.ordinal()][pieces.pawn.ordinal()], squareIdx);
                            break;
                        case 'R':
                            bitBoards[color.white.ordinal()][pieces.rook.ordinal()] = setBit(bitBoards[color.white.ordinal()][pieces.rook.ordinal()], squareIdx);
                            break;
                        case 'B':
                            bitBoards[color.white.ordinal()][pieces.bishop.ordinal()] = setBit(bitBoards[color.white.ordinal()][pieces.bishop.ordinal()], squareIdx);
                            break;
                        case 'N':
                            bitBoards[color.white.ordinal()][pieces.knight.ordinal()] = setBit(bitBoards[color.white.ordinal()][pieces.knight.ordinal()], squareIdx);
                            break;
                        case 'K':
                            bitBoards[color.white.ordinal()][pieces.king.ordinal()] = setBit(bitBoards[color.white.ordinal()][pieces.knight.ordinal()], squareIdx);
                            break;
                        case 'Q':
                            bitBoards[color.white.ordinal()][pieces.queen.ordinal()] = setBit(bitBoards[color.white.ordinal()][pieces.queen.ordinal()], squareIdx);
                            break;
                        case 'p':
                            bitBoards[color.black.ordinal()][pieces.pawn.ordinal()] = setBit(bitBoards[color.white.ordinal()][pieces.pawn.ordinal()], squareIdx);
                            break;
                        case 'r':
                            bitBoards[color.black.ordinal()][pieces.rook.ordinal()] = setBit(bitBoards[color.white.ordinal()][pieces.rook.ordinal()], squareIdx);
                            break;
                        case 'b':
                            bitBoards[color.black.ordinal()][pieces.bishop.ordinal()] = setBit(bitBoards[color.white.ordinal()][pieces.bishop.ordinal()], squareIdx);
                            break;
                        case 'n':
                            bitBoards[color.black.ordinal()][pieces.knight.ordinal()] = setBit(bitBoards[color.white.ordinal()][pieces.knight.ordinal()], squareIdx);
                            break;
                        case 'k':
                            bitBoards[color.black.ordinal()][pieces.king.ordinal()] = setBit(bitBoards[color.white.ordinal()][pieces.king.ordinal()], squareIdx);
                            break;
                        case 'q':
                            bitBoards[color.black.ordinal()][pieces.queen.ordinal()] = setBit(bitBoards[color.white.ordinal()][pieces.queen.ordinal()], squareIdx);
                            break;
                    }
                    board = board = setBit(board, squareIdx);
                    squareIdx+=1;
                }
            }
            rowIdx++;
        }
    }

    public void makeMove(){

    }

    //todo: makemove
    //todo: perft function
    //todo: minmax
}
