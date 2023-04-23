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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
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
import java.util.List;
import java.util.Properties;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javax.mail.*;
import javax.mail.internet.*;

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
    @FXML
    private Button btnRechercher;
    @FXML
    private TextField tfRechercheNom;
    @FXML
    private Button btn_verifier;
    @FXML
    private Label tfNomm;
    @FXML
    private Label LPrixMin;
    @FXML
    private TextField tfPrixMin;
    @FXML
    private Label LPrixMax;
    @FXML
    private TextField tfPrixMax;
    

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
                                    //tableView.getItems().clear();
                                    //afficherProduits();
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
                        String productName = produit.getNom();
                        double productPrice = produit.getPrix();
                        int productQuantite = produit.getQuantite();
                        double productPoids = produit.getPoids();
    
    try {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3307/pidev_java?useSSL=false","root","");
        String sql = "SELECT id FROM produit WHERE nom = ? AND prix = ? AND quantite=? AND poids=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, productName);
        stmt.setDouble(2, productPrice);
        stmt.setInt(3, productQuantite);
        stmt.setDouble(4, productPoids);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            int productId = rs.getInt("id");
            produit.setId(productId);
        }
        
        rs.close();
        stmt.close();
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
                        // Rediriger vers la vue de modification avec les informations du produit sélectionné
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierProduitFXML.fxml"));
                        Parent root = loader.load();

                        ModifierProduitFXMLController controller = loader.getController();
                        controller.initData(produit);
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.initStyle(StageStyle.DECORATED);
                        stage.setScene(scene);
                        stage.showAndWait();
                        // Actualiser la table des produits après la modification
                        tableView.getItems().clear();
                        afficherProduits();
                        //tableView.getItems();
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
    void afficherProduitT(String n,Double m,Double M) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connexion=DriverManager.getConnection("jdbc:mysql://localhost:3307/pidev_java?useSSL=false","root","");
            //connexion = MySQLConnexion.getInstance().getConnection();
            String req = "SELECT * FROM `produit` where nom LIKE ? AND prix >= ? and prix <= ? ";
            
            preparedStatement = connexion.prepareStatement(req);
            preparedStatement.setString(1,"%"+ n+ "%");
            preparedStatement.setDouble(2,m);
            preparedStatement.setDouble(3,M);
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


    @FXML
    private void RechercherNom(ActionEvent event) throws Exception {
            if (tfRechercheNom.getText().isEmpty() || tfPrixMin.getText().isEmpty() || tfPrixMax.getText().isEmpty() ) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Erreur de donnee");
            al.setContentText("Veuillez verifier les données !");
            tfRechercheNom.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            tfPrixMin.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            tfPrixMax.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                    
            al.show();
        }else{
            if (!isFloat(tfPrixMin.getText())) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Erreur de donnee");
            al.setContentText("Le champ du prix ne doit contenir aucun caractere !");
            al.show();
        }
            if (!isFloat(tfPrixMax.getText())) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Erreur de donnee");
            al.setContentText("Le champ du quantite ne doit contenir aucun caractere !");
            al.show();
        }
            
          
            Produit p = new Produit();
            ServiceProduit serviceProduit= new ServiceProduit();
            System.out.println(tfRechercheNom.getText());
            Double prixMin=Double.parseDouble(tfPrixMin.getText());
            Double prixMax=Double.parseDouble(tfPrixMax.getText());
            p.setNom(tfRechercheNom.getText().toLowerCase());
            if (!serviceProduit.controleProduit(p, prixMin, prixMax)){
                tableView.getItems().clear();
            Alert a2 = new Alert(Alert.AlertType.ERROR);
            a2.setTitle("Ce produit n'a pas été trouvé");
            a2.setContentText("Ce produit n'a pas été trouvé");
            a2.show();
            }   
            else 
            {
                tableView.getItems().clear();
                afficherProduitT(p.getNom(),prixMin, prixMax);
         
            }
                
            }}
    public boolean isFloat(String str) {
    try {
        Float.parseFloat(str);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}
    
    private void RechercherNom2() throws Exception {
            Produit p = new Produit();
            ServiceProduit serviceProduit= new ServiceProduit();
            System.out.println(tfRechercheNom.getText());
            p.setNom(tfRechercheNom.getText().toLowerCase());
            if (serviceProduit.ControleNOM2(p)){
            /*Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Ce produit n'a pas été trouvé");
            al.setContentText("Ce produit n'a pas été trouvé");
                
            al.show();*/
            tableView.getItems().clear();
            afficherProduit(p.getNom());
            }   
            else 
            {    
                tableView.getItems().clear();
         
            }
                
            }

    @FXML
    private void Verifier_quantite(ActionEvent event) {
         List<Produit> lowQuantityProducts = new ArrayList<>();
    for (Produit p : produits) {
        if (p.getQuantite()< 10) {
            lowQuantityProducts.add(p);
        }
    }
    
     if (!lowQuantityProducts.isEmpty()) {
        // Envoie un e-mail à l'utilisateur pour l'informer des produits à faible quantité
        //sendLowQuantityProductsEmail(lowQuantityProducts);
        //sendEmail();
        EnvoyerEmail test = new EnvoyerEmail();
        test.envoyer();
        // Mettre à jour la liste des produits pour afficher uniquement les produits à faible quantité
        produits.setAll(lowQuantityProducts);
    }
    }

    public class EnvoyerEmail {
        private String username = "test.symf123@gmail.com";
        private String password = "xmxesjckzxkreflq";
        public void envoyer() {
        // Etape 1 : Création de la session
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port","587");
        Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
        }
        });
        try {
        // Etape 2 : Création de l'objet Message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("test.symf123@gmail.com"));
        message.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse("test.symf123@gmail.com"));
        message.setSubject("Quantite du produit faible");
        message.setText("Bonjour, veuillez charger des produits ...");
        // Etape 3 : Envoyer le message
        Transport.send(message);
        System.out.println("Message_envoye");
        } catch (MessagingException e) {
        throw new RuntimeException(e);
        } }
    

    

    }
    
    
}

