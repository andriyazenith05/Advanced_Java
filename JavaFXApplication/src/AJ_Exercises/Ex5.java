/*Demo on Stage class and its methods*/
package AJ_Exercises;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Ex5 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(e -> {
               popmessage();
        });
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 300, 250);
        scene.setFill(Color.DARKSLATEGREY);
        primaryStage.setTitle("Stage class");
        primaryStage.setWidth(1000);
        primaryStage.setHeight(800);
        primaryStage.sizeToScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    public void popmessage(){
        final Stage dialog=new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Label l1=new Label("PLEASE CLICK OK TO CONFIRM DETAILS");
        Button okbutton=new Button("OK");
        okbutton.setOnAction(e -> dialog.close());
        FlowPane f1=new FlowPane();
        f1.getChildren().addAll(l1,okbutton);
        f1.setAlignment(Pos.CENTER);
        f1.setStyle("-fx-background-color:grey");
        Scene scene2=new Scene(f1,300,400);
        scene2.setFill(Color.DARKCYAN);
        dialog.setScene(scene2);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.showAndWait();
    }
}
