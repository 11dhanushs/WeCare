/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wecare;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Dhanush
 */
public class LoginController implements Initializable {
    @FXML
    private TextField TextField_email;
    @FXML
    private PasswordField TextField_password;
    @FXML
    private Label email;
    @FXML
    private Label invaliEmailLabel;
    @FXML
    private Label exitLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginbtn(MouseEvent event) throws SQLException, IOException, Exception  {
        String email =TextField_email.getText();
        String password = TextField_password.getText();
        if(isValid(email)){
            Connection conn = MySQLJDBCUtil.getConnection();
            Statement stmt  = conn.createStatement();
            String sql = "SELECT * from MedicalOfficer where email=\""+email+"\" and pass=\""+password+"\"";
            ResultSet rs    = stmt.executeQuery(sql);
            if(rs.next()){
                int id = rs.getInt("regID");
                String fname = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String Address = rs.getString("Address");
                String phone = rs.getString("phone");
                String pass = rs.getString("pass");
                Main.StoreData(id, fname, lastName, Address, email, phone,pass);
                loginSuccessful(event);
            }
            else{
                ShowErrorMessage("Incorrect emmail/password");
                TextField_email.setText("");
                TextField_password.setText("");
            }
        }
    }

    @FXML
    private void validateEmail(KeyEvent event) {
        String email = TextField_email.getText();
        if(isValid(email))
            invaliEmailLabel.setText("");
        else
            invaliEmailLabel.setText("Invalid Email");
        
    
    }
    public static boolean isValid(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 

    @FXML
    private void exitLabelClicked(MouseEvent event) {
        System.exit(0);
    }
    public void ShowErrorMessage(String message) throws IOException{
        Stage stage = new Stage();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/wecare/ErrorMessage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
}
    public static void closeStage(MouseEvent event){
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void loginSuccessful(MouseEvent event) throws IOException{
        closeStage(event);
        Stage window = new Stage();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/wecare/AccountDisplay.fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.initStyle(StageStyle.UNDECORATED);
        window.show();
    }
}
