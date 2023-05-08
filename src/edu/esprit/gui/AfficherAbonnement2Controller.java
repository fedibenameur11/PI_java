/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.esprit.gui;

import edu.esprit.entities.Salle;
import edu.esprit.entities.abonnementSalle;
import edu.esprit.services.AbonnementService;
import edu.esprit.services.SalleService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AfficherAbonnement2Controller implements Initializable {

    @FXML
    private TableView<abonnementSalle> table;
    private AbonnementHolder holder =AbonnementHolder.getInstance();
    private final abonnementSalle currentabonnement=holder.getAbonnement();
    
    @FXML
    private TableColumn<abonnementSalle, String> dureeColumn;
    
    
    
    private ObservableList<abonnementSalle> abonnementList;
    
    private AbonnementService su;
    
    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;
    
    private ObservableList<abonnementSalle> abonnementsList;
    
    @FXML 
        void switchButton(ActionEvent event) throws IOException {
  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AjouterAbonnement2.fxml"));
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
  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModifierAbonnement2.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Add ");
        stage.setScene(new Scene(parent));
        stage.showAndWait();
    }
        
        @FXML 
        void switchButton3(ActionEvent event) throws IOException {
   FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AfficherSalle1.fxml"));
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
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Abonnement supprimé avec succés !");
            alert.showAndWait();
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AfficherAbonnement2.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Add ");
        stage.setScene(new Scene(parent));
        stage.showAndWait();
    }
}
    
        @FXML
    void modifierAbonnement(ActionEvent event) throws SQLException, IOException  {
         abonnementSalle selectedAbonnement = table.getSelectionModel().getSelectedItem();
    if (selectedAbonnement != null) {
        holder.setAbonnement(selectedAbonnement);
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModifierAbonnement2.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Add ");
        stage.setScene(new Scene(parent));
        stage.showAndWait();

            

    } else {
       // Vérifier si aucun abonnement n'est sélectionné
        // Afficher un message d'erreur
       Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Pas d'abonnement séléctionné");
        alert.setContentText("S'il vous plait de séléctionner un abonnement");
        alert.showAndWait();
   }}
    
    @FXML
private void searchAbonnements() throws SQLException {
   String searchTerm = searchField.getText().trim();
    
   if (searchTerm.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez saisir un terme de recherche.");
        alert.showAndWait();
        return;
    }
    
    AbonnementService service = new AbonnementService();
    ArrayList<abonnementSalle> abonnements = service.search1(searchTerm);

    if (abonnements.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Aucun abonnement trouvé avec le terme '" + searchTerm + "'.");
        alert.showAndWait();
        return;
    }
    
    ObservableList<abonnementSalle> abonnementsList = FXCollections.observableArrayList(abonnements);
    table.setItems(abonnementsList);
}

@FXML
private void sortAbonnement(ActionEvent event) {
    abonnementList.sort(Comparator.comparing(abonnementSalle::getDuree_abonnement));
}
}
