/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.workshop.entites;

/**
 *
 * @author FGH
 */
public class User {
    
    private int id;
    private int categorie_user_id, telephone;
    private String nom, prenom, email;

    public User() {
    }

    public User(int id, int categorie_user_id, int telephone, String nom, String prenom, String email) {
        this.id = id;
        this.categorie_user_id = categorie_user_id;
        this.telephone = telephone;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategorie_user_id() {
        return categorie_user_id;
    }

    public void setCategorie_user_id(int categorie_user_id) {
        this.categorie_user_id = categorie_user_id;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", categorie_user_id=" + categorie_user_id + ", telephone=" + telephone + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + '}';
    }
    
}
