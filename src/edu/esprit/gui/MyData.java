/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

class MyData {

    private String nom;
    private String prenom ;
    private String email;
    private String telephone ;
    
    private int id;

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public int getId() {
        return id;
    }

   
    public MyData(int id, String nom, String prenom,String email, String telephone) {
        this.id = id;
        this.nom = nom ; 
        this.prenom = prenom ; 
        this.email = email ; 
        this.telephone = telephone  ; 
    }

   
}
