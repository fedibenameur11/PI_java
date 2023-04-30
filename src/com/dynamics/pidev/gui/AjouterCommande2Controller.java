/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dynamics.pidev.gui;

import com.dynamics.pidev.entites.Commande;
import com.dynamics.pidev.services.CommandeService;
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
public class AjouterCommande2Controller implements Initializable {

    @FXML
    private TextField tfdur;
    @FXML
    private Button btn;
     @FXML
    private TextField tfdate;


    @FXML 
        void switchButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/AfficherCommandes2.fxml"));
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
         btn.setOnAction(event -> {
        AjouterAbonnement2(event);
    });
    }    
    
    @FXML
    private void AjouterAbonnement2(ActionEvent event) {
    String duree = tfdur.getText();
    String date = tfdate.getText();

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
        Commande p = new Commande(duree,date);
        CommandeService sp = new CommandeService();

        try {
            sp.insertOne1(p);
        } catch (SQLException ex) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Erreur de donnée");
            al.setContentText(ex.getMessage());
            al.show();
        }
    }
}

}
