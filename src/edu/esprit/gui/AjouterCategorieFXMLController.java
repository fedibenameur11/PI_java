/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Categorie_prod;
import edu.esprit.services.ServiceCategorie_prod;
import com.google.zxing.BarcodeFormat;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
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
    @FXML
    private TextField br_data;

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
            tfNomCat.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            al.show();
        }else{
            Categorie_prod p = new Categorie_prod(tfNomCat.getText());
            ServiceCategorie_prod sp = new ServiceCategorie_prod();
            
            try {
                sp.insertOne(p);
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
                }
            } catch (WriterException | IOException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Impossible de générer le code-barres");
                alert.setContentText("Une erreur s'est produite lors de la génération du code-barres:\n" + e.getMessage());
                alert.showAndWait();
            }
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
