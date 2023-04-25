/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dynamics.pidev.gui;

import com.dynamics.pidev.entites.abonnementSalle;

/**
 *
 * @author MSI
 */
public final class AbonnementHolder {
    private abonnementSalle abonnement;
    private final static AbonnementHolder INSTANCE =new AbonnementHolder();

    public AbonnementHolder() {
    }
    
    public static AbonnementHolder getInstance(){
     return INSTANCE;   
    }
    
    
    
    public void setAbonnement (abonnementSalle a){
        this.abonnement =a;
    }
    
        public abonnementSalle getAbonnement (){
        return  this.abonnement;
    }
}

