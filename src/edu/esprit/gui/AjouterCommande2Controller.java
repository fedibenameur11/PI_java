/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.esprit.gui;

import edu.esprit.entities.Commande;
import edu.esprit.services.CommandeService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
public class AjouterCommande2Controller implements Initializable {

    @FXML
    private TextField tfdur;
    @FXML
    private Button btn;
    @FXML
    private TextField tfdate;

    @FXML 
    void switchButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/AfficherCommandes2.fxml"));
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
        btn.setOnAction(event -> {
            AjouterCommande(event);
        });
    }    
    
    @FXML
    private void AjouterCommande(ActionEvent event) {
        String duree = tfdur.getText();
        String dateString = tfdate.getText();
        
       if (duree.isEmpty()) {
    Alert al = new Alert(Alert.AlertType.WARNING);
    al.setTitle("Erreur");
    al.setContentText("Veuillez remplir le champ !");
    al.show();
} else if (duree.length() > 10) {
    Alert al = new Alert(Alert.AlertType.WARNING);
    al.setTitle("Erreur de donnée");
    al.setContentText("La durée ne doit pas dépasser 10 caractères !");
    al.show();
} else if (dateString.isEmpty()) {
    Alert al = new Alert(Alert.AlertType.WARNING);
    al.setTitle("Erreur");
    al.setContentText("Veuillez remplir le champ date !");
    al.show();
} else {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    try {
        formatter.setLenient(false); // ajout de cette ligne pour forcer la validation stricte de la date
        Date date = formatter.parse(dateString);
        Commande commande = new Commande(duree, date);
        CommandeService commandeService = new CommandeService();
        commandeService.insertOne1(commande);
    } catch (ParseException | SQLException ex) {
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Erreur de donnée");
        al.setContentText("La date entrée n'est pas valide !");
        al.show();
    }
}
    }
}
