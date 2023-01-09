/*Demonstrate root ,child node understanding : 
Create a circle as child node and add to the root node pane*/
package AJ_Exercises;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class Ex2 extends Application {
     public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        Circle c = new Circle(130,130,80);
        Pane root = new Pane(c);
        Scene scene = new Scene(root,300,300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

