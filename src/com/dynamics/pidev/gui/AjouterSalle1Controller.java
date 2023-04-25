/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dynamics.pidev.gui;

import Utils.ApiMailer;
import com.dynamics.pidev.entites.Salle;
import com.dynamics.pidev.entites.abonnementSalle;
import com.dynamics.pidev.services.AbonnementService;
import com.dynamics.pidev.services.SalleService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AjouterSalle1Controller implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tfnum;
    @FXML
    private TextField tfcode;
    @FXML
    private TextField tfville;
    @FXML
    private TextField tfprix;
    @FXML
    private Button btnenr;

    @FXML 
        void switchButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/AfficherSalle1.fxml"));
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
        btnenr.setOnAction(event -> {
            try {
                AjouterSalle1(event);
            } catch (MessagingException ex) {
                Logger.getLogger(AjouterSalle1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }  
    
    @FXML
        private void AjouterSalle1(ActionEvent event) throws MessagingException {
    String nom = tfnom.getText();
    String adresse = tfadresse.getText();
    String num = tfnum.getText();
    String code = tfcode.getText();
    String ville = tfville.getText();
    String prix = tfprix.getText();

    if (nom.isEmpty() || adresse.isEmpty() || num.isEmpty() || code.isEmpty() || ville.isEmpty() || prix.isEmpty()) {
        Alert al = new Alert(Alert.AlertType.WARNING);
        al.setTitle("Erreur");
        al.setContentText("Veuillez remplir les champs !");
        al.show();
    } else if (nom.length() < 3 || nom.length() > 20) {
        Alert al = new Alert(Alert.AlertType.WARNING);
        al.setTitle("Erreur de donnée");
        al.setContentText("Le champ 'nom' doit contenir entre 3 et 20 caractères !");
        al.show();
    } else {
        if (!num.matches("\\d+")) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Erreur de donnée");
            al.setContentText("Le champ 'num' doit contenir uniquement des chiffres !");
            al.show();
        } 
        else {
            try {
                Double.parseDouble(prix);
            } catch (NumberFormatException e) {
                Alert al = new Alert(Alert.AlertType.WARNING);
                al.setTitle("Erreur de donnée");
                al.setContentText("Le champ 'prix' doit être un nombre décimal !");
                al.show();
                return;
            }

            if (!ville.matches("[a-zA-Z]+")) {
                Alert al = new Alert(Alert.AlertType.WARNING);
                al.setTitle("Erreur de donnée");
                al.setContentText("Le champ 'ville' ne doit contenir que des lettres !");
                al.show();
            } else {
                Salle p = new Salle(nom, adresse, Integer.parseInt(num), Integer.parseInt(code), ville, Integer.parseInt(prix));
                SalleService sp = new SalleService();

                try {
                    sp.insertOne(p);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout d'une salle");
            alert.setHeaderText(null);
            alert.setContentText("Salle ajoutée avec succés !");
            alert.showAndWait();
            String username = "nawel.selmi@esprit.tn"; 
    String password = "223JFT1062";
    ApiMailer sender = new ApiMailer(username, password);

    String to = "nawel.selmi008@gmail.com";
    String subject = "Découvrir notre nouvelle salle et bienvenue ";
    String text = "securité";
    sender.sendEmail(to, subject, text);
                } catch (SQLException ex) {
                    Alert al = new Alert(Alert.AlertType.ERROR);
                    al.setTitle("Erreur de donnée");
                    al.setContentText(ex.getMessage());
                    al.show();
                }
            }
        }
    }
}
        

}
     
    

