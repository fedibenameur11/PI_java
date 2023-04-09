/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.workshop.gui;

import com.esprit.workshop.entites.Categorie_prod;
import com.esprit.workshop.entites.Produit;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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

