/*Using canvas and graphicscontext methods*/
package AJ_Exercises;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class Ex12 extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(400,300);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(3);
        gc.strokeOval(100, 50, 200, 200);
        gc.fillOval(155, 100, 15, 20);
        gc.fillOval(230, 100, 15, 20);
        gc.strokeArc(150, 160, 100, 50, 180, 180, ArcType.OPEN);
        HBox hbox = new HBox();
        Button btnfill = new Button("Fill");
        btnfill.setOnAction(e-> fill(gc));
        Button btncl = new Button("Clear");
        btncl.setOnAction(e-> clear(gc));
        hbox.getChildren().addAll(btnfill,btncl);
        hbox.setSpacing(15);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(5));
        VBox vbox = new VBox();
        vbox.getChildren().addAll(canvas,hbox);
        Scene scene = new Scene(vbox,400,400);
        primaryStage.setTitle("Smiley");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void fill(GraphicsContext gc){
       gc.setFill(Color.YELLOW);
       gc.fillOval(100, 50, 200, 200);
       gc.setFill(Color.BLACK);
       gc.fillOval(155, 100, 15, 20);
       gc.fillOval(230, 100, 15, 20);
       gc.strokeArc(150, 160, 100, 50, 180, 180, ArcType.OPEN);
    }
    public void clear(GraphicsContext gc){
       gc.clearRect(100, 50, 200, 200);
       gc.strokeOval(100, 50, 200, 200);
       gc.fillOval(155, 100, 15, 20);
       gc.fillOval(230, 100, 15, 20);
       gc.strokeArc(150, 160, 100, 50, 180, 180, ArcType.OPEN);
    }
}
/*strokeArc(double x, double y, double w, double h, double startAngle, double arcExtent, ArcType closure);
Strokes an Arc using the current stroke paint*/