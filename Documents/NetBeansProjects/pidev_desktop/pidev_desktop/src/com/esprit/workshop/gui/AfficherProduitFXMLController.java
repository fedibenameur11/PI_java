/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.workshop.gui;

import com.esprit.workshop.entites.Categorie_prod;
import com.esprit.workshop.entites.Produit;
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
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public class AfficherProduitFXMLController implements Initializable {

    @FXML
    private TableView<Produit> tableView;
    @FXML
    private TableColumn<Produit, String> nomColumn;
    @FXML
    private TableColumn<Produit, Double> prixColumn;
    @FXML
    private TableColumn<Produit, Integer> quantiteColumn;
    @FXML
    private TableColumn<Produit, Double> poidsColumn;
    private ObservableList<Produit> produits;
    @FXML
    private TableColumn<Produit, Categorie_prod> CategorieColumn;
    @FXML
    TableColumn<Produit, Void> colSuppression = new TableColumn<>("Supprimer");
    @FXML
    private TableColumn<Produit, Void> colModification;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        produits = FXCollections.observableArrayList();
        afficherProduits();
        nomColumn.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        prixColumn.setCellValueFactory(cellData -> cellData.getValue().prixProperty().asObject());  
        quantiteColumn.setCellValueFactory(cellData -> cellData.getValue().quantiteProperty().asObject());
        poidsColumn.setCellValueFactory(cellData -> cellData.getValue().poidsProperty().asObject());
        //CategorieColumn.setCellValueFactory(cellData -> cellData.getValue().catProperty().asString());
        //CategorieColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCat().getNom()));
        CategorieColumn.setCellValueFactory(cellData -> cellData.getValue().catProperty());
        CategorieColumn.setCellFactory(column -> new TableCell<Produit, Categorie_prod>() {
    @Override
    protected void updateItem(Categorie_prod item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setText(null);
        } else {
            //setText(""+item.getId());
            setText(item.getNom());
        }
    }
});
        nomColumn.setCellFactory(col -> {
        TableCell<Produit, String> cell = new TableCell<Produit, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item);
                setAlignment(Pos.CENTER); // Centrer le texte dans la cellule
                    }
                };
                return cell;
            });
        prixColumn.setCellFactory(col -> {
        TableCell<Produit, Double> cell = new TableCell<Produit, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : ""+item);
                setAlignment(Pos.CENTER); // Centrer le texte dans la cellule
                    }
                };
                return cell;
            });
        quantiteColumn.setCellFactory(col -> {
        TableCell<Produit, Integer> cell = new TableCell<Produit, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : ""+item);
                setAlignment(Pos.CENTER); // Centrer le texte dans la cellule
                    }
                };
                return cell;
            });
        poidsColumn.setCellFactory(col -> {
        TableCell<Produit, Double> cell = new TableCell<Produit, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : ""+item);
                setAlignment(Pos.CENTER); // Centrer le texte dans la cellule
                    }
                };
                return cell;
            });
        
        CategorieColumn.setCellFactory(col -> {
        TableCell<Produit, Categorie_prod> cell = new TableCell<Produit, Categorie_prod>() {
            @Override
            protected void updateItem(Categorie_prod item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : ""+item.getNom());
                setAlignment(Pos.CENTER); // Centrer le texte dans la cellule
                    }
                };
                return cell;
            });
        
        colSuppression.setPrefWidth(80);
        colSuppression.setCellFactory(column -> {
                        return new TableCell<Produit, Void>() {
                            private final Button btnSupprimer = new Button("Supprimer");

                            {
                                btnSupprimer.setOnAction(event -> {
                                    Produit produit = getTableView().getItems().get(getIndex());
                                    ServiceProduit serviceProduit = new ServiceProduit();
                                    try {
                                        serviceProduit.deleteOne(produit);
                                    } catch (SQLException ex) {
                                        Logger.getLogger(AfficherProduitFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    // Mettre à jour la table avec les produits restants
                                    //afficherProduits();
                                    tableView.getItems().remove(produit); // Supprimer le produit de la TableView
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
        
        colModification.setCellFactory(column -> {
        return new TableCell<Produit, Void>() {
            private final Button btn = new Button("Modifier");

            {
                btn.setOnAction(event -> {
                    try {
                        Produit produit = getTableView().getItems().get(getIndex());
                        // Rediriger vers la vue de modification avec les informations du produit sélectionné
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierProduitFXML.fxml"));
                        Parent root = loader.load();

                        ModifierProduitFXMLController controller = loader.getController();
                        controller.initData(produit);
                        System.out.println(produit);
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.showAndWait();
                        // Actualiser la table des produits après la modification
                        //afficherProduits();
                        tableView.getItems();
                    } catch (IOException ex) {
                        Logger.getLogger(AfficherProduitFXMLController.class.getName()).log(Level.SEVERE, null, ex);
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
        
        
        
        tableView.setItems(produits);
    }
    void afficherProduits() {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connexion=DriverManager.getConnection("jdbc:mysql://localhost:3307/pidev_java?useSSL=false","root","");
            //connexion = MySQLConnexion.getInstance().getConnection();
            String sql = "SELECT * FROM `produit` ";
            preparedStatement = connexion.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                Float prix = resultSet.getFloat("prix");
                int quantite = resultSet.getInt("quantite");
                float poids = resultSet.getFloat("poids");
                int idCategorie = resultSet.getInt("cat");
                Categorie_prod c = null;

            // Récupération de la catégorie à partir de la base de données
            String sql2 = "SELECT * FROM `categorie_prod` WHERE `id` = ?";
            PreparedStatement preparedStatement2 = connexion.prepareStatement(sql2);
            preparedStatement2.setInt(1, idCategorie);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            if (resultSet2.next()) {
                String nomCategorie = resultSet2.getString("nom");
                c = new Categorie_prod(idCategorie, nomCategorie);
            }
              //Categorie_prod c = new Categorie_prod(catId, "");
                //Categorie_prod c= (Categorie_prod) resultSet.getObject("cat");
                Produit p = new Produit( nom, prix, quantite,poids,c);
                produits.add(p);
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

