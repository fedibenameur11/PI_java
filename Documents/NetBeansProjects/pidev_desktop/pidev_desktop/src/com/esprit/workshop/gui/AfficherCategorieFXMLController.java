/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.workshop.gui;

import com.esprit.workshop.entites.Categorie_prod;
import com.esprit.workshop.entites.Produit;
import com.esprit.workshop.services.ServiceCategorie_prod;
import com.esprit.workshop.services.ServiceProduit;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AfficherCategorieFXMLController implements Initializable {

    @FXML
    private TableView<Categorie_prod> tableViewC;
    @FXML
    private TableColumn<Categorie_prod, String> nomCol;
    private ObservableList<Categorie_prod> Categories;
    @FXML
    TableColumn<Categorie_prod, Void> colSuppression = new TableColumn<>("Supprimer");
    @FXML
    TableColumn<Categorie_prod, Void> colModification;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Categories = FXCollections.observableArrayList();
        afficherCategories();     /*****************/
        //nomCol.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        nomCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        nomCol.setCellFactory(col -> {
        TableCell<Categorie_prod, String> cell = new TableCell<Categorie_prod, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item);
                setAlignment(Pos.CENTER); // Centrer le texte dans la cellule
            }
                };
                return cell;
            });
        colSuppression.setPrefWidth(80);
        colSuppression.setCellFactory(column -> {
                        return new TableCell<Categorie_prod, Void>() {
                            private final Button btnSupprimer = new Button("Supprimer");

                            {
                                btnSupprimer.setOnAction(event -> {
                                    Categorie_prod Cproduit = getTableView().getItems().get(getIndex());
                                    ServiceCategorie_prod serviceCProduit = new ServiceCategorie_prod();
                                    try {
                                        serviceCProduit.deleteOne(Cproduit);
                                    } catch (SQLException ex) {
                                        Logger.getLogger(AfficherProduitFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    // Mettre à jour la table avec les produits restants
                                    //afficherProduits();
                                    tableViewC.getItems().remove(Cproduit); // Supprimer le produit de la TableView
                                    //tableView.refresh(); 
                                });
                                    Image image = new Image("file:///C:/Users/MSI/Documents/NetBeansProjects/pidev_desktop/pidev_desktop/src/com/esprit/workshop/photos/delete.png");

                                    // Création de l'objet ImageView
                                    ImageView imageView = new ImageView(image);

                                    // Redimensionnement de l'image pour qu'elle s'adapte à la boîte de dialogue
                                    imageView.setFitWidth(20);
                                    imageView.setFitHeight(20);
                                //btnSupprimer.setGraphic(new ImageView(new Image("file:///C:/Users/MSI/Documents/NetBeansProjects/pidev_desktop/pidev_desktop/src/com/esprit/workshop/photos/delete.png")));
                                btnSupprimer.setGraphic(imageView);
                                btnSupprimer.getStyleClass().add("icon-only");
                                
                                
                                
                            }
                            @Override
                            protected void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    setGraphic(btnSupprimer);
                                }
                            }
                        };
                    });
        
        
        colModification.setPrefWidth(80);
        colModification.setCellFactory(column -> {
        return new TableCell<Categorie_prod, Void>() {
            private final Button btn = new Button("Modifier");

            {
                btn.setOnAction(event -> {
                    try {
                        Categorie_prod Cproduit = getTableView().getItems().get(getIndex());
                        // Rediriger vers la vue de modification avec les informations du produit sélectionné
                        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("ModifierCategorieFXML.fxml"));
                        Parent root = loader2.load();

                        ModifierCategorieFXMLController controller2 = loader2.getController();
                        controller2.initData(Cproduit);
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.showAndWait();
                        // Actualiser la table des produits après la modification
                        //afficherProduits();
                        //tableViewC.getItems();
                        tableViewC.refresh();
                    } catch (IOException ex) {
                        Logger.getLogger(AfficherCategorieFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                Image image2 = new Image("file:///C:/Users/MSI/Documents/NetBeansProjects/pidev_desktop/pidev_desktop/src/com/esprit/workshop/photos/modifier.png");

                                    // Création de l'objet ImageView
                                    ImageView imageView2 = new ImageView(image2);

                                    // Redimensionnement de l'image pour qu'elle s'adapte à la boîte de dialogue
                                    imageView2.setFitWidth(20);
                                    imageView2.setFitHeight(20);
                                //btnSupprimer.setGraphic(new ImageView(new Image("file:///C:/Users/MSI/Documents/NetBeansProjects/pidev_desktop/pidev_desktop/src/com/esprit/workshop/photos/delete.png")));
                                btn.setGraphic(imageView2);
                                btn.getStyleClass().add("icon-only");
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
                            }
                        };
                    });
        
        tableViewC.setItems(Categories);

    }    

    void afficherCategories() {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connexion=DriverManager.getConnection("jdbc:mysql://localhost:3307/pidev_java?useSSL=false","root","");
            //connexion = MySQLConnexion.getInstance().getConnection();
            String sql = "SELECT * FROM `categorie_prod` ";
            preparedStatement = connexion.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                Categorie_prod p = new Categorie_prod(nom);
                Categories.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connexion != null) {
                try {
                    connexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
        
    }


    
}
