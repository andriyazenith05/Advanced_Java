package Bus_Ticket_Reservation;
import java.sql.*;
import java.util.Optional; 
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class Code extends Application {
    public static void main(String args[])
    {
        launch(args);   
        
    }
    
    @Override
    public void start(Stage primaryStage) {
        HBox mainpage = new HBox();
        Image img = new Image(getClass().getResourceAsStream("MainImage1.jpg"));
        ImageView imgv = new ImageView(img);
        imgv.setFitWidth(950);
        imgv.setFitHeight(1010);
        VBox login = new VBox();
        Label logint = new Label("LOGIN"); 
        logint.setFont(new Font("Arial",36));
        FlowPane lfp1 = new FlowPane(logint);
        lfp1.setAlignment(Pos.CENTER);
        lfp1.setPadding(new Insets(30));
        GridPane logingp = new GridPane();
        Image imgui = new Image(getClass().getResourceAsStream("usericon.png"));
        ImageView imguiv = new ImageView(imgui);
        imguiv.setFitWidth(50);
        imguiv.setFitHeight(50);
        TextField usernametf = new TextField();
        usernametf.setPrefSize(270, 50);
        usernametf.setPromptText("Username");
        Image imgpi = new Image(getClass().getResourceAsStream("pwdicon.png"));
        ImageView imgpiv = new ImageView(imgpi);
        imgpiv.setFitWidth(50);
        imgpiv.setFitHeight(50);
        PasswordField pwdtf = new PasswordField();
        pwdtf.setPrefSize(270, 50);
        pwdtf.setPromptText("Password");
        logingp.setHgap(30);
        logingp.setVgap(30);
        logingp.add(imguiv, 0, 0);
        logingp.add(usernametf, 1, 0);
        logingp.add(imgpiv, 0, 1);
        logingp.add(pwdtf, 1, 1);
        Button loginbtn = new Button("Login");
        loginbtn.setId("loginbtn");
        Alert a = new Alert(AlertType.NONE); 
        
        loginbtn.setOnAction(e -> {
            try
            {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/Bus_Ticket_Reservation","andriya","19ucsa209");
                Statement st=con.createStatement();
                ResultSet res=st.executeQuery("select * from login where login.username='"+usernametf.getText()+"' and login.password='"+pwdtf.getText()+"'");
                if(res.next()){
                    a.setAlertType(AlertType.INFORMATION); 
                    a.setContentText("Login Successful"); 
                    Optional<ButtonType> result = a.showAndWait();
                    ButtonType button = result.orElse(ButtonType.CANCEL);
                    if (button == ButtonType.OK) {
                        
                        login();
                        primaryStage.close();
                    } 
                }
                else{
                    a.setAlertType(AlertType.INFORMATION); 
                    a.setContentText("Login Failed"); 
                    a.showAndWait();
                }
            }
            catch(ClassNotFoundException | SQLException err){
                System.out.println(err);
            }
            
        });
        loginbtn.setPrefSize(190, 50);
        FlowPane lfp2 = new FlowPane(loginbtn);
        lfp2.setAlignment(Pos.CENTER);
        lfp2.setPadding(new Insets(40,0,10,0));
        Button newloginbtn = new Button("Sign Up");
        newloginbtn.setId("newloginbtn");
        newloginbtn.setOnAction(e -> {
            signup();
        });
        newloginbtn.setPrefSize(190, 50);
        FlowPane lfp3 = new FlowPane(newloginbtn);
        lfp3.setAlignment(Pos.CENTER);
        lfp3.setPadding(new Insets(10,0,10,0));
        Button adminloginbtn = new Button("Admin Login");
        adminloginbtn.setId("adminloginbtn");
        adminloginbtn.setOnAction(e -> {
            adminlogin();
        });
        adminloginbtn.setPrefSize(190, 50);
        FlowPane lfp4 = new FlowPane(adminloginbtn);
        lfp4.setAlignment(Pos.CENTER);
        lfp4.setPadding(new Insets(10,0,10,0));
        login.getChildren().addAll(lfp1,logingp,lfp2,lfp3,lfp4);
        login.setPadding(new Insets(280));
        mainpage.getChildren().addAll(imgv,login);
        
        Scene scene = new Scene(mainpage);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Paradise Travel");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void signup(){
        Alert a = new Alert(AlertType.NONE); 
        final Stage dialog=new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox signup = new VBox();
        Label signupt = new Label("SIGN UP"); 
        signupt.setFont(new Font("Arial",36));
        FlowPane sufp1 = new FlowPane(signupt);
        sufp1.setAlignment(Pos.CENTER);
        sufp1.setPadding(new Insets(30));
        GridPane signupgp = new GridPane();
        TextField firstnametf = new TextField();
        firstnametf.setPrefSize(270, 50);
        firstnametf.setPromptText("First Name");
        TextField lastnametf = new TextField();
        lastnametf.setPrefSize(270, 50);
        lastnametf.setPromptText("Last Name");
        TextField usernametf = new TextField();
        usernametf.setPrefSize(270, 50);
        usernametf.setPromptText("Username");
        PasswordField pwdtf = new PasswordField();
        pwdtf.setPrefSize(270, 50);
        pwdtf.setPromptText("Password");
        PasswordField cpwdtf = new PasswordField();
        cpwdtf.setPrefSize(270, 50);
        cpwdtf.setPromptText("Confirm Password");
        Button signupbtn = new Button("Sign Up");
        signupbtn.setPrefSize(190, 50);
        signupbtn.setId("signupbtn");
        Button cancelbtn = new Button("Cancel");
        cancelbtn.setPrefSize(190, 50);
        cancelbtn.setId("cancelbtn");
        signupbtn.setOnAction(e -> {
            if(pwdtf.getText().equals(cpwdtf.getText()) && ((!(firstnametf.getText().isEmpty())) || (!(lastnametf.getText().isEmpty())) || (!(usernametf.getText().isEmpty())) || (!(pwdtf.getText().isEmpty())) || (!(cpwdtf.getText().isEmpty())))){
            try
            {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/Bus_Ticket_Reservation","andriya","19ucsa209");
                PreparedStatement ps=con.prepareStatement("insert into LOGIN values(?,?,?,?)");
                ps.setString(1,firstnametf.getText()); 
                ps.setString(2,lastnametf.getText());
                ps.setString(3,usernametf.getText());
                ps.setString(4,pwdtf.getText());
                ps.executeUpdate();
                a.setAlertType(AlertType.INFORMATION); 
                a.setContentText("Login Successful"); 
                Optional<ButtonType> result = a.showAndWait();
                ButtonType button = result.orElse(ButtonType.CANCEL);
                if (button == ButtonType.OK) {
                    dialog.close();
                    login();
                        
                } 
            }
            catch(ClassNotFoundException | SQLException err){
                System.out.println(err);
            }
            }
            else{
               Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setContentText("Please Fill in Proper Details");
               alert.showAndWait();  
            }
        });
        
        cancelbtn.setOnAction(e -> {
            dialog.close();
        });
        signupgp.setHgap(30);
        signupgp.setVgap(30);
        signupgp.add(firstnametf, 0, 0);
        signupgp.add(lastnametf, 1, 0);
        signupgp.add(usernametf, 0, 1);
        signupgp.add(pwdtf, 0, 2);
        signupgp.add(cpwdtf, 0, 3);
        signupgp.add(signupbtn, 0, 4);
        signupgp.add(cancelbtn, 1, 4);
        signup.getChildren().addAll(sufp1,signupgp);
        signup.setPadding(new Insets(100));
        Scene scene2=new Scene(signup);
        scene2.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        dialog.setScene(scene2);
        dialog.initStyle(StageStyle.DECORATED);
        dialog.showAndWait();
    }
    
    public void adminlogin(){
        final Stage dialog=new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        HBox mainpage = new HBox();
        Image img = new Image(getClass().getResourceAsStream("MainImage1.jpg"));
        ImageView imgv = new ImageView(img);
        imgv.setFitWidth(950);
        imgv.setFitHeight(1000);
        VBox login = new VBox();
        Label logint = new Label("ADMIN LOGIN"); 
        logint.setFont(new Font("Arial",40));
        FlowPane lfp1 = new FlowPane(logint);
        lfp1.setAlignment(Pos.CENTER);
        lfp1.setPadding(new Insets(30));
        GridPane logingp = new GridPane();
        Image imgui = new Image(getClass().getResourceAsStream("usericon.png"));
        ImageView imguiv = new ImageView(imgui);
        imguiv.setFitWidth(50);
        imguiv.setFitHeight(50);
        TextField usernametf = new TextField();
        usernametf.setPrefSize(270, 50);
        usernametf.setPromptText("Username");
        Image imgpi = new Image(getClass().getResourceAsStream("pwdicon.png"));
        ImageView imgpiv = new ImageView(imgpi);
        imgpiv.setFitWidth(50);
        imgpiv.setFitHeight(50);
        PasswordField pwdtf = new PasswordField();
        pwdtf.setPrefSize(270, 50);
        pwdtf.setPromptText("Password");
        logingp.setHgap(30);
        logingp.setVgap(30);
        logingp.add(imguiv, 0, 0);
        logingp.add(usernametf, 1, 0);
        logingp.add(imgpiv, 0, 1);
        logingp.add(pwdtf, 1, 1);
        Button loginbtn = new Button("Login");
        loginbtn.setId("loginbtn");
        Alert a = new Alert(AlertType.NONE);  
        loginbtn.setOnAction(e -> {
            if((!(usernametf.getText().isEmpty())) && (!(pwdtf.getText().isEmpty()))){
            try
            {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/Bus_Ticket_Reservation","andriya","19ucsa209");
                Statement st=con.createStatement();
                ResultSet res=st.executeQuery("select * from adminlogin where adminlogin.username='"+usernametf.getText()+"' and adminlogin.password='"+pwdtf.getText()+"'");
                if(res.next()){
                    a.setAlertType(AlertType.INFORMATION); 
                    a.setContentText("Login Successful"); 
                    Optional<ButtonType> result = a.showAndWait();
                    ButtonType button = result.orElse(ButtonType.CANCEL);
                    if (button == ButtonType.OK) {
                        addseat();
                        dialog.close();
                        login();
                    } 
                }
                else{
                    a.setAlertType(AlertType.INFORMATION); 
                    a.setContentText("Login Failed"); 
                    a.showAndWait();
                }
            }
            catch(ClassNotFoundException | SQLException err){
                System.out.println(err);
            }
            }
            else{
                a.setAlertType(AlertType.WARNING);
                a.setContentText("Fields are Empty");
                a.showAndWait();  
            }
        });
        loginbtn.setPrefSize(190, 50);
        FlowPane lfp2 = new FlowPane(loginbtn);
        lfp2.setAlignment(Pos.CENTER);
        lfp2.setPadding(new Insets(40,0,10,0)); 
        login.getChildren().addAll(lfp1,logingp,lfp2);
        login.setPadding(new Insets(280));
        mainpage.getChildren().addAll(imgv,login);
        Scene scene3=new Scene(mainpage);
        scene3.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        dialog.setScene(scene3);
        dialog.initStyle(StageStyle.DECORATED);
        dialog.showAndWait();
    }
    
    public void addseat(){
        Alert a = new Alert(AlertType.NONE); 
        final Stage dialog=new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox addseat = new VBox();
        Label addseatl = new Label("ADD SEAT"); 
        addseatl.setFont(new Font("Arial",36));
        FlowPane asfp = new FlowPane(addseatl);
        asfp.setAlignment(Pos.CENTER);
        asfp.setPadding(new Insets(30));
        GridPane addseatgp = new GridPane();
        Label busno = new Label("Bus No "); 
        ComboBox busnocb = new ComboBox();
        busnocb.getItems().addAll("101","102","103","104","105","106","107","108","109","110","111","112","113","114");
        busnocb.setPrefSize(270, 50);
        Label datel = new Label("Date "); 
        DatePicker dp = new DatePicker();
        Button addbtn = new Button("Add");
        addbtn.setOnAction(e -> {
            String busnumber =busnocb.getValue().toString();
            String date = dp.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            for(int i=1;i<=20;i++){
                try{
                    if(i%2!=0){
                        String seats=i+"W";
                        String status="Unbooked"; 
                        Class.forName("org.apache.derby.jdbc.ClientDriver");
                        Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/Bus_Ticket_Reservation","andriya","19ucsa209");
                        PreparedStatement ps=con.prepareStatement("insert into SEAT(busid,seats,date,status)values(?,?,?,?)");
                        ps.setString(1,busnumber); 
                        ps.setString (2,seats);
                        ps.setString(3,date);
                        ps.setString(4,status);
                        ps.executeUpdate();
                    }
                    else{
                        String seats=i+"A";
                        String status="Unbooked"; 
                        Class.forName("org.apache.derby.jdbc.ClientDriver");
                        Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/Bus_Ticket_Reservation","andriya","19ucsa209");
                        PreparedStatement ps=con.prepareStatement("insert into SEAT(busid,seats,date,status)values(?,?,?,?)");
                        ps.setString(1,busnumber); 
                        ps.setString (2,seats);
                        ps.setString(3,date);
                        ps.setString(4,status);
                        ps.executeUpdate();
                    }
                }
                catch(ClassNotFoundException | SQLException err){
                System.out.println(err);
                }
            }
            for(int i=21;i<=40;i++){
                try{
                    if(i%2!=0){
                        String seats=i+"W";
                        String status="Unbooked"; 
                        Class.forName("org.apache.derby.jdbc.ClientDriver");
                        Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/Bus_Ticket_Reservation","andriya","19ucsa209");
                        PreparedStatement ps=con.prepareStatement("insert into SEAT(busid,seats,date,status)values(?,?,?,?)");
                        ps.setString(1,busnumber); 
                        ps.setString (2,seats);
                        ps.setString(3,date);
                        ps.setString(4,status);
                        ps.executeUpdate();
                    }
                    else{
                        String seats=i+"A";
                        String status="Unbooked"; 
                        Class.forName("org.apache.derby.jdbc.ClientDriver");
                        Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/Bus_Ticket_Reservation","andriya","19ucsa209");
                        PreparedStatement ps=con.prepareStatement("insert into SEAT(busid,seats,date,status)values(?,?,?,?)");
                        ps.setString(1,busnumber); 
                        ps.setString (2,seats);
                        ps.setString(3,date);
                        ps.setString(4,status);
                        ps.executeUpdate();
                    }   
                }
                
                catch(ClassNotFoundException | SQLException err){
                System.out.println(err);
                }   
            }
            a.setAlertType(AlertType.INFORMATION); 
                a.setContentText("Sheet Added"); 
                Optional<ButtonType> result = a.showAndWait();
                ButtonType button = result.orElse(ButtonType.CANCEL);
                if (button == ButtonType.OK) {
                    addseat();
                } 
        });
        addbtn.setId("asaddbtn");
        addbtn.setPrefSize(190, 50);
        Button cbtn = new Button("Cancel");
        cbtn.setOnAction(e -> {
            dialog.close();
            mainpage();
        });
        cbtn.setId("ascbtn");
        cbtn.setPrefSize(190, 50);
        addseatgp.setHgap(30);
        addseatgp.setVgap(30);
        addseatgp.add(busno,0,0);
        addseatgp.add(busnocb,1,0);
        addseatgp.add(datel,0,1);
        addseatgp.add(dp,1,1);
        addseatgp.add(addbtn,0,2);
        addseatgp.add(cbtn,1,2);
        addseat.getChildren().addAll(asfp,addseatgp);
        addseat.setPadding(new Insets(100));
        Scene scene4=new Scene(addseat);
        scene4.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        dialog.setScene(scene4);
        dialog.initStyle(StageStyle.DECORATED);
        dialog.showAndWait();
    }
    
    public void mainpage(){
        final Stage dialog=new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        HBox mainpage = new HBox();
        Image img = new Image(getClass().getResourceAsStream("MainImage1.jpg"));
        ImageView imgv = new ImageView(img);
        imgv.setFitWidth(950);
        imgv.setFitHeight(1000);
        VBox login = new VBox();
        Label logint = new Label("LOGIN"); 
        logint.setFont(new Font("Arial",36));
        FlowPane lfp1 = new FlowPane(logint);
        lfp1.setAlignment(Pos.CENTER);
        lfp1.setPadding(new Insets(30));
        GridPane logingp = new GridPane();
        Image imgui = new Image(getClass().getResourceAsStream("usericon.png"));
        ImageView imguiv = new ImageView(imgui);
        imguiv.setFitWidth(50);
        imguiv.setFitHeight(50);
        TextField usernametf = new TextField();
        usernametf.setPrefSize(270, 50);
        usernametf.setPromptText("Username");
        Image imgpi = new Image(getClass().getResourceAsStream("pwdicon.png"));
        ImageView imgpiv = new ImageView(imgpi);
        imgpiv.setFitWidth(50);
        imgpiv.setFitHeight(50);
        PasswordField pwdtf = new PasswordField();
        pwdtf.setPrefSize(270, 50);
        pwdtf.setPromptText("Password");
        logingp.setHgap(30);
        logingp.setVgap(30);
        logingp.add(imguiv, 0, 0);
        logingp.add(usernametf, 1, 0);
        logingp.add(imgpiv, 0, 1);
        logingp.add(pwdtf, 1, 1);
        Button loginbtn = new Button("Login");
        loginbtn.setId("loginbtn");
        Alert a = new Alert(AlertType.NONE);
        loginbtn.setOnAction(e -> {
            try
            {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/Bus_Ticket_Reservation","andriya","19ucsa209");
                Statement st=con.createStatement();
                ResultSet res=st.executeQuery("select * from login where login.username='"+usernametf.getText()+"' and login.password='"+pwdtf.getText()+"'");
                if(res.next()){
                    a.setAlertType(AlertType.INFORMATION); 
                    a.setContentText("Login Successful"); 
                    Optional<ButtonType> result = a.showAndWait();
                    ButtonType button = result.orElse(ButtonType.CANCEL);
                    if (button == ButtonType.OK) {
                        dialog.close();
                        login();
                    } 
                }
                else{
                    a.setAlertType(AlertType.INFORMATION); 
                    a.setContentText("Login Failed"); 
                    a.showAndWait();
                }
            }
            catch(ClassNotFoundException | SQLException err){
                System.out.println(err);
            }
        });
        loginbtn.setPrefSize(190, 50);
        FlowPane lfp2 = new FlowPane(loginbtn);
        lfp2.setAlignment(Pos.CENTER);
        lfp2.setPadding(new Insets(40,0,10,0));
        Button newloginbtn = new Button("Sign Up");
        newloginbtn.setId("newloginbtn");
        newloginbtn.setOnAction(e -> {
            signup();
        });
        newloginbtn.setPrefSize(190, 50);
        FlowPane lfp3 = new FlowPane(newloginbtn);
        lfp3.setAlignment(Pos.CENTER);
        lfp3.setPadding(new Insets(10,0,10,0));
        Button adminloginbtn = new Button("Admin Login");
        adminloginbtn.setId("adminloginbtn");
        adminloginbtn.setOnAction(e -> {
            adminlogin();
        });
        adminloginbtn.setPrefSize(190, 50);
        FlowPane lfp4 = new FlowPane(adminloginbtn);
        lfp4.setAlignment(Pos.CENTER);
        lfp4.setPadding(new Insets(10,0,10,0));
        login.getChildren().addAll(lfp1,logingp,lfp2,lfp3,lfp4);
        login.setPadding(new Insets(280));
        mainpage.getChildren().addAll(imgv,login);
        Scene scene5=new Scene(mainpage);
        scene5.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        dialog.setScene(scene5);
        dialog.initStyle(StageStyle.DECORATED);
        dialog.showAndWait();
    }    
    public ObservableList<ObservableList> data;
    public TableView tableview;
    
    public void login(){
        final Stage dialog=new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Alert a = new Alert(AlertType.NONE); 
        BorderPane bpane = new BorderPane();
        
        GridPane headgp = new GridPane();
        Image imglogo = new Image(getClass().getResourceAsStream("logo.png"));
        ImageView imglogov = new ImageView(imglogo);
        imglogov.setFitWidth(80);
        imglogov.setFitHeight(100);
        Label bustick = new Label("BUS TICKETS");
        bustick.setId("head");
        Label contus = new Label("CONTACT US");
        contus.setOnMouseClicked(e -> {
            contactus();
        });
        contus.setId("head");
        Label cantick = new Label("CANCEL TICKET");
        cantick.setOnMouseClicked(e -> {
            canceltick();
        });
        cantick.setId("head"); 
        headgp.setHgap(10);
        headgp.add(imglogov, 20, 0);
        headgp.add(bustick, 22, 0);
        headgp.add(contus, 104, 0);
        headgp.add(cantick, 110, 0);
        headgp.setId("header");
        Image imgmain = new Image(getClass().getResourceAsStream("mainpic.png"));
        ImageView imgmainv = new ImageView(imgmain);
        imgmainv.setFitWidth(1920);
        imgmainv.setFitHeight(840);
        FlowPane contentfp = new FlowPane();
        ComboBox fromcb = new ComboBox();
        fromcb.getItems().addAll("  Chennai","  Bengaluru","  Coimbatore","  Trichy","  Tirunelveli","  Madurai");
        fromcb.setPrefSize(270, 50);
        fromcb.setId("fromlocicon");
        fromcb.setPromptText("   FROM");
        ComboBox tocb = new ComboBox();
        tocb.getItems().addAll("  Chennai","  Bengaluru","  Coimbatore","  Trichy","  Tirunelveli","  Madurai");
        tocb.setPrefSize(270, 50);
        tocb.setId("tolocicon");
        tocb.setPromptText("   TO");
        DatePicker datedp = new DatePicker();
        datedp.setPrefSize(220, 50);
        datedp.setId("dateicon");
        datedp.setPromptText("DATE");
        //ComboBox bustypecb = new ComboBox();
        //bustypecb.getItems().addAll("  Seater NONAC","  Seater AC","  Sleeper NONAC","  Sleeper AC");
        //bustypecb.setPrefSize(270, 50);
        //bustypecb.setPromptText("  BUS TYPE");
        Button btn = new Button("Search Buses"); 
        btn.setOnAction(e -> {
            final Stage dialog1=new Stage();
            dialog1.initModality(Modality.APPLICATION_MODAL);
            VBox vbox = new VBox();
            GridPane headgp1 = new GridPane();
            headgp1.setId("header");
            Image imglogo1 = new Image(getClass().getResourceAsStream("logo.png"));
            ImageView imglogov1 = new ImageView(imglogo1);
            imglogov1.setFitWidth(80);
            imglogov1.setFitHeight(100);
            Label bustick1 = new Label("AVAILABLE SERVICES");
            bustick1.setId("head");
            headgp1.setHgap(10);
            headgp1.add(imglogov1, 10, 0);
            headgp1.add(bustick1, 14, 0);
            if ((fromcb.getValue().equals("  Chennai")) && (tocb.getValue().equals("  Bengaluru"))) {
                Label fromtol = new Label(fromcb.getValue()+"  to"+tocb.getValue()+" - Journey date "+datedp.getValue());
                fromtol.setAlignment(Pos.CENTER_LEFT);
                fromtol.setPadding(new Insets(20));
                GridPane ctobgp = new GridPane();
                final Separator sepHor = new Separator();
                sepHor.setValignment(VPos.CENTER);
                GridPane.setConstraints(sepHor, 0, 1);
                GridPane.setColumnSpan(sepHor, 11);
                ctobgp.getChildren().add(sepHor);
                Label busidl = new Label("Bus Id");
                Label clofserl = new Label("Class of Service");
                Label viarl = new Label("Via Route");
                Label depl = new Label("Departure");
                Label arrl = new Label("Arrival");
                Label orgl = new Label("Origin");
                Label desl = new Label("Destination");
                Label ratl = new Label("Rating");
                Label farel = new Label("Fare");
                Label selserl = new Label("Select Service");
                Label busidl1 = new Label("101");
                Label clofserl1 = new Label("Seater NONAC");
                Label viarl1 = new Label("Hosur");
                Label depl1 = new Label("08:00 ");
                Label arrl1 = new Label("14:00");
                Label orgl1 = new Label("Chennai");
                Label desl1 = new Label("Bengaluru");
                Image imgrat = new Image(getClass().getResourceAsStream("rat1.png"));
                ImageView imgratv = new ImageView(imgrat);
                imgratv.setFitWidth(50);
                imgratv.setFitHeight(43);
                Label farel1 = new Label("500");
                Button ctobbtn = new Button("Book");
                String date = datedp.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                ctobbtn.setOnAction(e1->{
                    dialog1.close();
                    booktickets(farel1.getText());
                });
                ctobgp.setHgap(20);
                ctobgp.setVgap(20);
                ctobgp.add(busidl, 0, 0);
                ctobgp.add(clofserl, 1, 0);
                ctobgp.add(viarl, 2, 0);
                ctobgp.add(depl, 3, 0);
                ctobgp.add(arrl, 4, 0);
                ctobgp.add(orgl, 5, 0);
                ctobgp.add(desl, 6, 0);
                ctobgp.add(ratl, 7, 0);
                ctobgp.add(farel, 8, 0);
                ctobgp.add(selserl, 9, 0);
                ctobgp.add(busidl1, 0, 2);
                ctobgp.add(clofserl1, 1, 2);
                ctobgp.add(viarl1, 2, 2);
                ctobgp.add(depl1, 3, 2);
                ctobgp.add(arrl1, 4, 2);
                ctobgp.add(orgl1, 5, 2);
                ctobgp.add(desl1, 6, 2);
                ctobgp.add(imgratv, 7, 2);
                ctobgp.add(farel1, 8, 2);
                ctobgp.add(ctobbtn, 9, 2);
                ctobgp.setAlignment(Pos.CENTER);
                Button gobackbtn = new Button("Go Back");
                HBox gbhbox = new HBox(gobackbtn);
                gbhbox.setAlignment(Pos.CENTER);
                gbhbox.setPadding(new Insets(30));
                gobackbtn.setOnAction(e1->{
                    dialog1.close();
                });
                ctobgp.setBorder(new Border(new BorderStroke(Color.valueOf("#d60909"),BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                ctobgp.setPadding(new Insets(50));
                vbox.getChildren().addAll(headgp1,fromtol,ctobgp,gbhbox);
                Scene scene6=new Scene(vbox);
                scene6.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                dialog1.setScene(scene6);
                dialog1.initStyle(StageStyle.DECORATED);
                dialog1.showAndWait();
            } 
            else if((fromcb.getValue().equals("  Chennai")) && (tocb.getValue().equals("  Coimbatore"))){
                Label fromtol1 = new Label(fromcb.getValue()+"  to"+tocb.getValue()+" - Journey date "+datedp.getValue());
                fromtol1.setAlignment(Pos.CENTER_LEFT);
                fromtol1.setPadding(new Insets(20));
                GridPane ctocgp = new GridPane();
                final Separator sepHor = new Separator();
                sepHor.setValignment(VPos.CENTER);
                GridPane.setConstraints(sepHor, 0, 1);
                GridPane.setColumnSpan(sepHor, 11);
                ctocgp.getChildren().add(sepHor);
                Label busidl = new Label("Bus Id");
                Label clofserl = new Label("Class of Service");
                Label viarl = new Label("Via Route");
                Label depl = new Label("Departure");
                Label arrl = new Label("Arrival");
                Label orgl = new Label("Origin");
                Label desl = new Label("Destination");
                Label ratl = new Label("Rating");
                Label farel = new Label("Fare");
                Label selserl = new Label("Select Service");
                Label busidl1 = new Label("102");
                Label clofserl1 = new Label("Seater AC");
                Label viarl1 = new Label("Villupuram");
                Label depl1 = new Label("15:30 ");
                Label arrl1 = new Label("21:30");
                Label orgl1 = new Label("Chennai");
                Label desl1 = new Label("Coimbatore");
                Image imgrat = new Image(getClass().getResourceAsStream("rat2.png"));
                ImageView imgratv = new ImageView(imgrat);
                imgratv.setFitWidth(50);
                imgratv.setFitHeight(43);
                Label farel1 = new Label("650");
                Button ctobbtn = new Button("Book");
                String date = datedp.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                ctobbtn.setOnAction(e1->{
                    dialog1.close();
                    booktickets(farel1.getText());
                });
                ctocgp.setHgap(20);
                ctocgp.setVgap(20);
                ctocgp.add(busidl, 0, 0);
                ctocgp.add(clofserl, 1, 0);
                ctocgp.add(viarl, 2, 0);
                ctocgp.add(depl, 3, 0);
                ctocgp.add(arrl, 4, 0);
                ctocgp.add(orgl, 5, 0);
                ctocgp.add(desl, 6, 0);
                ctocgp.add(ratl, 7, 0);
                ctocgp.add(farel, 8, 0);
                ctocgp.add(selserl, 9, 0);
                ctocgp.add(busidl1, 0, 2);
                ctocgp.add(clofserl1, 1, 2);
                ctocgp.add(viarl1, 2, 2);
                ctocgp.add(depl1, 3, 2);
                ctocgp.add(arrl1, 4, 2);
                ctocgp.add(orgl1, 5, 2);
                ctocgp.add(desl1, 6, 2);
                ctocgp.add(imgratv, 7, 2);
                ctocgp.add(farel1, 8, 2);
                ctocgp.add(ctobbtn, 9, 2);
                ctocgp.setAlignment(Pos.CENTER);
                Button gobackbtn = new Button("Go Back");
                HBox gbhbox = new HBox(gobackbtn);
                gbhbox.setAlignment(Pos.CENTER);
                gbhbox.setPadding(new Insets(30));
                gobackbtn.setOnAction(e1->{
                    dialog1.close();
                });
                ctocgp.setBorder(new Border(new BorderStroke(Color.valueOf("#d60909"),BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                ctocgp.setPadding(new Insets(50));
                vbox.getChildren().addAll(headgp1,fromtol1,ctocgp,gbhbox);
                Scene scene6=new Scene(vbox);
                scene6.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                dialog1.setScene(scene6);
                dialog1.initStyle(StageStyle.DECORATED);
                dialog1.showAndWait();
            }
            else if((fromcb.getValue().equals("  Chennai")) && (tocb.getValue().equals("  Trichy"))){
                Label fromtol2 = new Label(fromcb.getValue()+"  to"+tocb.getValue()+" - Journey date "+datedp.getValue());
                fromtol2.setAlignment(Pos.CENTER_LEFT);
                fromtol2.setPadding(new Insets(20));
                GridPane ctotgp = new GridPane();
                final Separator sepHor = new Separator();
                sepHor.setValignment(VPos.CENTER);
                GridPane.setConstraints(sepHor, 0, 1);
                GridPane.setColumnSpan(sepHor, 11);
                ctotgp.getChildren().add(sepHor);
                Label busidl = new Label("Bus Id");
                Label clofserl = new Label("Class of Service");
                Label viarl = new Label("Via Route");
                Label depl = new Label("Departure");
                Label arrl = new Label("Arrival");
                Label orgl = new Label("Origin");
                Label desl = new Label("Destination");
                Label ratl = new Label("Rating");
                Label farel = new Label("Fare");
                Label selserl = new Label("Select Service");
                Label busidl1 = new Label("103");
                Label clofserl1 = new Label("Sleeper NONAC");
                Label viarl1 = new Label("Guindy");
                Label depl1 = new Label("23:30 ");
                Label arrl1 = new Label("05:30");
                Label orgl1 = new Label("Chennai");
                Label desl1 = new Label("Trichy");
                Image imgrat = new Image(getClass().getResourceAsStream("rat3.png"));
                ImageView imgratv = new ImageView(imgrat);
                imgratv.setFitWidth(50);
                imgratv.setFitHeight(43);
                Label farel1 = new Label("800");
                Button ctobbtn = new Button("Book");
                String date = datedp.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                ctobbtn.setOnAction(e1->{
                    dialog1.close();
                    booktickets(farel1.getText());
                });
                ctotgp.setHgap(20);
                ctotgp.setVgap(20);
                ctotgp.add(busidl, 0, 0);
                ctotgp.add(clofserl, 1, 0);
                ctotgp.add(viarl, 2, 0);
                ctotgp.add(depl, 3, 0);
                ctotgp.add(arrl, 4, 0);
                ctotgp.add(orgl, 5, 0);
                ctotgp.add(desl, 6, 0);
                ctotgp.add(ratl, 7, 0);
                ctotgp.add(farel, 8, 0);
                ctotgp.add(selserl, 9, 0);
                ctotgp.add(busidl1, 0, 2);
                ctotgp.add(clofserl1, 1, 2);
                ctotgp.add(viarl1, 2, 2);
                ctotgp.add(depl1, 3, 2);
                ctotgp.add(arrl1, 4, 2);
                ctotgp.add(orgl1, 5, 2);
                ctotgp.add(desl1, 6, 2);
                ctotgp.add(imgratv, 7, 2);
                ctotgp.add(farel1, 8, 2);
                ctotgp.add(ctobbtn, 9, 2);
                ctotgp.setAlignment(Pos.CENTER);
                Button gobackbtn = new Button("Go Back");
                HBox gbhbox = new HBox(gobackbtn);
                gbhbox.setAlignment(Pos.CENTER);
                gbhbox.setPadding(new Insets(30));
                gobackbtn.setOnAction(e1->{
                    dialog1.close();
                });
                ctotgp.setBorder(new Border(new BorderStroke(Color.valueOf("#d60909"),BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                ctotgp.setPadding(new Insets(50));
                vbox.getChildren().addAll(headgp1,fromtol2,ctotgp,gbhbox);
                Scene scene6=new Scene(vbox);
                scene6.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                dialog1.setScene(scene6);
                dialog1.initStyle(StageStyle.DECORATED);
                dialog1.showAndWait();
            }
            else if((fromcb.getValue().equals("  Chennai")) && (tocb.getValue().equals("  Tirunelveli"))){
                Label fromtol3 = new Label(fromcb.getValue()+"  to"+tocb.getValue()+" - Journey date "+datedp.getValue());
                fromtol3.setAlignment(Pos.CENTER_LEFT);
                fromtol3.setPadding(new Insets(20));
                GridPane ctotigp = new GridPane();
                final Separator sepHor = new Separator();
                sepHor.setValignment(VPos.CENTER);
                GridPane.setConstraints(sepHor, 0, 1);
                GridPane.setColumnSpan(sepHor, 11);
                ctotigp.getChildren().add(sepHor);
                Label busidl = new Label("Bus Id");
                Label clofserl = new Label("Class of Service");
                Label viarl = new Label("Via Route");
                Label depl = new Label("Departure");
                Label arrl = new Label("Arrival");
                Label orgl = new Label("Origin");
                Label desl = new Label("Destination");
                Label ratl = new Label("Rating");
                Label farel = new Label("Fare");
                Label selserl = new Label("Select Service");
                Label busidl1 = new Label("104");
                Label clofserl1 = new Label("Seater AC");
                Label viarl1 = new Label("Nagercoil");
                Label depl1 = new Label("20:00 ");
                Label arrl1 = new Label("07:30");
                Label orgl1 = new Label("Chennai");
                Label desl1 = new Label("Tirunveli");
                Image imgrat = new Image(getClass().getResourceAsStream("rat2.png"));
                ImageView imgratv = new ImageView(imgrat);
                imgratv.setFitWidth(50);
                imgratv.setFitHeight(43);
                Label farel1 = new Label("650");
                Button ctobbtn = new Button("Book");
                String date = datedp.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                ctobbtn.setOnAction(e1->{
                    dialog1.close();
                    booktickets(farel1.getText());
                });
                ctotigp.setHgap(20);
                ctotigp.setVgap(20);
                ctotigp.add(busidl, 0, 0);
                ctotigp.add(clofserl, 1, 0);
                ctotigp.add(viarl, 2, 0);
                ctotigp.add(depl, 3, 0);
                ctotigp.add(arrl, 4, 0);
                ctotigp.add(orgl, 5, 0);
                ctotigp.add(desl, 6, 0);
                ctotigp.add(ratl, 7, 0);
                ctotigp.add(farel, 8, 0);
                ctotigp.add(selserl, 9, 0);
                ctotigp.add(busidl1, 0, 2);
                ctotigp.add(clofserl1, 1, 2);
                ctotigp.add(viarl1, 2, 2);
                ctotigp.add(depl1, 3, 2);
                ctotigp.add(arrl1, 4, 2);
                ctotigp.add(orgl1, 5, 2);
                ctotigp.add(desl1, 6, 2);
                ctotigp.add(imgratv, 7, 2);
                ctotigp.add(farel1, 8, 2);
                ctotigp.add(ctobbtn, 9, 2);
                ctotigp.setAlignment(Pos.CENTER);
                Button gobackbtn = new Button("Go Back");
                HBox gbhbox = new HBox(gobackbtn);
                gbhbox.setAlignment(Pos.CENTER);
                gbhbox.setPadding(new Insets(30));
                gobackbtn.setOnAction(e1->{
                    dialog1.close();
                });
                ctotigp.setBorder(new Border(new BorderStroke(Color.valueOf("#d60909"),BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                ctotigp.setPadding(new Insets(50));
                vbox.getChildren().addAll(headgp1,fromtol3,ctotigp,gbhbox);
                Scene scene6=new Scene(vbox);
                scene6.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                dialog1.setScene(scene6);
                dialog1.initStyle(StageStyle.DECORATED);
                dialog1.showAndWait();
            }
            else if((fromcb.getValue().equals("  Chennai")) && (tocb.getValue().equals("  Madurai"))){
                Label fromtol4 = new Label(fromcb.getValue()+"  to"+tocb.getValue()+" - Journey date "+datedp.getValue());
                fromtol4.setAlignment(Pos.CENTER_LEFT);
                fromtol4.setPadding(new Insets(20));
                GridPane ctomgp = new GridPane();
                final Separator sepHor = new Separator();
                sepHor.setValignment(VPos.CENTER);
                GridPane.setConstraints(sepHor, 0, 1);
                GridPane.setColumnSpan(sepHor, 11);
                ctomgp.getChildren().add(sepHor);
                Label busidl = new Label("Bus Id");
                Label clofserl = new Label("Class of Service");
                Label viarl = new Label("Via Route");
                Label depl = new Label("Departure");
                Label arrl = new Label("Arrival");
                Label orgl = new Label("Origin");
                Label desl = new Label("Destination");
                Label ratl = new Label("Rating");
                Label farel = new Label("Fare");
                Label selserl = new Label("Select Service");
                Label busidl1 = new Label("105");
                Label clofserl1 = new Label("Sleeper AC");
                Label viarl1 = new Label("Maduravoyal");
                Label depl1 = new Label("23:00 ");
                Label arrl1 = new Label("08:00");
                Label orgl1 = new Label("Chennai");
                Label desl1 = new Label("Madurai");
                Image imgrat = new Image(getClass().getResourceAsStream("rat1.png"));
                ImageView imgratv = new ImageView(imgrat);
                imgratv.setFitWidth(50);
                imgratv.setFitHeight(43);
                Label farel1 = new Label("1000");
                Button ctobbtn = new Button("Book");
                String date = datedp.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                ctobbtn.setOnAction(e1->{
                    dialog1.close();
                    booktickets(farel1.getText());
                });
                ctomgp.setHgap(20);
                ctomgp.setVgap(20);
                ctomgp.add(busidl, 0, 0);
                ctomgp.add(clofserl, 1, 0);
                ctomgp.add(viarl, 2, 0);
                ctomgp.add(depl, 3, 0);
                ctomgp.add(arrl, 4, 0);
                ctomgp.add(orgl, 5, 0);
                ctomgp.add(desl, 6, 0);
                ctomgp.add(ratl, 7, 0);
                ctomgp.add(farel, 8, 0);
                ctomgp.add(selserl, 9, 0);
                ctomgp.add(busidl1, 0, 2);
                ctomgp.add(clofserl1, 1, 2);
                ctomgp.add(viarl1, 2, 2);
                ctomgp.add(depl1, 3, 2);
                ctomgp.add(arrl1, 4, 2);
                ctomgp.add(orgl1, 5, 2);
                ctomgp.add(desl1, 6, 2);
                ctomgp.add(imgratv, 7, 2);
                ctomgp.add(farel1, 8, 2);
                ctomgp.add(ctobbtn, 9, 2);
                ctomgp.setAlignment(Pos.CENTER);
                Button gobackbtn = new Button("Go Back");
                HBox gbhbox = new HBox(gobackbtn);
                gbhbox.setAlignment(Pos.CENTER);
                gbhbox.setPadding(new Insets(30));
                gobackbtn.setOnAction(e1->{
                    dialog1.close();
                });
                ctomgp.setBorder(new Border(new BorderStroke(Color.valueOf("#d60909"),BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                ctomgp.setPadding(new Insets(50));
                vbox.getChildren().addAll(headgp1,fromtol4,ctomgp,gbhbox);
                Scene scene6=new Scene(vbox);
                scene6.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                dialog1.setScene(scene6);
                dialog1.initStyle(StageStyle.DECORATED);
                dialog1.showAndWait();
            }
            else if((fromcb.getValue().equals("  Bengaluru")) && (tocb.getValue().equals("  Chennai"))){
                Label fromtol5 = new Label(fromcb.getValue()+"  to"+tocb.getValue()+" - Journey date "+datedp.getValue());
                fromtol5.setAlignment(Pos.CENTER_LEFT);
                fromtol5.setPadding(new Insets(20));
                GridPane btocgp = new GridPane();
                final Separator sepHor = new Separator();
                sepHor.setValignment(VPos.CENTER);
                GridPane.setConstraints(sepHor, 0, 1);
                GridPane.setColumnSpan(sepHor, 11);
                btocgp.getChildren().add(sepHor);
                Label busidl = new Label("Bus Id");
                Label clofserl = new Label("Class of Service");
                Label viarl = new Label("Via Route");
                Label depl = new Label("Departure");
                Label arrl = new Label("Arrival");
                Label orgl = new Label("Origin");
                Label desl = new Label("Destination");
                Label ratl = new Label("Rating");
                Label farel = new Label("Fare");
                Label selserl = new Label("Select Service");
                Label busidl1 = new Label("106");
                Label clofserl1 = new Label("Seater AC");
                Label viarl1 = new Label("Hosur");
                Label depl1 = new Label("15:30 ");
                Label arrl1 = new Label("21:30");
                Label orgl1 = new Label("Bengaluru");
                Label desl1 = new Label("Chennai");
                Image imgrat = new Image(getClass().getResourceAsStream("rat3.png"));
                ImageView imgratv = new ImageView(imgrat);
                imgratv.setFitWidth(50);
                imgratv.setFitHeight(43);
                Label farel1 = new Label("650");
                Button ctobbtn = new Button("Book");
                String date = datedp.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                ctobbtn.setOnAction(e1->{
                    dialog1.close();
                    booktickets(farel1.getText());
                });
                btocgp.setHgap(20);
                btocgp.setVgap(20);
                btocgp.add(busidl, 0, 0);
                btocgp.add(clofserl, 1, 0);
                btocgp.add(viarl, 2, 0);
                btocgp.add(depl, 3, 0);
                btocgp.add(arrl, 4, 0);
                btocgp.add(orgl, 5, 0);
                btocgp.add(desl, 6, 0);
                btocgp.add(ratl, 7, 0);
                btocgp.add(farel, 8, 0);
                btocgp.add(selserl, 9, 0);
                btocgp.add(busidl1, 0, 2);
                btocgp.add(clofserl1, 1, 2);
                btocgp.add(viarl1, 2, 2);
                btocgp.add(depl1, 3, 2);
                btocgp.add(arrl1, 4, 2);
                btocgp.add(orgl1, 5, 2);
                btocgp.add(desl1, 6, 2);
                btocgp.add(imgratv, 7, 2);
                btocgp.add(farel1, 8, 2);
                btocgp.add(ctobbtn, 9, 2);
                btocgp.setAlignment(Pos.CENTER);
                Button gobackbtn = new Button("Go Back");
                HBox gbhbox = new HBox(gobackbtn);
                gbhbox.setAlignment(Pos.CENTER);
                gbhbox.setPadding(new Insets(30));
                gobackbtn.setOnAction(e1->{
                    dialog1.close();
                });
                btocgp.setBorder(new Border(new BorderStroke(Color.valueOf("#d60909"),BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                btocgp.setPadding(new Insets(50));
                vbox.getChildren().addAll(headgp1,fromtol5,btocgp,gbhbox);
                Scene scene6=new Scene(vbox);
                scene6.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                dialog1.setScene(scene6);
                dialog1.initStyle(StageStyle.DECORATED);
                dialog1.showAndWait();
            }
            else if((fromcb.getValue().equals("  Bengaluru")) && (tocb.getValue().equals("  Coimbatore"))){
                Label fromtol6 = new Label(fromcb.getValue()+"  to"+tocb.getValue()+" - Journey date "+datedp.getValue());
                fromtol6.setAlignment(Pos.CENTER_LEFT);
                fromtol6.setPadding(new Insets(20));
                GridPane btocogp = new GridPane();
                final Separator sepHor = new Separator();
                sepHor.setValignment(VPos.CENTER);
                GridPane.setConstraints(sepHor, 0, 1);
                GridPane.setColumnSpan(sepHor, 11);
                btocogp.getChildren().add(sepHor);
                Label busidl = new Label("Bus Id");
                Label clofserl = new Label("Class of Service");
                Label viarl = new Label("Via Route");
                Label depl = new Label("Departure");
                Label arrl = new Label("Arrival");
                Label orgl = new Label("Origin");
                Label desl = new Label("Destination");
                Label ratl = new Label("Rating");
                Label farel = new Label("Fare");
                Label selserl = new Label("Select Service");
                Label busidl1 = new Label("107");
                Label clofserl1 = new Label("Seater NONAC");
                Label viarl1 = new Label("Hosur");
                Label depl1 = new Label("08:00 ");
                Label arrl1 = new Label("14:00");
                Label orgl1 = new Label("Bengaluru");
                Label desl1 = new Label("Coimbatore");
                Image imgrat = new Image(getClass().getResourceAsStream("rat1.png"));
                ImageView imgratv = new ImageView(imgrat);
                imgratv.setFitWidth(50);
                imgratv.setFitHeight(43);
                Label farel1 = new Label("500");
                Button ctobbtn = new Button("Book");
                String date = datedp.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                ctobbtn.setOnAction(e1->{
                    dialog1.close();
                    booktickets(farel1.getText());
                });
                btocogp.setHgap(20);
                btocogp.setVgap(20);
                btocogp.add(busidl, 0, 0);
                btocogp.add(clofserl, 1, 0);
                btocogp.add(viarl, 2, 0);
                btocogp.add(depl, 3, 0);
                btocogp.add(arrl, 4, 0);
                btocogp.add(orgl, 5, 0);
                btocogp.add(desl, 6, 0);
                btocogp.add(ratl, 7, 0);
                btocogp.add(farel, 8, 0);
                btocogp.add(selserl, 9, 0);
                btocogp.add(busidl1, 0, 2);
                btocogp.add(clofserl1, 1, 2);
                btocogp.add(viarl1, 2, 2);
                btocogp.add(depl1, 3, 2);
                btocogp.add(arrl1, 4, 2);
                btocogp.add(orgl1, 5, 2);
                btocogp.add(desl1, 6, 2);
                btocogp.add(imgratv, 7, 2);
                btocogp.add(farel1, 8, 2);
                btocogp.add(ctobbtn, 9, 2);
                btocogp.setAlignment(Pos.CENTER);
                Button gobackbtn = new Button("Go Back");
                HBox gbhbox = new HBox(gobackbtn);
                gbhbox.setAlignment(Pos.CENTER);
                gbhbox.setPadding(new Insets(30));
                gobackbtn.setOnAction(e1->{
                    dialog1.close();
                });
                btocogp.setBorder(new Border(new BorderStroke(Color.valueOf("#d60909"),BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                btocogp.setPadding(new Insets(50));
                vbox.getChildren().addAll(headgp1,fromtol6,btocogp,gbhbox);
                Scene scene6=new Scene(vbox);
                scene6.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                dialog1.setScene(scene6);
                dialog1.initStyle(StageStyle.DECORATED);
                dialog1.showAndWait();
            }
            else if((fromcb.getValue().equals("  Bengaluru")) && (tocb.getValue().equals("  Trichy"))){
                Label fromtol7 = new Label(fromcb.getValue()+"  to"+tocb.getValue()+" - Journey date "+datedp.getValue());
                fromtol7.setAlignment(Pos.CENTER_LEFT);
                fromtol7.setPadding(new Insets(20));
                GridPane btotgp = new GridPane();
                final Separator sepHor = new Separator();
                sepHor.setValignment(VPos.CENTER);
                GridPane.setConstraints(sepHor, 0, 1);
                GridPane.setColumnSpan(sepHor, 11);
                btotgp.getChildren().add(sepHor);
                Label busidl = new Label("Bus Id");
                Label clofserl = new Label("Class of Service");
                Label viarl = new Label("Via Route");
                Label depl = new Label("Departure");
                Label arrl = new Label("Arrival");
                Label orgl = new Label("Origin");
                Label desl = new Label("Destination");
                Label ratl = new Label("Rating");
                Label farel = new Label("Fare");
                Label selserl = new Label("Select Service");
                Label busidl1 = new Label("108");
                Label clofserl1 = new Label("Seater NONAC");
                Label viarl1 = new Label("Salem");
                Label depl1 = new Label("08:00 ");
                Label arrl1 = new Label("20:00");
                Label orgl1 = new Label("Bengaluru");
                Label desl1 = new Label("Trichy");
                Image imgrat = new Image(getClass().getResourceAsStream("rat1.png"));
                ImageView imgratv = new ImageView(imgrat);
                imgratv.setFitWidth(50);
                imgratv.setFitHeight(43);
                Label farel1 = new Label("500");
                Button ctobbtn = new Button("Book");
                String date = datedp.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                ctobbtn.setOnAction(e1->{
                    dialog1.close();
                    booktickets(farel1.getText());
                });
                btotgp.setHgap(20);
                btotgp.setVgap(20);
                btotgp.add(busidl, 0, 0);
                btotgp.add(clofserl, 1, 0);
                btotgp.add(viarl, 2, 0);
                btotgp.add(depl, 3, 0);
                btotgp.add(arrl, 4, 0);
                btotgp.add(orgl, 5, 0);
                btotgp.add(desl, 6, 0);
                btotgp.add(ratl, 7, 0);
                btotgp.add(farel, 8, 0);
                btotgp.add(selserl, 9, 0);
                btotgp.add(busidl1, 0, 2);
                btotgp.add(clofserl1, 1, 2);
                btotgp.add(viarl1, 2, 2);
                btotgp.add(depl1, 3, 2);
                btotgp.add(arrl1, 4, 2);
                btotgp.add(orgl1, 5, 2);
                btotgp.add(desl1, 6, 2);
                btotgp.add(imgratv, 7, 2);
                btotgp.add(farel1, 8, 2);
                btotgp.add(ctobbtn, 9, 2);
                btotgp.setAlignment(Pos.CENTER);
                Button gobackbtn = new Button("Go Back");
                HBox gbhbox = new HBox(gobackbtn);
                gbhbox.setAlignment(Pos.CENTER);
                gbhbox.setPadding(new Insets(30));
                gobackbtn.setOnAction(e1->{
                    dialog1.close();
                });
                btotgp.setBorder(new Border(new BorderStroke(Color.valueOf("#d60909"),BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                btotgp.setPadding(new Insets(50));
                vbox.getChildren().addAll(headgp1,fromtol7,btotgp,gbhbox);
                Scene scene6=new Scene(vbox);
                scene6.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                dialog1.setScene(scene6);
                dialog1.initStyle(StageStyle.DECORATED);
                dialog1.showAndWait();
            }
            else if((fromcb.getValue().equals("  Bengaluru")) && (tocb.getValue().equals("  Tirunelveli"))){
                Label fromtol8 = new Label(fromcb.getValue()+"  to"+tocb.getValue()+" - Journey date "+datedp.getValue());
                fromtol8.setAlignment(Pos.CENTER_LEFT);
                fromtol8.setPadding(new Insets(20));
                GridPane btotirgp = new GridPane();
                final Separator sepHor = new Separator();
                sepHor.setValignment(VPos.CENTER);
                GridPane.setConstraints(sepHor, 0, 1);
                GridPane.setColumnSpan(sepHor, 11);
                btotirgp.getChildren().add(sepHor);
                Label busidl = new Label("Bus Id");
                Label clofserl = new Label("Class of Service");
                Label viarl = new Label("Via Route");
                Label depl = new Label("Departure");
                Label arrl = new Label("Arrival");
                Label orgl = new Label("Origin");
                Label desl = new Label("Destination");
                Label ratl = new Label("Rating");
                Label farel = new Label("Fare");
                Label selserl = new Label("Select Service");
                Label busidl1 = new Label("109");
                Label clofserl1 = new Label("Sleeper NONAC");
                Label viarl1 = new Label("Madurai");
                Label depl1 = new Label("20:00 ");
                Label arrl1 = new Label("05:00");
                Label orgl1 = new Label("Bengaluru");
                Label desl1 = new Label("Tirunelveli");
                Image imgrat = new Image(getClass().getResourceAsStream("rat3.png"));
                ImageView imgratv = new ImageView(imgrat);
                imgratv.setFitWidth(50);
                imgratv.setFitHeight(43);
                Label farel1 = new Label("800");
                Button ctobbtn = new Button("Book");
                String date = datedp.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                ctobbtn.setOnAction(e1->{
                    dialog1.close();
                    booktickets(farel1.getText());
                });
                btotirgp.setHgap(20);
                btotirgp.setVgap(20);
                btotirgp.add(busidl, 0, 0);
                btotirgp.add(clofserl, 1, 0);
                btotirgp.add(viarl, 2, 0);
                btotirgp.add(depl, 3, 0);
                btotirgp.add(arrl, 4, 0);
                btotirgp.add(orgl, 5, 0);
                btotirgp.add(desl, 6, 0);
                btotirgp.add(ratl, 7, 0);
                btotirgp.add(farel, 8, 0);
                btotirgp.add(selserl, 9, 0);
                btotirgp.add(busidl1, 0, 2);
                btotirgp.add(clofserl1, 1, 2);
                btotirgp.add(viarl1, 2, 2);
                btotirgp.add(depl1, 3, 2);
                btotirgp.add(arrl1, 4, 2);
                btotirgp.add(orgl1, 5, 2);
                btotirgp.add(desl1, 6, 2);
                btotirgp.add(imgratv, 7, 2);
                btotirgp.add(farel1, 8, 2);
                btotirgp.add(ctobbtn, 9, 2);
                btotirgp.setAlignment(Pos.CENTER);
                Button gobackbtn = new Button("Go Back");
                HBox gbhbox = new HBox(gobackbtn);
                gbhbox.setAlignment(Pos.CENTER);
                gbhbox.setPadding(new Insets(30));
                gobackbtn.setOnAction(e1->{
                    dialog1.close();
                });
                btotirgp.setBorder(new Border(new BorderStroke(Color.valueOf("#d60909"),BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                btotirgp.setPadding(new Insets(50));
                vbox.getChildren().addAll(headgp1,fromtol8,btotirgp,gbhbox);
                Scene scene6=new Scene(vbox);
                scene6.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                dialog1.setScene(scene6);
                dialog1.initStyle(StageStyle.DECORATED);
                dialog1.showAndWait();
            }
            else if((fromcb.getValue().equals("  Bengaluru")) && (tocb.getValue().equals("  Madurai"))){
                Label fromtol9 = new Label(fromcb.getValue()+"  to"+tocb.getValue()+" - Journey date "+datedp.getValue());
                fromtol9.setAlignment(Pos.CENTER_LEFT);
                fromtol9.setPadding(new Insets(20));
                GridPane btomgp = new GridPane();
                final Separator sepHor = new Separator();
                sepHor.setValignment(VPos.CENTER);
                GridPane.setConstraints(sepHor, 0, 1);
                GridPane.setColumnSpan(sepHor, 11);
                btomgp.getChildren().add(sepHor);
                Label busidl = new Label("Bus Id");
                Label clofserl = new Label("Class of Service");
                Label viarl = new Label("Via Route");
                Label depl = new Label("Departure");
                Label arrl = new Label("Arrival");
                Label orgl = new Label("Origin");
                Label desl = new Label("Destination");
                Label ratl = new Label("Rating");
                Label farel = new Label("Fare");
                Label selserl = new Label("Select Service");
                Label busidl1 = new Label("110");
                Label clofserl1 = new Label("Sleeper AC");
                Label viarl1 = new Label("Salem");
                Label depl1 = new Label("23:00 ");
                Label arrl1 = new Label("05:00");
                Label orgl1 = new Label("Bengaluru");
                Label desl1 = new Label("Madurai");
                Image imgrat = new Image(getClass().getResourceAsStream("rat2.png"));
                ImageView imgratv = new ImageView(imgrat);
                imgratv.setFitWidth(50);
                imgratv.setFitHeight(43);
                Label farel1 = new Label("1000");
                Button ctobbtn = new Button("Book");
                String date = datedp.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                ctobbtn.setOnAction(e1->{
                    dialog1.close();
                    booktickets(farel1.getText());
                });
                btomgp.setHgap(20);
                btomgp.setVgap(20);
                btomgp.add(busidl, 0, 0);
                btomgp.add(clofserl, 1, 0);
                btomgp.add(viarl, 2, 0);
                btomgp.add(depl, 3, 0);
                btomgp.add(arrl, 4, 0);
                btomgp.add(orgl, 5, 0);
                btomgp.add(desl, 6, 0);
                btomgp.add(ratl, 7, 0);
                btomgp.add(farel, 8, 0);
                btomgp.add(selserl, 9, 0);
                btomgp.add(busidl1, 0, 2);
                btomgp.add(clofserl1, 1, 2);
                btomgp.add(viarl1, 2, 2);
                btomgp.add(depl1, 3, 2);
                btomgp.add(arrl1, 4, 2);
                btomgp.add(orgl1, 5, 2);
                btomgp.add(desl1, 6, 2);
                btomgp.add(imgratv, 7, 2);
                btomgp.add(farel1, 8, 2);
                btomgp.add(ctobbtn, 9, 2);
                btomgp.setAlignment(Pos.CENTER);
                Button gobackbtn = new Button("Go Back");
                HBox gbhbox = new HBox(gobackbtn);
                gbhbox.setAlignment(Pos.CENTER);
                gbhbox.setPadding(new Insets(30));
                gobackbtn.setOnAction(e1->{
                    dialog1.close();
                });
                btomgp.setBorder(new Border(new BorderStroke(Color.valueOf("#d60909"),BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                btomgp.setPadding(new Insets(50));
                vbox.getChildren().addAll(headgp1,fromtol9,btomgp,gbhbox);
                Scene scene6=new Scene(vbox);
                scene6.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                dialog1.setScene(scene6);
                dialog1.initStyle(StageStyle.DECORATED);
                dialog1.showAndWait();
            }
            else{
               Label avaserl = new Label("Sorry No Services Available "); 
               HBox avaserlhbox = new HBox(avaserl);
               avaserlhbox.setAlignment(Pos.CENTER);
               avaserlhbox.setPadding(new Insets(100));
               vbox.getChildren().addAll(headgp1,avaserlhbox);
               Scene scene15=new Scene(vbox,800,400);
               scene15.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
               dialog1.setScene(scene15);
               dialog1.initStyle(StageStyle.DECORATED);
               dialog1.showAndWait();
            }
        });
        btn.setId("btn");
        btn.setPrefSize(190, 50);
        contentfp.getChildren().addAll(fromcb,tocb,datedp,btn);
        contentfp.setAlignment(Pos.CENTER);
        StackPane tfsp = new StackPane();
        tfsp.getChildren().addAll(imgmainv,contentfp);
        VBox headervb = new VBox();
        headervb.getChildren().addAll(headgp,tfsp);
        bpane.setTop(headervb);
        GridPane gpane2 = new GridPane();
        gpane2.setId("footer");
        Image imgflogo = new Image(getClass().getResourceAsStream("logo.png"));
        ImageView imgflogov = new ImageView(imgflogo);
        imgflogov.setFitWidth(70);
        imgflogov.setFitHeight(70);
        Text t1 = new Text("PARADISE TRAVEL");
        t1.setFill(Color.WHITE);
        Text t2 = new Text("Ⓒ 2020 ibibogroup All rights reserved ");
        t1.setFont(Font.font ("Arial",FontWeight.BOLD, 19));
        t2.setFill(Color.WHITE);
        t2.setFont(Font.font ("Arial",FontWeight.BOLD, 19));
        gpane2.setHgap(10);
        gpane2.add(imgflogov, 0, 0);
        gpane2.add(t1, 1, 0);
        gpane2.add(t2, 119, 0);
        bpane.setBottom(gpane2);      
        Scene scene7=new Scene(bpane);
        scene7.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        dialog.setScene(scene7);
        dialog.initStyle(StageStyle.DECORATED);
        dialog.showAndWait();
    }
    public void canceltick(){
        Alert a = new Alert(AlertType.NONE); 
        final Stage dialog1=new Stage();
        dialog1.initModality(Modality.APPLICATION_MODAL);
        VBox vbox1 = new VBox();
        GridPane headgp2 = new GridPane();
        headgp2.setId("header");
        Image imglogo2 = new Image(getClass().getResourceAsStream("logo.png"));
        ImageView imglogov2 = new ImageView(imglogo2);
        imglogov2.setFitWidth(80);
        imglogov2.setFitHeight(100);
        Label bustick2 = new Label("CANCEL TICKETS");
        bustick2.setId("head");
        headgp2.setHgap(10);
        headgp2.add(imglogov2, 10, 0);
        headgp2.add(bustick2, 14, 0);   
        GridPane cangp = new GridPane();
        cangp.setPadding(new Insets(70));
        Label busid = new Label("Bus Id ");
        TextField busidtf = new TextField();
        //ComboBox busnocb = new ComboBox();
        //busnocb.getItems().addAll("101","102","103","104","105","106","107","108","109","110","111","112","113","114");
        busidtf.setPrefSize(270, 50);
        Label seatno = new Label("Seat No ");
        TextField seatnotf = new TextField();
        cangp.setHgap(10);
        cangp.add(busid, 0, 0);
        cangp.add(busidtf, 1, 0);
        cangp.add(seatno, 3, 0);
        cangp.add(seatnotf, 4, 0);
        Button canbtn =new Button("Cancel Ticket");
        canbtn.setOnAction( (ActionEvent e)->{
            if((busidtf.getText().isEmpty()) && seatnotf.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Required Fields Empty");
                alert.showAndWait();
            }
            else if(busidtf.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Bus Id Field is Empty");
                alert.showAndWait();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Seat No Field is Empty");
                alert.showAndWait();
            }
             try{
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/Bus_Ticket_Reservation","andriya","19ucsa209");
                PreparedStatement ps=con.prepareStatement("delete from bookbus where bookbus.busid=? and bookbus.seat=?");
                ps.setString(1,busidtf.getText()); 
                ps.setString (2,seatnotf.getText());
                ps.executeUpdate();
                a.setAlertType(AlertType.CONFIRMATION); 
                a.setContentText("Are you sure to cancel your ticket"); 
                Optional<ButtonType> result = a.showAndWait();
                ButtonType button = result.orElse(ButtonType.CANCEL);
                if (button == ButtonType.OK) {
                    a.setAlertType(AlertType.INFORMATION); 
                    a.setContentText("Ticket Cancelled");
                    a.showAndWait();
                } 
            }
            catch(ClassNotFoundException | SQLException err){
                System.out.println(err);
            }   
            
        });
        FlowPane cancelbtnfp = new FlowPane(canbtn);
        cancelbtnfp.setAlignment(Pos.CENTER);
        cancelbtnfp.setPadding(new Insets(20));
        vbox1.getChildren().addAll(headgp2,cangp,cancelbtnfp);
        Scene scene = new Scene(vbox1,870,450);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        dialog1.initStyle(StageStyle.DECORATED);
        dialog1.setScene(scene);
        dialog1.show();
    }
    public void booktickets(String farel1){
        Alert a = new Alert(AlertType.NONE); 
        final Stage dialog2=new Stage();
        dialog2.initModality(Modality.APPLICATION_MODAL);
        VBox vbox1 = new VBox();
        GridPane headgp2 = new GridPane();
        headgp2.setId("header");
        Image imglogo2 = new Image(getClass().getResourceAsStream("logo.png"));
        ImageView imglogov2 = new ImageView(imglogo2);
        imglogov2.setFitWidth(80);
        imglogov2.setFitHeight(100);
        Label bustick2 = new Label("BOOK TICKETS");
        bustick2.setId("head");
        headgp2.setHgap(10);
        headgp2.add(imglogov2, 20, 0);
        headgp2.add(bustick2, 22, 0);   
        HBox hbox = new HBox();
        GridPane bookgp = new GridPane();
        Label busid = new Label("Bus Id ");
        TextField busidtf = new TextField();
        Label custname = new Label("Customer Name ");
        TextField custnametf = new TextField();
        Label seatno = new Label("Seat No ");
        TextField seatnotf = new TextField();
        Label mobno = new Label("Mobile No ");
        TextField mobnotf = new TextField();
        Label datel = new Label("Date ");
        TextField datetf = new TextField();
        Button addbtn = new Button("Add");
        addbtn.setOnAction(e2 -> {
            String idbus=busidtf.getText();
            String seatnumber=seatnotf.getText();
            String customername=custnametf.getText();
            String mobileno=mobnotf.getText();
            String date=datetf.getText(); 
            try{
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/Bus_Ticket_Reservation","andriya","19ucsa209");
                PreparedStatement ps=con.prepareStatement("insert into BOOKBUS(busid,seat,customer,mobileno,date)values(?,?,?,?,?)");
                ps.setString(1,idbus); 
                ps.setString (2,seatnumber);
                ps.setString(3,customername);
                ps.setString(4,mobileno);
                ps.setString(5,date);
                ps.executeUpdate();
                PreparedStatement ps1=con.prepareStatement("update seat set status=? where seats=?");
                ps1.setString(1, "Booked");
                ps1.setString(2, seatnumber);
                ps1.executeUpdate();
                a.setAlertType(AlertType.INFORMATION); 
                a.setContentText("Bus Booked ! \n Your Fare "+farel1); 
                Optional<ButtonType> result = a.showAndWait();
                ButtonType button = result.orElse(ButtonType.CANCEL);
                if (button == ButtonType.OK) {
                    refresh();
                } 
            }
                catch(ClassNotFoundException | SQLException err){
                System.out.println(err);
                }   
            
        });
        Button canbtn = new Button("Cancel");
        canbtn.setOnAction(e->{
            dialog2.close();
        });
        bookgp.setHgap(10);
        bookgp.setVgap(10);
        bookgp.add(busid,0,0);
        bookgp.add(busidtf,1,0);
        bookgp.add(custname,0,1);
        bookgp.add(custnametf,1,1);
        bookgp.add(seatno,0,2);
        bookgp.add(seatnotf,1,2);
        bookgp.add(mobno,0,3);
        bookgp.add(mobnotf,1,3);
        bookgp.add(datel,0,4);
        bookgp.add(datetf,1,4);
        bookgp.add(addbtn,0,5);
        bookgp.add(canbtn,1,5);
        bookgp.setPadding(new Insets(20));
        tableview = new TableView();
        //tableview.setMaxSize(800, 800);
        tableview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        booking();
        tableview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
        public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
            if(tableview.getSelectionModel().getSelectedItem() != null) 
                {    
                    TableViewSelectionModel selectionModel = tableview.getSelectionModel();
                    ObservableList selectedCells = selectionModel.getSelectedCells();
                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                    Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
                    seatnotf.setText(val.toString());
                }
                    }
                    });
                    tableview.setPadding(new Insets(20));
                    hbox.getChildren().addAll(bookgp,tableview);
                    hbox.setPadding(new Insets(20));
                    vbox1.getChildren().addAll(headgp2,hbox);
                    //Main Scene
                    Scene scene = new Scene(vbox1);
                    scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                    dialog2.initStyle(StageStyle.DECORATED);
                    dialog2.setScene(scene);
                    dialog2.show();
    }

    public void contactus(){
        final Stage dialog2=new Stage();
        dialog2.initModality(Modality.APPLICATION_MODAL);
        VBox vbox1 = new VBox();
        GridPane headgp2 = new GridPane();
        headgp2.setId("header");
        Image imglogo2 = new Image(getClass().getResourceAsStream("logo.png"));
        ImageView imglogov2 = new ImageView(imglogo2);
        imglogov2.setFitWidth(80);
        imglogov2.setFitHeight(100);
        Label bustick2 = new Label("CONTACT US");
        bustick2.setId("head");
        headgp2.setHgap(10);
        headgp2.add(imglogov2, 8, 0);
        headgp2.add(bustick2, 10, 0);  
        GridPane contusgp = new GridPane();
        Image imgcontuslogo = new Image(getClass().getResourceAsStream("contactUs.png"));
        ImageView imgcontuslogov = new ImageView(imgcontuslogo);
        imgcontuslogov.setFitWidth(80);
        imgcontuslogov.setFitHeight(100);
        Label addl = new Label("Address ");
        Label addl1 = new Label("Paradise Travels Pvt.Ltd,");
        Label addl2 = new Label("Head Office, Pallavan Salai,");
        Label addl3 = new Label("Koyambedu,");
        Label addl4 = new Label("Chennai-600107");
        Label email = new Label("E-mail ");
        Hyperlink emaillink = new Hyperlink("paradisetravels@gmail.com");
        Label tollnol = new Label("Toll Free Number ");
        Label tollnol1 = new Label("9513508001");
        contusgp.setHgap(20);
        contusgp.setVgap(20);
        contusgp.add(imgcontuslogov, 0, 0);
        contusgp.add(addl, 1, 0);
        contusgp.add(addl1, 2, 0);
        contusgp.add(addl2, 2, 1);
        contusgp.add(addl3, 2, 2);
        contusgp.add(addl4, 2, 3);
        contusgp.add(email, 1, 4);
        contusgp.add(emaillink, 2, 4);
        contusgp.add(tollnol, 1, 5);
        contusgp.add(tollnol1, 2, 5);
        contusgp.setPadding(new Insets(20));
        vbox1.getChildren().addAll(headgp2,contusgp);
        Scene scene = new Scene(vbox1,800,550);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        dialog2.initStyle(StageStyle.DECORATED);
        dialog2.setScene(scene);
        dialog2.show();
    }
    
    public void refresh(){
        Alert a = new Alert(AlertType.NONE); 
        final Stage dialog2=new Stage();
        dialog2.initModality(Modality.APPLICATION_MODAL);
        VBox vbox1 = new VBox();
        GridPane headgp2 = new GridPane();
        headgp2.setId("header");
        Image imglogo2 = new Image(getClass().getResourceAsStream("logo.png"));
        ImageView imglogov2 = new ImageView(imglogo2);
        imglogov2.setFitWidth(80);
        imglogov2.setFitHeight(100);
        Label bustick2 = new Label("BOOK TICKETS");
        bustick2.setId("head");
        headgp2.setHgap(10);
        headgp2.add(imglogov2, 20, 0);
        headgp2.add(bustick2, 22, 0);   
        HBox hbox = new HBox();
        GridPane bookgp = new GridPane();
        Label busid = new Label("Bus Id ");
        TextField busidtf = new TextField();
        Label custname = new Label("Customer Name ");
        TextField custnametf = new TextField();
        Label seatno = new Label("Seat No ");
        TextField seatnotf = new TextField();
        Label mobno = new Label("Mobile No ");
        TextField mobnotf = new TextField();
        Label datel = new Label("Date ");
        TextField datetf = new TextField();
        Button addbtn = new Button("Add");
        addbtn.setOnAction(e2 -> {
            String idbus=busidtf.getText();
            String seatnumber=seatnotf.getText();
            String customername=custnametf.getText();
            String mobileno=mobnotf.getText();
            String date=datetf.getText(); 
            try{
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/Bus_Ticket_Reservation","andriya","19ucsa209");
                PreparedStatement ps=con.prepareStatement("insert into BOOKBUS(busid,seat,customer,mobileno,date)values(?,?,?,?,?)");
                ps.setString(1,idbus); 
                ps.setString (2,seatnumber);
                ps.setString(3,customername);
                ps.setString(4,mobileno);
                ps.setString(5,date);
                ps.executeUpdate();
                PreparedStatement ps1=con.prepareStatement("update seat set seats=? where seats=?");
                ps1.setString(1, "Booked");
                ps1.setString(2, seatnumber);
                ps1.executeUpdate();
                a.setAlertType(AlertType.INFORMATION); 
                a.setContentText("Login Successful"); 
                Optional<ButtonType> result = a.showAndWait();
                ButtonType button = result.orElse(ButtonType.CANCEL);
                if (button == ButtonType.OK) {
                    //refresh();
                } 
            }
                catch(ClassNotFoundException | SQLException err){
                System.out.println(err);
                }   
            
        });
        Button canbtn = new Button("Cancel");
        bookgp.setHgap(10);
        bookgp.setVgap(10);
        bookgp.add(busid,0,0);
        bookgp.add(busidtf,1,0);
        bookgp.add(custname,0,1);
        bookgp.add(custnametf,1,1);
        bookgp.add(seatno,0,2);
        bookgp.add(seatnotf,1,2);
        bookgp.add(mobno,0,3);
        bookgp.add(mobnotf,1,3);
        bookgp.add(datel,0,4);
        bookgp.add(datetf,1,4);
        bookgp.add(addbtn,0,5);
        bookgp.add(canbtn,1,5);
        bookgp.setPadding(new Insets(20));
        tableview = new TableView();
        tableview.setMaxSize(800, 800);
        tableview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        booking();
        tableview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
        public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
            if(tableview.getSelectionModel().getSelectedItem() != null) 
                {    
                    TableViewSelectionModel selectionModel = tableview.getSelectionModel();
                    ObservableList selectedCells = selectionModel.getSelectedCells();
                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                    Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
                    seatnotf.setText(val.toString());
                }
                    }
                    });
                    tableview.setPadding(new Insets(20));
                    hbox.getChildren().addAll(bookgp,tableview);
                    hbox.setPadding(new Insets(20));
                    vbox1.getChildren().addAll(headgp2,hbox);
                    //Main Scene
                    Scene scene = new Scene(vbox1);
                    scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                    dialog2.initStyle(StageStyle.DECORATED);
                    dialog2.setScene(scene);
                    dialog2.show();
    }


    public void booking(){
        data = FXCollections.observableArrayList();
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/Bus_Ticket_Reservation","andriya","19ucsa209");
            Statement st=con.createStatement();
            ResultSet res=st.executeQuery("select * from seat");  
            for(int i=0 ; i<res.getMetaData().getColumnCount(); i++){
                final int j = i;                
                TableColumn col = new TableColumn(res.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });
                tableview.getColumns().addAll(col); 
                System.out.println("Column ["+i+"] ");
            }
            while(res.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=res.getMetaData().getColumnCount(); i++){
                    row.add(res.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);
            }
            tableview.setItems(data);
        }
        catch(ClassNotFoundException | SQLException err){
                System.out.println(err);
        }
    } 
    
   
}   

