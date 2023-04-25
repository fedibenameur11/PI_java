/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dynamics.pidev.gui;



import com.dynamics.pidev.entites.Salle;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModifierSalle1Controller implements Initializable {
    
    @FXML
    private TextField idf;
    
    @FXML
    private TextField nomf;
    
    @FXML
    private TextField adressef;
    
    @FXML
    private TextField numf;
    
    @FXML
    private TextField codef;
    
    @FXML
    private TextField villef;
    
    @FXML
    private TextField prixf;
    
    private SalleService salleService;
    
    @FXML 
        void switchButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/AfficherSalle1.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        salleService = new SalleService();
    }    
    
    @FXML
    public void modifierSalle() {
        try {
            int id = Integer.parseInt(idf.getText());
            String nom = nomf.getText();
            String adresse = adressef.getText();
            int num = Integer.parseInt(numf.getText());
            int code = Integer.parseInt(codef.getText());
            String ville = villef.getText();
            int prix = Integer.parseInt(prixf.getText());
            
            Salle salles = new Salle();
            salles.setNom_livraison(nom);
            salles.setDate(adresse);
            
         
            salles.setDestination(ville);
         
            
            salleService.updateOne(salles, id);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modification de salle");
            alert.setHeaderText(null);
            alert.setContentText("La salle a été modifié avec succès !");
            alert.showAndWait();
            
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir un nombre valide pour l'ID !");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur est survenue lors de la modification de la salle !");
            alert.showAndWait();
        }
    }
    
}


