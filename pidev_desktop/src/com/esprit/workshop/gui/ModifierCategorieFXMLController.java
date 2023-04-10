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
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ModifierCategorieFXMLController implements Initializable {

    @FXML
    private TextField tfNomCat;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnModifier_Categorie;
    
    private Categorie_prod CategorieSelectionne;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Reset(ActionEvent event) {
        tfNomCat.setText("");

    }

    @FXML
    public void ModifierCategorie(ActionEvent event) throws SQLException {
        
        ServiceCategorie_prod sp = new ServiceCategorie_prod();
        if (tfNomCat.getText().isEmpty()) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Erreur de donnee");
            al.setContentText("Veuillez verifier les données !");
            al.show();
        }else{
        // Appeler la méthode update avec le produit modifié
        CategorieSelectionne.setNom(tfNomCat.getText());
        sp.updateOne(CategorieSelectionne);
        System.out.println(CategorieSelectionne.getNom());
        Stage stage = (Stage) btnModifier_Categorie.getScene().getWindow();
        stage.close();
       
    }
    }
    public void initData(Categorie_prod Cproduit) {
    // Récupérer les champs de la vue de modification
   // ComboBox<Categorie> categorieComboBox = ...;

    // Initialiser les champs avec les données du produit
    this.CategorieSelectionne = Cproduit;
    tfNomCat.setText(Cproduit.getNom());
    //categorieComboBox.setValue(produit.getCat());
}

}
