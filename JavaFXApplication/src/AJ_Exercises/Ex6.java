/*Demo on Scene class and its methods*/
package AJ_Exercises;
import javafx.application.Application;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Ex6 extends Application {
    @Override
    public void start(Stage primaryStage) {
        HBox hbox=new HBox();
        hbox.setBackground(null);
        hbox.setAlignment(Pos.CENTER);
        Button btn1 = new Button();
        btn1.setText("button1");
        Button btn2 = new Button();
        btn2.setText("button2");
        Button btn3 = new Button();
        btn3.setText("button3");
        hbox.getChildren().addAll(btn1,btn2,btn3);
        VBox vbox=new VBox();
        vbox.setBackground(null);
        vbox.setAlignment(Pos.CENTER);
        Button btn4=new Button("button4");
        Button btn5=new Button("button5");
        vbox.getChildren().addAll(btn4,btn5);

        Scene scene = new Scene(hbox, 300, 250);
        scene.setOnMouseClicked(e-> {
            scene.setRoot(vbox);                         
        });
        scene.setOnKeyTyped(e-> {
            scene.setRoot(hbox);                         
        });
        scene.nodeOrientationProperty().setValue(NodeOrientation.LEFT_TO_RIGHT);
        scene.setCursor(Cursor.OPEN_HAND);
        scene.setFill(Color.CADETBLUE);

        primaryStage.setTitle("Scene class");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

