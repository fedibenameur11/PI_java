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
public class Categorie_prod {
    
    private int id;
    private String nom;

    public Categorie_prod() {
    }

    public Categorie_prod(String nom) {
        this.nom = nom;
    }
    public Categorie_prod(int id,String nom) {
        this.id=id;
        this.nom = nom;
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

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", nom=" + nom + '}';
    }
    
    
}
