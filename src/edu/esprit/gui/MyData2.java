/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.users;

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

    public users getCoach() {
        return coach;
    }

    public users getClient() {
        return client;
    }
    int id,duree ; 
    users coach,client;

    public MyData2(int id, users coach, users client, int duree) {
        this.id = id;
        this.duree = duree;
        this.coach = coach;
        this.client = client;
    }
    
    
}
