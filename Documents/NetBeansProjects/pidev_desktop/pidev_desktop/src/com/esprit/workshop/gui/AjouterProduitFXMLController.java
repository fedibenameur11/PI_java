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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    @FXML
    private ComboBox<String> categorieBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            // TODO
            remplirCategorieBox();
        } catch (SQLException ex) {
            Logger.getLogger(AjouterProduitFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    private void remplirCategorieBox() throws SQLException {
        ServiceCategorie_prod sp=new ServiceCategorie_prod();
        List<Categorie_prod> categories= sp.selectAll(); // Récupérer la liste des catégories depuis la base de données
        List<String> nomsCategories = categories.stream().map(Categorie_prod::getNom).collect(Collectors.toList());
        categorieBox.setItems(FXCollections.observableArrayList(nomsCategories));
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
    private void AjouterProduit(ActionEvent event) throws SQLException {
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
            ServiceCategorie_prod sp=new ServiceCategorie_prod();
        List<Categorie_prod> listeCategories= sp.selectAll();
            
            
            String categorieNom = categorieBox.getValue(); // assuming comboBoxCategorie is your ComboBox<String>
            System.out.println(categorieNom);

// Find the corresponding Categorie_prod object based on its name
                Categorie_prod categorie = null;
                for (Categorie_prod c : listeCategories) {
                    if (c.getNom().equals(categorieNom)) {
                        categorie = c;
                        break;
                    }
                }
                System.out.println(categorie);

// Create a new SimpleObjectProperty object and assign the categorie object to its value
            //SimpleObjectProperty<Categorie_prod> categorieProp = new SimpleObjectProperty<>(categorie);
   
            Produit p = new Produit(tfNom.getText(), Float.parseFloat(tfPrix.getText()), Integer.parseInt(tfQuantite.getText()),Float.parseFloat(tfPoids.getText()),categorie);
            ServiceProduit sp1 = new ServiceProduit();
            
            try {
                sp1.insertOne1(p);
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
            //afPFXC.afficherProduits();
            
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
        
        tfNom.setText("");
        tfPrix.setText("");
        tfQuantite.setText("");
        tfPoids.setText("");
    }
    
}
