/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dynamics.pidev.entites;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class abonnementSalle {
    
    //var
    private int id;
    private String nom_commande;
    private String date_commande;
    
    
    //constructor

    public abonnementSalle() {
    }

    public abonnementSalle(int id, String nom_commande, String date_commande) {
        this.id = id;
        this.nom_commande = new String(nom_commande);
        this.date_commande = new String(date_commande);
       
    }
    public abonnementSalle( String nom_commande, String date_commande) {
        this.nom_commande = new String(nom_commande);
        this.date_commande = new String(date_commande);
    }

    public abonnementSalle(String duree) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //this.date_commande = new String(duree);
        this.nom_commande = new String(duree);
    }

    


    public int getId() {
        return id;
    }

    public String getNom_commande() {
        return nom_commande;
    }

    public String getDate_commande() {
        return date_commande;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom_commande(String nom_commande) {
        this.nom_commande = nom_commande;
    }

    public void setDate_commande(String date_commande) {
        this.date_commande = date_commande;
    }
    
    


    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom_commande=" + nom_commande + ", date-commande=" + date_commande +  '}';
    }

    
    
    
    
}