/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.esprit.gui;

import edu.esprit.util.SmsApi;
import edu.esprit.entities.abonnementSalle;
import edu.esprit.services.AbonnementService;
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






public class AjouterAbonnementController implements Initializable {

    @FXML
    private TextField tfduree;
    @FXML
    private Button btnenv;
    
        @FXML 
        void switchButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/AfficherSalle.fxml"));
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
       
        btnenv.setOnAction(event -> {
        AjouterAbonnement(event);
    });
    }   
    
    @FXML
      private void AjouterAbonnement(ActionEvent event) {
    String duree = tfduree.getText();

    if (duree.isEmpty()) {
        Alert al = new Alert(Alert.AlertType.WARNING);
        al.setTitle("Erreur");
        al.setContentText("Veuillez remplir le champ !");
        al.show();
    } else if (duree.length() > 10) {
        Alert al = new Alert(Alert.AlertType.WARNING);
        al.setTitle("Erreur de donnée");
        al.setContentText("La durée ne doit pas dépasser 10 caractères !");
        al.show();
    } else {
        abonnementSalle p = new abonnementSalle(duree);
        AbonnementService sp = new AbonnementService();

        try {
            sp.insertOne1(p);
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout d'un abonnement");
            alert.setHeaderText(null);
            alert.setContentText("Abonnement ajouté avec succés !");
            alert.showAndWait();
            // Envoyer un SMS pour informer le client que son abonnement a été effectué avec succés
        SmsApi smsApi = new SmsApi();
        String numTel = "+21652467059"; // Numéro de téléphone de client à informer
        String messageContent = "Votre abonnement a été effectué avec succés"; // Contenu du SMS à envoyer
        smsApi.send(numTel, messageContent);
            
        } catch (SQLException ex) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Erreur de donnée");
            al.setContentText(ex.getMessage());
            al.show();
        }
    }
}}
     



    

