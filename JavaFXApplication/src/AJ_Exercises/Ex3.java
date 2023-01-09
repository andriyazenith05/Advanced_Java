/*To demonstrate the flowpane as the root node*/
package AJ_Exercises;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class Ex3 extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        FlowPane fp = new FlowPane();
        fp.setPadding(new Insets(10,5,10,5));
        fp.setVgap(10);
        fp.setHgap(10);
        fp.setAlignment(Pos.CENTER);
        
        Label l1 = new Label("FULLNAME");
        TextField tf1 = new TextField();
        Label l2 = new Label("LASTNAME");
        TextField tf2 = new TextField();
        
        fp.getChildren().addAll(l1,tf1,l2,tf2);
        Scene s = new Scene(fp, 800, 450);
        primaryStage.setTitle("FlowPane");
        primaryStage.setScene(s);
        primaryStage.show();
    }
}

