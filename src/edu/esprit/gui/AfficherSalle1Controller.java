/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.esprit.gui;

import edu.esprit.util.ExportPdf;
import edu.esprit.entities.Salle;
import edu.esprit.services.SalleService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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
 * @author MSI
 */
public class AfficherSalle1Controller implements Initializable {

  @FXML
    private TableView<Salle> table;
    private SalleHolder holder =SalleHolder.getInstance();
    private final Salle currentsalle=holder.getSalle();
    
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
    
    
    private ObservableList<Salle> salleList=  FXCollections.observableArrayList();
    Salle g = null;
    private SalleService su;
    
    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;
    
    private ObservableList<Salle> sallesList;
   
    
    @FXML 
        void switchButton1(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AjouterSalle1.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Add ");
        stage.setScene(new Scene(parent));
        stage.showAndWait();
    }
        
    @FXML 
        void switchButton2(ActionEvent event) throws IOException {
           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModifierSalle1.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Add ");
        stage.setScene(new Scene(parent));
        stage.showAndWait();
    }
     

     @FXML 
        void switchButton3(ActionEvent event) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AfficherAbonnement2.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Add ");
        stage.setScene(new Scene(parent));
        stage.showAndWait();
    }   


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        su = new SalleService();
        
      try {
          salleList = FXCollections.observableArrayList(su.selectAll());
      } catch (SQLException ex) {
          Logger.getLogger(AfficherSalle1Controller.class.getName()).log(Level.SEVERE, null, ex);
      }
        
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom_salle"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse_salle"));
        numColumn.setCellValueFactory(new PropertyValueFactory<>("num_telephone"));
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("codepostal"));
        villeColumn.setCellValueFactory(new PropertyValueFactory<>("ville"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix_abonnement"));
        
        table.setItems(salleList);
        try {
          rehercherSalle();
      } catch (SQLException ex) {
          Logger.getLogger(AfficherSalle1Controller.class.getName()).log(Level.SEVERE, null, ex);
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
        su.deleteOne(selectedSalle);
        System.out.println(selectedSalle.getId());
        // Supprimer la salle sélectionnée en utilisant la méthode deleteOne() de la classe SalleService
        
        
        
        
        
        
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Salle supprimée avec succés !");
            alert.showAndWait();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AfficherSalle1.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Add ");
        stage.setScene(new Scene(parent));
        stage.showAndWait();
    }
}


        @FXML
    void modifierSalle(ActionEvent event) throws SQLException, IOException  {
         Salle selectedSalle = table.getSelectionModel().getSelectedItem();
    if (selectedSalle != null) {
        holder.setSalle(selectedSalle);
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModifierSalle1.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Add ");
        stage.setScene(new Scene(parent));
        stage.showAndWait();

            //loadUsers();

    } else {
       Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Pas de salle séléctionnée");
        alert.setContentText("S'il vous plait de séléctionner une salle");
        alert.showAndWait();
   }
    
   }
    
    /****************Recherche Dynamique *****************/
    
    @FXML
    private void rehercherSalle() throws SQLException{
    SalleService service = new SalleService();
    ObservableList<Salle> sallesList = FXCollections.observableArrayList();
    sallesList.addAll(service.selectAll1());
        
        FilteredList<Salle> filteredData = new FilteredList<>(sallesList, b -> true);
      
	searchField.textProperty().addListener((observable, oldValue, newValue) -> {
		filteredData.setPredicate(g -> {
				if (newValue == null || newValue.isEmpty()) {
				return true;
			}
			String lowerCaseFilter = newValue.toLowerCase();

				if (g.getNom_salle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
                                        
				} else if (g.getAdresse_salle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				        return true;
                                        
                                } else if (Integer.toString(g.getNum_telephone()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                                  return true;
                                } else if (Integer.toString(g.getCodepostal()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                                  return true;
                                
				}
                                 else if (g.getVille().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
                                
				}else if (Integer.toString(g.getPrix_abonnement()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                                  return true;
                                
                                } 
				else
					return false;
			
                        });
		});
		    SortedList<Salle> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		table.setItems(sortedData);
    
        
  }

//@FXML
//private void searchSalles() throws SQLException {
  //  String searchTerm = searchField.getText().trim();
    
   // if (searchTerm.isEmpty()) {
     //   Alert alert = new Alert(AlertType.INFORMATION);
       // alert.setTitle("Information");
       // alert.setHeaderText(null);
       // alert.setContentText("Veuillez saisir un terme de recherche.");
       // alert.showAndWait();
       // return;
   // }
    
    //SalleService service = new SalleService();
    //ArrayList<Salle> salles = service.searchByName(searchTerm);

    //if (salles.isEmpty()) {
      //  Alert alert = new Alert(AlertType.INFORMATION);
       // alert.setTitle("Information");
       // alert.setHeaderText(null);
       // alert.setContentText("Aucune salle trouvée avec le terme '" + searchTerm + "'.");
       // alert.showAndWait();
       // return;
   // }
    
   // ObservableList<Salle> sallesList = FXCollections.observableArrayList(salles);
   // table.setItems(sallesList);
//}

    //@FXML
    //private void rehercherSalle() throws SQLException{
    //SalleService service = new SalleService();
    //ObservableList<Salle> sallesList = FXCollections.observableArrayList();
    //sallesList.addAll(service.selectAll1());
        
      //  FilteredList<Salle> filteredData = new FilteredList<>(sallesList, b -> true);
//	searchField.textProperty().addListener((observable, oldValue, newValue) -> {
//		filteredData.setPredicate(g -> {
//				if (newValue == null || newValue.isEmpty()) {
//				return true;
//			}
//			String lowerCaseFilter = newValue.toLowerCase();

//				if (g.getNom_salle().toLowerCase().indexOf(lowerCaseFilter) > -1) {
//					return true;
                                        
//				} else if (g.getAdresse_salle().toLowerCase().indexOf(lowerCaseFilter) > -1) {
//				        return true;
                                        
  //                              } else if (Integer.toString(g.getNum_telephone()).toLowerCase().indexOf(lowerCaseFilter) > -1) {
    //                              return true;
     //                           } else if (Integer.toString(g.getCodepostal()).toLowerCase().indexOf(lowerCaseFilter) > -1) {
       //                           return true;
                                
	//			}
          //                       else if (g.getVille().toLowerCase().indexOf(lowerCaseFilter) > -1) {
	//				return true;
                                
	//			}else if (Integer.toString(g.getPrix_abonnement()).toLowerCase().indexOf(lowerCaseFilter) > -1) {
          //                        return true;
                                
            //                    } 
		//		else
		//			return false;
			
                  //      });
	//	});
	//	SortedList<Salle> sortedData = new SortedList<>(filteredData);
	//	sortedData.comparatorProperty().bind(table.comparatorProperty());
	//	table.setItems(sortedData);
    
        
 // }

@FXML
private void sortSalle(ActionEvent event) {
    salleList.sort(Comparator.comparing(Salle::getNom_salle));
}

@FXML
    private void ExportButton(ActionEvent event) throws SQLException, IOException {
    Salle selectedSalle = table.getSelectionModel().getSelectedItem();
    ExportPdf myClass = new ExportPdf(); // replace MyClass with the name of your class
    if (selectedSalle != null) {
        myClass.exportPDF(event,selectedSalle);
    } else {
         Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Pas de salle séléctionnée");
        alert.setContentText("S'il vous plait de séléctionner une salle");
        alert.showAndWait();
    }
    }
}
