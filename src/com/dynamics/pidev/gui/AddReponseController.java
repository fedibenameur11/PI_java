/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dynamics.pidev.gui;

import com.dynamics.pidev.entites.Question;
import com.dynamics.pidev.entites.Reponse;
import com.dynamics.pidev.services.ServiceQuestion;
import com.dynamics.pidev.services.ServiceReponse;
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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class AddReponseController implements Initializable {

    @FXML
    private TextArea tfReponse;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
           @FXML
    void clickMe(ActionEvent event) {
     System.out.println("on envoyer taped..");
           
        if (tfReponse.getText().isEmpty()) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Erreur de donnee");
            al.setContentText("Veuillez verifier les données !");
            al.show();
        }else{
            Reponse r = new Reponse(tfReponse.getText());
            ServiceReponse sp = new ServiceReponse();
            
            try {
                sp.insertOne1(r);
            } catch (SQLException ex) {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Erreur de donnee");
                al.setContentText(ex.getMessage());
                al.show();
            }
            
      
        }
    }
     @FXML
    private void AfficherReponse(ActionEvent event) {
        
        try {
            
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReponse.fxml"));
            Parent root = loader.load();
            AfficherReponseController afPFXC = loader.getController();
            List<Reponse> list = new ServiceReponse().selectAll();
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
}
