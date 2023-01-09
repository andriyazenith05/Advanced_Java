/*Student_JDBC*/
package jdbc;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StudentJDBC extends Application {
    @Override
    public void start(Stage primaryStage) {
    Text t=new Text("Student Information");
    t.setFont(new Font("Arial",25));
    VBox vbox = new VBox();
    vbox.setAlignment(Pos.CENTER);
    Label l1=new Label("Name"); 
    TextField tf1=new TextField();
    Label l2=new Label("Dept No");
    TextField tf2=new TextField();
    Label l3=new Label("Marks");
    TextField tf3=new TextField();
    Button btn=new Button("Save");
    GridPane gp=new GridPane();
    gp.setAlignment(Pos.CENTER);
    gp.setHgap(10);
    gp.setVgap(10);
    gp.setPadding(new Insets(8));
    gp.add(l1,0,0); 
    gp.add(l2,0,1);
    gp.add(l3,0,2);
    gp.add(tf1,1,0);
    gp.add(tf2,1,1);
    gp.add(tf3,1,2);
    gp.add(btn,1,3); 
    vbox.getChildren().addAll(t,gp);
    btn.setOnAction(e ->{
    try
    {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/StudentJDBC","ucsa209","209");
        PreparedStatement ps=con.prepareStatement("insert into STUDENT values(?,?,?)");
        ps.setString(1,tf1.getText()); 
        ps.setString(2,tf2.getText());
        ps.setString(3,tf3.getText());
        int res = ps.executeUpdate();
        System.out.println("All Records Inserted "+ res);
    }
    catch(ClassNotFoundException | SQLException err){
        System.out.println(err);
    }
    });
 
   Scene scene = new Scene(vbox,400,450);
   primaryStage.setTitle("Student JDBC");
   primaryStage.setScene(scene);
   primaryStage.show(); 
   }
 
   public static void main(String[] args) {
   launch(args);
   } 
}