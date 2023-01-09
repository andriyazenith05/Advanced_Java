/*Demo of a TextArea control*/
package AJ_Exercises;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Ex9 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        TextArea ta = new TextArea("Live the Life of Your Dreams: Be brave enough to live the life "
                + "of your dreams according to your vision and purpose instead of the expectations and "
                + "opinions of others.");
        ta.setPrefColumnCount(50);
        ta.setWrapText(true);
        ta.setFont(new Font("Arial",25));
        
        Label caretll = new Label("<<");
        caretll.setFont(new Font(23));
        caretll.setTextFill(Color.BLUE);
        caretll.setOnMouseEntered(e->caretll.setTextFill(Color.ORANGE));
        caretll.setOnMouseExited(e->caretll.setTextFill(Color.BLUE));
        
        Label caretrl = new Label(">>");
        caretrl.setFont(new Font(24));
        caretrl.setTextFill(Color.BLUE);
        caretrl.setOnMouseEntered(e->caretrl.setTextFill(Color.ORANGE));
        caretrl.setOnMouseExited(e->caretrl.setTextFill(Color.BLUE));
        
        Label wrapl = new Label("w");
        wrapl.setFont(new Font(24));
        wrapl.setTextFill(Color.BLUE);
        wrapl.setOnMouseEntered(e->wrapl.setTextFill(Color.ORANGE));
        wrapl.setOnMouseExited(e->wrapl.setTextFill(Color.BLUE));
        wrapl.setOnMouseClicked(e->ta.setWrapText(!ta.isWrapText()));
        
        HBox hbox = new HBox(caretll,wrapl,caretrl);
        hbox.setSpacing(12);
        hbox.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(hbox,ta);
        
        Scene scene = new Scene(vbox, 500, 340);
        primaryStage.setTitle("TextArea control Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

/*Label caretstartl = new Label("<<");
        caretstartl.setFont(new Font(24));
        caretstartl.setTextFill(Color.BLUE);
        caretstartl.setOnMouseEntered(e->caretstartl.setTextFill(Color.ORANGE));
        caretstartl.setOnMouseExited(e->caretstartl.setTextFill(Color.BLUE));
        
        Label caretendl = new Label(">>");
        caretendl.setFont(new Font(24));
        caretendl.setTextFill(Color.BLUE);
        caretendl.setOnMouseEntered(e->caretendl.setTextFill(Color.ORANGE));
        caretendl.setOnMouseExited(e->caretendl.setTextFill(Color.BLUE));*/