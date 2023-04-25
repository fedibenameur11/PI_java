/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.workshop.gui;

import com.esprit.workshop.entites.User;

/**
 *
 * @author klair
 */
public class MyData2 {

    public int getId() {
        return id;
    }

    public int getDuree() {
        return duree;
    }

    public User getCoach() {
        return coach;
    }

    public User getClient() {
        return client;
    }
    int id,duree ; 
    User coach,client;

    public MyData2(int id, User coach, User client, int duree) {
        this.id = id;
        this.duree = duree;
        this.coach = coach;
        this.client = client;
    }
    
    
}
