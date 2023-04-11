/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dynamics.pidev.entites;


public class Salle {
    
    private int id;
    private String nom_salle;
    private String adresse_salle;
    private int num_telephone;
    private int codepostal;
    private String ville;
    private int prix_abonnement;

    public Salle() {
    }

    public Salle(int id, String nom_salle, String adresse_salle, int num_telephone, int codepostal, String ville, int prix_abonnement) {
        this.id = id;
        this.nom_salle = nom_salle;
        this.adresse_salle = adresse_salle;
        this.num_telephone = num_telephone;
        this.codepostal = codepostal;
        this.ville = ville;
        this.prix_abonnement = prix_abonnement;
    }

   

    public Salle(String nom, String adresse, int num, int code, String ville, int prix) {
        this.nom_salle = nom;
        this.adresse_salle = adresse;
        this.num_telephone = num;
        this.codepostal = code;
        this.ville = ville;
        this.prix_abonnement = prix;
    }
    
    public Salle(String nom) {
        this.nom_salle =nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_salle() {
        return nom_salle;
    }

    public void setNom_salle(String nom_salle) {
        this.nom_salle = nom_salle;
    }

    public String getAdresse_salle() {
        return adresse_salle;
    }

    public void setAdresse_salle(String adresse_salle) {
        this.adresse_salle = adresse_salle;
    }

    public int getNum_telephone() {
        return num_telephone;
    }

    public void setNum_telephone(int num_telephone) {
        this.num_telephone = num_telephone;
    }

    public int getCodepostal() {
        return codepostal;
    }

    public void setCodepostal(int codepostal) {
        this.codepostal = codepostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getPrix_abonnement() {
        return prix_abonnement;
    }

    public void setPrix_abonnement(int prix_abonnement) {
        this.prix_abonnement = prix_abonnement;
    }

    @Override
    public String toString() {
        return "Salle{" + "id=" + id + ", nom_salle=" + nom_salle + ", adresse_salle=" + adresse_salle + ", num_telephone=" + num_telephone + ", codepostal=" + codepostal + ", ville=" + ville + ", prix_abonnement=" + prix_abonnement + '}';
    }
    
    
}
