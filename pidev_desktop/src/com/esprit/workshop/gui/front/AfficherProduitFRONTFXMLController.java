/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.workshop.gui.front;

import com.esprit.workshop.entites.Categorie_prod;
import com.esprit.workshop.entites.Produit;
import com.esprit.workshop.gui.AfficherProduitFXMLController;
import com.esprit.workshop.gui.ModifierProduitFXMLController;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AfficherProduitFRONTFXMLController implements Initializable {

    @FXML
    private Button btnRechercher;
    @FXML
    private TextField tfRechercheNom;
    
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
    private TableColumn<Produit, Void> actionColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        produits = FXCollections.observableArrayList();
        //afficherProduits();
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
        
        actionColumn.setPrefWidth(80);        
        actionColumn.setCellFactory(column -> {
        return new TableCell<Produit, Void>() {
            private final Button btn = new Button("Acheter produit");

            {
                btn.setOnAction(event -> {
                    //try {
                        Alert a =new Alert(Alert.AlertType.CONFIRMATION);
                        a.setTitle("Confiramtion d'achat");
                        a.setContentText("Êtes-vous sûr d'ajouter ce produit au panier ?");                   
                        a.show();                   
                        // Actualiser la table des produits après la modification
                        tableView.getItems();

                });
                Image image2 = new Image("file:///C:/Users/MSI/Documents/NetBeansProjects/pidev_desktop/pidev_desktop/src/com/esprit/workshop/photos/acheter.JPG");

                                    // Création de l'objet ImageView
                ImageView imageView2 = new ImageView(image2);

                                    // Redimensionnement de l'image pour qu'elle s'adapte à la boîte de dialogue
                imageView2.setFitWidth(40);
                imageView2.setFitHeight(40);
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
        
        
        tfRechercheNom.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                RechercherNom2();
            } catch (Exception ex) {
                Logger.getLogger(AfficherProduitFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    
    void afficherProduit(String n) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connexion=DriverManager.getConnection("jdbc:mysql://localhost:3307/pidev_java?useSSL=false","root","");
            //connexion = MySQLConnexion.getInstance().getConnection();
            String req = "SELECT * FROM `produit` where nom LIKE ? ";
            
            preparedStatement = connexion.prepareStatement(req);
            preparedStatement.setString(1,"%"+ n+ "%");
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
        void afficherProduit_CAT(Categorie_prod cc) {
            Connection connexion = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try {
                connexion=DriverManager.getConnection("jdbc:mysql://localhost:3307/pidev_java?useSSL=false","root","");
                String req = "SELECT * FROM `produit` where cat = ? ";

                preparedStatement = connexion.prepareStatement(req);
                preparedStatement.setInt(1,cc.getId());
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

    @FXML
    private void RechercherNom(ActionEvent event) throws Exception {
            Produit p = new Produit();
            ServiceProduit serviceProduit= new ServiceProduit();
                    System.out.println(tfRechercheNom.getText());
            p.setNom(tfRechercheNom.getText().toLowerCase());
            if (!serviceProduit.ControleNOM(p)){
                tableView.getItems().clear();
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Ce produit n'a pas été trouvé");
            al.setContentText("Ce produit n'a pas été trouvé");
            al.show();
            }   
            else 
            {
                tableView.getItems().clear();
                afficherProduit(p.getNom());
         
            }
                
            }
    
    private void RechercherNom2() throws Exception {
            Produit p = new Produit();
            ServiceProduit serviceProduit= new ServiceProduit();
            System.out.println(tfRechercheNom.getText());
            p.setNom(tfRechercheNom.getText().toLowerCase());
            if (serviceProduit.ControleNOM2(p)){
            tableView.getItems().clear();
            afficherProduit(p.getNom());
            }   
            else 
            {    
                tableView.getItems().clear();
         
            }
                
            }

    
}
