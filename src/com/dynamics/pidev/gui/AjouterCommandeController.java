/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dynamics.pidev.gui;

import com.dynamics.pidev.entites.Commande;
import com.dynamics.pidev.services.CommandeService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author MSI
 */






public class AjouterCommandeController implements Initializable {

    @FXML
    private TextField tfduree;
    @FXML
    private Button btnenv;

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
        if (tfduree.getText().isEmpty()) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Erreur");
            al.setContentText("Veuillez remplir le champ !");
            al.show();
        }else{
            Commande p = new Commande(tfduree.getText());
            CommandeService sp = new CommandeService();
            
            try {
                sp.insertOne1(p);
            } catch (SQLException ex) {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Erreur");
                al.setContentText(ex.getMessage());
                al.show();
            }
            
        }}}
     



    

