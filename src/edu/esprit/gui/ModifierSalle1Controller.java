/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.esprit.gui;



import edu.esprit.entities.Salle;
import edu.esprit.services.SalleService;
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

public class ModifierSalle1Controller implements Initializable {
    
    @FXML
    private TextField idf;
    
    @FXML
    private TextField nomf;
    
    @FXML
    private TextField adressef;
    
    @FXML
    private TextField numf;
    
    @FXML
    private TextField codef;
    
    @FXML
    private TextField villef;
    
    @FXML
    private TextField prixf;
    
    private SalleService salleService;
    private final SalleHolder holder =SalleHolder.getInstance();
    private Salle CurrentSalle =holder.getSalle();
    
    @FXML
        void switchButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/AfficherSalle1.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
    @FXML
    

public void modifierSalle() {

    try {
        String nom = nomf.getText();
        String adresse = adressef.getText();
        String numStr = numf.getText();
        String codeStr = codef.getText();
        String ville = villef.getText();
        String prixStr = prixf.getText();

        if (nom.isEmpty() || adresse.isEmpty() || numStr.isEmpty() || codeStr.isEmpty() || ville.isEmpty() || prixStr.isEmpty()) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Erreur");
            al.setContentText("Veuillez remplir tous les champs !");
            al.show();
            return;
        }
        if (nom.length() < 3 || nom.length() > 20) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Erreur de données");
            al.setContentText("Le champ 'nom' doit contenir entre 3 et 20 caractères !");
            al.show();
            return;
        }
        
        if (!numStr.matches("\\d+")) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Erreur de données");
            al.setContentText("Le champ 'num' doit contenir uniquement des chiffres !");
            al.show();
            return;
        }
        
        if (!ville.matches("[a-zA-Z]+")) {
                Alert al = new Alert(Alert.AlertType.WARNING);
                al.setTitle("Erreur de donnée");
                al.setContentText("Le champ 'ville' ne doit contenir que des lettres !");
                al.show();
                return;
        }
        
        if (!prixStr.matches("\\d+(\\.\\d+)?")) {
    Alert al = new Alert(Alert.AlertType.WARNING);
    al.setTitle("Erreur de donnée");
    al.setContentText("Le champ 'prix' doit être un nombre décimal !");
    al.show();
    return;
}

        int num = Integer.parseInt(numStr);
        int code = Integer.parseInt(codeStr);
        int prix = Integer.parseInt(prixStr);

        CurrentSalle.setNom_salle(nom);
        CurrentSalle.setAdresse_salle(adresse);
        CurrentSalle.setNum_telephone(num);
        CurrentSalle.setCodepostal(code);
        CurrentSalle.setVille(ville);
        CurrentSalle.setPrix_abonnement(prix);

        SalleService service = new SalleService();
        service.updateOne(CurrentSalle);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification de salle");
        alert.setHeaderText(null);
        alert.setContentText("La salle a été modifiée avec succès !");
        alert.showAndWait();

    } catch (Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Une erreur est survenue lors de la modification de la salle !");
        alert.showAndWait();
    }
}





    //public void modifierSalle() {
      //  try {
            
           
        //    String nom = nomf.getText();
          //  String adresse = adressef.getText();
          //  int num = Integer.parseInt(numf.getText());
          //  int code = Integer.parseInt(codef.getText());
          //  String ville = villef.getText();
          //  int prix = Integer.parseInt(prixf.getText());
            
            
          //  CurrentSalle.setNom_salle(nom);
          //  CurrentSalle.setAdresse_salle(adresse);
          // CurrentSalle.setNum_telephone(num);
          //  CurrentSalle.setCodepostal(code);
          //  CurrentSalle.setVille(ville);
          //  CurrentSalle.setPrix_abonnement(prix);
          //  SalleService service =new SalleService();
          //  service.updateOne(CurrentSalle, CurrentSalle.getId());
            
          //  Alert alert = new Alert(Alert.AlertType.INFORMATION);
          //  alert.setTitle("Modification de salle");
          //  alert.setHeaderText(null);
          //  alert.setContentText("La salle a été modifié avec succès !");
          //  alert.showAndWait();
            
       // } catch (NumberFormatException e) {
         //   Alert alert = new Alert(Alert.AlertType.ERROR);
          //  alert.setTitle("Erreur");
          //  alert.setHeaderText(null);
          //  alert.setContentText("Veuillez saisir un nombre valide pour l'ID !");
           // alert.showAndWait();
       // } catch (SQLException e) {
         //   Alert alert = new Alert(Alert.AlertType.ERROR);
          //  alert.setTitle("Erreur");
          //  alert.setHeaderText(null);
          //  alert.setContentText("Une erreur est survenue lors de la modification de la salle !");
          //  alert.showAndWait();
       // }
    //}
    
    
public void initData(Salle salle) {
    CurrentSalle = salle;
    holder.setSalle(CurrentSalle);
  
    nomf.setText(CurrentSalle.getNom_salle());
    adressef.setText(CurrentSalle.getAdresse_salle());
    villef.setText(CurrentSalle.getVille());
    codef.setText(String.valueOf(CurrentSalle.getCodepostal()));
    numf.setText(String.valueOf(CurrentSalle.getNum_telephone()));
    prixf.setText(String.valueOf(CurrentSalle.getPrix_abonnement()));
}


        
    
    
    
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     this.initData(CurrentSalle);
        
    }    
}


