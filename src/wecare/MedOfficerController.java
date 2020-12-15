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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Dhanush
 */
public class MedOfficerController implements Initializable {

    @FXML
    private Button newButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button accountButton;
    public  Label regID ;
    public Label name_Label;
    public Label address_label;
    private TextField medicalID;
    private Label invalidUserLabel;
    private TextField lName;
    private TextField medicalIDSearch;
    private TextField lNameSearch;
    private Label invalidUserLabelSearch;
    @FXML
    public Label regIDAcc;
    @FXML
    private Label nameAcc;
    @FXML
    private Label AddressAcc;
    @FXML
    private Label emailAcc;
    @FXML
    private Label phoneAcc;

    /**
     * Initializes the controller class.
     */
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void newButtonClicked(MouseEvent event) throws IOException {
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
    public void searchButtonClicked(MouseEvent event) throws IOException {
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
    public void accountButtonClicked(MouseEvent event) throws IOException {
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
    private void searchNextButton(MouseEvent event) throws SQLException {
        String medID = medicalIDSearch.getText().toLowerCase();
        String lastname = lNameSearch.getText().toLowerCase();
        String url       = "jdbc:mysql://localhost:3306/UserDetails";
        String user      = "root";
        String pass  = "zjCYg5@1";
        String dob = "";
        String fname = "";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,user,pass);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Statement stmt  = conn.createStatement();
        String sql = "SELECT * from user where medId =\""+medID+"\" and lname=\""+lastname+"\"";
        ResultSet rs    = stmt.executeQuery(sql);
        if(rs.next()){
            dob = String.valueOf(rs.getDate("dob"));
            fname = rs.getString("fname");
            System.out.println(fname+dob);
        }
        else{
            invalidUserLabelSearch.setText("Invalid User");
            medicalIDSearch.setText("");
            lNameSearch.setText("");
    }
    }
    @FXML
    private void nextButton(MouseEvent event) throws SQLException{
        String medID = medicalID.getText().toLowerCase();
        String lastname = lName.getText().toLowerCase();
        String url       = "jdbc:mysql://localhost:3306/UserDetails";
        String user      = "root";
        String pass  = "zjCYg5@1";
        String dob = "";
        String fname = "";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,user,pass);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Statement stmt  = conn.createStatement();
        String sql = "SELECT * from user where medId =\""+medID+"\" and lname=\""+lastname+"\"";
        ResultSet rs    = stmt.executeQuery(sql);
        if(rs.next()){
            invalidUserLabel.setText("");
            dob = String.valueOf(rs.getDate("dob"));
            fname = rs.getString("fname");
            System.out.println(fname+dob);
        }
        else{
            invalidUserLabel.setText("Invalid User");
            medicalID.setText("");
            lName.setText("");
    }
    }

   
    
}
