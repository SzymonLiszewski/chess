package com.example.pieces;

import java.io.Serializable;
import java.util.*;


public class Game implements Serializable {
    static long board = 0;
    static long[] occupancy = new long[2];   //occupancy bitboard for white and black
    static long[][] bitBoards = new long[2][6];    //bitboards containing positions of all pieces


    static color engineSide = color.white;
    static color onMove = color.white;

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
        //printBitBoard(bitBoards[color.white.ordinal()][pieces.king.ordinal()]);
        UCI(lookupTables);
        //bitBoards[color.white.ordinal()][pieces.pawn.ordinal()] = setBit(bitBoards[color.white.ordinal()][pieces.pawn.ordinal()],squares.e4);
        List<Move> movesList= generateMovesList(color.white,lookupTables);
        //System.out.println(squares.b1.ordinal());
        //printBitBoard(movesList.get(squares.b1.ordinal()));
        //System.out.println(movesList.get(0).toString());
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
        if (getbit(bitBoard,square)) { //check if bit on given square is 1
            bitBoard ^= ((long) 1 << square.ordinal());
        }
        return bitBoard;
    }

    public static boolean getbit(long bitboard, squares square){
        long x = (long) 1 << square.ordinal();
        return ((bitboard & ((long) 1 << square.ordinal()))!=0);
    }




    //connecting to gui using UCI
    public static void UCI(LookupTables lookupTables){
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
                String[] s = command.split(" ");
                if (!Objects.equals(s[s.length - 1], "startpos")){
                    makeMove(encode(s[s.length - 1],onMove));
                    onMove = color.values()[(onMove.ordinal()+1)%2];
                }
                //handlePositionCommand(command);
            } else if (command.startsWith("go")) {
                Move move = decide(generateMovesList(engineSide, lookupTables));
                System.out.println("bestmove " +move.toString());
                makeMove(move);
                onMove = color.values()[(onMove.ordinal()+1)%2];
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
                            occupancy[color.white.ordinal()] = setBit(occupancy[color.white.ordinal()],squareIdx );
                            break;
                        case 'R':
                            bitBoards[color.white.ordinal()][pieces.rook.ordinal()] = setBit(bitBoards[color.white.ordinal()][pieces.rook.ordinal()], squareIdx);
                            occupancy[color.white.ordinal()] = setBit(occupancy[color.white.ordinal()],squareIdx );
                            break;
                        case 'B':
                            bitBoards[color.white.ordinal()][pieces.bishop.ordinal()] = setBit(bitBoards[color.white.ordinal()][pieces.bishop.ordinal()], squareIdx);
                            occupancy[color.white.ordinal()] = setBit(occupancy[color.white.ordinal()],squareIdx );
                            break;
                        case 'N':
                            bitBoards[color.white.ordinal()][pieces.knight.ordinal()] = setBit(bitBoards[color.white.ordinal()][pieces.knight.ordinal()], squareIdx);
                            occupancy[color.white.ordinal()] = setBit(occupancy[color.white.ordinal()],squareIdx );
                            break;
                        case 'K':
                            bitBoards[color.white.ordinal()][pieces.king.ordinal()] = setBit(bitBoards[color.white.ordinal()][pieces.king.ordinal()], squareIdx);
                            occupancy[color.white.ordinal()] = setBit(occupancy[color.white.ordinal()],squareIdx );
                            break;
                        case 'Q':
                            bitBoards[color.white.ordinal()][pieces.queen.ordinal()] = setBit(bitBoards[color.white.ordinal()][pieces.queen.ordinal()], squareIdx);
                            occupancy[color.white.ordinal()] = setBit(occupancy[color.white.ordinal()],squareIdx );
                            break;
                        case 'p':
                            bitBoards[color.black.ordinal()][pieces.pawn.ordinal()] = setBit(bitBoards[color.white.ordinal()][pieces.pawn.ordinal()], squareIdx);
                            occupancy[color.black.ordinal()] = setBit(occupancy[color.black.ordinal()],squareIdx );
                            break;
                        case 'r':
                            bitBoards[color.black.ordinal()][pieces.rook.ordinal()] = setBit(bitBoards[color.white.ordinal()][pieces.rook.ordinal()], squareIdx);
                            occupancy[color.black.ordinal()] = setBit(occupancy[color.black.ordinal()],squareIdx );
                            break;
                        case 'b':
                            bitBoards[color.black.ordinal()][pieces.bishop.ordinal()] = setBit(bitBoards[color.white.ordinal()][pieces.bishop.ordinal()], squareIdx);
                            occupancy[color.black.ordinal()] = setBit(occupancy[color.black.ordinal()],squareIdx );
                            break;
                        case 'n':
                            bitBoards[color.black.ordinal()][pieces.knight.ordinal()] = setBit(bitBoards[color.white.ordinal()][pieces.knight.ordinal()], squareIdx);
                            occupancy[color.black.ordinal()] = setBit(occupancy[color.black.ordinal()],squareIdx );
                            break;
                        case 'k':
                            bitBoards[color.black.ordinal()][pieces.king.ordinal()] = setBit(bitBoards[color.white.ordinal()][pieces.king.ordinal()], squareIdx);
                            occupancy[color.black.ordinal()] = setBit(occupancy[color.black.ordinal()],squareIdx );
                            break;
                        case 'q':
                            bitBoards[color.black.ordinal()][pieces.queen.ordinal()] = setBit(bitBoards[color.white.ordinal()][pieces.queen.ordinal()], squareIdx);
                            occupancy[color.black.ordinal()] = setBit(occupancy[color.black.ordinal()],squareIdx );
                            break;
                    }
                    board = board = setBit(board, squareIdx);
                    squareIdx+=1;
                }
            }
            rowIdx++;
        }
    }


    //making move
    static int[] index64 = new int[]{
            63, 30,  3, 32, 59, 14, 11, 33,
            60, 24, 50,  9, 55, 19, 21, 34,
            61, 29,  2, 53, 51, 23, 41, 18,
            56, 28,  1, 43, 46, 27,  0, 35,
            62, 31, 58,  4,  5, 49, 54,  6,
            15, 52, 12, 40,  7, 42, 45, 16,
            25, 57, 48, 13, 10, 39,  8, 44,
            20, 47, 38, 22, 17, 37, 36, 26
    };
    //getting LS1B using Matt Taylor's Folding trick
    public static int getLSB(long bb){
        if (bb == 0){
            return -1;
        }
        int folded;
        assert (bb != 0);
        bb ^= bb - 1;
        folded = (int) (bb ^ (bb >>> 32));
        return index64[folded * 0x78291ACF >>> 26];
    }
    public static List<Move> generateMovesList(color color, LookupTables tables){
        List<Move> list = new ArrayList<>();
        int LS1Bindex;
        int index;
        long temp;
        int piece = 0;
        for (long i : bitBoards[color.ordinal()]){
            LS1Bindex = getLSB(i);
            index = LS1Bindex;
            temp = i;
            while (index!=-1){
                long attacks = tables.getAttacks(pieces.values()[piece],LS1Bindex,color, occupancy[color.white.ordinal()],occupancy[color.black.ordinal()]);
                int LS1Bindex2 = getLSB(attacks);
                int index2 = LS1Bindex2;
                while (index2!=-1){
                    list.add(new Move(squares.values()[LS1Bindex], squares.values()[LS1Bindex2],pieces.values()[piece], color));
                    attacks = attacks>>>(index2+1);
                    index2 = getLSB(attacks);
                    LS1Bindex2 += index2+1;
                }
                //list.put(LS1Bindex, tables.getAttacks(pieces.values()[piece],LS1Bindex,color, occupancy[color.white.ordinal()],occupancy[color.black.ordinal()]));
                i = i>>>(index+1);
                index = getLSB(i);
                LS1Bindex += index+1;
            }
            i = temp;
            piece++;
        }
        return list;
    }


    public static Move decide(List<Move> movesList){
        Random rand = new Random();
        int randomInt = rand.nextInt(movesList.size());
        return (movesList.get(randomInt));
    }


    public static void makeMove(Move move){
        //remove bit from previous position and set on new position for pieces bitboard
        bitBoards[move.getColor().ordinal()][move.getPiece().ordinal()] = removeBit(bitBoards[move.getColor().ordinal()][move.getPiece().ordinal()],move.getSource());
        bitBoards[move.getColor().ordinal()][move.getPiece().ordinal()] = setBit(bitBoards[move.getColor().ordinal()][move.getPiece().ordinal()], move.getTarget());

        //remove bit from previous position and set on new position for occupancy bitboard
        occupancy[move.getColor().ordinal()] = removeBit( occupancy[move.getColor().ordinal()],move.getSource());
        occupancy[move.getColor().ordinal()] = setBit( occupancy[move.getColor().ordinal()], move.getTarget());

        //remove bit if captured opponent pieces
        long enemyOcc = occupancy[(move.getColor().ordinal()+1)%2];
        occupancy[(move.getColor().ordinal()+1)%2] = removeBit(occupancy[(move.getColor().ordinal()+1)%2],move.getTarget());
        if (enemyOcc != occupancy[(move.getColor().ordinal()+1)%2]){    //check if captured any piece
            for (int i=0;i< bitBoards[(move.getColor().ordinal()+1)%2].length;i++){
                if ((bitBoards[(move.getColor().ordinal()+1)%2][i] & bitBoards[(move.getColor().ordinal()+1)%2][move.getPiece().ordinal()])!=0){
                    bitBoards[(move.getColor().ordinal()+1)%2][i] = removeBit(bitBoards[(move.getColor().ordinal()+1)%2][i],move.getTarget());
                    break;
                }
            }
        }
    }

    public static Move encode(String string, color color){
        squares s1 = squares.valueOf(string.substring(0,2));
        squares s2 = squares.valueOf(string.substring(2,4));
        long pos = 0;
        pos = setBit(pos,s1);
        pieces piece = pieces.king;
        for (int i=0;i< bitBoards[(color.ordinal()+1)%2].length;i++){
            if ((bitBoards[color.ordinal()][i] & pos)!=0){
                piece = pieces.values()[i];
                break;
            }
        }
        return new Move(s1,s2,piece,color);
    }

    //todo: encode/decode move, makemove
    //todo: perft function
    //todo: minmax
}
