/*Integrating Scene graph to javafx application*/
package AJ_Exercises;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ex4 extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        HBox root = new HBox();
        VBox vb = new VBox();
        root.setPadding(new Insets(20));
        
        Button btn1 = new Button("Button1");
        Button btn2 = new Button("Button2");
        Button btn3 = new Button("Button3");
        root.getChildren().addAll(btn1,btn2,btn3);
        
        Button btn4 = new Button("Button4");
        Button btn5 = new Button("Button5");
        vb.getChildren().addAll(btn4,btn5);
        
        root.getChildren().add(vb);
        
        Scene scene = new Scene(root, 350, 250);
        primaryStage.setTitle("HBox&VBox");
        primaryStage.setScene(scene);
        primaryStage.show();
    }  
}

