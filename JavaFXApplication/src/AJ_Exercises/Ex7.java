/*Basic controls-RadioButton,ToggleButton,CheckBox*/
package AJ_Exercises;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Ex7 extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        GridPane gpane = new GridPane();
        Label namel = new Label("Name");
        namel.setStyle("-fx-font-weight: bold");
        TextField nametf = new TextField();
        Label dobl = new Label("Date of birth");
        dobl.setStyle("-fx-font-weight: bold");
        DatePicker dp = new DatePicker();
        Label genderl = new Label("Gender ");
        genderl.setStyle("-fx-font-weight: bold");
        FlowPane fp1 = new FlowPane();
        fp1.setHgap(30);
        ToggleGroup tp1 = new ToggleGroup();
        RadioButton rb1 =new RadioButton("Male");
        RadioButton rb2 = new RadioButton("Female");
        rb1.setToggleGroup(tp1);
        rb2.setToggleGroup(tp1);
        fp1.getChildren().addAll(rb1,rb2);
        Label rl = new Label("Reservation");
        rl.setStyle("-fx-font-weight: bold");
        FlowPane fp2 = new FlowPane();
        fp2.setHgap(40);
        ToggleGroup tp2 = new ToggleGroup();
        ToggleButton tb1 = new ToggleButton("Yes");
        ToggleButton tb2 = new ToggleButton("No");
        tb1.setToggleGroup(tp2);
        tb2.setToggleGroup(tp2);
        fp2.getChildren().addAll(tb1,tb2);
        Label techl = new Label("Technologies Known ");
        techl.setStyle("-fx-font-weight: bold");
        FlowPane fp3 = new FlowPane();
        fp3.setHgap(30);
        CheckBox cb1 = new CheckBox("Java");
        CheckBox cb2 = new CheckBox("DotNet");
        fp3.getChildren().addAll(cb1,cb2);
        Label pal = new Label("Pet Animals");
        ComboBox<String> cb = new ComboBox<>();
        ObservableList<String> data = FXCollections.observableArrayList();
        data.addAll("Dog","Cat","Horse");
        cb.itemsProperty().setValue(data);
        
        
 
        gpane.setAlignment(Pos.CENTER);
        gpane.setPrefSize(50, 50);
        gpane.setHgap(15);
        gpane.setVgap(15);
        gpane.setPadding(new Insets(8));
        gpane.add(namel,0,0);
        gpane.add(nametf,1,0);
        gpane.add(dobl,0,1);
        gpane.add(dp,1,1);
        gpane.add(genderl,0,2);
        gpane.add(fp1,1,2);
        gpane.add(rl,0,3);
        gpane.add(fp2,1,3);
        gpane.add(techl,0,4);
        gpane.add(fp3,1,4);
        gpane.add(pal,0,5);
        gpane.add(cb,1,5);
        
        Scene scene = new Scene(gpane, 800, 450);
        primaryStage.setTitle("Registration Form");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

