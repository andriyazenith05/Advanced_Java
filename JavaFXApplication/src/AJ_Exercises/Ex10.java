package AJ_Exercises;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ex10 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        VBox vbox = new VBox();
        Label nl = new Label("Name");
        TextField ntf = new TextField();
        Label dobl = new Label("Date of birth");
        DatePicker dp = new DatePicker();
        Label gl = new Label("Gender");
        ToggleGroup tp1 = new ToggleGroup();
        RadioButton rb1 =new RadioButton("Male");
        RadioButton rb2 = new RadioButton("Female");
        rb1.setToggleGroup(tp1);
        rb2.setToggleGroup(tp1);
        Label govtempl = new Label("Government Employee");
        ToggleGroup tp2 = new ToggleGroup();
        ToggleButton tb1 = new ToggleButton("Yes");
        ToggleButton tb2 = new ToggleButton("No");
        tb1.setToggleGroup(tp2);
        tb2.setToggleGroup(tp2);
        Label coml = new Label("Community");
        CheckBox cb1 = new CheckBox("BC");
        CheckBox cb2 = new CheckBox("OC");
        Hyperlink link = new Hyperlink("https://www.google.com/");
        Button btn = new Button("Register");
        TextArea ta = new TextArea();
        btn.setOnAction(e->
        {
            ta.setText("Name                             : "+ntf.getText()+"\n");
            ta.appendText("Date of birth                  : "+dp.getValue()+"\n");
            if(rb1.isSelected()){
                ta.appendText("Gender                          : Male");
            }
            else if(rb2.isSelected()){
                ta.appendText("Gender                          : Female");
            }
            ta.appendText("\n");
            
            if(tb1.isSelected()){
                ta.appendText("Government Employee : Yes");
            }
            else if(tb2.isSelected()){
                ta.appendText("Government Employee : No");
            }
            ta.appendText("\n");
            
            if(cb1.isSelected()){
               ta.appendText("Community                   : BC"); 
            }
            else if(cb2.isSelected()){
               ta.appendText("Community                   : OC"); 
            }
        });
        vbox.getChildren().addAll(nl,ntf,dobl,dp,gl,rb1,rb2,govtempl,tb1,tb2,coml,cb1,cb2,link,btn,ta);
        
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setTitle("Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
