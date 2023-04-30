/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dynamics.pidev.gui;

import com.dynamics.pidev.entites.Salle;
import com.dynamics.pidev.services.SalleService;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.google.zxing.BarcodeFormat;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AfficherSalle1Controller implements Initializable {

  @FXML
    private TableView<Salle> table;
    
    @FXML
    private TableColumn<Salle, String> nomColumn;
    
    @FXML
    private TableColumn<Salle, String> adresseColumn;
    
    @FXML
    private TableColumn<Salle, Integer> numColumn;
    
    @FXML
    private TableColumn<Salle, Integer> codeColumn;
    
    @FXML
    private TableColumn<Salle, String> villeColumn;
    
    @FXML
    private TableColumn<Salle, Integer> prixColumn;
     @FXML
    private TextField filterField;
       @FXML
    private TextField br_data;

    @FXML
    private Button brcbtn;

    
    
    private ObservableList<Salle> salleList;
    private ObservableList<Salle> dataList;

    
    private SalleService su;
    
    @FXML 
        void switchButton1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/AjouterSalle1.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
        
    @FXML 
        void switchButton2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/ModifierSalle1.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
     @FXML 
        void switchButton3(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/AfficherAbonnement2.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    

  @Override
public void initialize(URL url, ResourceBundle rb) {
    su = new SalleService();
    
    try {
        salleList = FXCollections.observableArrayList(su.selectAll());
    } catch (SQLException ex) {
        Logger.getLogger(AfficherSalle1Controller.class.getName()).log(Level.SEVERE, null, ex);
    }

    nomColumn.setCellValueFactory(new PropertyValueFactory<>("id_livraison"));
    adresseColumn.setCellValueFactory(new PropertyValueFactory<>("nom_livraison"));
    numColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    codeColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));
    //villeColumn.setCellValueFactory(new PropertyValueFactory<>("ville"));
    //prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix_abonnement"));

    table.setItems(salleList);

    // Ajouter un écouteur de changement de texte sur le champ de recherche
    filterField.textProperty().addListener((observable, oldValue, newValue) -> {
        search(); // Appeler la méthode search() lorsque le texte change
    });
    
}
void search() {
    // Récupérer le texte de recherche
    String searchText = filterField.getText();
    
    // Vérifier si le texte de recherche n'est pas vide
    if (searchText != null && !searchText.isEmpty()) {
        dataList = FXCollections.observableArrayList();
        for (Salle salle : salleList) {
            // Vérifier si le nom ou l'adresse de la salle contient le texte de recherche
            if (salle.getNom_livraison().toLowerCase().contains(searchText.toLowerCase()) || salle.getDestination().toLowerCase().contains(searchText.toLowerCase())) {
                // Ajouter la salle à la liste de données filtrée
                dataList.add(salle);
            }
        }
        // Afficher les salles filtrées dans la table
        table.setItems(dataList);
    } else {
        // Si le texte de recherche est vide, afficher toutes les salles dans la table
        table.setItems(salleList);
    }
}

    
    
        @FXML
    void deleteSalle(ActionEvent event) throws SQLException, IOException  {
    su = new SalleService();
    Salle selectedSalle = table.getSelectionModel().getSelectedItem(); 
    if (selectedSalle == null) { // Vérifier si aucune salle n'est sélectionné
        // Afficher un message d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Pas de salle séléctionnée");
        alert.setContentText("S'il vous plait de séléctionner une salle");
        alert.showAndWait();
    } else {
        su.deleteOne(selectedSalle.getId_livraison()); // Supprimer la salle sélectionnée en utilisant la méthode deleteOne() de la classe SalleService
        Parent root = FXMLLoader.load(getClass().getResource("../gui/AfficherSalle1.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
    @FXML
void generateBarcode(ActionEvent event) {
    String data = br_data.getText();
    if (data == null || data.trim().isEmpty()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Aucune donnée saisie");
        alert.setContentText("Veuillez saisir une valeur pour générer le code-barres");
        alert.showAndWait();
        return;
    }
    try {
        // Créer un code-barres Code 128 à partir des données saisies
        Code128Writer writer = new Code128Writer();
        BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.CODE_128, 400, 200);

        // Écrire le code-barres dans un fichier
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer le code-barres");
        fileChooser.setInitialFileName("code-barres.png");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            MatrixToImageWriter.writeToFile(bitMatrix, "PNG", file);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Code-barres généré avec succès");
            alert.setContentText("Le code-barres a été enregistré dans le fichier:\n" + file.getAbsolutePath());
            alert.showAndWait();
            EnvoyerSMS sms = new EnvoyerSMS();
                        String numTel = "+21690623673"; // Numéro de téléphone de livreur à informer
                        String messageContent = "Votre livraison sera faite dans 24h"; // Contenu du SMS à envoyer
                        sms.send(numTel, messageContent);
                        
        }
    } catch (WriterException | IOException e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Impossible de générer le code-barres");
        alert.setContentText("Une erreur s'est produite lors de la génération du code-barres:\n" + e.getMessage());
        alert.showAndWait();
    }
}
 public class EnvoyerSMS {
             // Vos identifiants Twilio
            private static final String ACCOUNT_SID = "ACa8bdf26d92fc217fec844cb67933f574";
            private static final String AUTH_TOKEN = "d55f472b96e17ad9342b27f74e050bc7";

            // Numéro de téléphone Twilio
            private static final String TWILIO_PHONE_NUMBER = "+16074146933";

            public void send(String toPhoneNumber, String message) {
                try {
                    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                    Message twilioMessage = Message.creator(
                            new PhoneNumber(toPhoneNumber),
                            new PhoneNumber(TWILIO_PHONE_NUMBER),
                            message).create();
                    System.out.println("Message envoyé avec succées");
                } catch (Exception ex) {
                    System.out.println("Error sending SMS: " + ex.getMessage());
                }

        }
        }   
}
