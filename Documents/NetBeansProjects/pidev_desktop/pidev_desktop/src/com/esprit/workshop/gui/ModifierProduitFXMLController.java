/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.workshop.gui;

import com.esprit.workshop.entites.Produit;
import com.esprit.workshop.services.ServiceProduit;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ModifierProduitFXMLController implements Initializable {


    @FXML
    private TextField tfPoids;
    @FXML
    private TextField tfQuantite;
    @FXML
    private TextField tfPrix;
    @FXML
    private TextField tfNom;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnModifier_Produit;
    
    private Produit produitSelectionne;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Reset(ActionEvent event) {
        tfNom.setText("");
        tfPrix.setText("");
        tfQuantite.setText("");
        tfPoids.setText("");
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
    public void ModifierProduit(ActionEvent event) throws SQLException {
        
        ServiceProduit serviceProduit = new ServiceProduit();
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

        // Appeler la méthode update avec le produit modifié
        produitSelectionne.setNom(tfNom.getText());
        produitSelectionne.setPrix(Double.parseDouble(tfPrix.getText()));
        produitSelectionne.setQuantite(Integer.parseInt(tfQuantite.getText()));
        produitSelectionne.setPoids(Double.parseDouble(tfPoids.getText()));
        serviceProduit.updateOne(produitSelectionne);
        System.out.println("Produit modifié !");
        Stage stage = (Stage) btnModifier_Produit.getScene().getWindow();
        stage.close();
    }
    }
    
    public void initData(Produit produit) {
    // Récupérer les champs de la vue de modification
   // ComboBox<Categorie> categorieComboBox = ...;

    // Initialiser les champs avec les données du produit
    this.produitSelectionne = produit;
    tfNom.setText(produit.getNom());
    tfPrix.setText(String.valueOf(produit.getPrix()));
    tfQuantite.setText(String.valueOf(produit.getQuantite()));
    tfPoids.setText(String.valueOf(produit.getPoids()));
    //categorieComboBox.setValue(produit.getCat());
}


    
}
