/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wecare;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.*;
import java.lang.*; 
import java.io.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;

/**
 * FXML Controller class
 *
 * @author Dhanush
 */
public class NewBirthRecordController implements Initializable {

    @FXML
    private Button newButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button accountButton;
    @FXML
    private Label invalidUserLabel;
    @FXML
    private TextField medicalID;
    @FXML
    private TextField fnameUser;
    @FXML
    private TextField lnameUser;
    @FXML
    private DatePicker dobUser ;
    @FXML
    private ComboBox<String> bloodGroupUser;
    
    private ObservableList<String> bloodGroupList = FXCollections.observableArrayList("A+","A-","B+","B-","AB+","AB-","O+","O-");
    @FXML
    private Label fNameError;
    @FXML
    private Label lNamError;
    @FXML
    private Label dobError;
    @FXML
    private Label bloodGroupError;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bloodGroupUser.setItems(bloodGroupList);
        try {
            medicalID.setText(createMedicalID());
        } catch (SQLException ex) {
            Logger.getLogger(NewBirthRecordController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bloodGroupUser.getSelectionModel().select(0);
        dobUser.setValue(LocalDate.now());
    }    
    

    @FXML
    private void submitButton(MouseEvent event) throws SQLException, IOException {
        String medID = medicalID.getText();
        String firstName = ""; 
        firstName = fnameUser.getText().toLowerCase();
        String lastName = "";
        lastName = lnameUser.getText().toLowerCase();
        String dob ="";
        dob = String.valueOf(dobUser.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        String bloodGroup ="";
        bloodGroup = bloodGroupUser.getValue();
        Connection conn = MySQLJDBCUtil.getConnection();
        Statement stmt  = conn.createStatement();
        
        if(firstName.equals(""))
            fNameError.setText("Cannot be empty");
        else
            fNameError.setText("");
        if(lastName.equals(""))
            lNamError.setText("Cannot be empty");
        else
            lNamError.setText("");
        if(!(firstName.equals(""))&&!(lastName.equals("")))
        {
            System.out.println(medID+dob+firstName+lastName+bloodGroup);
            
            String sql = "INSERT into user "+"VALUES(?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql,
                              Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, medID);
            pstmt.setString(2, dob);
            pstmt.setString(3, firstName);
            pstmt.setString(4, lastName);
            pstmt.setString(5, bloodGroup);
            
            int rows = pstmt.executeUpdate();
            System.out.println(sql);
           showSuccessMessage("Submitted"); 
           fnameUser.setText("");
           lnameUser.setText("");
           bloodGroupUser.getSelectionModel().select(0);
           dobUser.setValue(LocalDate.now());
        }
        
            
    }
    
    @FXML
    private void newButtonClicked(MouseEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Stage window = new Stage();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/wecare/newRecord.fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.initStyle(StageStyle.UNDECORATED);
        window.show();
    }

    @FXML
    private void searchButtonClicked(MouseEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Stage window = new Stage();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/wecare/searchRecord.fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.initStyle(StageStyle.UNDECORATED);
        window.show();
    }

    @FXML
    private void accountButtonClicked(MouseEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        Stage window = new Stage();
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/wecare/AccountDisplay.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.initStyle(StageStyle.UNDECORATED);
        window.show();
    }

    

    @FXML
    private void logoutButtonClicked(MouseEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Stage window = new Stage();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/wecare/login.fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.initStyle(StageStyle.UNDECORATED);
        window.show();
    }
    
     public String createMedicalID() throws SQLException{
        String url       = "jdbc:mysql://localhost:3306/UserDetails";
        String user      = "root";
        String pass  = "zjCYg5@1";
        String id = "";
        String opNum = null;
        Connection conn = MySQLJDBCUtil.getConnection();
        Statement stmt  = conn.createStatement();
        String sql = "SELECT medId from user";
        ResultSet rs    = stmt.executeQuery(sql);
        while(rs.next()){
             id = String.valueOf(rs.getString("medID"));  
        }
        if(id.equals("")){
            return "A001";
        }
        char letter = id.charAt(0);
        int number=0;
        for(int i=1;i<=3;i++){
            String character = String.valueOf(id.charAt(i));
            number = number*10+Integer.parseInt(character);
        }
        if(number<999){
            number++;
            opNum = String.valueOf(number);
            if(number<10)
                opNum = "00"+opNum;
            else if(number<100)
                opNum = "0"+opNum;
        }
        else 
            opNum = "000";
        if(opNum.equals("000")){
            letter = (char) (letter +1);
        }
        id = String.valueOf(letter)+opNum;
        return id;
    }
     
     public void showSuccessMessage(String message) throws IOException{
        Main.displayMessage = message;
        Stage stage = new Stage();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/wecare/Submitted.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
}
}
