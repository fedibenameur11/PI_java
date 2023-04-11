/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dynamics.pidev.entites;

import java.util.Date;

/**
 *
 * @author trabe
 */
public class Reponse {
    private int id;
    private String rep;
    private String created_at;

    public Reponse() {
    }

    public Reponse(int id, String contenurep, String created_at) {
        this.id = id;
        this.rep = contenurep;
        this.created_at = created_at;
    }

    public Reponse(String rep) {
        this.rep = rep;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenurep() {
        return rep;
    }

    public void setContenurep(String contenurep) {
        this.rep = contenurep;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", contenurep=" + rep + ", created_at=" + created_at + '}';
    }
    
    
}
