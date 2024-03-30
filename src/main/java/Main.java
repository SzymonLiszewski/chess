import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pieces.Piece;
import pieces.Position;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    public static Piece onMove = null;

    @Override
    public void start(Stage stage) {
        System.out.println("Hello XD");
        Game game = new Game();
        String imageUrl = getClass().getResource("/images/wood2.jpg").toExternalForm();
        drawBoard(game, stage, imageUrl);
    }

    public static void drawBoard(Game game, Stage stage, String imageUrl){
        ImageView imageView = new ImageView(new Image(imageUrl));

        //adding views of chess pieces
        List<ImageView> views = new ArrayList<>();
        for (Piece element : game.pieces){
            views.add(new ImageView(element.getImage()));
        }
        //setting pieces display position
        for (int i=0;i<views.size();i++){
            views.get(i).setTranslateX(-350+(game.pieces.get(i).getPosition().getX()-1)*100);
            views.get(i).setTranslateY(+350+(game.pieces.get(i).getPosition().getY()-1)*(-100));
        }

        StackPane root = new StackPane();
        root.getChildren().add(imageView);

        root.setOnMouseClicked(event -> {
            double mouseX = event.getSceneX();
            double mouseY = event.getSceneY();
            System.out.println("Kliknięto myszką na pozycji X: " + (int)((mouseX)/100+1) + ", Y: " + (int)(-(mouseY)/100+9));
            int x1 = (int)((mouseX)/100+1);
            int y1 = (int)(-(mouseY)/100+9);
            if (x1 == game.pieces.get(0).getPosition().getX() && y1 == game.pieces.get(0).getPosition().getY()) {
                List<Position> l = game.pieces.get(0).getPossibleMoves();
                for (Position p : l) {
                    //System.out.println(String.valueOf(p.getX())+", "+String.valueOf(p.getY()));
                }
            }
                if (onMove == null){
                    onMove = game.pieces.get(0);
                }
                else{
                    onMove.Move(new Position(x1, y1));
                    System.out.println("nowa poz: "+String.valueOf(game.pieces.get(0).getPosition().getX())+", "+String.valueOf(game.pieces.get(0).getPosition().getY()));
                    drawBoard(game, stage, imageUrl);
                    onMove = null;
                }
        });


        for (ImageView view : views){
            root.getChildren().add(view);
        }

        // creating new scene
        Scene scene = new Scene(root, 800, 800);

        // setting title and scene
        stage.setTitle("Display SVG in JavaFX");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        System.out.println("World XDD");
        launch();
    }

}
