/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.workshop.entites;

import java.sql.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
/**
 *
 * @author FGH
 */
public class AbonnementCoach {
    
    private IntegerProperty id;
    private ObjectProperty<User> coach_id, client_id ;
    private IntegerProperty duree;
    private ObjectProperty<Date> dateDebut, dateFin;
    private IntegerProperty statut;

    public AbonnementCoach() {
    }

    public AbonnementCoach(int id,User coach_id, User client_id, int duree, Date dateDebut, Date dateFin, int statut) {
        this.id = new SimpleIntegerProperty(id);
        this.coach_id = new SimpleObjectProperty<User>(coach_id);
        this.client_id = new SimpleObjectProperty<User>(client_id);
        this.duree = new SimpleIntegerProperty(duree);
        this.dateDebut = new SimpleObjectProperty<Date>(dateDebut);
        this.dateFin = new SimpleObjectProperty<Date>(dateFin);
        this.statut = new SimpleIntegerProperty(statut);
    }
    
    public AbonnementCoach(User coach_id, User client_id, int duree, Date dateDebut, Date dateFin, int statut) {
        this.coach_id = new SimpleObjectProperty<User>(coach_id);
        this.client_id = new SimpleObjectProperty<User>(client_id);
        this.duree = new SimpleIntegerProperty(duree);
        this.dateDebut = new SimpleObjectProperty<Date>(dateDebut);
        this.dateFin = new SimpleObjectProperty<Date>(dateFin);
        this.statut = new SimpleIntegerProperty(statut);
    }
    
    public IntegerProperty idProperty() {
        return id;
    }
    
    public ObjectProperty<User> coachProperty() {
        return coach_id;
    }
    
    public ObjectProperty<User> clientProperty() {
        return client_id;
    }
    
    public ObjectProperty<Date> datedebutProperty() {
        return dateDebut;
    }
    
    public ObjectProperty<Date> datefinProperty() {
        return dateFin;
    }
    
    public IntegerProperty dureeProperty() {
        return duree;
    }
    
    public IntegerProperty statutProperty() {
        return statut;
    }
    
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public User getCoach_id() {
        return coach_id.get();
    }

    public void setCoach_id(User coach_id) {
        this.coach_id.set(coach_id);
    }

    public User getClient_id() {
        return client_id.get();
    }

    public void setClient_id(User client_id) {
        this.client_id.set(client_id);
    }
    
    public Date getDateDebut() {
        return dateDebut.get();
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut.set(dateDebut);
    }

    public Date getDateFin() {
        return dateFin.get();
    }

    public void setDateFin(Date dateFin) {
        this.dateFin.set(dateFin);
    }

    public int getDuree() {
        return duree.get();
    }

    public void setDuree(int duree) {
        this.duree.set(duree);
    }

    
    public int getStatut() {
        return statut.get();
    }

    public void setStatut(int statut) {
        this.statut.set(statut);
    }

    @Override
    public String toString() {
        return "AbonnementCoach{" + "id=" + id + ", coach_id=" + coach_id + ", client_id=" + client_id + ", duree=" + duree + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", statut=" + statut + '}';
    }

    
    
    
}
