/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.workshop.gui;

import com.esprit.workshop.entites.Person;
import com.esprit.workshop.services.ServicePerson;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author FGH
 */
public class AjouterPersonFXMLController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfAge;
    @FXML
    private Button btnAjouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterPerson(ActionEvent event) {
        if (tfNom.getText().isEmpty() || tfPrenom.getText().isEmpty() || tfAge.getText().isEmpty()) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Erreur de donnee");
            al.setContentText("Veuillez verifier les données !");
            al.show();
        }else{
            Person p = new Person(tfNom.getText(), tfPrenom.getText(), Integer.parseInt(tfAge.getText()));
            ServicePerson sp = new ServicePerson();
            
            try {
                sp.insertOne1(p);
            } catch (SQLException ex) {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Erreur de donnee");
                al.setContentText(ex.getMessage());
                al.show();
            }
            
        }
    }

    @FXML
    private void AfficherPerson(ActionEvent event) {
        
        try {
            
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherPersonsFXML.fxml"));
            Parent root = loader.load();
            AfficherPersonsFXMLController afPFXC = loader.getController();
            List<Person> list = new ServicePerson().selectAll();
            String s = list.toString();
            afPFXC.setLabel(s);
            
            //Step 1 = Par scene
//            tfNom.getScene().setRoot(root);
            //Step 2 = Par Stage
            Stage st = new Stage();
            st.setTitle("");
            st.setScene(new Scene(root));
            st.show();
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }

    @FXML
    private void Reset(ActionEvent event) {
        
    }
    
}
