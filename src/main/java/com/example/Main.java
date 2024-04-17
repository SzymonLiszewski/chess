package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import com.example.pieces.Game;
import com.example.pieces.Piece;
import com.example.pieces.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Main extends Application {
    public static Piece onMove = null;

    @Override
    public void start(Stage stage) {
        Game game = new Game();
        String imageUrl = getClass().getResource("/images/wood2.jpg").toExternalForm();
        Parameters params = getParameters();
        String sParams[] = params.getRaw().toArray(new String[0]);
        if (sParams.length > 0 && Objects.equals(sParams[1], "--fen")) {
            game.readFen("r2k1r2/p3b2p/2Q5/1pN5/P7/4pP1q/1P2KP2/8 w - - 1 27");
        }
        MinMaxAgent bot = new MinMaxAgent(game, 'w');
        drawBoard(game, stage, imageUrl, params.getRaw().toArray(new String[0]), bot);
    }

    public static void drawBoard(Game game, Stage stage, String imageUrl, String[] params, MinMaxAgent bot){
        if (game.onMove == 'w') {
            if (params.length > 0 && Objects.equals(params[0], "--pva")) {
                bot.makeMove(1);
                drawBoard(game, stage, imageUrl, params, bot);
            }
        }
        ImageView imageView = new ImageView(new Image(imageUrl));

        //adding views of chess pieces
        Map<Position,ImageView> views = new HashMap<>();
        for (Map.Entry<Position, Piece> element : game.whitePieces.entrySet()){
            views.put(element.getKey(),new ImageView(new Image(element.getValue().getImage())));
        }
        for (Map.Entry<Position, Piece> element : game.blackPieces.entrySet()){
            views.put(element.getKey(),new ImageView(new Image(element.getValue().getImage())));
        }
        //setting pieces display position
        for (Map.Entry<Position, ImageView> element : views.entrySet()){
            element.getValue().setTranslateX(-350+(element.getKey().getX()-1)*100);
            element.getValue().setTranslateY(+350+(element.getKey().getY()-1)*(-100));
        }

        StackPane root = new StackPane();
        root.getChildren().add(imageView);

        root.setOnMouseClicked(event -> handleInteractions(game, event, stage, imageUrl, params, bot));


        for (ImageView view : views.values()){
            root.getChildren().add(view);
        }

        // creating new scene
        Scene scene = new Scene(root, 800, 800);

        // setting title and scene
        stage.setTitle("Display SVG in JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void handleInteractions(Game game, MouseEvent event, Stage stage, String imageUrl, String[] params, MinMaxAgent bot){
            double mouseX = event.getSceneX();
            double mouseY = event.getSceneY();
            int x1 = (int)((mouseX)/100+1);
            int y1 = (int)(-(mouseY)/100+9);
            if (game.onMove == 'w'){
                if (params.length>0 && Objects.equals(params[0], "--pva")){
                    //bot.makeMove();
                    //drawBoard(game, stage, imageUrl, params, bot);
                }
                else{
                    if (game.whitePieces.get(new Position(x1,y1)) != null) {
                        List<Position> l = game.whitePieces.get(new Position(x1,y1)).getPossibleMoves();
                    }
                    if (onMove == null){
                        onMove = game.whitePieces.get(new Position(x1,y1));
                        //System.out.println(onMove.getImage());
                    }
                    else{
                        onMove.Move(new Position(x1, y1), true);
                        //System.out.println("nowa poz: "+String.valueOf(game.pieces.get(new Position(x1,y1)).getPosition().getX())+", "+String.valueOf(game.pieces.get(new Position(x1,y1)).getPosition().getY()));
                        drawBoard(game, stage, imageUrl, params, bot);
                        onMove = null;
                    }
                }
            }
            else if (game.onMove == 'b'){
                if (game.blackPieces.get(new Position(x1,y1)) != null) {
                    List<Position> l = game.blackPieces.get(new Position(x1,y1)).getPossibleMoves();
                }
                if (onMove == null){
                    onMove = game.blackPieces.get(new Position(x1,y1));
                    //System.out.println(onMove.getImage());
                }
                else{
                    onMove.Move(new Position(x1, y1), true);
                    //System.out.println("nowa poz: "+String.valueOf(game.pieces.get(new Position(x1,y1)).getPosition().getX())+", "+String.valueOf(game.pieces.get(new Position(x1,y1)).getPosition().getY()));
                    drawBoard(game, stage, imageUrl, params, bot);
                    onMove = null;
                }
            }
    }
    public static void main(String[] args) {
        launch(args);
    }

}
