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
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.regex.*;

/**
 * FXML Controller class
 *
 * @author Dhanush
 */
public class changePasswordController implements Initializable {

    @FXML
    private Button newButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button accountButton;
    @FXML
    private PasswordField oldPass;
    @FXML
    private PasswordField newPass;
    @FXML
    private PasswordField confirmPass;
    @FXML
    private Label errorMessage;

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
    private void changePasswordButton(MouseEvent event) throws SQLException, IOException {
        String old = oldPass.getText();
        String newPassword = newPass.getText();
        String confirmPassword = confirmPass.getText();
        if(!(old.equals(Main.password))){
            errorMessage.setText("Incorrect Old Password");
            System.out.println(old+" "+Main.password);
            oldPass.setText("");
            newPass.setText("");
            confirmPass.setText("");
        }
        else if(!(isValidPassword(newPassword)))
                {
                    errorMessage.setText("Invalid New Password");
                    newPass.setText("");
                    confirmPass.setText("");
                }
        else if(!(newPassword.equals(confirmPassword)))
        {
            errorMessage.setText("Passwords do not match");
            newPass.setText("");
            confirmPass.setText("");
        }
        else
        {
            errorMessage.setText("");
            String url       = "jdbc:mysql://localhost:3306/UserDetails";
            String user      = "root";
            String pass  = "zjCYg5@1";
            String id = "";
            Connection conn = null;
            String opNum = null;
            try {
            conn = DriverManager.getConnection(url,user,pass);
        }   
            catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
            String sqlUpdate = "UPDATE MedicalOfficer "
                + "SET pass = ? "
                + "WHERE regId = ?";
        Statement stmt  = conn.createStatement();
        PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
            pstmt.setString(1, newPassword);
            pstmt.setString(2, Main.id);
            int rowAffected = pstmt.executeUpdate();
        showSuccessMessage("Password Changed Successfully");
         }
            
        
    }
    public static boolean isValidPassword(String password) 
    { 
  
        // Regex to check valid password. 
        String regex = "^(?=.*[0-9])"
                       + "(?=.*[a-z])(?=.*[A-Z])"
                       + "(?=.*[@#$%^&+=])"
                       + "(?=\\S+$).{8,20}$"; 
  
        // Compile the ReGex 
        Pattern p = Pattern.compile(regex); 
  
        // If the password is empty 
        // return false 
        if (password == null) { 
            return false; 
        } 
  
        // Pattern class contains matcher() method 
        // to find matching between given password 
        // and regular expression. 
        Matcher m = p.matcher(password); 
  
        // Return if the password 
        // matched the ReGex 
        return m.matches(); 
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
