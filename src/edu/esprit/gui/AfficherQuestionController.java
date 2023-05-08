/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.esprit.gui;

import edu.esprit.entities.Question;
import edu.esprit.services.ServiceQuestion;
import edu.esprit.services.ServiceReponse;
import edu.esprit.util.TableExporter;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class AfficherQuestionController implements Initializable {

    @FXML
    private TableView<Question> tablequestion;
    @FXML
    private TableColumn<Question, String> contenuColumn;
    @FXML
    private TableColumn<Question, String> datedecreationColumn;
    @FXML
    private TableColumn<Question, Integer> likes;
    @FXML
    private TableColumn<Question, Integer> user;
    @FXML
    private TextField searchField;

    private ObservableList<Question> questionList;
    private ServiceQuestion quest;
    private ServiceReponse repon;
    
    private QuestHolder holder = QuestHolder.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        quest = new ServiceQuestion();

        try {
            questionList = FXCollections.observableArrayList(quest.selectAll());
        } catch (SQLException ex) {
            Logger.getLogger(AfficherQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        datedecreationColumn.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        contenuColumn.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        user.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        likes.setCellValueFactory(new PropertyValueFactory<>("likes"));

        tablequestion.setItems(questionList);
        try {
            dynamicSearch();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void dynamicSearch() throws SQLException {
        quest = new ServiceQuestion();
        ObservableList<Question> questionsList = FXCollections.observableArrayList();
        questionsList.addAll(quest.selectAll());

        FilteredList<Question> filteredData = new FilteredList<>(questionsList, b -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(g -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (g.getCreated_at().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

                } else if (g.getContenu().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

                } else {
                    return false;
                }

            });
        });
        SortedList<Question> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tablequestion.comparatorProperty());
        tablequestion.setItems(sortedData);

    }

    /************** Supprimer Question *******************/
    @FXML
    void deleteQuestion(ActionEvent event) throws SQLException, IOException {
        quest = new ServiceQuestion();
        repon = new ServiceReponse();
        Question selectedQuestion = tablequestion.getSelectionModel().getSelectedItem();
        if (selectedQuestion == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Pas de Question séléctionnée");
            alert.setContentText("S'il vous plait de séléctionner une Question");
            alert.showAndWait();
        } else {
            repon.deleteByQuestionId(selectedQuestion.getId());
            quest.deleteOne(selectedQuestion.getId());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Question supprimée avec succés !");
          
        }
    }

    /************** Modifier Question *******************/
    @FXML
    void modifierQuestion(ActionEvent event) throws SQLException, IOException {
        Question selectedQuestion = tablequestion.getSelectionModel().getSelectedItem();
        if (selectedQuestion != null) {
            holder.setQuest(selectedQuestion);
            
              FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModifierQuestion.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Add ");
        stage.setScene(new Scene(parent));
        stage.showAndWait();

            //loadUsers();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Pas de Question séléctionnée");
            alert.setContentText("S'il vous plait de séléctionner une question");
            alert.showAndWait();
        }

    }

    @FXML
    void statButton(ActionEvent event) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StatsQuestions.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Add ");
        stage.setScene(new Scene(parent));
        stage.showAndWait();
    }

    
    @FXML
    void exportExcel(ActionEvent event) throws IOException {

        String filePath = "C:\\Users\\wassim\\OneDrive\\Documents\\NetBeansProjects\\PI_dev\\src\\edu\\esprit\\Questions.xlsx";
        TableExporter.exportToExcel(tablequestion, filePath);
        Desktop.getDesktop().open(new File("C:\\Users\\wassim\\OneDrive\\Documents\\NetBeansProjects\\PI_dev\\src\\edu\\esprit\\Questions.xlsx"));
    }

}
