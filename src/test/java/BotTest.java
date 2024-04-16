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
        game.readFen("3r4/p4R2/1p5p/4ppp1/5k2/3r1P2/PP2RKPP/8 b - - 1 32");
        Map<Position, Piece> prev = new HashMap<>(game.whitePieces);
        agent.makeMove(2);
        assertTrue(game.whitePieces.get(new Position(5,4)) instanceof Rook);
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
        assertNotEquals(prev, next);
        //assertTrue(game.whitePieces.get(new Position(7,7)) instanceof Queen);
    }

}
