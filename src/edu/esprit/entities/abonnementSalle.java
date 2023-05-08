/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.entities;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author MSI
 */
public class abonnementSalle {
    
    private int id;
    private String duree_abonnement;
    private ObjectProperty<Salle> salle;

    public abonnementSalle() {
    }

    public abonnementSalle(int id, String duree_abonnement) {
        this.id = id;
        this.duree_abonnement = duree_abonnement;
    }

    public abonnementSalle(String duree) {
        this.duree_abonnement = duree;
    }

    public abonnementSalle(int id, String duree_abonnement, SimpleObjectProperty<Salle> salle) {
        this.id = id;
        this.duree_abonnement = duree_abonnement;
        this.salle = salle;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDuree_abonnement() {
        return duree_abonnement;
    }

    public void setDuree_abonnement(String duree_abonnement) {
        this.duree_abonnement = duree_abonnement;
    }

    public ObjectProperty<Salle> getSalle() {
        return salle;
    }

    public void setSalle(SimpleObjectProperty<Salle> salle) {
        this.salle = salle;
    }
    
    

    @Override
    public String toString() {
        return "abonnementSalle{" + "id=" + id + ", duree_abonnement=" + duree_abonnement + '}';
    }
    
     public ObjectProperty<Salle> salleProperty() {
        return salle;
    }
    
    
}
