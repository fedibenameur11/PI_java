/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dynamics.pidev.gui;

import com.dynamics.pidev.entites.Livraison;
import com.dynamics.pidev.entites.Commande;
import com.dynamics.pidev.services.CommandeService;
import com.dynamics.pidev.services.LivraisonService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AfficherLivraisonController implements Initializable {

    @FXML
    private ListView<String> ListViewSalles;
    @FXML
    private Button BtnId;
    private LivraisonService salleService = new LivraisonService();
    
    @FXML 
        void switchButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/AjouterCommande.fxml"));
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
    try {
        List<String> salles = salleService.selectAllNames();
        ListViewSalles.getItems().addAll(salles);
    } catch (SQLException ex) {
        Logger.getLogger(AfficherLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
    }
}} 



//@FXML
    //private void AjouterAbonnement(ActionEvent event) throws SQLException {
      //  if (tfduree.getText().isEmpty()) {
        //    Alert al = new Alert(Alert.AlertType.WARNING);
          //  al.setTitle("Erreur de donnee");
            //al.setContentText("Veuillez verifier les données !");
            //al.show();
        //}else{
          //  Commande p = new Commande(tfduree.getText()));
           // CommandeService sp = new CommandeService();
            
           // sp.insertOne1(p);
            
       // }
   // }
    
//}
