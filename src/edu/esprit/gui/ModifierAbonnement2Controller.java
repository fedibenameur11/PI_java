/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.esprit.gui;



import edu.esprit.entities.abonnementSalle;
import edu.esprit.services.AbonnementService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModifierAbonnement2Controller implements Initializable {
    
    @FXML
    private TextField idField;
    
    @FXML
    private TextField dureeAbonnementField;
    
    private AbonnementService abonnementService;
    private final AbonnementHolder holder =AbonnementHolder.getInstance();
    private abonnementSalle CurrentAbonnement =holder.getAbonnement();
    
    @FXML 
        void switchButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/AfficherAbonnement2.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
    
    
    @FXML
public void modifierAbonnement() {
    try {
        String dureeAbonnement = dureeAbonnementField.getText();
        if (dureeAbonnement.isEmpty()) { // Vérifier si le champ est vide
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir les champs !");
            alert.showAndWait();
            return;
        }
        if (dureeAbonnement.length() > 10) { // Vérifier la longueur de la chaîne de caractères
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La durée de l'abonnement ne doit pas dépasser 10 caractères !");
            alert.showAndWait();
            return;
        }
        CurrentAbonnement.setDuree_abonnement(dureeAbonnement);
        AbonnementService service = new AbonnementService();
        service.updateOne(CurrentAbonnement, CurrentAbonnement.getId());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification d'abonnement");
        alert.setHeaderText(null);
        alert.setContentText("L'abonnement a été modifié avec succès !");
        alert.showAndWait();

    } catch (SQLException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Une erreur est survenue lors de la modification de l'abonnement !");
        alert.showAndWait();
    }
}

    

      //  try {
            //int id = Integer.parseInt(idField.getText());
        //    String dureeAbonnement = dureeAbonnementField.getText();
          //  CurrentAbonnement.setDuree_abonnement(dureeAbonnement);
            //AbonnementService service = new AbonnementService();
           // service.updateOne(CurrentAbonnement, CurrentAbonnement.getId());
            
          //  Alert alert = new Alert(Alert.AlertType.INFORMATION);
          //  alert.setTitle("Modification d'abonnement");
          //  alert.setHeaderText(null);
           // alert.setContentText("L'abonnement a été modifié avec succès !");
           // alert.showAndWait();
            
      //  } catch (NumberFormatException e) {
        //    Alert alert = new Alert(Alert.AlertType.ERROR);
          //  alert.setTitle("Erreur");
   //  alert.setHeaderText(null);
     //       alert.setContentText("Veuillez remplir les champs !");
       //     alert.showAndWait();
      
       // } catch (SQLException e) {
        //    Alert alert = new Alert(Alert.AlertType.ERROR);
          //  alert.setTitle("Erreur");
          //  alert.setHeaderText(null);
          //  alert.setContentText("Une erreur est survenue lors de la modification de l'abonnement !");
          //  alert.showAndWait();
       // }
   // }
    
    public void initData(abonnementSalle abonnement) {
    CurrentAbonnement = abonnement;
    holder.setAbonnement(CurrentAbonnement);
  
    dureeAbonnementField.setText(CurrentAbonnement.getDuree_abonnement());
    
    
}
    
            @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     this.initData(CurrentAbonnement);
        
    } 
    
}

