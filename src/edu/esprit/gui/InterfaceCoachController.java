/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.AbonnementCoach;
import edu.esprit.entities.users;
import edu.esprit.services.ServiceAbonnementCoach;
import edu.esprit.services.ServiceUser;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author klair
 */
public class InterfaceCoachController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private ListView<MyData2> lv;
    @FXML
    private Label nomLab;
    @FXML
    private Label prenomlab;
    @FXML
    private Label emaillab;
    @FXML
    private Label tellab;
users us ;
AbonnementCoach ac ; 
    @FXML
    private Label dureelab;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        List<AbonnementCoach> lv2 = new ArrayList<>();
        ServiceAbonnementCoach su = new ServiceAbonnementCoach();
       
         try {
            lv2.addAll(su.selectAbonnementByCoch(31, 0));
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        List<MyData2> lmd = new ArrayList<>();
        for (int i = 0; i < lv2.size(); i++)      
            lmd.add(new MyData2(lv2.get(i).getId(), lv2.get(i).getCoach_id(), lv2.get(i).getClient_id(),lv2.get(i).getDuree()));
            
        

        ObservableList<MyData2> data = FXCollections.observableArrayList(
                lmd);
        lv.setItems(data);
        lv.setCellFactory(new Callback<ListView<MyData2>, ListCell<MyData2>>() {
            @Override
            public ListCell<MyData2> call(ListView<MyData2> listView) {
                return new MyListCell2();
            }
        });
      

          lv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MyData2>() {
            @Override
            public void changed(ObservableValue<? extends MyData2> observable, MyData2 oldValue, MyData2 newValue) {
              
                if(lv.getSelectionModel().getSelectedItem()!=null)
                  {
                us  = lv.getSelectionModel().getSelectedItem().getClient();
                 ac = su.getByID(lv.getSelectionModel().getSelectedItem().getId()) ;                      
                 nomLab.setText(us.getNom());
                 prenomlab.setText(us.getPrenom());
                 tellab.setText(Integer.toString(us.getTelephone()));
                 emaillab.setText(us.getEmail());
                 dureelab.setText(Integer.toString(lv.getSelectionModel().getSelectedItem().getDuree()));
                  }
            }
            
        });    
        }
    

    @FXML
    private void accepterbtn(ActionEvent event) throws SQLException{
            LocalDate date = LocalDate.now();
            System.out.println(ac);
            ac.setDateDebut(Date.valueOf(date));
            ac.setDateFin(Date.valueOf(date.plusMonths(ac.getDuree())));
            ac.setStatut(1);
             List<AbonnementCoach> lv2 = new ArrayList<>();
         ServiceAbonnementCoach su = new ServiceAbonnementCoach();
       su.updateOne(ac);
         try {
            lv2.addAll(su.selectAbonnementByCoch(31, 0));
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        List<MyData2> lmd = new ArrayList<>();
        for (int i = 0; i < lv2.size(); i++)      
            lmd.add(new MyData2(lv2.get(i).getId(), lv2.get(i).getCoach_id(), lv2.get(i).getClient_id(),lv2.get(i).getDuree()));
            
        

        ObservableList<MyData2> data = FXCollections.observableArrayList(
                lmd);
        lv.setItems(data);
        lv.setCellFactory(new Callback<ListView<MyData2>, ListCell<MyData2>>() {
            @Override
            public ListCell<MyData2> call(ListView<MyData2> listView) {
                return new MyListCell2();
            }
        });
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Vous avez accepté l'offre avec succès!");
            alert.showAndWait();
             nomLab.setText("");
                 prenomlab.setText("");
                 tellab.setText("");
                 emaillab.setText("");
                 dureelab.setText("");
     
    }

    @FXML
    private void refuserbtn(ActionEvent event) throws SQLException {
       ServiceAbonnementCoach su = new ServiceAbonnementCoach();
       su.deleteOne(ac.getId()); 
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Vous avez refusé l'offre avec succès!");
            alert.showAndWait();
                  
            List<AbonnementCoach> lv2 = new ArrayList<>();
         try {
            lv2.addAll(su.selectAbonnementByCoch(31, 0));
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        List<MyData2> lmd = new ArrayList<>();
        for (int i = 0; i < lv2.size(); i++)      
            lmd.add(new MyData2(lv2.get(i).getId(), lv2.get(i).getCoach_id(), lv2.get(i).getClient_id(),lv2.get(i).getDuree()));
            
        

        ObservableList<MyData2> data = FXCollections.observableArrayList(
                lmd);
        lv.setItems(data);
        lv.setCellFactory(new Callback<ListView<MyData2>, ListCell<MyData2>>() {
            @Override
            public ListCell<MyData2> call(ListView<MyData2> listView) {
                return new MyListCell2();
            }
        });
             nomLab.setText("");
             prenomlab.setText("");
             tellab.setText("");
             emaillab.setText("");
             dureelab.setText("");
    }
    
}
