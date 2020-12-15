package wecare;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.regex.Pattern;
import javafx.stage.StageStyle;


public class Main extends Application  {
    static String fname,lastName,address,email;
    static String id;
    static String phone;
    static String displayMessage;
    static String password;
    static String medicalID, userFirstName, userLastName, userName,userDob, userBloodGroup;
    static String officerName;
    static int size;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        stage.setTitle("Login");
    }
    public static void StoreData(int id,String fname,String lastName,String address, String email, String phone,String pass){
        Main.id = String.valueOf(id);
        Main.fname = fname;
        Main.lastName = lastName;
        Main.address = address;
        Main.email = email;
        Main.phone = phone;
        Main.password = pass;
        Main.officerName = Main.fname +" "+Main.lastName;
        Main.officerName = Main.officerName.toUpperCase();
        System.out.println(id+fname+lastName+address+email);
        
    }
    public static void StoreUserInformation(String medID, String fname, String lName, String dob, String bloodGroup){
        Main.medicalID = medID;
        Main.userFirstName = fname;
        Main.userLastName = lName;
        Main.userName = Main.userFirstName +" "+Main.userLastName;
        Main.userDob = dob;
        Main.userBloodGroup = bloodGroup;
    }
    
     /*
     * @param args
     */
   
    public static void main(String[] args){
        launch(args);
    } 
}


