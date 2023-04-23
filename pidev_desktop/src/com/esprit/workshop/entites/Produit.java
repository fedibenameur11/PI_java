/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.workshop.entites;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author MSI
 */
public class Produit {
    
    //var
    private int id;
    private StringProperty nom;
    private DoubleProperty prix;
    private IntegerProperty quantite;
    private DoubleProperty poids;
    private SimpleObjectProperty<Categorie_prod> cat;
    
    //constructor

    public Produit() {
    }

    public Produit(int id, String nom, double prix, int quantite, double poids) {
        this.id = id;
        this.nom = new SimpleStringProperty(nom);
        this.prix = new SimpleDoubleProperty(prix);
        this.quantite = new SimpleIntegerProperty(quantite);
        this.poids = new SimpleDoubleProperty(poids);
    }
    public Produit( String nom, double prix, int quantite, double poids) {
        this.nom = new SimpleStringProperty(nom);
        this.prix = new SimpleDoubleProperty(prix);
        this.quantite = new SimpleIntegerProperty(quantite);
        this.poids = new SimpleDoubleProperty(poids);
    }
    public Produit( String nom, double prix, int quantite, double poids,Categorie_prod cat) {
        this.nom = new SimpleStringProperty(nom);
        this.prix = new SimpleDoubleProperty(prix);
        this.quantite = new SimpleIntegerProperty(quantite);
        this.poids = new SimpleDoubleProperty(poids);
        this.cat = new SimpleObjectProperty<>(cat);
    }
    public void setId(int id) {
        this.id=id;
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public DoubleProperty prixProperty() {
        return prix;
    }

    public IntegerProperty quantiteProperty() {
        return quantite;
    }

    public DoubleProperty poidsProperty() {
        return poids;
}

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom.get();
    }

    public double getPrix() {
        return prix.get();
    }

    public int getQuantite() {
        return quantite.get();
    }

    public double getPoids() {
        return poids.get();
    }

    public void setNom(String nom) {
        this.nom=new SimpleStringProperty(nom);
    }


    public void setPrix(double prix) {
        this.prix.set(prix);
    }

    public void setQuantite(int quantite) {
        this.quantite.set(quantite);
    }

    public void setPoids(double poids) {
        this.poids.set(poids);
    }
    public void setCat(Categorie_prod cat ) {
        this.cat.set(cat);
    }
    public Categorie_prod getCat()
    {
        return cat.get();
    }


    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ", prix=" + prix + ", quantite=" + quantite + ", poids=" + poids + '}';
    }

    public SimpleObjectProperty<Categorie_prod> catProperty() {
        return cat;
    }
    
    
    
    
}