/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.esprit.gui;

import edu.esprit.entities.Livraison;
import edu.esprit.entities.Commande;
import edu.esprit.services.CommandeService;
import edu.esprit.services.LivraisonService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AfficherCommandes2Controller implements Initializable {

    @FXML
    private TableView<Commande> table;
    
    @FXML
    private TableColumn<Commande, String> dureeColumn;
    
    @FXML
    private TableColumn<Commande, Date> tc_commande;
    
    private ObservableList<Commande> abonnementList;
    
    private CommandeService su;
    
    @FXML 
        void switchButton(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AjouterCommande2.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Add ");
        stage.setScene(new Scene(parent));
        stage.showAndWait();
    }
       
    @FXML 
        void switchButton1(ActionEvent event) throws IOException {
           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModifierCommande2.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Add ");
        stage.setScene(new Scene(parent));
        stage.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        su = new CommandeService();
        
      try {
          abonnementList = FXCollections.observableArrayList(su.selectAll());
      } catch (SQLException ex) {
          Logger.getLogger(AfficherCommandes2Controller.class.getName()).log(Level.SEVERE, null, ex);
      }
        
       // dureeColumn.setCellValueFactory(new PropertyValueFactory<>("nom_commande"));
        dureeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom_commande()));
        tc_commande.setCellValueFactory(cellData -> new SimpleObjectProperty<Date>(cellData.getValue().getDate_commande()));

        

        
        table.setItems(abonnementList);
    }
    
         @FXML
    void deleteAbonnement(ActionEvent event) throws SQLException, IOException  {
    su = new CommandeService();
    Commande selectedAbonnement = table.getSelectionModel().getSelectedItem(); 
    if (selectedAbonnement == null) { // Vérifier si aucun abonnement n'est sélectionné
        // Afficher un message d'erreur
       Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Pas de commande séléctionné");
        alert.setContentText("S'il vous plait de séléctionner une commande");
        alert.showAndWait();
    } else {
        su.deleteOne(selectedAbonnement.getId()); // Supprimer l'abonnement sélectionné en utilisant la méthode deleteOne() de la classe LivraisonService
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Suppression");
        alert.setContentText("Commande supprimer avec succées");
        alert.showAndWait();
    }
}
}
