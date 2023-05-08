/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui.front;

import edu.esprit.entities.Categorie_prod;
import edu.esprit.gui.AfficherCategorieFXMLController;
import edu.esprit.gui.AfficherProduitFXMLController;
import edu.esprit.gui.ModifierCategorieFXMLController;
import edu.esprit.services.ServiceCategorie_prod;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AfficherCategorieFRONTFXMLController implements Initializable {

    @FXML
    private TextField tfRechercheNom;
    @FXML
    private Button btn_recherche;
    @FXML
    private TableColumn <Categorie_prod, String> nomCol;
    @FXML
    private TableColumn <Categorie_prod, Void> ActionCol;
    
    private ObservableList<Categorie_prod> Categories;
    @FXML
    private TableView<Categorie_prod> tableViewC;

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
        
        
        
        ActionCol.setPrefWidth(80);
        ActionCol.setCellFactory(column -> {
        return new TableCell<Categorie_prod, Void>() {
            private final Button btn = new Button("Voir produits");

            {
                btn.setOnAction(event -> {
                    try {
                        Categorie_prod Cproduit = getTableView().getItems().get(getIndex());
                        String CproductNom = Cproduit.getNom();
    
                    try {
                        Connection conn = null;
                        PreparedStatement preparedStatement = null;
                        ResultSet resultSet = null;
                        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev_project?useSSL=false","root","");
                        String sql = "SELECT id FROM categorie WHERE nom = ? ";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, CproductNom);
                        ResultSet rs = stmt.executeQuery();

                        if (rs.next()) {
                            int CproductId = rs.getInt("id");
                            Cproduit.setId(CproductId);
                        }

                        rs.close();
                        stmt.close();
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                        // Rediriger vers la vue de modification avec les informations du produit sélectionné
                        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("AfficherProduitFRONTFXML.fxml"));
                        Parent root = loader2.load();

                        AfficherProduitFRONTFXMLController controller2 = loader2.getController();
                        //controller2.initData(Cproduit);
                        controller2.afficherProduit_CAT(Cproduit);
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
                
                
                
                
                
                
                Image image2 = new Image("file:///C:/Users/wassim/OneDrive/Documents/NetBeansProjects/PI_dev/src/edu/esprit/photos/search.jpg");

                                    // Création de l'objet ImageView
                                    ImageView imageView2 = new ImageView(image2);

                                    // Redimensionnement de l'image pour qu'elle s'adapte à la boîte de dialogue
                                    imageView2.setFitWidth(40);
                                    imageView2.setFitHeight(40);
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
        tfRechercheNom.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                RechercherNom2();
            } catch (Exception ex) {
                Logger.getLogger(AfficherProduitFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            });
        
        
        tableViewC.setItems(Categories);

    }    
    
    
    void afficherCategories() {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev_project?useSSL=false","root","");
            //connexion = MySQLConnexion.getInstance().getConnection();
            String sql = "SELECT * FROM `categorie` ";
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
    
    private void RechercherNom2() throws Exception {
            Categorie_prod p = new Categorie_prod();
            ServiceCategorie_prod serviceCProduit= new ServiceCategorie_prod();
            System.out.println(tfRechercheNom.getText());
            p.setNom(tfRechercheNom.getText().toLowerCase());
            if (serviceCProduit.ControleNOM2(p)){
            /*Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Ce produit n'a pas été trouvé");
            al.setContentText("Ce produit n'a pas été trouvé");
                
            al.show();*/
            tableViewC.getItems().clear();
            afficherCategorie(p.getNom());
            }   
            else 
            {    
         tableViewC.getItems().clear();
            }
                
            }
    
    void afficherCategorie(String n) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev_project?useSSL=false","root","");
            //connexion = MySQLConnexion.getInstance().getConnection();
            String sql = "SELECT * FROM `categorie` where nom LIKE ?";
            
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1,"%"+n+"%");
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

    @FXML
    private void RechercherNom(ActionEvent event) {
    }
    
}
