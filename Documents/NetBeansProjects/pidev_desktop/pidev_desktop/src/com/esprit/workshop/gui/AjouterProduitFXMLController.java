/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.workshop.gui;

import com.esprit.workshop.entites.Categorie_prod;
import com.esprit.workshop.entites.Produit;
import com.esprit.workshop.services.ServiceCategorie_prod;
import com.esprit.workshop.services.ServiceProduit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
public class AjouterProduitFXMLController implements Initializable {

    @FXML
    private Button btnAjouter_Produit;
    @FXML
    private Button btnAfficher_Produit;
    @FXML
    private Button btnReset;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrix;
    @FXML
    private TextField tfQuantite;
    @FXML
    private TextField tfPoids;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public boolean isFloat(String str) {
    try {
        Float.parseFloat(str);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}
public boolean isNumber(String input) {
    try {
        Double.parseDouble(input);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}

    @FXML
    private void AjouterProduit(ActionEvent event) {
        if (tfNom.getText().isEmpty() || tfPrix.getText().isEmpty() || tfQuantite.getText().isEmpty() || tfPoids.getText().isEmpty()) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Erreur de donnee");
            al.setContentText("Veuillez verifier les données !");
            al.show();
        }else{
            if (!isFloat(tfPrix.getText())) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Erreur de donnee");
            al.setContentText("Le champ du prix ne doit contenir aucun caractere !");
            al.show();
        }
            if (!isNumber(tfQuantite.getText())) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Erreur de donnee");
            al.setContentText("Le champ du quantite ne doit contenir aucun caractere !");
            al.show();
        }
            if (!isFloat(tfPoids.getText())) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Erreur de donnee");
            al.setContentText("Le champ du poids ne doit contenir aucun caractere !");
            al.show();
        }
            
            Produit p = new Produit(tfNom.getText(), Float.parseFloat(tfPrix.getText()), Integer.parseInt(tfQuantite.getText()),Float.parseFloat(tfPoids.getText()));
            ServiceProduit sp = new ServiceProduit();
            
            try {
                sp.insertOne1(p);
            } catch (SQLException ex) {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Erreur de donnee");
                al.setContentText(ex.getMessage());
                al.show();
            }
            
        }
    }

    @FXML
    private void AfficherProduit(ActionEvent event) throws IOException {
        try {
            
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduitFXML.fxml"));
            Parent root = loader.load();
            AfficherProduitFXMLController afPFXC = loader.getController();
            afPFXC.afficherProduits();
            
            //Step 1 = Par scene
//            tfNom.getScene().setRoot(root);
            //Step 2 = Par Stage
            Stage st = new Stage();
            st.setTitle("Liste des produits");
            st.setScene(new Scene(root));
            st.show();
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void Reset(ActionEvent event) {
    }
    
}
