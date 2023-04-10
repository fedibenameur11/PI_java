/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.workshop.gui;

import com.esprit.workshop.entites.Categorie_prod;
import com.esprit.workshop.services.ServiceCategorie_prod;
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
public class AjouterCategorieFXMLController implements Initializable {

    @FXML
    private Button btnAjouter_Categorie;
    @FXML
    private TextField tfNomCat;
    @FXML
    private Button btnAfficher_Categorie;
    @FXML
    private Button btnReset;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterCategorie(ActionEvent event) {
        if (tfNomCat.getText().isEmpty()) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Erreur de donnee");
            al.setContentText("Veuillez verifier les données !");
            al.show();
        }else{
            Categorie_prod p = new Categorie_prod(tfNomCat.getText());
            ServiceCategorie_prod sp = new ServiceCategorie_prod();
            
            try {
                sp.insertOne(p);
            } catch (SQLException ex) {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Erreur de donnee");
                al.setContentText(ex.getMessage());
                al.show();
            }
            
        }
    }

    @FXML
    private void AfficherCategorie(ActionEvent event) {
        try {
            
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCategorieFXML.fxml"));
            Parent root = loader.load();
            AfficherCategorieFXMLController afPFXC = loader.getController();
            //afPFXC.afficherCategories();
            
            //Step 1 = Par scene
//            tfNom.getScene().setRoot(root);
            //Step 2 = Par Stage
            Stage st = new Stage();
            st.setTitle("Liste des Categories");
            st.setScene(new Scene(root));
            st.show();
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void Reset(ActionEvent event) {
        tfNomCat.setText("");
        
    }
    
}
