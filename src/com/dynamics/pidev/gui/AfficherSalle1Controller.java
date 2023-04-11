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
import java.util.List;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AfficherSalle1Controller implements Initializable {

  @FXML
    private TableView<Salle> table;
    
    @FXML
    private TableColumn<Salle, String> nomColumn;
    
    @FXML
    private TableColumn<Salle, String> adresseColumn;
    
    @FXML
    private TableColumn<Salle, Integer> numColumn;
    
    @FXML
    private TableColumn<Salle, Integer> codeColumn;
    
    @FXML
    private TableColumn<Salle, String> villeColumn;
    
    @FXML
    private TableColumn<Salle, Integer> prixColumn;
    
    
    private ObservableList<Salle> salleList;
    
    private SalleService su;
    
    @FXML 
        void switchButton1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/AjouterSalle1.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
        
    @FXML 
        void switchButton2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/ModifierSalle1.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
        


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        su = new SalleService();
        
      try {
          salleList = FXCollections.observableArrayList(su.selectAll());
      } catch (SQLException ex) {
          Logger.getLogger(AfficherSalle1Controller.class.getName()).log(Level.SEVERE, null, ex);
      }
        
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom_salle"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse_salle"));
        numColumn.setCellValueFactory(new PropertyValueFactory<>("num_telephone"));
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("codepostal"));
        villeColumn.setCellValueFactory(new PropertyValueFactory<>("ville"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix_abonnement"));
        
        table.setItems(salleList);
        
    }
    
    
        @FXML
    void deleteSalle(ActionEvent event) throws SQLException, IOException  {
    su = new SalleService();
    Salle selectedSalle = table.getSelectionModel().getSelectedItem(); 
    if (selectedSalle == null) { // Vérifier si aucune salle n'est sélectionné
        // Afficher un message d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Pas de salle séléctionnée");
        alert.setContentText("S'il vous plait de séléctionner une salle");
        alert.showAndWait();
    } else {
        su.deleteOne(selectedSalle.getId()); // Supprimer la salle sélectionnée en utilisant la méthode deleteOne() de la classe SalleService
        Parent root = FXMLLoader.load(getClass().getResource("../gui/AfficherSalle1.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}}
