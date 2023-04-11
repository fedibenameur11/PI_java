/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.workshop.gui;

import com.esprit.workshop.entites.AbonnementCoach;
import com.esprit.workshop.entites.Person;
import com.esprit.workshop.entites.User;
import com.esprit.workshop.services.ServiceAbonnementCoach;
import com.esprit.workshop.services.ServicePerson;
import com.esprit.workshop.services.ServiceUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author FGH
 */
public class CRUDAbonnementCoachFXMLController implements Initializable {

    @FXML
    private Button btnAddAbonnCoach;

    @FXML
    private Button btnDeleteAbonnCoach;

    @FXML
    private Button btnUpdateAbonnCoach;

    @FXML
    private ChoiceBox<User> choiceClient;

    @FXML
    private ChoiceBox<User> choiceCoach;

    @FXML
    private TableColumn<AbonnementCoach, User> clientCol;

    @FXML
    private TableColumn<AbonnementCoach, User> coachCol;

    @FXML
    private DatePicker dateDebut;

    @FXML
    private TableColumn<AbonnementCoach, Date> dateDebutCol;

    @FXML
    private DatePicker dateFin;

    @FXML
    private TableColumn<AbonnementCoach, Date> dateFinCol;

    @FXML
    private TableColumn<AbonnementCoach, Integer> dureeCol;

    @FXML
    private TableColumn<AbonnementCoach, Integer > idCol;

    @FXML
    private TableColumn<AbonnementCoach, Integer> statutCol;

    @FXML
    private TableView<AbonnementCoach> tableAbonn;

    @FXML
    private TextField txtDuree;

    @FXML
    private TextField txtStatut;
    
    @FXML
    void AddAbonnCoach(ActionEvent event) {
        if (choiceCoach.getValue()== null || choiceClient.getValue()== null || dateDebut.getValue()== null || dateFin.getValue()== null || txtDuree.getText().isEmpty() || txtStatut.getText().isEmpty()) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Erreur de donnee");
            al.setContentText("Veuillez verifier les données !");
            al.show();
        }else{
            Date dateD = Date.valueOf(dateDebut.getValue());
            Date dateF = Date.valueOf(dateFin.getValue());
            AbonnementCoach p = new AbonnementCoach(choiceCoach.getValue(), choiceClient.getValue(), Integer.parseInt(txtDuree.getText()), dateD,dateF , Integer.parseInt(txtStatut.getText()));
            ServiceAbonnementCoach sp = new ServiceAbonnementCoach();
            
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

    void afficherTable(){
        ServiceAbonnementCoach sp = new ServiceAbonnementCoach();
        try {
            tableAbonn.setItems(sp.selectAll());
            idCol.setCellValueFactory(f -> f.getValue().idProperty().asObject());
            coachCol.setCellValueFactory(f -> f.getValue().coachProperty());
            clientCol.setCellValueFactory(f -> f.getValue().clientProperty());
            dureeCol.setCellValueFactory(f -> f.getValue().dureeProperty().asObject());
            dateDebutCol.setCellValueFactory(f -> f.getValue().datedebutProperty());
            dateFinCol.setCellValueFactory(f -> f.getValue().datefinProperty());
            statutCol.setCellValueFactory(f -> f.getValue().statutProperty().asObject());
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CRUDAbonnementCoachFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        tableAbonn.setRowFactory( tv -> {
            TableRow<AbonnementCoach> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                int myIndex = tableAbonn.getSelectionModel().getSelectedIndex();
                int id = Integer.parseInt(String.valueOf(tableAbonn.getItems().get(myIndex).getId()));
                /*txtName.setText(table.getItems().get(myIndex).getName());
                txtMobile.setText(table.getItems().get(myIndex).getMobile());
                txtCourse.setText(table.getItems().get(myIndex).getCourse());*/          
                }
            });
            return myRow;
        });        
    }
    
    @FXML
    void DeleteAbonnCoach(ActionEvent event) {
        System.out.print("bonjour");
    }

    @FXML
    void UpdateAbonnCoach(ActionEvent event) {
        System.out.print("bonjour");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceUser su = new ServiceUser();
        try {
            choiceCoach.getItems().addAll(su.selectAllCoachs());
        } catch (SQLException ex) {
            Logger.getLogger(CRUDAbonnementCoachFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            choiceClient.getItems().addAll(su.selectAllClients());
        } catch (SQLException ex) {
            Logger.getLogger(CRUDAbonnementCoachFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
