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
import java.io.IOException;
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
import javafx.scene.control.ComboBox;
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
    private Button exportpdf;

    @FXML
    private ComboBox<users> choiceClient;

    @FXML
    private ComboBox<users> choiceCoach;

    @FXML
    private TableColumn<AbonnementCoach, users> clientCol;

    @FXML
    private TableColumn<AbonnementCoach, users> coachCol;

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
    
    int myIndex;
    int id;
    
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
            SMS sms = new SMS();
            
            try {
                sp.insertOne1(p);
                afficherTable();
                sms.send(Integer.toString(p.getClient_id().getTelephone()),
                        "Bonjour monsieur "+p.getClient_id().getNom()+", vous avez ete affecter a un abonnement coach de "+p.getDuree()+" au coach sportif "+p.getCoach_id().getNom());
                sms.send(Integer.toString(p.getCoach_id().getTelephone()),
                        "Bonjour monsieur "+p.getCoach_id().getNom()+", vous avez ete affecter a un abonnement coach de "+p.getDuree()+" au client "+p.getClient_id().getNom());
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
            ObservableList<AbonnementCoach> abonnements = sp.selectAll();
            tableAbonn.setItems(abonnements);
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
                myIndex = tableAbonn.getSelectionModel().getSelectedIndex();
                id = Integer.parseInt(String.valueOf(tableAbonn.getItems().get(myIndex).getId()));                    
                choiceClient.setValue(tableAbonn.getItems().get(myIndex).getClient_id());
                choiceCoach.setValue(tableAbonn.getItems().get(myIndex).getCoach_id());
                if (tableAbonn.getItems().get(myIndex).getDateDebut() != null || tableAbonn.getItems().get(myIndex).getDateFin() != null){
                    dateDebut.setValue(tableAbonn.getItems().get(myIndex).getDateDebut().toLocalDate());
                    dateFin.setValue(tableAbonn.getItems().get(myIndex).getDateFin().toLocalDate());
                }
                else{
                    dateDebut.setValue(null);
                    dateFin.setValue(null);
                }
                txtDuree.setText(String.valueOf(tableAbonn.getItems().get(myIndex).getDuree()));
                txtStatut.setText(String.valueOf(tableAbonn.getItems().get(myIndex).getStatut()));
                }
            });
            return myRow;
        });        
    }
    
    @FXML
    void UpdateAbonnCoach(ActionEvent event) {
        myIndex = tableAbonn.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(tableAbonn.getItems().get(myIndex).getId()));
        if ( myIndex == -1){
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Erreur de selection");
            al.setContentText("Veuillez sélectionner la ligne a mettre a jour !");
            al.show();
        }else if (choiceCoach.getValue()== null || choiceClient.getValue()== null || dateDebut.getValue()== null || dateFin.getValue()== null || txtDuree.getText().isEmpty() || txtStatut.getText().isEmpty()) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Erreur de donnee");
            al.setContentText("Veuillez verifier les données !");
            al.show();
        }else{
            Date dateD = Date.valueOf(dateDebut.getValue());
            Date dateF = Date.valueOf(dateFin.getValue());
            AbonnementCoach p = new AbonnementCoach(id,choiceCoach.getValue(), choiceClient.getValue(), Integer.parseInt(txtDuree.getText()), dateD,dateF , Integer.parseInt(txtStatut.getText()));
            ServiceAbonnementCoach sp = new ServiceAbonnementCoach();
            
            try {
                sp.updateOne(p);
                afficherTable();
            } catch (SQLException ex) {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Erreur de donnee");
                al.setContentText(ex.getMessage());
                al.show();
            }
        }
    }
    
    @FXML
    void DeleteAbonnCoach(ActionEvent event) {
        myIndex = tableAbonn.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(tableAbonn.getItems().get(myIndex).getId()));
        if ( myIndex == -1){
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Erreur de selection");
            al.setContentText("Veuillez sélectionner la ligne a mettre a jour !");
            al.show();
        }else{
            ServiceAbonnementCoach sp = new ServiceAbonnementCoach();
            try {
                sp.deleteOne(id);
                afficherTable();
            } catch (SQLException ex) {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Erreur de donnee");
                al.setContentText(ex.getMessage());
                al.show();
            }
        }
    }

    @FXML
    private void ExportButton(ActionEvent event) throws SQLException, IOException {
    AbonnementCoach selectedabonn = tableAbonn.getSelectionModel().getSelectedItem();
    ExportPdf myClass = new ExportPdf(); // replace MyClass with the name of your class
    if (selectedabonn != null) {
        myClass.exportPDF(event,selectedabonn);
    }else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Pas d'abonnement séléctionnée");
        alert.setContentText("S'il vous plait de séléctionner un abonnement");
        alert.showAndWait();
    }
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
        afficherTable();
    }    
    
}
