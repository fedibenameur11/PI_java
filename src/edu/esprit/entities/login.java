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
public class login {

    // Create a private static instance of Login class
    private static login instance;

    // Declare instance variables for username and password
    private String username;
    private String password;
    private String nom;
     private String adresse;
    private int id;
    private int telephone;
    private String prenom;
    private int code_postale;
    private int role;
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }



    // Create a private constructor to prevent external instantiation
    private login() {
    }

    // Create a public static method to get the single instance of Login class
    public static login getInstance() {
        if (instance == null) {
            instance = new login();
        }
        return instance;
    }

    // Setters and getters for username and password
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    // Method to validate login credentials
    public boolean isValidLogin() {
        // Add your login validation code here
        return false;
    }
}