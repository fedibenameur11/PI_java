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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AfficherAbonnement2Controller implements Initializable {

    @FXML
    private TableView<abonnementSalle> table;
    
    @FXML
    private TableColumn<abonnementSalle, String> dureeColumn;
    
    
    
    private ObservableList<abonnementSalle> abonnementList;
    
    private AbonnementService su;
    
    @FXML 
        void switchButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/AjouterAbonnement2.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
       
    @FXML 
        void switchButton1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/ModifierAbonnement2.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        su = new AbonnementService();
        
      try {
          abonnementList = FXCollections.observableArrayList(su.selectAll());
      } catch (SQLException ex) {
          Logger.getLogger(AfficherAbonnement2Controller.class.getName()).log(Level.SEVERE, null, ex);
      }
        
        dureeColumn.setCellValueFactory(new PropertyValueFactory<>("duree_abonnement"));
        

        
        table.setItems(abonnementList);
    }
    
         @FXML
    void deleteAbonnement(ActionEvent event) throws SQLException, IOException  {
    su = new AbonnementService();
    abonnementSalle selectedAbonnement = table.getSelectionModel().getSelectedItem(); 
    if (selectedAbonnement == null) { // Vérifier si aucun abonnement n'est sélectionné
        // Afficher un message d'erreur
       Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Pas d'abonnement séléctionné");
        alert.setContentText("S'il vous plait de séléctionner un abonnement");
        alert.showAndWait();
    } else {
        su.deleteOne(selectedAbonnement.getId()); // Supprimer l'abonnement sélectionné en utilisant la méthode deleteOne() de la classe SalleService
       Parent root = FXMLLoader.load(getClass().getResource("../gui/AfficherAbonnement2.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
}