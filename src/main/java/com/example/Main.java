package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import com.example.pieces.Game;
import com.example.pieces.Piece;
import com.example.pieces.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<Position,ImageView> views = new HashMap<>();
        for (Map.Entry<Position, Piece> element : game.whitePieces.entrySet()){
            views.put(element.getKey(),new ImageView(element.getValue().getImage()));
        }
        for (Map.Entry<Position, Piece> element : game.blackPieces.entrySet()){
            views.put(element.getKey(),new ImageView(element.getValue().getImage()));
        }
        //setting pieces display position
        for (Map.Entry<Position, ImageView> element : views.entrySet()){
            element.getValue().setTranslateX(-350+(element.getKey().getX()-1)*100);
            element.getValue().setTranslateY(+350+(element.getKey().getY()-1)*(-100));
        }

        StackPane root = new StackPane();
        root.getChildren().add(imageView);

        root.setOnMouseClicked(event -> {
            double mouseX = event.getSceneX();
            double mouseY = event.getSceneY();
            int x1 = (int)((mouseX)/100+1);
            int y1 = (int)(-(mouseY)/100+9);
            if (game.onMove == 'w'){
                if (game.whitePieces.get(new Position(x1,y1)) != null) {
                    List<Position> l = game.whitePieces.get(new Position(x1,y1)).getPossibleMoves();
                }
                if (onMove == null){
                    onMove = game.whitePieces.get(new Position(x1,y1));
                    //System.out.println(onMove.getImage());
                }
                else{
                    onMove.Move(new Position(x1, y1));
                    //System.out.println("nowa poz: "+String.valueOf(game.pieces.get(new Position(x1,y1)).getPosition().getX())+", "+String.valueOf(game.pieces.get(new Position(x1,y1)).getPosition().getY()));
                    drawBoard(game, stage, imageUrl);
                    onMove = null;
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
                    onMove.Move(new Position(x1, y1));
                    //System.out.println("nowa poz: "+String.valueOf(game.pieces.get(new Position(x1,y1)).getPosition().getX())+", "+String.valueOf(game.pieces.get(new Position(x1,y1)).getPosition().getY()));
                    drawBoard(game, stage, imageUrl);
                    onMove = null;
                }
            }

        });


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
    public static void main(String[] args) {
        System.out.println("World XDD");
        launch();
    }

}
