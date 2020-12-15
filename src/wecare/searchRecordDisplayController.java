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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Dhanush
 */
public class searchRecordDisplayController implements Initializable {

    @FXML
    private Button newButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button accountButton;
    @FXML
    private Label medIDLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label bloodgroupLabel;
    @FXML
    private Label dobLabel;
    @FXML
    private Label P1;
    @FXML
    private Label P2;
    @FXML
    private Label P3;
    @FXML
    private Label P4;
    @FXML
    private Label P5;
    @FXML
    private Label PM1;
    @FXML
    private Label PM2;
    @FXML
    private Label PM3;
    @FXML
    private Label PM4;
    @FXML
    private Label PM5;
    @FXML
    private Label T5;
    @FXML
    private Label T4;
    @FXML
    private Label T3;
    @FXML
    private Label T1;
    @FXML
    private Label T2;
    @FXML
    private Label D1;
    @FXML
    private Label D4;
    @FXML
    private Label D2;
    @FXML
    private Label D3;
    @FXML
    private Label D5;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        medIDLabel.setText(Main.medicalID);
        nameLabel.setText(Main.userName);
        bloodgroupLabel.setText(Main.userBloodGroup);
        dobLabel.setText(Main.userDob);
        for(int i=1;i<=Main.size;i++){
            if(i==1){
                P1.setText(searchRecord.problem.get(0));
                PM1.setText(searchRecord.prescription.get(0));
                T1.setText(searchRecord.treatedBy.get(0));
                D1.setText(searchRecord.date.get(0));
            }
            if(i==2){
                P2.setText(searchRecord.problem.get(0));
                PM2.setText(searchRecord.prescription.get(0));
                T2.setText(searchRecord.treatedBy.get(0));
                D2.setText(searchRecord.date.get(0));
            }
            if(i==3){
                P3.setText(searchRecord.problem.get(0));
                PM3.setText(searchRecord.prescription.get(0));
                T3.setText(searchRecord.treatedBy.get(0));
                D3.setText(searchRecord.date.get(0));
            }
            if(i==4){
                P4.setText(searchRecord.problem.get(0));
                PM4.setText(searchRecord.prescription.get(0));
                T4.setText(searchRecord.treatedBy.get(0));
                D4.setText(searchRecord.date.get(0));
            }
            if(i==5){
                P5.setText(searchRecord.problem.get(0));
                PM5.setText(searchRecord.prescription.get(0));
                T5.setText(searchRecord.treatedBy.get(0));
                D5.setText(searchRecord.date.get(0));
            }
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

    @FXML
    private void backButton(MouseEvent event) throws IOException {
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
}


