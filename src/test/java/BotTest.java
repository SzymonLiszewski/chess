import com.example.MinMaxAgent;
import com.example.pieces.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class BotTest {

    @Test
    public void botMove(){
        Game game = new Game();
        MinMaxAgent agent = new MinMaxAgent(game, 'w');
        Map<Position, Piece> prev = new HashMap<>(game.whitePieces);
        agent.makeMove(2);
        Map<Position, Piece> next = new HashMap<>(game.whitePieces);
        assertNotEquals(prev, next);
    }

    @Test
    public void mateInOne(){
        Game game = new Game();
        MinMaxAgent agent = new MinMaxAgent(game, 'w');
        game.readFen("r2k1r2/p3b2p/2Q5/1pN5/P7/4pP1q/1P2KP2/8 w - - 1 27");
        Map<Position, Piece> prev = new HashMap<>(game.whitePieces);
        agent.makeMove(2);
        assertTrue(game.whitePieces.get(new Position(2,7)) instanceof Knight);
    }
    @Test
    public void mateInOne2(){
        Game game = new Game();
        MinMaxAgent agent = new MinMaxAgent(game, 'w');
        game.readFen("3r4/5R2/pp5p/4ppp1/5k2/3r1P2/PP2RKPP/8 w - - 0 33");
        Map<Position, Piece> prev = new HashMap<>(game.whitePieces);
        agent.makeMove(2);
        assertTrue(game.whitePieces.get(new Position(5,4)) instanceof Rook || game.whitePieces.get(new Position(7,3)) instanceof Pawn);
    }

    @Test
    public void mateInOne3(){
        Game game = new Game();
        MinMaxAgent agent = new MinMaxAgent(game, 'w');
        game.readFen("3qkbnr/R3p3/7p/1r4p1/4p3/2P5/5PPP/3QK1NR w Kk - 0 19");
        Map<Position, Piece> prev = new HashMap<>(game.whitePieces);
        agent.makeMove(2);
        assertTrue(game.whitePieces.get(new Position(8,5)) instanceof Queen);
    }
    @Test
    public void mateInTwo(){
        Game game = new Game();
        MinMaxAgent agent = new MinMaxAgent(game, 'w');
        game.readFen("5r1k/5Rpp/rq2n2N/3pP1Q1/8/1p6/1PP3PP/2KR4 w - - 0 26");
        Map<Position, Piece> prev = new HashMap<>(game.whitePieces);
        agent.makeMove(2);
        Map<Position, Piece> next = new HashMap<>(game.whitePieces);
        //assertNotEquals(prev, next);
        //System.out.print(game.whitePieces.get(new Position(7,7)).getClass().getName());
        assertTrue(game.whitePieces.get(new Position(7,7)) instanceof Queen);
    }

    @Test
    public void mateInTwo2(){
        Game game = new Game();
        MinMaxAgent agent = new MinMaxAgent(game, 'w');
        game.readFen("r7/2pR4/p3Prnk/1p4Rp/6pP/8/P1P2PP1/6K1 w - - 1 30");
        Map<Position, Piece> prev = new HashMap<>(game.whitePieces);
        agent.makeMove(2);
        Map<Position, Piece> next = new HashMap<>(game.whitePieces);
        //assertNotEquals(prev, next);
        //System.out.print(game.whitePieces.get(new Position(7,7)).getClass().getName());
        assertTrue(game.whitePieces.get(new Position(8,5)) instanceof Rook);
    }

    @Test
    public void mateInTwo3(){
        Game game = new Game();
        MinMaxAgent agent = new MinMaxAgent(game, 'w');
        game.readFen("6k1/1R6/5P1R/p1b5/8/8/3r1r2/4K3 w - - 1 50");
        Map<Position, Piece> prev = new HashMap<>(game.whitePieces);
        agent.makeMove(2);
        Map<Position, Piece> next = new HashMap<>(game.whitePieces);
        //assertNotEquals(prev, next);
        //System.out.print(game.whitePieces.get(new Position(7,7)).getClass().getName());
        assertTrue(game.whitePieces.get(new Position(7,7)) instanceof Rook);
    }
}
