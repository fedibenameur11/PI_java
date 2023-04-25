/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.workshop.gui;

import com.esprit.workshop.entites.AbonnementCoach;
import com.esprit.workshop.entites.User;
import com.esprit.workshop.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 * FXML Controller class
 *
 * @author klair
 */
public class InterfaceClientController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private ListView<MyData> lv;
    @FXML
    private TextField tf;
    @FXML
    private Label nomLab;
    @FXML
    private Label prenomlab;
    @FXML
    private Label emaillab;
    @FXML
    private Label tellab;
    User us  ;
    /**
     * Initializes the controller class.
     */
    
       public boolean verif_Num(String num) {
        int i = 0;
        while (i < num.length() && Character.isDigit(num.charAt(i))) {
            i++;
        }
        if (i < num.length()) {
            return false;
        }
        return true;
    }
    
     private boolean test(String x)
    {
         if (x.length() == 0 || !verif_Num(x)) {
            return false;
        }
        return true;
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        List<User> lv2 = new ArrayList<>();
         ServiceUser su = new ServiceUser();
        try {
            lv2.addAll(su.selectAllCoachsList());
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        List<MyData> lmd = new ArrayList<>();
        for (int i = 0; i < lv2.size(); i++)      
            lmd.add(new MyData(lv2.get(i).getId(), lv2.get(i).getNom(), lv2.get(i).getPrenom(),lv2.get(i).getEmail(),Integer.toString(lv2.get(i).getTelephone())));
            
        

        ObservableList<MyData> data = FXCollections.observableArrayList(
                lmd);
        lv.setItems(data);
        lv.setCellFactory(new Callback<ListView<MyData>, ListCell<MyData>>() {
            @Override
            public ListCell<MyData> call(ListView<MyData> listView) {
                return new MyListCell();
            }
        });
        
          lv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MyData>() {
            @Override
            public void changed(ObservableValue<? extends MyData> observable, MyData oldValue, MyData newValue) {
                 us  = su.getByID(lv.getSelectionModel().getSelectedItem().getId());
                 nomLab.setText(us.getNom());
                 prenomlab.setText(us.getPrenom());
                 tellab.setText(Integer.toString(us.getTelephone()));
                 emaillab.setText(us.getEmail());
               
            }
        });

    }    

   
    
    @FXML
    private void envoyerDemande(ActionEvent event) {
        String duree = tf.getText() ; 
        if(us==null)
        {
               Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("veuillez selectionnez un coach !");
            alert.showAndWait();
        }
        else if(!test(duree))
        {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Erreur");
          alert.setHeaderText(null);
          alert.setContentText("duree invalide !");
          alert.showAndWait();
        }
        else
        {
         ServiceUser su = new ServiceUser();
         su.ajouterAbonnement(new AbonnementCoach(su.getByID(us.getId()),su.getByID(1),Integer.parseInt(tf.getText()), null, null, 0));
         Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Ajout réussi !");
            alert.showAndWait();
                 nomLab.setText("");
                 prenomlab.setText("");
                 tellab.setText("");
                 emaillab.setText("");
                 tf.setText("");
                 us=null;
        }
        
    }
    
}
