/*UI Controls Demo*/
package AJ_Exercises;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Ex8 extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane bpane = new BorderPane();
        
        FlowPane fp1 = new FlowPane();
        fp1.setHgap(20);
        Label msgl = new Label("Enter a new message :");
        TextField msgtf = new TextField();
        msgtf.setPrefSize(550, 45);
        fp1.getChildren().addAll(msgl,msgtf);
        fp1.setAlignment(Pos.CENTER);
        fp1.setPadding(new Insets(8));
        bpane.setTop(fp1);
        
        Text t = new Text("JavaFX programming is cool!");
        t.setStyle("-fx-font-weight: bold");
        t.setFont(new Font("Arial",23));
        t.setTextAlignment(TextAlignment.CENTER);
        bpane.setCenter(t);
        
        VBox vbox1 = new VBox(); 
        ToggleGroup tp1 = new ToggleGroup();
        RadioButton rb1 =new RadioButton("Red");
        RadioButton rb2 = new RadioButton("Green");
        RadioButton rb3 = new RadioButton("Blue");
        rb1.setToggleGroup(tp1);
        rb2.setToggleGroup(tp1);
        rb3.setToggleGroup(tp1);
        vbox1.getChildren().addAll(rb1,rb2,rb3);
        vbox1.setPadding(new Insets(5));
        rb1.setPadding(new Insets(15));
        rb2.setPadding(new Insets(15));
        rb3.setPadding(new Insets(15));
        bpane.setLeft(vbox1);
        
        VBox vbox2 = new VBox(); 
        CheckBox cb1 = new CheckBox("Bold");
        CheckBox cb2 = new CheckBox("Italic");
        vbox2.getChildren().addAll(cb1,cb2);
        vbox2.setPadding(new Insets(5));
        cb1.setPadding(new Insets(15));
        cb2.setPadding(new Insets(15));
        bpane.setRight(vbox2);
       
        FlowPane fp2 = new FlowPane();
        fp2.setHgap(30);
        ToggleGroup tp2 = new ToggleGroup();
        ToggleButton tb1 = new ToggleButton("Left");
        ToggleButton tb2 = new ToggleButton("Right");
        tb1.setToggleGroup(tp2);
        tb2.setToggleGroup(tp2);
        fp2.getChildren().addAll(tb1,tb2);
        fp2.setAlignment(Pos.CENTER);
        fp2.setPadding(new Insets(8));
        bpane.setBottom(fp2);
        
        t.setStyle(
            "-fx-border-color: #ff0000;"+
            "-fx-border-width: 2px;" );
        fp1.setStyle(
            "-fx-border-color: #ff0000;"+
            "-fx-border-width: 2px;" );
        vbox1.setStyle(
            "-fx-border-color: #ff0000;"+
            "-fx-border-width: 2px;" );
        vbox2.setStyle(
            "-fx-border-color: #ff0000;"+
            "-fx-border-width: 2px;" );
        fp2.setStyle(
            "-fx-border-color: #ff0000;"+
            "-fx-border-width: 2px;" );
        
        Scene scene = new Scene(bpane, 800, 340);
        primaryStage.setTitle("UIControls Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
