/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.entities;

import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Commande {
    
    private int id;
    private String nom_commande;
    private Date date_commande;

    public Commande() {
    }

    public Commande(int id, String nom_commande, Date date_commande) {
        this.id = id;
        this.nom_commande = new String(nom_commande);
        this.date_commande = new Date(date_commande.getTime());
    }

    public Commande(String nom_commande, Date date_commande) {
        this.nom_commande = new String(nom_commande);
        this.date_commande = new Date(date_commande.getTime());
    }

    public Commande(Date date_commande) {
        this.nom_commande = new String(date_commande.toString());
        this.date_commande = new Date(date_commande.getTime());
    }

    public int getId() {
        return id;
    }

    public String getNom_commande() {
        return nom_commande;
    }

    public Date getDate_commande() {
        return date_commande;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom_commande(String nom_commande) {
        this.nom_commande = nom_commande;
    }

    public void setDate_commande(Date date_commande) {
        this.date_commande = new Date(date_commande.getTime());
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom_commande=" + nom_commande + ", date_commande=" + date_commande +  '}';
    }
}