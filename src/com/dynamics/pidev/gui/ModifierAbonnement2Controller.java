/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dynamics.pidev.gui;



import com.dynamics.pidev.entites.abonnementSalle;
import com.dynamics.pidev.services.AbonnementService;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModifierAbonnement2Controller implements Initializable {
    
    @FXML
    private TextField idField;
    
    @FXML
    private TextField dureeAbonnementField;
    
    private AbonnementService abonnementService;
    
    @FXML 
        void switchButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/AfficherAbonnement2.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        abonnementService = new AbonnementService();
    }    
    
    @FXML
    public void modifierAbonnement() {
        try {
            int id = Integer.parseInt(idField.getText());
            String dureeAbonnement = dureeAbonnementField.getText();
            
            abonnementSalle abonnement = new abonnementSalle();
            abonnement.setDuree_abonnement(dureeAbonnement);
            
            abonnementService.updateOne(abonnement, id);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modification d'abonnement");
            alert.setHeaderText(null);
            alert.setContentText("L'abonnement a été modifié avec succès !");
            alert.showAndWait();
            
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir les champs !");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur est survenue lors de la modification de l'abonnement !");
            alert.showAndWait();
        }
    }
    
}

