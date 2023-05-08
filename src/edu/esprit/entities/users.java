/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author wassim
 */
public class users {
    private int id , categorie_user_id;
    private String nom,prenom,email,password,adresse;
    private int telephone,code_postale;

    public users() {
    }

    public users(String nom, String prenom, String email, String password, String adresse, int telephone, int code_postale) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.adresse = adresse;
        this.telephone = telephone;
        this.code_postale = code_postale;
    }
      public users(String nom, String prenom, String email, String password, String adresse, int telephone, int code_postale, int categorie_user_id) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.adresse = adresse;
        this.telephone = telephone;
        this.code_postale = code_postale;
    }

    public users(int id, String nom, String prenom, String email, String password, String adresse, int telephone, int code_postale) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.adresse = adresse;
        this.telephone = telephone;
        this.code_postale = code_postale;
    }

    public users(int id, int categorie_user_id, int telephone, String nom, String prenom, String email, String password, String adresse, int code_postale) {
        this.id = id;
        this.categorie_user_id = categorie_user_id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.adresse = adresse;
        this.telephone = telephone;
        this.code_postale = code_postale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public int getCode_postale() {
        return code_postale;
    }

    public void setCode_postale(int code_postale) {
        this.code_postale = code_postale;
    }

    public int getCategorie_user_id() {
        return categorie_user_id;
    }

    public void setCategorie_user_id(int categorie_user_id) {
        this.categorie_user_id = categorie_user_id;
    }
    
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password + ", adresse=" + adresse + ", telephone=" + telephone + ", code_postale=" + code_postale + '}';
    }
    

    
    
}
