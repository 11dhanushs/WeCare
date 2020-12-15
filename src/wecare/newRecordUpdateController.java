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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
public class newRecordUpdateController implements Initializable {

    @FXML
    private Button newButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button accountButton;
    @FXML
    private TextField probelmsTextField;
    @FXML
    private TextField medicineTextField;
    @FXML
    private Label problemsEmptyLabel;
    @FXML
    private Label medicineEmplyLabel;
    @FXML
    private Label medicalIDLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label bgLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        medicalIDLabel.setText(Main.medicalID);
        nameLabel.setText(Main.userName.toUpperCase());
        dateLabel.setText(Main.userDob);
        bgLabel.setText(Main.userBloodGroup);
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

    @FXML
    private void submitButton(MouseEvent event) throws IOException, SQLException {
        String problems = probelmsTextField.getText();
        String medicine = medicineTextField.getText();
        String date = String.valueOf(java.time.LocalDate.now());
        if(problems.equals(""))
        {
            problemsEmptyLabel.setText("Cannot be empty");
            medicineEmplyLabel.setText("");
        }
        
        else if(medicine.equals(""))
        {
            medicineEmplyLabel.setText("Cannot be empty");
            problemsEmptyLabel.setText("");
        }
        else
        {
            Connection conn = MySQLJDBCUtil.getConnection();
            String sql = "INSERT into MedicalRecord "+"VALUES(?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, Main.medicalID);
            pstmt.setString(2, problems);
            pstmt.setString(3, medicine);
            pstmt.setString(4, Main.officerName);
            pstmt.setString(5, date);
            int rows = pstmt.executeUpdate();
        Statement stmt  = conn.createStatement();
            showSuccessMessage("Submitted Successfully");
            
        }         
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
