package com.example.pieces;

import static com.example.pieces.Game.setBit;

//class containing attack tables for knights,pawns and kings, and functions for generating sliding pieces moves


public class LookupTables {
    static long[][] pawnAttacks = new long[2][64];   //bitboards containing possible pawn attacks from
                                                    // all positions for both black and white
    static long[][] pawnMoves = new long[2][64];
    public static long[] knightAttacks = new long[64];
    public static long[] kingAttacks = new long[64];
    public static long[] bitMask = new long[64];
    public static long[] diagonalMask = new long[64];
    public static long[] antiDiagonalMask = new long[64];
    public static long[] fileMask = new long[64];
    public static long[] rankMask = new long[64];

    public LookupTables() {
        pawnAttacks = generatePawnAttackTable();
        pawnMoves = generatePawnMovesTable();
        knightAttacks = generateKnightAttackTable();
        kingAttacks = generateKingAttackTable();
        bitMask = generateBitMask();
        generateMasks();
    }

    //generating moves
    // using >>> to avoid generating ones on most significant bits (due to U2)
    static long notAfile = Long.decode("-72340172838076674");
    static long notHfile = Long.decode("9187201950435737471");
    static long notABfile = Long.decode("-217020518514230020");
    static long notHGfile = Long.decode("4557430888798830399");
    public static long maskPawnAttacks(Game.squares square, Game.color side){
        long attacks = 0;
        long bitboard = 0;

        bitboard = setBit(bitboard, square);

        if (side == Game.color.white){
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
        for (Game.squares i : Game.squares.values()){
            attacks[Game.color.white.ordinal()][i.ordinal()] = maskPawnAttacks(i, Game.color.white);
            attacks[Game.color.black.ordinal()][i.ordinal()] = maskPawnAttacks(i, Game.color.black);
        }
        return attacks;
    }
    public static long maskKnightAttacks(Game.squares square){
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
        for (Game.squares i : Game.squares.values()){
            attacks[i.ordinal()] = maskKnightAttacks(i);
        }
        return attacks;
    }
    public static long maskKingAttacks(Game.squares square, Game.color side){
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
        for (Game.squares i : Game.squares.values()){
            attacks[i.ordinal()] = maskKingAttacks(i, Game.color.white);
        }
        return attacks;
    }
    public static long maskPawnMoves(Game.squares square, Game.color side) {
        long attacks = 0;
        long bitboard = 0;

        bitboard = setBit(bitboard, square);

        if (side == Game.color.white){
            if (((bitboard>>>8))!=0) {
                attacks |= (bitboard >>> 8);
            }
            if (square.ordinal()<=Game.squares.h2.ordinal() && square.ordinal()>=Game.squares.a2.ordinal()){
                //todo: first pawn move
                //attacks |= (bitboard >>> 16);
            }
        }
        else {
            if ((bitboard << 9) != 0) {
                attacks |= (bitboard << 8);
            }
            if (square.ordinal()<=Game.squares.h7.ordinal() && square.ordinal()>=Game.squares.a7.ordinal()){
                //attacks |= (bitboard << 16);
            }
        }

        return attacks;
    }
    public static long[][] generatePawnMovesTable(){
        long[][] attacks = new long[2][64];
        for (Game.squares i : Game.squares.values()){
            attacks[Game.color.white.ordinal()][i.ordinal()] = maskPawnMoves(i, Game.color.white);
            attacks[Game.color.black.ordinal()][i.ordinal()] = maskPawnMoves(i, Game.color.black);
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
    public static long maskDiagonalAttacks(Game.squares square){
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

    public static long maskAntiDiagonalAttacks(Game.squares square){
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
    public static long maskrankAttacks(Game.squares square){
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
    public static long maskfileAttacks(Game.squares square){
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
        for (Game.squares i : Game.squares.values()){
            diagonalMask[i.ordinal()] = maskDiagonalAttacks(i);
        }
        for (Game.squares i : Game.squares.values()){
            antiDiagonalMask[i.ordinal()] = maskAntiDiagonalAttacks(i);
        }
        for (Game.squares i : Game.squares.values()){
            fileMask[i.ordinal()] = maskfileAttacks(i);
        }
        for (Game.squares i : Game.squares.values()){
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
    static public long rankAttacks(long occ, Game.squares square) {
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

    public long getAttacks(Game.pieces piece, int position, Game.color color, long wocc, long bocc){    //wocc, bocc - white/black occupancy bitboard
        long occ;
        long opponentOcc;
        long all = wocc+bocc;
        if (color == Game.color.white){
            occ = wocc;
            opponentOcc = bocc;
        }
        else{
            occ = bocc;
            opponentOcc = wocc;
        }
        if (piece == Game.pieces.knight){
            return knightAttacks[position] - (knightAttacks[position] & occ);                       // masking bits so pieces cant attack squares occupied
        }                                                                                           //by other pieces of same color
        else if (piece == Game.pieces.king) {
            return kingAttacks[position] - (kingAttacks[position] & occ);
        }
        else if (piece == Game.pieces.pawn){
            long moves = pawnMoves[color.ordinal()][position] -(pawnMoves[color.ordinal()][position] & all);
            //return pawnAttacks[color.ordinal()][position] -(pawnAttacks[color.ordinal()][position] & occ);
            return moves;
        }
        else if (piece == Game.pieces.rook){
            long attacks = fileAttacks(all, position) + rankAttacks(all, Game.squares.values()[position]);
            attacks = attacks - (attacks & occ);
            return attacks;
        }
        else if (piece == Game.pieces.bishop){
            long attacks = diagonalAttacks (all, position) + antiDiagonalAttacks (all, position);
            attacks = attacks - (attacks & occ);
            return attacks;
        }
        else if (piece == Game.pieces.queen){
            long attacks = diagonalAttacks (all, position) + antiDiagonalAttacks (all, position)+fileAttacks(all, position) + rankAttacks(all, Game.squares.values()[position]);
            attacks = attacks - (attacks & occ);
            return attacks;
        }
        return 0;
    }

}
