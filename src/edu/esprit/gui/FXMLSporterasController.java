/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.login;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class FXMLSporterasController implements Initializable {

    @FXML
    private Button home;
    @FXML
    private BorderPane bp;
    private int id;
    @FXML
    private Button produit;
    @FXML
    private Button commande;
    @FXML
    private Button categories;
    @FXML
    private Button abonnement;
    @FXML
    private Button livraison;
    @FXML
    private Button salle;
    @FXML
    private Button gproduit;
    @FXML
    private Button gcategorie;
    @FXML
    private Button gsalle;
    @FXML
    private Button account;
    @FXML
    private Button gabonnement;
     @FXML
    private Button gcoach;
      @FXML
    private Button client;
    @FXML
    private Button coach;
    @FXML
    private Button utilisateur;
    @FXML
    private Button forum;
     @FXML
    private Button gforum;
    @FXML
    private Button logout;
   
    private login Log_in = login.getInstance();
 
    @FXML
    private Label username;
    private HBox centrepage;
    @FXML
    private ImageView panier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        username.setText(Log_in.getPrenom()+" "+Log_in.getNom());
        
        int role = Log_in.getRole();
        if(role==3){
            categories.setVisible(false);
            abonnement.setVisible(false);
            produit.setVisible(false);
            salle.setVisible(false);
            client.setVisible(false);
            forum.setVisible(false);
            
            
        }else if(role==1|| role==10 || role==2){
            gcategorie.setVisible(false);
            gcoach.setVisible(false);
            coach.setVisible(false);
            gabonnement.setVisible(false);
            utilisateur.setVisible(false); 
            gsalle.setVisible(false);
            gproduit.setVisible(false);
            livraison.setVisible(false);
            gforum.setVisible(false);
        } 
         
            
            
     
         
        
        
    }

    private void loadPage(String page) {
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLSporterasController.class.getName()).log(Level.SEVERE, null, ex);
        }
       bp.setCenter(root);
    }

    @FXML
    private void Produit(ActionEvent event) {
        loadPage("AfficherProduitFRONTFXML");
    }

    @FXML
    private void Categories(ActionEvent event) {
        loadPage("AfficherCategorieFRONTFXML");
    }

    @FXML
    private void Commande(ActionEvent event) {
        loadPage("AfficherCommandes2");
    }

    @FXML
    private void Abonnement (ActionEvent event) {
        loadPage("AjouterAbonnement");
    }

    @FXML
    private void Livraison(ActionEvent event) {
        loadPage("AfficherLivraison1");
    }

    @FXML
    private void Salle(ActionEvent event) {
        loadPage("AfficherSalle");
    }

    @FXML
    private void Gproduit(ActionEvent event) {
        loadPage("AfficherProduitFXML");
    }

    @FXML
    private void Gcategorie(ActionEvent event) {
        loadPage("AfficherCategorieFXML");
    }

    @FXML
    private void Gsalle(ActionEvent event) {
        loadPage("AfficherSalle1");
    }

    @FXML
    private void myAccount(ActionEvent event) {
        loadPage("Modifierutilisateurconnecte");
    }

    @FXML
    private void home(ActionEvent event) {
    }

    @FXML
    private void Gabonnement(ActionEvent event) {
        loadPage("AfficherAbonnement2");
    }

    @FXML
    private void Gcoach(ActionEvent event) {
        loadPage("CRUDAbonnementCoachFXML");
    }
     @FXML
    private void Client(ActionEvent event) {
        loadPage("InterfaceClient");

    }
     @FXML
    private void Coach(ActionEvent event) {
        loadPage("InterfaceCoach");

    }
       @FXML
    private void Forum(ActionEvent event) {
        loadPage("ForumQuestions");

    }
      @FXML
     private void Gforum(ActionEvent event) {
        loadPage("AfficherQuestion");
    }

    @FXML
    private void utilisateurList(ActionEvent event) {
        loadPage("UserInterface");

    }

   @FXML
    private void Logout(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene homaepageScene = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(homaepageScene);
            appStage.show();
        } catch (IOException ex) {
            Logger.getLogger(UserInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

}
