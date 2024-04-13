import com.example.MinMaxAgent;
import com.example.pieces.Game;
import com.example.pieces.Piece;
import com.example.pieces.Position;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotEquals;

public class BotTest {

    @Test
    public void botMove(){
        Game game = new Game();
        MinMaxAgent agent = new MinMaxAgent(game, 'w');
        Map<Position, Piece> prev = new HashMap<>(game.whitePieces);
        agent.makeMove();
        Map<Position, Piece> next = new HashMap<>(game.whitePieces);
        assertNotEquals(prev, next);
    }
}
