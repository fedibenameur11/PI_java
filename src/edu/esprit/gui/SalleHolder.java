/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.gui;

import edu.esprit.entities.Salle;

/**
 *
 * @author MSI
 */
public final class SalleHolder {
    private Salle salle;
    private final static SalleHolder INSTANCE =new SalleHolder();

    public SalleHolder() {
    }
    
    public static SalleHolder getInstance(){
     return INSTANCE;   
    }
    
    
    
    public void setSalle (Salle s){
        this.salle =s;
    }
    
        public Salle getSalle (){
        return  this.salle;
    }
}