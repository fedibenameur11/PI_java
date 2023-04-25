/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dynamics.pidev.entites;




import javafx.collections.ObservableList;

public class Salle {
    
    private int id_livraison;
    private String nom_livraison;
    private String date;
    private String destination;

    public Salle(int id_livraison, String nom_livraison, String date, String destination) {
        this.id_livraison = id_livraison;
        this.nom_livraison = nom_livraison;
        this.date = date;
        this.destination = destination;
    }

    public Salle(int id_livraison, String nom_livraison) {
        this.id_livraison = id_livraison;
        this.nom_livraison = nom_livraison;
    }

    public Salle(String nom_livraison) {
        this.nom_livraison = nom_livraison;
    }

    public Salle() {
        
    }

    public Salle(String nom, String adresse, int parseInt, int parseInt0, String ville, int parseInt1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getId_livraison() {
        return id_livraison;
    }

    public String getNom_livraison() {
        return nom_livraison;
    }

    public String getDate() {
        return date;
    }

    public String getDestination() {
        return destination;
    }

    public void setId_livraison(int id_livraison) {
        this.id_livraison = id_livraison;
    }

    public void setNom_livraison(String nom_livraison) {
        this.nom_livraison = nom_livraison;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    // La méthode suivante est inutile et peut être supprimée
    //static void setItems(ObservableList<Categorie_prod> list) {
    //    throw new UnsupportedOperationException("Not supported yet."); 
    //}
}