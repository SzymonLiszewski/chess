import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MyApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Graphic interface initialization
        ImageView imageView = new ImageView(new Image("/resources/images/board.svg"));
        StackPane root = new StackPane(imageView);
        Scene scene = new Scene(root, 400, 300);

        // main window configuration
        primaryStage.setTitle("Display Image in JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}