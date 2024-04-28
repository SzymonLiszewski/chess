package com.example.pieces;

import java.io.Serializable;
import java.util.*;


public class Game implements Serializable {
    static long board = 0;
    static long[] occupancy = new long[2];   //occupancy bitboard for white and black
    long[][] bitBoards = new long[2][6];    //bitboards containing positions of all pieces
    static long[][] pawnAttacks = new long[2][64];   //bitboards containing possible pawn attacks from
                                            // all positions for both black and white
    static long[] knightAttacks = new long[64];
    static long[] kingAttacks = new long[64];
    static long[] bitMask = new long[64];
    static long[] diagonalMask = new long[64];
    static long[] antiDiagonalMask = new long[64];
    static long[] fileMask = new long[64];
    static long[] rankMask = new long[64];

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
        pawnAttacks = generatePawnAttackTable();
        knightAttacks = generateKnightAttackTable();
        kingAttacks = generateKingAttackTable();
        bitMask = generateBitMask();
        generateMasks();

        UCI();
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
    // using >>> to avoid generating ones on most significant bits (due to U2)
    static long notAfile = Long.decode("-72340172838076674");
    static long notHfile = Long.decode("9187201950435737471");
    static long notABfile = Long.decode("-217020518514230020");
    static long notHGfile = Long.decode("4557430888798830399");
    public static long maskPawnAttacks(squares square, color side){
        long attacks = 0;
        long bitboard = 0;

        bitboard = setBit(bitboard, square);

        if (side == color.white){
            if (((bitboard>>>7) & notAfile)!=0) {
                attacks |= (bitboard >>> 7);
            }
            if (((bitboard>>>9) & notHfile)!=0){
                attacks |= (bitboard >>> 9);
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
    public static long maskKnightAttacks(squares square){
        long attacks = 0;
        long bitboard = 0;

        bitboard = setBit(bitboard, square);

        if (((bitboard>>>17) & notHfile)!=0) {
            attacks |= (bitboard >>> 17);
        }
        if (((bitboard>>>15) & notAfile)!=0){
            attacks |= (bitboard >>> 15);
        }
        if (((bitboard>>>10) & notHGfile)!=0) {
            attacks |= (bitboard >>> 10);
        }
        if (((bitboard>>>6) & notABfile)!=0){
            attacks |= (bitboard >>> 6);
        }
        if (((bitboard<<17) & notAfile)!=0) {
            attacks |= (bitboard << 17);
        }
        if (((bitboard<<15) & notHfile)!=0){
            attacks |= (bitboard << 15);
        }
        if (((bitboard<<10) & notABfile)!=0) {
            attacks |= (bitboard << 10);
        }
        if (((bitboard<<6) & notHGfile)!=0){
            attacks |= (bitboard << 6);
        }

        return attacks;
    }
    public static long[] generateKnightAttackTable(){
        long[] attacks = new long[64];
        for (squares i : squares.values()){
            attacks[i.ordinal()] = maskKnightAttacks(i);
        }
        return attacks;
    }
    public static long maskKingAttacks(squares square, color side){
        long attacks = 0;
        long bitboard = 0;

        bitboard = setBit(bitboard, square);


        if (((bitboard>>>8))!=0) {
            attacks |= (bitboard >>> 8);
        }
        if (((bitboard>>>9) & notHfile)!=0){
            attacks |= (bitboard >>> 9);
        }
        if (((bitboard>>>7) & notAfile)!=0) {
            attacks |= (bitboard >>> 7);
        }
        if (((bitboard>>>1) & notHfile)!=0){
            attacks |= (bitboard >>> 1);
        }
        if (((bitboard<<8))!=0 ) {
            attacks |= (bitboard << 8);
        }
        if (((bitboard<<9) & notAfile)!=0){
            attacks |= (bitboard << 9);
        }
        if (((bitboard<<7) & notHfile)!=0) {
            attacks |= (bitboard << 7);
        }
        if (((bitboard<<1) & notAfile)!=0){
            attacks |= (bitboard << 1);
        }

        return attacks;
    }
    public static long[] generateKingAttackTable(){
        long[] attacks = new long[64];
        for (squares i : squares.values()){
            attacks[i.ordinal()] = maskKingAttacks(i,color.white);
        }
        return attacks;
    }

    //sliding moves
    public static long[] generateBitMask(){
        long[] bits = new long[64];
        long k = 1;
        for (int i =0;i<64;i++){
            bits[i]=k;
            k = k<<1;
        }
        return bits;
    }


    //generating sliding piece attacks using Hyperbola Quintessence method
    public static long maskDiagonalAttacks(squares square){
        long attacks = 0;
        long bitboard = 0;

        bitboard = 0;
        bitboard = setBit(bitboard, square);
        while (((bitboard>>>9) & notHfile)!=0){
            attacks |= (bitboard >>> 9);
            bitboard = (bitboard >>> 9);
        }
        bitboard = 0;
        bitboard = setBit(bitboard, square);
        while (((bitboard<<9) & notAfile)!=0){
            attacks |= (bitboard << 9);
            bitboard = (bitboard << 9);
        }
        return attacks;
    }

    public static long maskAntiDiagonalAttacks(squares square){
        long attacks = 0;
        long bitboard = 0;

        bitboard = setBit(bitboard, square);
        while (((bitboard>>>7) & notAfile)!=0) {
            attacks |= (bitboard >>> 7);
            bitboard = (bitboard >>> 7);
        }

        bitboard = 0;
        bitboard = setBit(bitboard, square);
        while (((bitboard<<7) & notHfile)!=0) {
            attacks |= (bitboard << 7);
            bitboard = (bitboard << 7);
        }

        return attacks;
    }
    public static long maskrankAttacks(squares square){
        long attacks = 0;
        long bitboard = 0;

        bitboard = setBit(bitboard, square);
        while (((bitboard>>>1) & notHfile)!=0) {
            attacks |= (bitboard >>> 1);
            bitboard = (bitboard >>> 1);
        }

        bitboard = 0;
        bitboard = setBit(bitboard, square);
        while (((bitboard<<1) & notAfile)!=0) {
            attacks |= (bitboard << 1);
            bitboard = (bitboard << 1);
        }

        return attacks;
    }
    public static long maskfileAttacks(squares square){
        long attacks = 0;
        long bitboard = 0;

        bitboard = setBit(bitboard, square);
        while (((bitboard>>>8))!=0) {
            attacks |= (bitboard >>> 8);
            bitboard = (bitboard >>> 8);
        }

        bitboard = 0;
        bitboard = setBit(bitboard, square);
        while (((bitboard<<8))!=0) {
            attacks |= (bitboard << 8);
            bitboard = (bitboard << 8);
        }

        return attacks;
    }
    public static void generateMasks(){
        for (squares i : squares.values()){
            diagonalMask[i.ordinal()] = maskDiagonalAttacks(i);
        }
        for (squares i : squares.values()){
            antiDiagonalMask[i.ordinal()] = maskAntiDiagonalAttacks(i);
        }
        for (squares i : squares.values()){
            fileMask[i.ordinal()] = maskfileAttacks(i);
        }
        for (squares i : squares.values()){
            rankMask[i.ordinal()] = maskrankAttacks(i);
        }
    }
    static public long diagonalAttacks(long occ, int sq) {
        long forward = occ & diagonalMask[sq];
        long reverse = Long.reverseBytes(forward);
        forward -= bitMask[sq];
        reverse -= bitMask[sq^56];
        forward ^= Long.reverseBytes(reverse);
        forward &= diagonalMask[sq];
        return forward;
    }
    static public long antiDiagonalAttacks(long occ, int sq) {
        long forward = occ & antiDiagonalMask[sq];
        long reverse = Long.reverseBytes(forward);
        forward -= bitMask[sq];
        reverse -= bitMask[sq^56];
        forward ^= Long.reverseBytes(reverse);
        forward &= antiDiagonalMask[sq];
        return forward;
    }
    static public long fileAttacks(long occ, int sq) {
        long forward = occ & fileMask[sq];
        long reverse = Long.reverseBytes(forward);
        forward -= bitMask[sq];
        reverse -= bitMask[sq^56];
        forward ^= Long.reverseBytes(reverse);
        forward &= fileMask[sq];
        return forward;
    }
    static public long rankAttacks(long occ, squares square) {
        long attacks = 0;
        long bitboard = 0;

        bitboard = setBit(bitboard, square);
        while (((bitboard>>>1) & notHfile)!=0 && ((bitboard) & occ)==0) {
            attacks |= (bitboard >>> 1);
            bitboard = (bitboard >>> 1);
        }

        bitboard = 0;
        bitboard = setBit(bitboard, square);
        while (((bitboard<<1) & notAfile)!=0  && ((bitboard) & occ)==0) {
            attacks |= (bitboard << 1);
            bitboard = (bitboard << 1);
        }

        return attacks;
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

    public void readFen(String fen){
        String[] parts = fen.split(" ");
        String[] rows = parts[0].split("/");

        String[][] board = new String[8][8];
        int rowIdx = 0;
        for (String row : rows) {
            int colIdx = 0;
            for (char c : row.toCharArray()) {
                if (Character.isDigit(c)) {
                    int emptySpaces = Character.getNumericValue(c);
                    for (int i = 0; i < emptySpaces; i++) {
                        board[rowIdx][colIdx++] = "-";
                    }
                } else {
                    board[rowIdx][colIdx++] = String.valueOf(c);
                }
            }
            rowIdx++;
        }
    }

    //todo: read fen
    //todo: makemove
    //todo: perft function
    //todo: minmax
}
