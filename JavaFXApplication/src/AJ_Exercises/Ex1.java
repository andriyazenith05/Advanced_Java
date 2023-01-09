/*Demonstrate lifycycle methods using javafx*/
package AJ_Exercises;
import javafx.application.Application;
import javafx.stage.Stage;

public class Ex1 extends Application {
     public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        System.out.println("At Start");
        primaryStage.show();
    }
    @Override
    public void init() {
        System.out.println("At Init"); 
    }
    @Override
    public void stop() {
        System.out.println("At Stop"); 
    } 
}
