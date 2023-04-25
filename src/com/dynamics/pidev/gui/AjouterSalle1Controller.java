/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dynamics.pidev.gui;

import com.dynamics.pidev.entites.Salle;
import com.dynamics.pidev.entites.abonnementSalle;
import com.dynamics.pidev.services.AbonnementService;
import com.dynamics.pidev.services.SalleService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AjouterSalle1Controller implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tfnum;
    @FXML
    private TextField tfcode;
    //@FXML
    //private TextField tfville;
    @FXML
    //private TextField tfprix;
    //@FXML
    private Button btnenr;
   


    @FXML 
        void switchButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/AfficherSalle1.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnenr.setOnAction(event -> {
        AjouterSalle1(event);
        });
    }  
    
    @FXML
        private void AjouterSalle1(ActionEvent event) {
    //int id = Integer.parseInt(tfnom.getText());
    String adresse = tfadresse.getText();
    String num = tfnum.getText();
    String code = tfcode.getText();
    //String ville = tfville.getText();
    //String prix = tfprix.getText();

    if ( adresse.isEmpty() || num.isEmpty() || code.isEmpty()) {
        Alert al = new Alert(Alert.AlertType.WARNING);
        al.setTitle("Erreur");
        al.setContentText("Veuillez remplir les champs !");
        al.show();
    
    } 
    }
}


     
    

