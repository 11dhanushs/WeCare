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
import java.util.ArrayList;
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
public class searchRecord implements Initializable {

    @FXML
    private Button newButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button accountButton;
    @FXML
    private TextField medicalIDSearch;
    @FXML
    private TextField lNameSearch;
    @FXML
    private Label invalidUserLabelSearch;
    
    static ArrayList<String> problem = new ArrayList<String>();
    static ArrayList<String> prescription = new ArrayList<String>();
    static ArrayList<String> treatedBy = new ArrayList<String>();
    static ArrayList<String> date = new ArrayList<String>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void searchNextButton(MouseEvent event) throws SQLException, IOException {
        String medID = medicalIDSearch.getText().toLowerCase();
        String lastname = lNameSearch.getText().toLowerCase();
        Connection conn = MySQLJDBCUtil.getConnection();
        String dob = "";
        String fname = "";
        Statement stmt  = conn.createStatement();
        String sql = "SELECT * from user where medId =\""+medID+"\" and lname=\""+lastname+"\"";
        ResultSet rs    = stmt.executeQuery(sql);
        if(rs.next()){
            dob = String.valueOf(rs.getDate("dob"));
            fname = rs.getString("fname");
            String bg = rs.getString("bloodgroup");
            Main.StoreUserInformation(medID, fname, lastname, dob, bg);
            getData();
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage window = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/wecare/searchRecordDisplay.fxml"));
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.initStyle(StageStyle.UNDECORATED);
            window.show();
        }
        else{
            invalidUserLabelSearch.setText("Invalid User");
            medicalIDSearch.setText("");
            lNameSearch.setText("");
    }
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
    
    public static void getData() throws SQLException{

        Connection conn = MySQLJDBCUtil.getConnection();
        Statement stmt  = conn.createStatement();
        String sql = "SELECT * from MedicalRecord where medId=\""+Main.medicalID+"\"";
        ResultSet rs    = stmt.executeQuery(sql);
        int count =0;
        while(rs.next()&&count<5){
            problem.add(rs.getString("problem"));
            prescription.add(rs.getString("prescription"));
            treatedBy.add(rs.getString("treatedBy"));
            date.add(rs.getString("DateTreatment"));
            count++;
        }
        int size = problem.size();
        Main.size = size;
        }
    
}
