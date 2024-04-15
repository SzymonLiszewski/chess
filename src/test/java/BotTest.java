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
        game.readFen("rn2Qbk1/pp3p1p/2p3p1/8/3PBnq1/2P5/PP1N1PPP/R1B2K1R w - - 1 19");
        Map<Position, Piece> prev = new HashMap<>(game.whitePieces);
        agent.makeMove(2);
        assertTrue(game.whitePieces.get(new Position(5,8)) instanceof Queen);
    }

}
